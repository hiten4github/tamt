Add geometry column to tamt database for roadDetails table:

```
SELECT AddGeometryColumn ('public','roadDetails','geometry',4326,'LINESTRING',2);
SELECT AddGeometryColumn ('public','roadDetails','centroid',4326,'POINT',2);
```

Now, the table looks like this:

```
-- Table: "roadDetails"

-- DROP TABLE "roadDetails";

CREATE TABLE "roadDetails"
(
  id text NOT NULL,
  "name" text NOT NULL,
  description text,
  region text NOT NULL,
  tag_id text NOT NULL,
  geometry geometry,
  centroid geometry,
  CONSTRAINT "roadDetails_pkey" PRIMARY KEY (id),
  CONSTRAINT "roadDetails_tag_id_fkey" FOREIGN KEY (tag_id)
      REFERENCES "tagDetails" (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT unique_road_name UNIQUE (name),
  CONSTRAINT enforce_dims_centroid CHECK (st_ndims(centroid) = 2),
  CONSTRAINT enforce_dims_geometry CHECK (st_ndims(geometry) = 2),
  CONSTRAINT enforce_geotype_centroid CHECK (geometrytype(centroid) = 'POINT'::text OR centroid IS NULL),
  CONSTRAINT enforce_geotype_geometry CHECK (geometrytype(geometry) = 'LINESTRING'::text OR geometry IS NULL),
  CONSTRAINT enforce_srid_centroid CHECK (st_srid(centroid) = 4326),
  CONSTRAINT enforce_srid_geometry CHECK (st_srid(geometry) = 4326)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE "roadDetails" OWNER TO gis;
```