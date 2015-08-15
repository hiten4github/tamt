

&lt;hr/&gt;


**Topics on this page**



&lt;hr/&gt;



## Introduction ##

The Assign module attempts to obtain the nearest road (given a certain distance and bearing) to every point so that the points may inherit the tag associated with the road for further analysis in the GHG model.

## Stored Procedures ##

Using PL/PgSQL (navitve procedural language for postgresql), we have created stored procedures to obtain the road tags for the points.

### TAMT\_assignPoints() ###

```
/*
 * TAMT_assignPoints
 *
 * @param gpstraceid (the id of the gps trace whose points we want to process)
 * @param tolerance (distance in meters)
 * @return number of assigned points
 *
 * @author stuart.moffatt@emcode.org
 * @date 17 July 2010
 * @version 0.1
 * 
 * This stored procedure performs "map matching" between GPS points
 * and user-defined roads as part of the TAMT Assign application module.
 *
 */
CREATE OR REPLACE FUNCTION TAMT_assignPoints(
	id text, 
	distance_tolerance double precision, 
	bearing_tolerance double precision)
  RETURNS int AS
$BODY$
DECLARE
    line geometry;
    p gpspoints%rowtype;
    rds roaddetails%rowtype;
    totalAffected int;
    isSimilarBearing boolean;
BEGIN

    totalAffected := 0;
    
    -- Get the points for the given id
    FOR p IN SELECT * FROM gpspoints WHERE gpstraceid = id ORDER BY created
    LOOP
	-- Get nearest line(s) to this point. We fetch multiple lines because we may 
	-- be at an intersection. We won't know that the point should be attributed
	-- the road's tag until we find out if the point has a similar bearing
	-- to its closest road segment.
	FOR rds IN SELECT *
		FROM roaddetails 
		WHERE ST_DWithin(
			ST_GeographyFromText(AsText(p.geometry)), 
			ST_GeographyFromText(AsText(roaddetails.geometry)), 
			distance_tolerance)
	LOOP
		IF (rds.geometry IS NOT NULL) THEN
			/*
			 * Now, compare the bearings of the point with the line
			 * The 3rd parameter is the acceptable range of degrees.
			 */
			line := rds.geometry;
			isSimilarBearing := TAMT_compareBearing(
						p.geometry, 
						p.bearing, 
						rds.geometry, 
						bearing_tolerance);
			IF( isSimilarBearing ) THEN
				UPDATE gpspoints SET tag_id = rds.tag_id WHERE pid = p.pid;
				totalAffected := totalAffected + 1;
			END IF;
		END IF;
	END LOOP;
    END LOOP;
    RETURN totalAffected;
END;
$BODY$
LANGUAGE 'plpgsql';
```

### TAMT\_compareBearing() ###

