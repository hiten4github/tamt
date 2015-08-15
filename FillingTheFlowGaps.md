## Description of data interpolation for traffic flow ##

(From John's Task Four.docx document)

### Filling in the flow gaps ###

It is usual practice to not count traffic flows during the hours of least flow. By not counting from say 10:00 PM to 5:00 AM only two shifts of people are required. Also safety is often an issue in the midnight hours!

However there are likely to be two types of blank:
  1. Blanks during the working day where there is a before- and after- value. In these cases the missing data can be interpolated
  1. Night-time empties where there is no before-value  (ie first data is at 5:00 AM) or where there is no after-value (ie last data is at 22:00). In these cases the user entered default value can be used.

In John's document, there follows a mock-up of a screen (showing the Sunday/Holiday page for filling gaps in flows)

![http://tamt.googlecode.com/files/fillinflowscreen-small.png](http://tamt.googlecode.com/files/fillinflowscreen-small.png)

## Discussion ##

We need to flush this out a bit. There is not enough in the original TOR or John's Task Four.docx to determine what the UI should look like for the Data Interpolation subcomponent.

### Questions ###

  * Are all of the "cells" on this page editable? Or just the blue ones for default
  * Are the "cells" on this page all interpolated? Or is it a mix of recorded + interpolated?

### Assumptions ###

**Editing**

  1. The User can only edit the default night-time traffic flow (the blue cells in the image above)
  1. The default night-time traffic flow values should be average flows per hour
  1. The default night-time traffic flow values can be set for each day-type (ie, WEEKDAY, SATURDAY, SUNDAY\_HOLIDAY)

**Data in the cells**

  1. A specific cell's data in the matrix above (e.g. On the SUNDAY\_HOLIDAY view for HWY001 tag, at the intersection of Two-Wheeler and 13:00-13:59) should represent the **average traffic flow per hour for that vehicle type in that timeframe for that day-type for that road tag**.

For instance, if the user had collected traffic count field data representing:
  * 200 passenger cars on Road A (with HWY001 tag) on a WEEKDAY from 09:00 to 09:15
  * 100 passenger cars on Road B (with HWY001 tag) on a WEEKDAY from 09:20 to 09:40

Then we need to make the following calculations to obtain the **average** traffic flow for HWY001/WEEKDAY/09:00-09:59):

  1. Calculate the average number of cars per hour for **Road A on a WEEKDAY from 09:00 to 09:59**
    1. Multiply the count for the 15 minute interval by 4: 200 **4 = 800 PCs / hr
  1. Calculate the average number of cars per hour for**Road B on a WEEKDAY from 09:00 to 09:59**1. Multiply the count for the 20 minute interval by 5: 100** 5 = 500 PCs / hr
  1. Average the two hourlyr averages together to get an average