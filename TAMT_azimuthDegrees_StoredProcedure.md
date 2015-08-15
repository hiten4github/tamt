## Function ##

Requires PostGIS 1.5

```
CREATE OR REPLACE FUNCTION TAMT_azimuthDegrees(line geometry)
  RETURNS float AS
$$
DECLARE
	degAz float;
BEGIN
   degAz := ST_Azimuth(ST_StartPoint(line), ST_EndPoint(line))/(2*pi())*360;
   RETURN degAz;
END;
$$
LANGUAGE 'plpgsql' VOLATILE;
COMMENT ON FUNCTION azimuthDegrees(geometry) IS 'Calculates the bearing (in degrees) of a 2D linestring representing a road in WGS84'
```

## Example ##

Query:

```
SELECT *, TAMT_azimuthDegrees(geometry) as bearing from roaddetails
```

Output:

```
"Winneba Road";99.8694727007072
"Ring Road East";105.14339517631
"Ring Road West";189.68033448825
"Dansoman Road";215.585395742348
```

## TODO ##

Create a stored procedure to fetch TAMT GPS points near a road with bearing similar to road:

  * Query for points with x meters of a road
  * Pass road and points into a stored procedure
  * Break road into segments
  * For each segment:
    * Determine bearing of the segment
    * Determine acceptable bearing range of point (e.g. +- 20 degrees of segment bearing, forwards and backwards)
    * Query for the subset of original points (which have bearing as an attribute) that fall within acceptable bearing range
    * Collect acceptable points
  * Return collection of points with x meters of a road with acceptable bearing

Consider this thread: http://groups.google.ca/group/wb-tamt/browse_thread/thread/05deb104f59bdacd/fef4d984f5abde34?hl=en

And in particular this post: http://groups.google.ca/group/wb-tamt/msg/fef4d984f5abde34?hl=en