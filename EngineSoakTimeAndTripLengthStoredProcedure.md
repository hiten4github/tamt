## Overview ##

Assumption: each GPS log file within a GPS trace (.ZIP) file is unique to a certain vehicle. That is, every .DAT file inside the .ZIP GPS data file that is uploaded into TAMT represents a unique vehicle.

The purpose of this stored procedure is to calculate the engine soak time. (Need definition of soak time TBD).

**TODO**: have not yet inserted code for trip length. What you see below is just for engine soak time

## Stored Procedure ##

```
-- Function: tamt_updatefromclosestdistribution(text, text, integer, double precision, double precision)

-- DROP FUNCTION tamt_updatefromclosestdistribution(text, text, integer, double precision, double precision);

CREATE OR REPLACE FUNCTION TAMT_populateEngineSoakTimeAndTripLength(_traceid text)
  RETURNS SETOF record AS
$BODY$
DECLARE
	minSpeedThreshold double precision;
	startSoak timestamp;
	endSoak timestamp;
	diffSoak interval;
	minSoakInterval interval;
	binCandidate interval;
	r RECORD;
	logs RECORD;
	soakBinNumber integer;
	tmp text;
BEGIN

	--RAISE NOTICE 'Start TAMT_populateEngineSoakTimeAndTripLength';

	-- clearout soakbin table
	UPDATE soakbin SET bincount = 0;

	-- TODO: get this from the database
	SELECT INTO minSoakInterval interval '10 seconds'; -- 10 minutes is more realistic
	
	-- Since the GPS does not record many 0s for speed, 
	-- we will use 0.5kph as a minimum threshold for being at rest
	-- 0.5kph = 0.138888889 meters / second
	minSpeedThreshold := 0.138888889;

	-- loop over distinct gpslogs (aka unique vehicles from gps logs inside of gps trace zip files)
	FOR logs IN SELECT 
		DISTINCT gpslogid 
		FROM gpspoints 
		WHERE gpstraceid = _traceid 
		ORDER BY gpslogid
	LOOP
		startSoak := NULL;
		FOR r IN SELECT * FROM gpspoints 
			WHERE gpstraceid = _traceid
			AND gpslogid = logs.gpslogid
			ORDER BY created
		LOOP
			-- this should be entered on the first row, and after a soakbin has been identified
			IF startSoak IS NULL
			THEN
				startSoak = r.created;
			ELSE
				IF r.speed <= minSpeedThreshold
				THEN
					IF endSoak IS NULL
					THEN
						endSoak = r.created;
					ELSE
						-- if startSoak IS NOT NULL, then we already have one
						-- and another speed <= threshold means stopped again
						-- which could mean:
						-- a) GPS has still not registered movement over time
						-- b) GPS has registered a stop after driving for a time
						diffSoak = endSoak - startSoak;
						startSoak := NULL;
						endSoak := NULL;
						-- determine which soak bin this should be in:
						-- 0	0-15 min
						-- 1	16-30 min
						-- 2	31-60 min
						-- 3	61-120 min
						-- 4	121-180 min
						-- 5	181-240 min
						-- 6	241-360 min
						-- 7	361-480 min
						-- 8	481-720 min
						-- 9	over 720 min
						soakBinNumber := NULL;
						binCandidate = diffSoak - minSoakInterval;
						IF binCandidate > INTERVAL '0 seconds'
						THEN
							IF binCandidate > INTERVAL '720 minutes'
							THEN
								soakBinNumber = 9;
							ELSIF binCandidate > INTERVAL '480 minutes'
							THEN
								soakBinNumber = 8;
							ELSIF binCandidate > INTERVAL '360 minutes'
							THEN
								soakBinNumber = 7;
							ELSIF binCandidate > INTERVAL '240 minutes'
							THEN
								soakBinNumber = 6;
							ELSIF binCandidate > INTERVAL '180 minutes'
							THEN
								soakBinNumber = 5;
							ELSIF binCandidate > INTERVAL '120 minutes'
							THEN
								soakBinNumber = 4;
							ELSIF binCandidate > INTERVAL '60 minutes'
							THEN
								soakBinNumber = 3;
							ELSIF binCandidate > INTERVAL '30 minutes'
							THEN
								soakBinNumber = 2;
							ELSIF binCandidate > INTERVAL '15 minutes'
							THEN
								soakBinNumber = 1;
							ELSE
								soakBinNumber = 0;
							END IF;

							-- increment the soak bin number
							UPDATE soakbin SET bincount = bincount + 1
								WHERE binNumber = soakBinNumber;					
						ELSE 
							-- ignore binCandidate if it is less than 0	
						END IF;
										
					END IF;

				ELSE
					-- if speed > minSpeed, then update endSoak
					endSoak = r.created;
				END IF;
			END IF;
		END LOOP;
	END LOOP;
	--RAISE NOTICE 'End TAMT_populateEngineSoakTimeAndTripLength';
END;
$BODY$
  LANGUAGE 'plpgsql' VOLATILE
  COST 100
  ROWS 1000;
ALTER FUNCTION TAMT_populateEngineSoakTimeAndTripLength(text) OWNER TO gis;

```

## Sample Output ##
```
0;946
1;3
2;0
3;0
4;0
5;0
6;0
7;0
8;0
9;0
```

### Interpretation ###
For the Accra "large.zip" data there are 946 trips that are 0-15 minutes long, and 3 trips that are 15-30 min long.