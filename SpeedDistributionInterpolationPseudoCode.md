

# Introduction #

After creating the speed distribution table for existing GPS data (see SpeedDistributionPseudoCode), we must fill in the gaps where the speed distribution does not have data. This is the speed distribution interpolation process, outlined below.

# Java Code #

## triggerSpeedDistributionInterpolation() ##

```
void triggerSpeedDistributionInterpolation()
{
    // first, create the speed dist observed records
    createSpeedDistObserved();

    // next, interpolate missing data in speed dist, based on speed dist observed
    interpolateSpeedDistribution();

}
```

## createSpeedDistObserved() ##

```
void createSpeedDistObserved
{
  tags = getTags();
  dayTypes = getDayTypes();
  hourBins = getHourBin();

  // loop on tags
  foreach tag in tags
  {
     // loop on dayTypes
     foreach dayType in dayTypes
     {
         
          // loop on hourBins
          foreach hourBin in hourBins
          {
              
                 // check if this row has observed GPS data
                 boolean isObserved = hasObservedGPSData( tag, dayType, hourBin);

                 int totalFlow = null;
                 if( isObserved )
                 {
                     totalFlow = getTotalFlow(tagId, dayType, hourBin);
                 }

                 // create the record
                 /* Note: total flow could be null in the case that we have
                 no observed GPS data. The fact that it is NOT observed data will be 
                 recorded and we will subsequently fill in the gaps.
                 */
                createObservedGPSDataRecord(tagID, dayType, hourBin, isObserved, totalFlow);

          }
     }
  }

// now fill in the gaps 
//TODO: create meaningful method name to fill in the gaps


}
```

## hasObservedGPSData() ##

```
boolean hasObservedData(tagId, dayType, hourBin)
{
     // TODO: db connection 
     
     // sql
     String sql = "SELECT SUM(timeinbin) FROM speeddistribution " +
                           "WHERE tagId = tagId " +
                           "AND dayType = dayType " +
                           "AND hourBin = hourBin ";

     // TODO: execute SQL

     // TODO: catch errors

     // first item in result set is...
     result = <sum of time in bin for all speed bins for this hour>

     boolean hasObserved = false;
     if( result > 0)
     {
          hasObserved = true;
     }

    return hasObserved;
}
```

## createObservedGPSDataRecord() ##

This method depends on a new table called `speeddistobserved` to keep track of the observed (ie has GPS data) state of each row in the speeddistribution table.

```
void createObservedGPSDataRecord(tagId, dayType, hourBin, isObserved, totalFlow)
{
   // TODO: db connectino

   // sql
   String sql = "INSERT INTO speeddistobserved " +
                         // TODO: insert values for tagId, dayType, hourBin, isObserved, and totalFlow

   // TODO: execute SQL

   // TODO: catch errors
}
```

## getTotalFlow() ##

This method depends on a change to the Traffic Flow Report generation. As a user generates the traffic flow report data, we can add up the vehicle counts for each hour bin across all vehicle types and put the total in a new column in the table called totalFlow. That way it is easily extracted during this speed distribution interpolation process.

```
int getTotalFlow(tagId, dayType, hourBin)
{
  // TODO: db connection
  
  // SQL
  String sql = "SELECT totalFlow FROM trafficflowreport " +
             "WHERE tagId = tagId" +
             "AND dayType = dayType" +
             "AND hourBin = hourBin";

  // TODO: execute SQL
  totalFlow = results[0];

  return totalFlow;
}
```

## interpolateSpeedDistribution() ##

```
void interpolateUnObservedSpeedDistributions()
{

    // get a list of unobserved hour bins using isObserved = false in speeddistobserved table
    unObservedSpeedDistributions = getUnobservedSpeedDistributions();

   // loop over unobserved speed distributions
   foreach unObservedSpeedDistribution in unObservedSpeedDistributions
   {
      // find row in speeddistobserved that is closest to totalFlow +/- 10% for same daytype
      // if exists, use this speeddist row
      // if not exists, find row in speeddistobserved that is closest to totalFlow, regardless of dayType
      
      // copy row from speeddistribution based on matching tag/day/hour from speeddistobserved into
      // new row in speeddistribution with updated values for vehicleSecondsPerDay, vehicleMetersPerDay
      // and weightedAverageSpeed
   }

}

```

## getUnobservedSpeedDistributions() ##

TODO: return the rows from speeddistobserved where isObserved is false.


# Speed Distribution Observed (speeddistobserved) Table Schema #

| tagId | dayType | hourBin | isObserved | totalFlow |
|:------|:--------|:--------|:-----------|:----------|
| str   | str     | int     | boolean    | double    |