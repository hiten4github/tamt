# Introduction #

The assignment algorithm queries each point for the nearest roads given a distance tolerance. It is possible to get more than one road that falls within the distance tolerance, thus, it is import to also determine the _nearest_ of the _near roads_.

## Sample Query ##

In a test dataset and study region, we happen to know there are 4 points that have multiple near roads within the 20m tolerance, as shown by this query:

```
SELECT
	p.id,
	r.name,
	ST_Distance(p.geometry, r.geometry) as d
FROM 
	roaddetails r
JOIN
	gpspoints p
ON
	(ST_DWithin(
			ST_GeographyFromText(AsText(p.geometry)), 
			ST_GeographyFromText(AsText(r.geometry)), 
			20.0))
WHERE p.id = 'ae063358-5e3b-47d9-88d5-91e23e85f828'
ORDER BY d DESC
LIMIT 1
```

Results:

```
"ae063358-5e3b-47d9-88d5-91e23e85f828";"Winneba";4.51305748397936e-05
"ae063358-5e3b-47d9-88d5-91e23e85f828";"Dansoman";0.00017779456554095
```

### Which road is nearer to the point in question? ###

  * Winneba is 4.51305748397936e-05 or 0.000045 meters away (notice the exponential notation)
  * Dansoman is 0.0001775 meters away

We conclude that Winneba is closest, which works for this data set. But another dataset could generate a set ordered incorrectly. Order can be assured by an additional ORDER BY and LIMIT clause on the query:

```
SELECT
	p.id,
	r.name,
	ST_Distance(p.geometry, r.geometry) as d
FROM 
	roaddetails r
JOIN
	gpspoints p
ON
	(ST_DWithin(
			ST_GeographyFromText(AsText(p.geometry)), 
			ST_GeographyFromText(AsText(r.geometry)), 
			20.0))
WHERE p.id = 'ae063358-5e3b-47d9-88d5-91e23e85f828'
ORDER BY d ASC
LIMIT 1
```

Result:
```
"ae063358-5e3b-47d9-88d5-91e23e85f828";"Winneba B";4.10008626986984e-05
```

However, we have not determined the bearing of the roads compared to the bearing of the point:

### Including bearing ###

In the sample study region, Winneba Rd and Dansoman Rd are at near right-angles to each other -- they intersect. Our GPS point above seems to be nearest to Dansoman, but we have not taken into account the bearings of two roads as compares to the bearing of the point.

We can include bearing in the spatial query like this:

```
SELECT
	p.id,
	p.bearing,
	r.name,
	ST_Distance(p.geometry, r.geometry) as d,
	TAMT_compareBearing(p.geometry, p.bearing, r.geometry, 45.0) as similarbearing
FROM 
	roaddetails r
JOIN
	gpspoints p
ON
	(ST_DWithin(
			ST_GeographyFromText(AsText(p.geometry)), 
			ST_GeographyFromText(AsText(r.geometry)), 
			20.0))
WHERE p.id = '08cf481f-745b-4ad5-bf37-dabf905d8c75'
```

The **TAMT\_compareBearing** function dissects the road into composite lines, finds the nearest line to our point in question, determines the bearing of the line, and compares it within a range (here 45.0 degrees) to the bearing of the point (which is captured by the GPS unit and recorded as p.bearing). The result of the function is a boolean value to help us determine if the closest road is similar enough in bearing to be considered.

The results are as follows:

```
"08cf481f-745b-4ad5-bf37-dabf905d8c75";112.54;"Winneba";0.000176996279962906;t
"08cf481f-745b-4ad5-bf37-dabf905d8c75";112.54;"Dansoman";0.000162299115323239;f
```

In this case we have verified that Winneba is the **_nearest_ of the _near_ roads with a _similar enough_ bearing**

Let's add an ORDER BY DESC and LIMIT clause to ask for only the nearest of the near roads with that has a similar enough bearing:

```
SELECT
	p.id,
	p.bearing,
	r.name,
	ST_Distance(p.geometry, r.geometry) as d,
	TAMT_compareBearing(p.geometry, p.bearing, r.geometry, 45.0) as similarbearing
FROM 
	roaddetails r
JOIN
	gpspoints p
ON
	(ST_DWithin(
			ST_GeographyFromText(AsText(p.geometry)), 
			ST_GeographyFromText(AsText(r.geometry)), 
			20.0))
WHERE p.id = '08cf481f-745b-4ad5-bf37-dabf905d8c75'
ORDER BY similarbearing DESC
LIMIT 1
```

### Digitized double-carriage roadways ###

What would happen to our query if a user had digitized _both sides_ of Winneba Rd? In this case, we might end up with two "roads" (Winneba A and Winneba B) that fell within the 20m distance tolerance, with both having similar bearings to our point.

To demonstrate, I digitized another portion of Winneba, and renamed the components to Winneba A and Winneba B and issued the following query:

