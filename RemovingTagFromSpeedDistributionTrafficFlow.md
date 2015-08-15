

# Goal #

Reduce data in Table D by factoring out tagId...

| tagId | vehicleType | speedBin | vehicleSecondsPerBinPerYear | vehicleMetersPerBinPerYear | avgSpeed |
|:------|:------------|:---------|:----------------------------|:---------------------------|:---------|

...to create a new Table E:

| vehicleType | speedBin | totalSeconds | totalMeters | avgSpeed |
|:------------|:---------|:-------------|:------------|:---------|


<a href='Hidden comment: 
QUESTION: My notes for Table F show the exact same schema as Table E:

|| vehicleType || speedBin || fractionalSeconds || fractionalMeters || avgSpeed ||

* fractionalSeconds = E.totalSeconds / E.sum_of_totalSeconds_by_vehicleType
* fractionalMeters = E.totalMeters / E.sum_of_totalMeters_by_vehicleType
'></a>

# Overview #

  * Calculate meter length for each tag (be careful with units!)
    * Calculate length of all user-defined roads assigned to a tag
    * Calculate proxy length for user-defined zones (length = average block length / area of zone)
> > > (area = m^2, therefore area divided by block length so answer is in meters)
    * Calculate proxy length for default zone
      * e.g. if default zone is IND, then IND area = (study region area) - (area of other user-defined zones)
      * then get proxy length like user-defined zone
  * For each row in D,  D.vehicleMetersPerBinPerYear X tag.length = E.totalMeters
  * For each row in D,  D.vehicleSecondsPerBinPerYear X tag.length = E.totalSeconds
  * For each row in D,  E.avgSpeed = E.totalMeters / E.totalSeconds
**check E.avgSpeed should be the same as D.averageMetersPerSecond**
# Java Code #

## triggerTagReduction ##

```
void triggerTagReduction
{
      // get tags 
      tags = getTags();
      foreach tag in tags
     {
          meterLengthByTag = getMeterLengthByTag(tagId);
          calculateTableERecord( meterLengthByTag );
     }
}
```

## getMeterLengthByTag ##

Given a tagId, return the length of all roads that are tagged with that tag

```
double getMeterLengthByTag(tagId)
{
     
    double totalMetersForTag = 0.0

   // get road length with the tagId
   lengthUserDefinedRoads = getUserDefinedRoadsLength( tagId );

   // get the proxy length for zones in the study region
   proxyLengthUserDefinedZones = getProxyUserDefinedZonesLength( currentStudyRegion );   
   
   // get the default zone proxy length
   proxyLengthDefaultZones = getProxyDefaultZonesLength( currentStudyRegion );

  // add them all up
  meterLength = lengthUserDefinedRoads + proxyLengthUserDefinedZones + proxyLengthDefaultZones;
  return meterLength

}
```

## getUserDefinedRoadsLength ##

```
void getUserDefinedRoadsLength(tagId)
{
     // TODO: setup db connection, SQL and execute
     // TODO: SQL = TAMT_getUserDefinedRoadsLength
     return value;
}
```

## getProxyUserDefinedZonesLength ##

```
void getProxyUserDefinedZonesLength( studyRegion )
{
     // TODO: setup db connection, SQL and execute
     // TODO: SQL = TAMT_getProxyUserDefinedZonesLength
     return value;
}
```

## getProxyDefaultZonesLength ##

```
void getProxyDefaultZonesLength( studyRegion )
{
     // TODO: setup db connection, SQL and execute
     // TODO: SQL = TAMT_getProxyDefaultZonesLength
     return value;
}
```

## calculateTableERecord ##

  * For each row in D,  D.fractionalVehicleMetersPerBinPerYear X tag.length = E.totalMeters
  * For each row in D,  D.fractionalVehicleSecondsPerBinPerYear X tag.length = E.totalSeconds
  * For each row in D,  E.avgSpeed = E.totalMeters / E.totalSeconds

```
void calculateTableERecord( meterLengthByTag )
{
      // TODO: setup SQL and execute
      // TAMT_calculateTotalMetersSecondsAverageSpeed
}
```

# SQL Code #

## TAMT\_getUserDefinedRoadsLength ##

TODO

## TAMT\_getProxyUserDefinedZonesLength ##

TODO

## TAMT\_getProxyDefaultZonesLength ##

TODO

## TAMT\_calculateTotalMetersSecondsAverageSpeed ##

TODO