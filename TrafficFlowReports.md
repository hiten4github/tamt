# Introduction #

Traffic flow is counted in the field, but not for every single road. Ideally, enough data _per hour_ is counted for a specific _road tag_ such that we can calculate flows for other roads having the same tag (ie, the same kind of road).

## Data that is counted ##

A user is in the field on a specific road with a known tag classification. The user counts all vehicles types for an arbitrary amount of time. The user may be in the field the whole day, but the flow count should be broken up into chunks with the interval no longer than an hour. For instance, a specific traffic flow count may be 12, 15 or 20 min. It is not necessary that all counts be the same interval, but all counts should be something less than 60 minutes. Multiple-hour counts on the same day on the same road are permitted, but they should be entered as separate counts in TAMT Query.

Once back in the office, the user inputs the counts into TAMT Query.

## Data that is reported ##

There are two vehicle flow reports in TAMT Query: Tag Count and Traffic Flow

### Tag Count ###

The Tag Count report outputs the total number of counts that were recorded for a tag broken down by _hour of day_ and _day type_ (weekday, saturday, and sunday/holiday). The Tag Count helps a user determine if they have few enough gaps in the data to proceed with a traffic flow report.

### Traffic Flow ###

The Traffic Flow report is a little bit more complicated. Since it is impossible for a user to get all possible counts for all possible roads at all times of the day, we use data interpolation to produce a comprehensive traffic flow report.

Traffic flow is measured by summing the average number of vehicles per hour for each tag and reporting on all vehicle types for all hours of the day.

# Database queries #

## Hourly Average Per Recorded Count ##

We select all traffic count records from the field with a specific road tag. Before we use the data, several manipulations are performed on each matching record:

  1. We determine what hour the count was started at
  1. For each vehicle type (w2, w3, etc) we multiply the actual count by the time interval of the record. For instance, if a record began at 09:00 and ended at 09:15, and recorded 6 passenger cars, the `pcavg` for this record would be `6 * 60/15 = 6 * 4 = 24`.

Here is the stored procedure that does the work:

```
CREATE OR REPLACE FUNCTION TAMT_trafficCountHourlyAverage(_tagid text)
  RETURNS SETOF record AS
$BODY$
DECLARE
	my_record RECORD;
BEGIN

	FOR my_record IN SELECT
		id,
		_tagid,
		date_part('hour', (starttime)) as hour,
		w2 * (60.0 / date_part('minute', (endtime - starttime))) as w2avg,
		w3 * (60.0 / date_part('minute', (endtime - starttime))) as w3avg,
		pc * (60.0 / date_part('minute', (endtime - starttime))) as pcavg,
		tx * (60.0 / date_part('minute', (endtime - starttime))) as txavg,
		ldv * (60.0 / date_part('minute', (endtime - starttime))) as ldvavg,
		ldc * (60.0 / date_part('minute', (endtime - starttime))) as ldcavg,
		hdc * (60.0 / date_part('minute', (endtime - starttime))) as hdcavg,
		mdb * (60.0 / date_part('minute', (endtime - starttime))) as mdbavg,
		hdb * (60.0 / date_part('minute', (endtime - starttime))) as hdbavg
	FROM
		trafficcount
	WHERE
		tagid = _tagid
	LOOP
		RETURN NEXT my_record;
	END LOOP;
END;
$BODY$
  LANGUAGE 'plpgsql';
ALTER FUNCTION TAMT_trafficCountHourlyAverage(_tagid text) OWNER TO postgres;
```

And here is a sample result set:

```
"023050cc-3660-470b-9370-2bd10663719b";"62231a17-eb90-48c2-8049-9cd4034998d2";8;4;8;12;16;20;24;28;32;36
"441401da-987f-41b6-92ef-2f00a429b507";"62231a17-eb90-48c2-8049-9cd4034998d2";9;4;8;12;16;20;24;28;32;36
"8b7eced0-fc25-441b-8014-06c40a58889b";"62231a17-eb90-48c2-8049-9cd4034998d2";9;3;6;9;12;15;18;21;24;27
"a8fec050-e642-447e-aa4b-0922ee74562f";"62231a17-eb90-48c2-8049-9cd4034998d2";16;4;8;12;16;20;24;28;32;36
"3265a500-97d5-40f9-9a92-61e13d635369";"62231a17-eb90-48c2-8049-9cd4034998d2";9;6.31578947368421;6.31578947368421;6.31578947368421;6.31578947368421;6.31578947368421;6.31578947368421;6.31578947368421;6.31578947368421;6.31578947368421
"477b8e62-5e17-4afa-b593-381c607be660";"62231a17-eb90-48c2-8049-9cd4034998d2";9;13.3333333333333;13.3333333333333;13.3333333333333;13.3333333333333;13.3333333333333;13.3333333333333;13.3333333333333;13.3333333333333;13.3333333333333
"40405f0c-09d1-4b64-ac51-16b6c6635a26";"62231a17-eb90-48c2-8049-9cd4034998d2";12;1.33333333333333;2.66666666666667;4;5.33333333333333;6.66666666666667;8;9.33333333333333;10.6666666666667;12
"b7498fac-98b3-498a-920b-112b1e6c891c";"62231a17-eb90-48c2-8049-9cd4034998d2";15;2.72727272727273;5.45454545454545;8.18181818181818;10.9090909090909;13.6363636363636;16.3636363636364;19.0909090909091;21.8181818181818;24.5454545454545
"12812d66-5f20-4f10-8f53-8563eebefb51";"62231a17-eb90-48c2-8049-9cd4034998d2";14;8;8;8;8;8;8;8;8;8
```

