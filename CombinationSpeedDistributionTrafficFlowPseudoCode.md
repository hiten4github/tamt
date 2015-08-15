

# Java Code #

## combineSpeedDistributionTrafficFlow() ##

```
void combineSpeedDistributionTrafficFlow()
{
  tags = getTags();
  dayTypes = getDayTypes();
  vehicleTypes = getVehicleTypes();
  speedBins = getSpeedBins();
  hourBins = getHourBins(); // always 0..23

  // loop on tags
  foreach tag in tags:

    // loop over daytypes
    foreach dayType in dayTypes:

      // loop on speed bins
      foreach speedBin in speedBins:

         // initialize cumulative variables
         vehicleSecondsPerDay = 0.0;
         vehicleMetersPerDay = 0.0;

        // loop on hourBin
        foreach hourBin in hourBins:
        {       
          // traffic count from traffic flow report
          trafficCount = getTrafficCount( tag, dayType, vehicleType, hourBin);

          // fractional time in bin and average speed in bin from speed distribution table
          fractionalTimeInBin = getFractionalTimeInBin( tag, dayType, vehicleType, hourBin, speedBin);
          averageSpeedInBin = getAverageSpeedInBin( tag, dayType, vehicleType, hourBin, speedBin);
     
          // calculate vehicle seconds per hour and add to vehicle seconds per day
          // Note: even though vehicleSecondsPerDay is derived from fractionalTimeInBin,
          // we are not naming this variable fractionalVehicleSecondsPerDay because that
          // variable name is reserved for the sum and fraction calculation performed later
          vehicleSecondsPerHour = trafficCount * fractionalTimeInBin;
          vehicleSecondsPerDay = vehicleSecondsPerDay + vehicleSecondsPerHour;

          // calculate vehicleMetersPerDay
          vehicleMetersPerHour = fractionalTimeInBin * averageSpeedInBin;
          vehicleMetersPerDay = vehicleMetersPerDay + vehicleMetersPerHour;
   
      } // close hour loop

     // calculate speed
      weightedAverageSpeed = vehicleSecondsPerDay * vehicleMetersPerDay;

      // insert cumulative variables in new table
      insertSpeedDistTrafficFlowRecord( tag, dayType, vehicleType, speedBin, 
                          vehicleSecondsPerDay, vehicleMetersPerDay, weightedAverageSpeed);

     }  // close speed bin loop

     // now loop again over speed bins to get the sums and insert the fractional values
     foreach speedBin in speedBins
     {
       sumVehicleSecondsPerDay = getSumVehicleSecondsPerDay( speedBin );
       sumVehicleMetersPerDay = getSumVehicleMetersPerDay( speedBin );
       setFractionalValuesForSpeedBin( speedBin, sumVehicleSecondsPerDay, sumVehicleMetersPerDay);
     }

   } // close day type loop
  } // close tag loop
} // close method
```

## getTrafficCount() ##

TODO: we will reach into the traffic flow report table to extract this information based on tag, daytype, vehicle type and hour bin

## getFractionalTimeInBin() ##

TODO: we will reach into the speed distribution table to extract fractionalTimeInBin based on tag, dayType, vehicleType, hourBin, and speedBin.

## getAverageSpeedInBin() ##

TODO: we will reach into the speed distribution table to extract this information based on tag, dayType, vehicleType, hourBin, and speedBin.

## getSpeedBins() ##

TODO: calc based on highest speed for gps points in study region. Use 5kph increments, but take into account user-defined max speed.

## getVehicleTypes() ##

TODO: this is a fixed list, but should be available as a global call for this code and others like it.

## getHourBins() ##

TODO: this is always 0 to 23 as an integer array

## getTags() ##

TODO: this code exists already, I just have to pick up the current study region and request the tags. Check the TagBO for existing methods

## insertSpeedDistTrafficFlowRecord() ##

TODO: insert the record with the parameters tagID, dayType, vehicleType, speedBin, vehicleSecondsPerDay, vehicleMetersPerDay and weightedAverageSpeed

```
void insertSpeedDistTrafficFlowRecord( tagId, dayType, vehicleType, speedBin, vehicleSecondsPerDay, vehicleMetersPerDay, weightedAverageSpeed)
{
    // TODO: setup db connection

    // setup sql
    String sql = "INSERT INTO speeddisttrafficflow ..."
                           // also insert two null values at the end as placeholders for fractionalVehicleSecondsPerDay
                           // and fractionalVehicleMetersPerDay
    
    // TODO: execute SQL

    // TODO: catch errors
}
```

## setFractionalValuesForSpeedBin() ##

```
void setFractionalValuesForSpeedBin( _speedBin, sumVehicleSecondsPerDay, sumVehicleMetersPerDay )
{
     // TODO: setup db connection

    // setup sql
    // For each row in speeddisttrafficflow where speedBin = _speedBin,
    // set fractionalVehicleSecondsPerDay = vehicleSecondsPerDay / sumVehicleSecondsPerDay and
    // set fractionalVehicleMetersPerDay = vehicleMetersPerDay / sumVehicleMetersPerDay
    
    // TODO: execute SQL, assign result

    // TODO: catch errors

}
```

# Speed Distribution Traffic Flow Table Schema #

This table will be called `speeddistributiontrafficflow`

| tagID | dayType | vehicleType | speedBin | vehicleSecondsPerDay | vehicleMetersPerDay | weightedAverageSpeed | fractionalVehicleSecondsPerDay | fractionalVehicleMetersPerDay |
|:------|:--------|:------------|:---------|:---------------------|:--------------------|:---------------------|:-------------------------------|:------------------------------|
| str   | str     | str         | int      | double               | double              | double               | double                         | double                        |

# SQL Code #

No stored procedures necessary, so all SQL code will be explicitly setup in the java code above.