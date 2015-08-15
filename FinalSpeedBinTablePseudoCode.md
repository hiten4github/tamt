# Goal #

This is where we wanted to be all along. We'll offer this table as a view for the users, along with a download of its contents to CSV. This is the elusive Table F, or Output of Fractional Distance from the original TOR.

We will converting Table E:
| vehicleType | speedBin | totalSeconds | totalMeters | avgSpeed |
|:------------|:---------|:-------------|:------------|:---------|

...to Table F
| vehicleType | speedBin | fractionalSeconds | fractionalMeters | avgSpeed |
|:------------|:---------|:------------------|:-----------------|:---------|

WHERE:
  * fractionalSeconds = E.totalSeconds / E.sum\_of\_totalSeconds\_by\_vehicleType
  * fractionalMeters = E.totalMeters / E.sum\_of\_totalMeters\_by\_vehicleType

# Overview #

  * Calculate E.sum\_of\_totalMeters\_by\_vehicleType
  * For every row in E, calculate:
    * E.totalSeconds / E.sum\_of\_totalSeconds\_by\_vehicleType, insert as fractionalSeconds
    * E.totalMeters / E.sum\_of\_totalMeters\_by\_vehicleType, insert as fractionalMeters
    * E.avgSpeed = E.fractionalMeters / E.fractionalSeconds

# Java Code #

```
void triggerFinalSpeedBinCalculations()
{

   // TODO: setup db connection
   // TODO: SQL = TAMT_setFractionalSpeedDistValues
   // TODO: catch errors
}

```

# SQL Code #

```
CREATE OR REPLACE FUNCTION TAMT_setFractionalSpeedDistValues

-- declare vars

-- begin

      -- totalSeconds = sum E.totalSecondsByVehicleType by vehicle type

      -- for each row in E
            -- insert into E.fractionalSeconds = E.totalSecondsByVehicleType / E.totalSeconds
            -- insert into E.fractionalMeters = E.totalMetersByVehicleType / E.totalMeters
            -- insert into E.avgSpeed = E.fractionalSeconds / E.fractionalMeters (*** is this correct? ***)

```