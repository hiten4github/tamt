# Introduction #

Speed Distribution is the term we apply to describe analyzing the GPS points for time and average speed according to speed bin, hour bin, day type, and tag. Speed Distribution data is combined with Traffic Flow Report data to obtain the (elusive) Fractional Distance Traveled by Tag.

Some code will be handled by Java, some by stored procedures in PostGreSQL.

# Java Code #

We enter the code at the Tag level. The following code will need to be executed for each tag in the study region. This will be done in one fell swoop on a user-initiated "Calculate" request.

Some of the following code is not quite Java, but you get the picture:

## populateSpeedDistribution() ##

This method is called for each tag in the study region.

```
void populateSpeedDistribution( TagDetails tagDetails, int lastSpeedBin )
{
    String tagId = tagDetails.getID();
    String dayType = null;
    int hourBin = 0;
    int speedBin = 0;

    ArrayList(String) dayTypes = <get from other classes>;

    double timeInBin = 0.0;
    double avgSpeedInBin = 0.0;

    foreach dayType in dayTypes:

       // loop on hour bins
       for (i=0; i < 24; i++) 
       {
          hourBin = i;
          // loop on speed bin numbers (not speed values, just the bin number)
          for (j=0; j <= lastSpeedBin; j++)
          {
             speedBin = j;
             insertSpeedDistributionRecord(tagId, dayType, hourBin, speedBin);
          }


          // now that all records are in the table for each speed bin, calculate 
          // sumTimeInSpeedBin and sumDistanceInSpeedBin for each speed bin
          // so we can update each record with a fractionalTimeInSpeedBin and fractionalDistanceInSpeedBin
          sumTimeInSpeedBin = getSumTimeInSpeedBin( tagId, dayType, hourBin );
          sumDistanceInSpeedBin = getSumDistanceInSpeedBin( tagId, dayType, hourBin );
          for (j=0; j <= lastSpeedBin; j++)
          {
             speedBin = j;
             setFractionalValuesForSpeedBin( tagId, dayType, hourBin, speedBin, sumTimeInSpeedBin, sumDistanceInSpeed);
          }
          
       }
}
```

## insertSpeedDistributionRecord() ##

This method is called from **populateSpeedDistribution** and is called for every _speed bin_ of every _hour bin_ of every _day type_ of every _tag_. Obviously, I have a concern about how long this method will take to run. No preliminary performance testing has been done.

```
void insertSpeedDistributionRecord( String tagId, String dayType, int hourBin, int speedBin)
{
    // TODO: setup db connection

    // setup sql
    String sql = "SELECT * FROM TAMT_insertSpeedDistributionRecord(" +
                  // TODO: interpolate tagId, dayType, hourBin, speedBin
                 ")";
    
    // TODO: execute SQL

    // TODO: catch errors
}
```

## getSumTimeInSpeedBin() ##

```
double getSumTimeInSpeedBin( _hourBin, _speedBin )
{
     // TODO: setup db connection

    // setup sql
    String sql = "SELECT sum(timeInBin) FROM speeddistribution " +
               "WHERE speedBin = _speedBin and hourBin = _hourBin";
    
    // TODO: execute SQL, assign result

    // TODO: catch errors

    return sumTimeInSpeedBin;
}
```

## getSumDistanceInSpeedBin() ##

```
double getSumDistanceInSpeedBin( _hourBin, _speedBin )
{
     // TODO: setup db connection

    // setup sql
    String sql = "SELECT sum(distanceInBin) FROM speeddistribution " +
                "WHERE speedBin = _speedBin and hourBin = _hourBin";
    
    // TODO: execute SQL, assign result

    // TODO: catch errors

    return sumDistanceInSpeedBin;
}
```

## setFractionalValuesForSpeedBin() ##

```
void setFractionalValuesForSpeedBin( _hourBin, _speedBin, sumTimeInSpeedBin, sumDistanceInSpeedBin )
{
     // TODO: setup db connection

    // setup sql
    // For each row in speeddistribution where speedBin = _speedBin and hourBin = _hourBin,
    // set fractionalTimeInBin = timeInBin / sumTimeInSpeedBin and
    // set fractionalDistanceInSpeedBin = distanceInBin / sumDistanceInSpeedBin
    
    // TODO: execute SQL, assign result

    // TODO: catch errors

}
```

# Speed Distribution Table Schema #

| tagId | dayType | hourBin | speedBin | timeInBin | fractionalTimeInBin | avgSpeed | distanceInBin | fractionalDistanceInBin |
|:------|:--------|:--------|:---------|:----------|:--------------------|:---------|:--------------|:------------------------|
| str   | str     | int     | int      | double    | double              | double   | double        | double                  |

# SQL Stored Procedures #

The following are the stored procedures required to insert records into the speed distribution table. They are pseudo code and may not be syntactically accurate.

## TAMT\_insertSpeedDistributionRecord() ##

This SQL stored procedure is called by the java method **insertSpeedDistributionRecord**. It subsequently calls to more internal stored procedures (getTimeInBin and getAverageSpeedInBin) and finally inserts the timeInBin and avgSpeed into the speed distribution record.

```
CREATE OR REPLACE FUNCTION TAMT_insertSpeedDistributionRecord(_tagId, _dayType, _hourBin, _speedBin)
-- declare vars
  timeInBin double
  avgSpeed double
-- begin
   -- PERFORM TAMT_getTimeInBin with proper vars and set result in timeInBin
   -- PERFORM TAMT_getAverageSpeedInBin with proper vars and set result in avgSpeed
   -- Calculate distanceInBin = avgSpeed * timeInBin
   -- INSERT INTO speeddistribution _tagId, _dayType, _hourBin, _speedBin, timeInBin, avgSpeed, distanceInBin, null, null
   -- The two nulls are placeholders for fractionalTimeInBin and fractionalDistanceInBin
-- end

  RETURN 1

```

## TAMT\_getTimeInBin() ##

```
CREATE OR REPLACE FUNCTION TAMT_getTimeInBin(_tagId, _dayType, _hourBin, _speedBin)
-- declare vars
  timeInBin double
-- begin
  timeInBin = 0.0
  SELECT into timeInBin
     count(*) FROM gpspoints
  WHERE tagId = _tagId
  AND dayType = _dayType
  AND hourBin = _hourBin
  AND speedBin = _speedBin

  RETURN timeInBin

-- end

```

## TAMT\_getAverageSpeedInBin() ##

```
CREATE OR REPLACE FUNCTION TAMT_getAverageSpeedInBin(_tagId, _dayType, _hourBin, _speedBin)
-- declare vars
  avgSpeed double
-- begin
  timeInBin = 0.0
  SELECT into avgSpeed
     AVG(kph) FROM gpspoints // don't forget to switch nautical miles to kph
  WHERE tagId = _tagId
  AND dayType = _dayType
  AND hourBin = _hourBin
  AND speedBin = _speedBin

  RETURN timeInBin

-- end

```