```
SELECT
	p.id,
	p.bearing,
	r.name,
	ST_Distance(p.geometry, r.geometry) as d,
	TAMT_compareBearing(p.geometry, p.bearing, r.geometry, 45.0) as similarbearing
FROM 
	roaddetails r
JOIN
	gpspoints p
ON
	(ST_DWithin(
			ST_GeographyFromText(AsText(p.geometry)), 
			ST_GeographyFromText(AsText(r.geometry)), 
			20.0))
WHERE p.id = 'ae063358-5e3b-47d9-88d5-91e23e85f828'
```

Results:

```
"ae063358-5e3b-47d9-88d5-91e23e85f828";258.67;"Dansoman";0.00017779456554095;f
"ae063358-5e3b-47d9-88d5-91e23e85f828";258.67;"Winneba A";0.000142380579261884;t
"ae063358-5e3b-47d9-88d5-91e23e85f828";258.67;"Winneba B";4.10008626986984e-05;t
```

Notice that Winneba A and B both have similar bearings. If we limited the query with the same ORDER BY and LIMIT clauses, we would find:

```
"ae063358-5e3b-47d9-88d5-91e23e85f828";258.67;"Winneba A";0.000142380579261884;t
```

However, we cannot forget distance in this case. Take a close look at the Winneba road distances. We need to be careful with exponential notation here again. The similar bearing roads are:

  * Winneba A, 0.00014 meters away from our point
  * Winneba B, 0.00004 meters away from our point

Now we know that Winneba B is closer to our point that Winneba A, even though both of them have similar bearings.

### Query for bearing and distance ###

Combining the two parameters of bearing and distance in our spatial query, we get:

**Without ORDER BY and LIMIT**

```
SELECT
	p.id,
	p.bearing,
	r.name,
	ST_Distance(p.geometry, r.geometry) as d,
	TAMT_compareBearing(p.geometry, p.bearing, r.geometry, 45.0) as similarbearing
FROM 
	roaddetails r
JOIN
	gpspoints p
ON
	(ST_DWithin(
			ST_GeographyFromText(AsText(p.geometry)), 
			ST_GeographyFromText(AsText(r.geometry)), 
			20.0))
WHERE p.id = 'ae063358-5e3b-47d9-88d5-91e23e85f828'
```

Result:

```
"ae063358-5e3b-47d9-88d5-91e23e85f828";258.67;"Dansoman";0.00017779456554095;f
"ae063358-5e3b-47d9-88d5-91e23e85f828";258.67;"Winneba A";0.000142380579261884;t
"ae063358-5e3b-47d9-88d5-91e23e85f828";258.67;"Winneba B";4.10008626986984e-05;t
```


**With incorrect ORDER BY #1**

```
...
ORDER BY d DESC, similarbearing DESC
```

```
"ae063358-5e3b-47d9-88d5-91e23e85f828";258.67;"Dansoman";0.00017779456554095;f
"ae063358-5e3b-47d9-88d5-91e23e85f828";258.67;"Winneba A";0.000142380579261884;t
"ae063358-5e3b-47d9-88d5-91e23e85f828";258.67;"Winneba B";4.10008626986984e-05;t
```

**With incorrect ORDER BY #2**

```
ORDER BY similarbearing DESC, d DESC
```

```
"ae063358-5e3b-47d9-88d5-91e23e85f828";258.67;"Winneba A";0.000142380579261884;t
"ae063358-5e3b-47d9-88d5-91e23e85f828";258.67;"Winneba B";4.10008626986984e-05;t
"ae063358-5e3b-47d9-88d5-91e23e85f828";258.67;"Dansoman";0.00017779456554095;f
```

**With CORRECT ORDER BY and LIMIT clauses**

```
SELECT
	p.id,
	p.bearing,
	r.name,
	ST_Distance(p.geometry, r.geometry) as d,
	TAMT_compareBearing(p.geometry, p.bearing, r.geometry, 45.0) as similarbearing
FROM 
	roaddetails r
JOIN
	gpspoints p
ON
	(ST_DWithin(
			ST_GeographyFromText(AsText(p.geometry)), 
			ST_GeographyFromText(AsText(r.geometry)), 
			20.0))
WHERE p.id = 'ae063358-5e3b-47d9-88d5-91e23e85f828'
ORDER BY similarbearing DESC, d ASC LIMIT 1
```

Result:

```
"ae063358-5e3b-47d9-88d5-91e23e85f828";258.67;"Winneba B";4.10008626986984e-05;t
```

Below is the screenshot of the study region and roads in question. Our point is at the confluence of Dansoman and Winneba, but is on the Winneba B road (the road selected on the map with information on the editing panel at left).

![http://tamt.googlecode.com/files/double-carriage-way-nearest-of-near-roads-with-similar-bearing.png](http://tamt.googlecode.com/files/double-carriage-way-nearest-of-near-roads-with-similar-bearing.png)