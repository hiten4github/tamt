## Introduction ##

One way to match GPS points to roads is to query every GPS point for the nearest user-defined road at a given distance. The following query inspected over 300K points from 2 drivers taken during one week of driving in Accra, Ghana.

Query:

```
SELECT
	p.*,
	r.name road,
	r.tag_id,
	t.name tag
FROM
	gpspoints p,
	roaddetails r
JOIN
	tagdetails t
ON
	(r.tag_id = t.id)
WHERE
ST_DWithin(
		ST_GeographyFromText(AsText(p.geometry)), 
		ST_GeographyFromText(AsText(r.geometry)), 
		10.0)
```

For each point we picked up a road along with its tag. Recall that the goal of querying for nearest road is so we can associate every GPS point with a road tag.

## Issues ##

While the spatial query does give a result (ie, a tag for every point within 10 m of a road), there are some problems.

Let's visualize one intersection from the resulting map:


---


![http://tamt.googlecode.com/files/within10m.png](http://tamt.googlecode.com/files/within10m.png)


---


Legend:
  * black lines = user-defined roads
  * blue dots = GPS points NOT WITHIN 10m of roads
  * red dots = GPS points WITHIN 10m of roads

### Notes on how this image was created ###

Exported query from the command line:
```
tamt@tamt-vbox:~/Desktop/output$ pgsql2shp -f pointsGetNearestRoadWith329533Points -h localhost -u gis -P gis tamt15 "SELECT p.*, r.name road, r.tag_id, t.name tag FROM gpspoints p, roaddetails r JOIN tagdetails t ON (r.tag_id = t.id) WHERE ST_DWithin(ST_GeographyFromText(AsText(p.geometry)), ST_GeographyFromText(AsText(r.geometry)), 10.0)"
Preparing table for user query... Done.
Initializing... Done (postgis major version: 1).
Output shape: Point
Dumping: XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX [63275 rows].
```

Opened Quantum GIS from the TAMT Appliance desktop, then added a Vector layer and selected the ~/Desktop/output/pointsGetNearestRoadWith329533Points.shp Shapefile. Zoomed in on the area in question, adjusted the symbols for coloring, and exported as an image.


## What is happening here? Why are there so may blue dots? ##

### Problem 1: Poorly digitized road ###

For one thing, this is a double carriage-way (ie, a divided highway with a median strip in the middle). We can see that by the red traces on either side of the black line, which seem to be enough apart to allow a median.

Notice the row of blue dots just barely above the red dots on the north side of the road. It seems that this is another lane of the main artery, but unfortunately just outside the 10m range of our query.

**Solution**: the user needs to draw a better centerline, or draw two lines (one for each side of the double-carriage way). Or both.

### Problem 2: Inadequate tolerance distance ###

We used 10m as the distance for this query. Sure, we could change the tolerance to 15m, but if you look closely at the proximity of the black line to the red dots just below it, and compare that to the space between the black and red dots above it, you can tell that the black line does not accurately represent the centerline.

**Solution**: we need to have a better default tolerance, or have it user-defined (per road). Or both.

### Problem 3: Account for additional lanes not part of the main artery ###

Perhaps if we had a greater tolerance distance, we would have caught all those blue dots on the east-bound lane (south side). But on closer inspection, we have not taken into account a separate turning or feeder lane / service road.

Here is a static Google Map of the same intersection:

![http://maps.google.com/maps/api/staticmap?center=05.5750261,-000.2524709&zoom=18&size=400x400&sensor=false&maptype=satellite&path=color:0x0000ff|weight:5|5.57445,-0.251271|5.576113,-0.254063&sfm=.png](http://maps.google.com/maps/api/staticmap?center=05.5750261,-000.2524709&zoom=18&size=400x400&sensor=false&maptype=satellite&path=color:0x0000ff|weight:5|5.57445,-0.251271|5.576113,-0.254063&sfm=.png)

The blue line on this satellite image represents our black line drawn by the user in the previous image. Notice how much off the centerline it is.

The other noticeable difference looking at the satellite image are the additional service roads on the north and south.

**Solution**: Always digitize from the satellite map for true roads, and watch out for additional paths like turning / feeder lanes or service roads.