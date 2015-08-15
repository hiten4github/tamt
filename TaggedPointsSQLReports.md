### Join 4 tables, report on tag and road names for each tagged point ###

```
SELECT
	g.id gid,
	g.name gname,
	g.description gdesc,
	rg.id rid,
	rg.name rname,
	p.id pid,
	AsText(p.geometry) as ptext,
	p.tag_id as tagId,
	t.name,
	r.name
FROM
	gpspoints p,
	gpstraces g,
	studyregion rg,
	tagdetails t,
	roaddetails r
WHERE
	p.gpstraceid = g.id
AND
	g.region = rg.id
AND
	t.id = p.tag_id
AND
	r.id = p.road_id
AND
	rg.name = 'Sample Accra'
AND
	p.tag_id != ''
ORDER BY t.name
```