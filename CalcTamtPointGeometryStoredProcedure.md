This stored procedure is called during Import of a GPS archive (after the COPY) command.

COPY cannot handle the creating PostGIS geometry, so lat/lng go in as double values, then are used to create a proper PostGIS point geometry on the GPS logs that were part of the uploaded archive.

```
-- Function: calculate_gpspoint_geometry(text)

-- DROP FUNCTION calculate_gpspoint_geometry(text);

CREATE OR REPLACE FUNCTION calculate_gpspoint_geometry(gtid text)
  RETURNS integer AS
$BODY$
BEGIN
   UPDATE gpspoints SET geometry = ST_SetSRID(ST_MakePoint(latitude, longitude), 4326) WHERE gpstraceid = gtid;
   return 1;
END;
$BODY$
  LANGUAGE 'plpgsql' VOLATILE
  COST 100;
ALTER FUNCTION calculate_gpspoint_geometry(text) OWNER TO postgres;

```