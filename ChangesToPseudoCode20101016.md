**On the Traffic Speed Table**

On calculating the SpeedDistributionTable, in insertSpeedDistributionRecord(), we are just performing a count of all the GPS points in each speed bin to get the time in bin, right? (that is Tag/DayType/Hour/SpeedBinNumber).
I think the problem starts here.

The number of GPS points on a tag does not have and significance on the traffic flow on that tag. (If your car has a GPS, the greatest concentration of points will likely be on your driveway), So I think we need to get the Fractional time in bin, not the absolute time in bin.

We need to add:
```
For each Tag/DayType/Hour/SpeedBinNumber, DistanceInBin = AverageSpeedInBin x TimeInBin
Then
For each Tag/DayType/Hour, Sum TimeInBin from all speed bins , Sum DistanceInBin from all speed bins
For each Tag/DayType/Hour/SpeedBinNumber
               FractionalTimeInBin = TimeInBin  / "Sum TimeInBin"
               FractionalDistanceInBin = DistanceInBin / "Sum DistanceInBin "
               AverageSpeedInBin = AverageSpeedInBin
```
Then we use these 4 variables in all the other "reducing" calculations.

In the **Speed Distribution Traffic Flow Table** we are going to have to do the same.

After doing the "sum of products" on Hour using the  FractionalTimeInBin and FractionalDistanceInBin from the previous step we will need to refractionalize using a similar routine to the above. Average speed will remain unchanged.

In tables D the same will be needed.

In table E we are already doing it (and presenting the output as Table F)


**On the Traffic Flow table**

I have one concern on how we are interpolating the blanks.

Using your example

```
9;6.66;8.41;10.16;11.91;13.66;15.41;17.16;18.91;20.66
10;;;;;;;;;
11;;;;;;;;;
12;1.33;2.67;4.00;5.33;6.67;8.00;9.33;10.67;12.00
```

For the first vehicle type, both 10 and 11 would be calculated inHourly Average Per Recorded Count as
10: (6.66 + 1.33)/2 = 4, I think, right? However 10 should be clearly larger than 11 to get a "straight Line trajectory from 6.66 to 1.33.
It would be better if 10 was: 6.66+ (1.33-6.66)/3\*1 = 4.88
11 would be  6.66+ (1.33-6.66)/3\*2 = 3.10

Re the questions in your email
Re averageSpeed: you are right, please do not calculate, just copy from the previous table (E, I think).
Re: Calculate proxy length for user-defined zones (length = average block length / area of zone). I think it is the other way round, (length= area of zone / average block length)

**One Additional Table**

All the above calculations give us for each vehicle type

  * Fractional time in each speed bin
  * Fractional distance in each speed bin
  * Average speed in each speed bin

To use these we need to know the total vehicle kilometers traveled for each vehicle type
This comes from the traffic flow table
1) for each Tag/DayType/VehicleType, traffic flow per day = sum of traffic flow per hour over hours 0 to 23
2) For each Tag/VehicleType, traffic flow per year = sum of  traffic flow per day x daytype multiplier for all daytypes
3) For Each VehicleType, Total Vehicle Kilometers travelled = sum of  traffic flow per year  for each tag type multiplied by the tag length (in kms).