```
/*
 * TAMT_compareBearing(point, line, acceptableRange)
 *
 * @param point	- A GPS point geometry
 * @param pointBearing - The bearing of the given point
 * @param line  - A Road geometry 	
 * @param acceptableRange - The +/- interval of an acceptable range
 * @return boolean
 *
 * @author stuart.moffatt@emcode.org
 * @date 17 July 2010
 * @version 0.1
 * 
 * This stored procedure determines if a point and a line are within an acceptable
 * range of the same bearing. It should be used in conjuction with the TAMT_assign()
 * stored procedure.
 *
 */
CREATE OR REPLACE FUNCTION TAMT_compareBearing(
		point geometry, 
		pointBearing double precision, 
		line geometry, 
		acceptableRange double precision)
  RETURNS boolean AS
$BODY$
DECLARE
    currentSegment geometry;
    closestSegment geometry;
    nextPointIndex int;
    currDist float;
    lastDist float;
    bearingClosestSegment float;
    bearingClosestSegmentReversed float;
    isSimilarBearing boolean;
BEGIN

	/*
	 * Because a line may be made up of segments that "curve", the bearing of the line
	 * is not necessarily the bearing of the start point to the end point. First we 
	 * must determine which segment of the line is closest to our original point.
	 */
	FOR segment IN 1..ST_NumPoints(line)-1
	LOOP
		nextPointIndex := segment + 1;
		currentSegment := ST_MakeLine( ST_PointN(line,segment), ST_PointN(line,nextPointIndex));
		currDist := ST_Distance(point,currentSegment);
		IF (lastDist IS NULL) THEN
			lastDist = currDist;
		END IF;
		IF (currDist < lastDist) THEN
			closestSegment = currentSegment;
		END IF;			
	END LOOP;

	/*
	 * Now we can determine if the bearing of our original point is within
	 * the acceptable range of the bearing of the closest line segment.
	 */
	bearingClosestSegment := ST_Azimuth(ST_StartPoint(closestSegment), ST_EndPoint(closestSegment))/(2*pi())*360;
	bearingClosestSegmentReversed = bearingClosestSegment + 180.0;
	IF (pointBearing BETWEEN (bearingClosestSegment - acceptableRange) AND (bearingClosestSegment + acceptableRange) ) THEN
		isSimilarBearing := true;
	END IF;

	/* 
	 * We also need to take into account that the bearing for the line 
	 * (ie, the road in the TAMT Tag UI) may be reversed (ie, 180 degrees, 
	 * or, going the other way) depending on the direction it was drawn by the user.
	 */
	bearingClosestSegmentReversed = bearingClosestSegment + 180.0;
	IF (pointBearing BETWEEN (bearingClosestSegmentReversed - acceptableRange) AND (bearingClosestSegmentReversed + acceptableRange) ) THEN
		isSimilarBearing := true;
	END IF;	
	
    RETURN isSimilarBearing;
END;
$BODY$
LANGUAGE 'plpgsql';
```

## Calling the stored procedures ##

In SQL, we use the GPS Trace ID (which is obtained programmatically) along with the distance tolerance (e.g. 10 meters) and the bearing tolerance (e.g. 30 degrees):

```
select * from TAMT_assignPoints('349ef8d2-8ca9-4870-9085-aa851bcfbe44',10.0,30.0)
```

The `TAMT_assignPoints()` function makes use of the `TAMT_compareBearing()` function.

## Sample runs ##

### Test: 10m, 30 degree tolerances ###
```
select * from TAMT_assignPoints('349ef8d2-8ca9-4870-9085-aa851bcfbe44',10.0,30.0)
```
  * Processing: 168479 ms to scan 159707 points and update 13059 within 10m/30d range
  * Percentage of GPS points with Tag ID: 8.17%  (13059/159707)
  * Hours of tagged driving time: 3.63

### Test: 10m, 45 degree tolerances ###
```
select * from TAMT_assignPoints('349ef8d2-8ca9-4870-9085-aa851bcfbe44',10.0,45.0)
```
  * Processing: 158756 ms to scan 159707 points and update 14545 within 10m/45d range
  * Percentage of GPS points with Tag ID: 9.1%  (14545/159707)
  * Hours of tagged driving time: 4.04

### Test: 15m, 30 degree tolerances ###
```
select * from TAMT_assignPoints('349ef8d2-8ca9-4870-9085-aa851bcfbe44',15.0,30.0)
```
  * Processing: 156335 ms to scan 159707 points and update 17175 within 15m/30d range
  * Percentage of GPS points with Tag ID: 10.75%  (17175/159707)
  * Hours of tagged driving time: 4.77

### Test: 20m, 30 degree tolerances ###
```
select * from TAMT_assignPoints('349ef8d2-8ca9-4870-9085-aa851bcfbe44',20.0,30.0)
```
  * Processing: 170926 ms to scan 159707 points and update 19008 within 20m/30d range
  * Percentage of GPS points with Tag ID: 11.9%  (19008/159707)
  * Hours of tagged driving time: 5.28