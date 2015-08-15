## Traffic Count Report ##

```
-- Function: trafficCountReport(tag text, daytype text)

DROP FUNCTION trafficCountReport(_tag text, _region text, _daytype text);

CREATE OR REPLACE FUNCTION trafficCountReport(_tag text, _region text, _daytype text)
  RETURNS SETOF RECORD AS
$BODY$
DECLARE
	hours integer;
	start_hour integer;
	end_hour integer;
	my_record RECORD;
BEGIN
	 hours := 0;
	 start_hour := 0;
	 end_hour := 0;
	 -- for each hour of the day, in 24hr time
	 WHILE ( hours < 24 ) LOOP
		-- set up (or reset) the time ranges
		start_hour := hours;
		end_hour := hours + 1;
		-- add up all the traffic counts
		SELECT INTO my_record
			hours as hour,
			SUM(W2) as w2, 
			SUM(W3) as w3,
			SUM(PC) as pc,
			SUM(TX) as tx,
			SUM(LDV) as ldv,
			SUM(LDC) as ldc,
			SUM(HDC) as hdc,
			SUM(MDB) as mdb,
			SUM(HDB) as hdb

			FROM trafficcount
			-- that match the incoming tag
			WHERE tag = _tag
			-- and match the incoming region
			AND region = _region
			-- and match the incoming day type
			AND daytype = _daytype
			-- and only those with starttimes between the time ranges
			-- e.g. between 9 and 10 
			-- (date_part extracts the hour as an integer from starttime)
			AND date_part('hour', starttime) BETWEEN start_hour AND end_hour;
			
		RETURN NEXT my_record;
		hours := hours + 1;
		
	 END LOOP;
	 RETURN;
END;
$BODY$
  LANGUAGE 'plpgsql';
ALTER FUNCTION trafficCountReport(_tag text, _region text, _daytype text) OWNER TO postgres;
```

**Execute like this:**

```
select * from trafficCountReport('HWY001', 'default', 'WEEKDAY')
	AS foo(hour int, w2 bigint, w3 bigint, pc bigint, tx bigint, 
	ldv bigint, ldc bigint, hdc bigint, mdb bigint, hdb bigint)
```

**Results:**

```
0;;;;;;;;;
1;;;;;;;;;
2;;;;;;;;;
3;;;;;;;;;
4;;;;;;;;;
5;;;;;;;;;
6;;;;;;;;;
7;1;2;3;4;5;6;7;8;9
8;3;6;9;12;15;18;21;24;27
9;2;4;6;8;10;12;14;16;18
10;;;;;;;;;
11;;;;;;;;;
12;;;;;;;;;
13;;;;;;;;;
14;;;;;;;;;
15;;;;;;;;;
16;;;;;;;;;
17;;;;;;;;;
18;;;;;;;;;
19;;;;;;;;;
20;;;;;;;;;
21;;;;;;;;;
22;;;;;;;;;
23;;;;;;;;;
```

The rows represent the hour of the day from 0 to 23 (as in 00:00 hrs to 23:00 hrs).
The columns are in the following order:

  * hour (of the day, in 24hr time)
  * w2
  * w3
  * pc
  * tx
  * ldv
  * ldc
  * hdc
  * mdb
  * hdb