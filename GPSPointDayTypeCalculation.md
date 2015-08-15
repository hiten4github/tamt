## Introduction ##

For speed bins, we need the dayType of each GPS point.

## Calc the dayType ##

Every GPS point has a created date. Based on this date, we extract the day of the week (using the `dow` function in postgres). Howevery, this only gives us the numeric day (0...6) from Sunday to Saturday. This has to be converted to the TAMT dayType, which is: WEEKDAY, SATURDAY, SUNDAY\_HOLIDAY.

### TAMT\_calculateGPSPointDayOfWeek ###

Here is the stored procedure to calculate the dayType based on numeric day of week:

```
CREATE OR REPLACE FUNCTION tamt_calculategpspointdayofweek(_dow text)
  RETURNS text AS
$BODY$
DECLARE
   daytype text;
BEGIN

	IF( _dow = '0' ) THEN
		daytype := 'SUNDAY_HOLIDAY';
	END IF;

	IF( _dow = '1' ) THEN
		daytype := 'WEEKDAY';
	END IF;

	IF( _dow = '2' ) THEN
		daytype := 'WEEKDAY';
	END IF;

	IF( _dow = '3' ) THEN
		daytype := 'WEEKDAY';
	END IF;

	IF( _dow = '4' ) THEN
		daytype := 'WEEKDAY';
	END IF;

	IF( _dow = '5' ) THEN
		daytype := 'WEEKDAY';
	END IF;

	IF( _dow = '6' ) THEN
		daytype := 'SATURDAY';
	END IF;
	
	RETURN daytype;
END;
$BODY$
  LANGUAGE 'plpgsql' VOLATILE
  COST 100;
ALTER FUNCTION TAMT_calculateGPSPointDayOfWeek(text) OWNER TO postgres;
```

There is probably a nice way to handle the IFs from 1...5, but postgres is a little obtuse that way.

### Calling the function ###

On import, after all the data for a GPS trace has been copied into the database, we issue this call:

```
UPDATE 
	gpspoints
SET 
	daytype = (select * from TAMT_calculateGPSPointDayOfWeek( extract(dow from created)::text))
WHERE
	gpstraceid = '<insert gpstraceid here>;
```

## Issues ##

It takes too long, almost 1 second per point:

```
Query returned successfully: 174372 rows affected, 102239 ms execution time.
```