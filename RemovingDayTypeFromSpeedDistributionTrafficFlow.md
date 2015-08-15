

# Java Code #

## removeDayTypeFromSpeedDistributionTrafficFlow() ##

This is the initial method called to remove dayType from the `speeddistributiontrafficflow` table. In the my notes this is Table D, but we needed a more descriptive name than that, so this will be called `speeddistributiontrafficflowtagvehiclespeed`

```
void removeDayTypeFromSpeedDistributionTrafficFlow()
{

      tags = getTags();
      vehicleTypes = getVehicleTypes();
      speedBins = getSpeedBins();

       dayTypePerYearOption = getDayTypePerYearOption();
       equivalentDaysPerYear = null;
       daysPerYearByDayTypes = null; // hash: key = daytype, value = number of days

       if( dayTypePerYearOption = OPTION001 )
       {
          equivalentDaysPerYear = getEquivalentDaysPerYear();
       } else {
          daysPerYearByDayType = getDaysPerYearByDayType();
       }

      foreach tag in tags
      {
         foreach vehicleType in vehicleTypes
         {
            foreach speedBin in speedBins
            {
                  if( equivalentDaysPerYear != null)
                  {
                      calculateVehicleVariablesPerBinPerYear( multiplier );
                  } else {
                      foreach daysPerYearByDayType in daysPerYearByDayType
                      {
                        // remember, this is a hash
                        calculateVehicleVariablesPerBinPerYear( daysPerYearByDayType.multiplier,
                                              daysPerYearByDayType.dayType );
                      }
                  }                
            }
 
            // now calculate fractionalVehicleSecondsPerBinPerYear and fractionalVehicleMetersPerBinPerYear
            foreach speedBin in speedBins
            {
               sumVehicleSecondsPerBinPerYear = getSumVehicleSecondsPerBinPerYear( tag, vehicleType, speedBin );
               sumVehicleMetersPerBinPerYear = getSumMetersSecondsPerBinPerYear( tag, vehicleType, speedBin );
               setFractionalValuesForSpeedBin( tag, vehicleType, speedBin, 
                        sumVehicleSecondsPerBinPerYear, sumVehicleMetersPerBinPerYear);
            }
         }
      }

      calcAverageSpeed();
}
```

## calculateVehicleVariablesPerBinPerYear() ##

```
void calculateVehicleVariablesPerBinPerYear( multiplier, dayType )
{
      // if dayType is null, then multiplier is applied to every row from Table C:
      // foreach row in `speeddistributiontrafficflow` (Table C in my notes)
      //       vehicleSecondsPerBinPerYear = C.fractionalVehicleSecondsPerDay * equivalentDaysPerYear
      //       vehicleMetersPerBinPerYear = C.fractionalVehicleMetersPerDay * equivalentDaysPerYear
      //       insert a row in the new Table D: 
      //                    C.tagId, C.vehicleType, C.speedBin
      //                    vehicleSecondsPerBinPerYear, vehicleMetersPerBinPerYear

      // if dayType is not null, then multiplier is only applied to rows from Table C with that daytype
       // TODO: write SQL code
      // foreach row in `speeddistributiontrafficflow` (Table C in my notes)

      //       vSBY_WEEKDAYS = C.vehicleSecondsPerDay[WEEKDAY] * numWeekdays
      //       vSBY_SATURDAYS = C.vehicleSecondsPerDay[SATURDAY] * numSaturdays
      //       vSBY_SUNDAYHOLIDAYS = C.vehicleSecondsPerDay[SUNDAY_HOLIDAY] * numSundayHolidays
      //       vehicleSecondsPerBinPerYear = vSBY_WEEKDAYS + vSBY_SATURDAYS + vSBY_SUNDAYHOLIDAYS

      //       vMBY_WEEKDAYS = C.vehicleMetersPerDay[WEEKDAY] * numWeekdays
      //       vMBY_SATURDAYS = C.vehicleMetersPerDay[SATURDAY] * numSaturdays
      //       vMBY_SUNDAYHOLIDAYS = C.vehicleMetersPerDay[SUNDAY_HOLIDAY] * numSundayHolidays
      //       vehicleMetersPerBinPerYear = vMBY_WEEKDAYS + vMBY_SATURDAYS + vMBY_SUNDAYHOLIDAYS

      //       insert a row in the new Table D: 
      //                    C.tagId, C.vehicleType, C.speedBin
      //                    vehicleSecondsPerBinPerYear, vehicleMetersPerBinPerYear

}
```

## calcAverageSpeed() ##

```
void calcAverageSpeed()
{
      // TODO: write SQL code
      // foreach row in Table D
      //     SET fractionalAverageSpeed = D.fractionalVehicleMetersPerBinPerYear / D.fractionVehicleSecondsPerBinPerYear
}
```


## setFractionalValuesForSpeedBin() ##

```
void setFractionalValuesForSpeedBin( _tag_, _vehicleType, _speedBin, 
                 sumVehicleSecondsPerBinPerYear, sumVehicleMetersPerBinPerYear)
{
     // TODO: setup db connection

    // setup sql
    // For each row in speeddisttrafficflow 
    // set fractionalVehicleSecondsPerDay = vehicleSecondsPerBinPerYear / sumVehicleSecondsPerBinPerYear and
    // set fractionalVehicleMetersPerDay = vehicleMetersPerBinPerYear / sumVehicleMetersPerBinPerYear
    // WHERE  tag = _tag
    // AND  vehicleType = _vehicleType
    // AND  speedBin = _speedBin

    // TODO: execute SQL, assign result

    // TODO: catch errors

}
```

# SQL Code #

## "Speed Distribution Traffic Flow by Tag, Vehicle and Speed" Schema ##

This table will be named `speeddistributiontrafficflowtagvehiclespeed`

| tagId | vehicleType | speedBin | vehicleSecondsPerBinPerYear | vehicleMetersPerBinPerYear | fractionalAverageSpeed | fractionalVehicleSecondsPerBinPerYear | fractionalVehicleMetersPerBinPerYear |
|:------|:------------|:---------|:----------------------------|:---------------------------|:-----------------------|:--------------------------------------|:-------------------------------------|
| str   | str         | int      | double                      | double                     | double                 | double                                | double                               |