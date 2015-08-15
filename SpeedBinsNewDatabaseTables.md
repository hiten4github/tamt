# Overview #

The Speed Bins component of the Analysis module requires a number of tables to reduce or generalize the data for export. This page summarizes what these tables do and what they look like.

The tables have been created in the TAMT Appliance. The table creation scripts have also been [checked into the repository](http://code.google.com/p/tamt/source/detail?r=23be7bf85c58ec5ade88fa6f947c112601682a82#).

# Speed Bin Tables #

## `speeddistribution` ##

As found on SpeedDistributionPseudoCode, this table holds the observed and interpolated speed bin distributions.

| tagId | dayType | hourBin | speedBin | timeInBin | fractionalTimeInBin | avgSpeed | distanceInBin | fractionalDistanceInBin |
|:------|:--------|:--------|:---------|:----------|:--------------------|:---------|:--------------|:------------------------|
| str   | str     | int     | int      | double    | double              | double   | double        | double                  |


## `speeddistributiontrafficflow` ##

As found on CombinationSpeedDistributionTrafficFlowPseudoCode, this table holds the product of the `speeddistribution` and `trafficflowreport` tables.

| tagID | dayType | vehicleType | speedBin | vehicleSecondsPerDay | vehicleMetersPerDay | weightedAverageSpeed | fractionalVehicleSecondsPerDay | fractionalVehicleMetersPerDay |
|:------|:--------|:------------|:---------|:---------------------|:--------------------|:---------------------|:-------------------------------|:------------------------------|
| str   | str     | str         | int      | double               | double              | double               | double                         | double                        |


## `speeddistributiontrafficflowtagvehiclespeed` ##

As found on RemovingDayTypeFromSpeedDistributionTrafficFlow, this table is a derivative of `speeddistributiontrafficflow` where we remove the dayType variable. This may also referred to in this wiki as **Table D**.

| tagId | vehicleType | speedBin | vehicleSecondsPerBinPerYear | vehicleMetersPerBinPerYear | fractionalAverageSpeed | fractionalVehicleSecondsPerBinPerYear | fractionalVehicleMetersPerBinPerYear |
|:------|:------------|:---------|:----------------------------|:---------------------------|:-----------------------|:--------------------------------------|:-------------------------------------|
| str   | str         | int      | double                      | double                     | double                 | double                                | double                               |

## `speeddistributiontrafficflowvehiclespeed` ##

As found on RemovingTagFromSpeedDistributionTrafficFlow, this table is a derivative of `speeddistributiontrafficflowtagvehiclespeed` where we remove the tag variable. This may also be referred to in this wiki as **Table E**.

| vehicleType | speedBin | totalSeconds | totalMeters | avgSpeed |
|:------------|:---------|:-------------|:------------|:---------|
| str         | int      | double       | double      | double   |

## `speeddistributiontrafficflowvehiclespeedfraction` ##

As found on FinalSpeedBinTablePseudoCode, this table is a derivative of `speeddistributiontrafficflowvehiclespeed` where we convert totalSeconds and totalMeters to fractionalSeconds and fractionalMeters respectively.

This may also be referred to in this wiki as **Table F**.

Depending on the outcome of the UI design for the Speed Bin component, some of the intermediate tables (above) may be created as needed and then dropped. This table will not be dropped as it represents the view we want to give the user.

| vehicleType | speedBin | fractionalSeconds | fractionalMeters | avgSpeed |
|:------------|:---------|:------------------|:-----------------|:---------|
| str         | int      | double            | double           | double   |