## Traffic Count Report ##

The previous database work produces a table of data based on _all field-collected traffic flow records_. However, the final traffic count report must be a **matrix of average counts per hour of day by vehicle type**. The average counts per hour must be an average of the hourly averages above based on all records matching the hour of day.

The following stored procedure helps us achieve this second order of averages:

```
CREATE OR REPLACE FUNCTION TAMT_trafficCountReport(_tagid text)
  RETURNS SETOF RECORD AS
$BODY$
DECLARE
	my_record RECORD;
	sql text;
	num_rows integer;
	tmp_data RECORD;
	my_hour integer;
	tmp text;
BEGIN

	-- call the TAMT_trafficCountHourlyAverage stored procedure as a result table
	CREATE TABLE tmp_hrly AS 
	SELECT * FROM TAMT_trafficCountHourlyAverage(_tagid) AS 
	foo(
		id text,
		tagid text, 
		hour_bucket float,
		w2_hrly float, 
		w3_hrly float, 
		pc_hrly float, 
		tx_hrly float, 
		ldv_hrly float, 
		ldc_hrly float, 
		hdc_hrly float, 
		mdb_hrly float, 
		hdb_hrly float
	);
	
	my_hour := 0;
	WHILE ( my_hour < 24 ) LOOP

		num_rows := 0;
		SELECT INTO num_rows count(*) 
			FROM tmp_hrly WHERE hour_bucket = my_hour;
	
		SELECT INTO my_record
			my_hour as hour_slice,
			round((SUM(w2_hrly)/num_rows)::numeric, 2) as w2total,
			round((SUM(w3_hrly)/num_rows)::numeric, 2) as w3total,
			round((SUM(pc_hrly)/num_rows)::numeric, 2) as pctotal,
			round((SUM(tx_hrly)/num_rows)::numeric, 2) as txtotal,
			round((SUM(ldv_hrly)/num_rows)::numeric, 2) as ldvtotal,
			round((SUM(ldc_hrly)/num_rows)::numeric, 2) as ldctotal,
			round((SUM(hdc_hrly)/num_rows)::numeric, 2) as hdctotal,
			round((SUM(mdb_hrly)/num_rows)::numeric, 2) as mdbtotal,
			round((SUM(hdb_hrly)/num_rows)::numeric, 2) as hdbtotal
		FROM 
			tmp_hrly
		WHERE 
			tagid = _tagid
		AND 
			hour_bucket = my_hour;

		my_hour := my_hour + 1;

		RETURN NEXT my_record;
		
	END LOOP;

	DROP TABLE tmp_hrly;
	
	RETURN;
END;
$BODY$
  LANGUAGE 'plpgsql';
ALTER FUNCTION TAMT_trafficCountReport(_tagid text) OWNER TO postgres;
```

Here is a sample output:

```
0;;;;;;;;;
1;;;;;;;;;
2;;;;;;;;;
3;;;;;;;;;
4;;;;;;;;;
5;;;;;;;;;
6;;;;;;;;;
7;;;;;;;;;
8;4.00;8.00;12.00;16.00;20.00;24.00;28.00;32.00;36.00
9;6.66;8.41;10.16;11.91;13.66;15.41;17.16;18.91;20.66
10;;;;;;;;;
11;;;;;;;;;
12;1.33;2.67;4.00;5.33;6.67;8.00;9.33;10.67;12.00
13;;;;;;;;;
14;8.00;8.00;8.00;8.00;8.00;8.00;8.00;8.00;8.00
15;2.73;5.45;8.18;10.91;13.64;16.36;19.09;21.82;24.55
16;4.00;8.00;12.00;16.00;20.00;24.00;28.00;32.00;36.00
17;;;;;;;;;
18;;;;;;;;;
19;;;;;;;;;
20;;;;;;;;;
21;;;;;;;;;
22;;;;;;;;;
23;;;;;;;;;
```

Note that there were no record counts for this tag from 00:00am through to 7:59am and from 5:00pm through to 11:59pm. There are also no counts for 10am, 11am, and 1pm.

What do we do with all of the rows that have blanks?

### Before and After Blanks ###

From the previous result set, we see blanks between 00:00 and 07:59. There are also blanks at the end of the day, from 17:00 through to 23:59. (Note: this is not a good representation of reality, but it will do for our discussion.)

These two sets of blanks can be considered "before-or-after" blanks, and will be replaced in our next data manipulation sequence by "default traffic flow" values (provisioned on another UI screen in the Vehicle Flow screen of the Query module).

For the final traffic flow report, we substitute default traffic flow values in place of all before-or-after blanks.

### In-Between Blanks ###

The other kind of blanks in the previous result set can be considered "in-between" blanks. The rows for 10am, 11am, and 1pm are "in-between" other rows that _do_ have data. So, for in-between blanks, we will interpolate values.

For instance, examine the 09:00 row (the row beginning with '9' in the result set above). The second column (representing W2 or two-wheeler vehicles) reports the average hourly count at 6.66. The next row that has a value for this column is 12:00 ('12') which reports  an average hourly rate of 1.33. We take the average of these values: (6.66 + 1.33)/2 = 3.99. Thus, we substitute this interpolated value in the same column of rows 10 and 11. This is repeated for all the columns in the row.