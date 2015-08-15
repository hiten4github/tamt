## Database upgrade ##

Upgraded to PostGIS 1.5 so we could use the geography type (see SQL below for ST\_Geography in the ST\_DWithin clause). The upgrade caused problems with command line drivers for GDAL utilities (like ogr2ogr) in order to export data for viewing in QGIS, but I switched over to using pgsql2shp instead (and got more control over queries).

## Exporting spatial queries for rapid viewing ##

SQL for points near roads:

SELECT points.id, points.gpstraceid, AsText(points.geometry) FROM gpspoints as points, roaddetails as roads WHERE roads.name = 'Dansoman Road' AND ST\_DWithin(ST\_GeographyFromText(AsText(points.geometry)), ST\_GeographyFromText(AsText(roads.geometry)), 10.0)

From the command line to export as SHP

```
tamt@tamt-vbox:~/Desktop/output$ pgsql2shp -f near10m-post-road-edit -h localhost -u gis -P gis tamt15 \
"SELECT points.id, points.gpstraceid, points.geometry FROM gpspoints as points, roaddetails as roads WHERE \ 
roads.name = 'Dansoman Road' AND ST_DWithin(ST_GeographyFromText(AsText(points.geometry)), \ 
ST_GeographyFromText(AsText(roads.geometry)), 10.0)"
Preparing table for user query... Done.
Initializing... Done (postgis major version: 1).
Output shape: Point
Dumping: XXXXXXXXXXXXXXXXX [1600 rows].
tamt@tamt-vbox:~/Desktop/output$ pwd
/home/tamt/Desktop/output
```

Then open the SHP file in QGIS to see the results!

## Data validation ##

**The user has to decide the trade-off between nearness threshold (ie, 10m? 50m?) and accuracy of the road network in the Tag application**

For instance, I exported the comparisons (near10m and near10m-post-road-edit) as PNG files in Desktop/output. TODO: will put them on the wiki for comparison. It demonstrates that 10m is a good threshold IF the user has accurately digitized the road in TAG.

A larger threshold (like 50m) captured points to aggressively at the junction of roads in the network.

## Stored procedure design ##

ALIGN TODO: create a stored procedure that loops over all roads, fetches the associated tag name, assigns the tag name to the road (need
a new column in the gpspoints table). Will also need to do the same for points within zones (that aren't already assigned to roads).
And will likely need a default zone type if there are no zones.