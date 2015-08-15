## Download "split" files ##

The TAMT Appliance is a 3.5GB VirtualBox virtual machine. In order to host the appliance on this website, it was necessary to split the 3.5GB file into a number of smaller files (29 files at ~125MB each).

Each of these files needs to be downloaded, then "joined" using the HJSplitter application

The files are:

  * http://tamt.googlecode.com/files/tamt-assign.zip.001
  * http://tamt.googlecode.com/files/tamt-assign.zip.002
  * http://tamt.googlecode.com/files/tamt-assign.zip.003
  * http://tamt.googlecode.com/files/tamt-assign.zip.004
  * http://tamt.googlecode.com/files/tamt-assign.zip.005
  * http://tamt.googlecode.com/files/tamt-assign.zip.006
  * http://tamt.googlecode.com/files/tamt-assign.zip.007
  * http://tamt.googlecode.com/files/tamt-assign.zip.008
  * http://tamt.googlecode.com/files/tamt-assign.zip.009
  * http://tamt.googlecode.com/files/tamt-assign.zip.010
  * http://tamt.googlecode.com/files/tamt-assign.zip.011
  * http://tamt.googlecode.com/files/tamt-assign.zip.012
  * http://tamt.googlecode.com/files/tamt-assign.zip.013
  * http://tamt.googlecode.com/files/tamt-assign.zip.014
  * http://tamt.googlecode.com/files/tamt-assign.zip.015
  * http://tamt.googlecode.com/files/tamt-assign.zip.016
  * http://tamt.googlecode.com/files/tamt-assign.zip.017
  * http://tamt.googlecode.com/files/tamt-assign.zip.018
  * http://tamt.googlecode.com/files/tamt-assign.zip.019
  * http://tamt.googlecode.com/files/tamt-assign.zip.020
  * http://tamt.googlecode.com/files/tamt-assign.zip.021
  * http://tamt.googlecode.com/files/tamt-assign.zip.022
  * http://tamt.googlecode.com/files/tamt-assign.zip.023
  * http://tamt.googlecode.com/files/tamt-assign.zip.024
  * http://tamt.googlecode.com/files/tamt-assign.zip.025
  * http://tamt.googlecode.com/files/tamt-assign.zip.026
  * http://tamt.googlecode.com/files/tamt-assign.zip.027
  * http://tamt.googlecode.com/files/tamt-assign.zip.028
  * http://tamt.googlecode.com/files/tamt-assign.zip.029

Save each file to the same folder.

## Download HJSplit ##

To join the split files, you must use the HJSplit application found at http://www.freebyte.com/hjsplit/
There is a Windows, Linux or Java version available. Choose the version that's best for you.

## Join the files ##

  * Start HJSplit
  * Select "Join"
  * Use the file selector to choose tamt-assign.zip.001 (the rest will be found automatically if they are in the same folder)
  * For the output file, choose tamt-assign.zip in a new folder. You will need 3.5GB of space for this file.

## Unzip tamt-assign.zip ##

Use 7z, WinZip, or other archive program to unzip tamt-assign.zip

You will end up with a folder with these contents:

```
tamt-assign-2010-07-30.mf
tamt-assign-2010-07-30.ovf
ubuntu-10.04.vmdk
```

These files represent the appliance that will be imported into VirtualBox

## Download VirtualBox ##

  * Obtain the latest version of VirtualBox at http://www.virtualbox.org/wiki/Downloads
  * Download and install a version compatible with your computer, which will be the "host" for the TAMT Appliance

## Create a new virtual machine ##

  * Please ensure that your computer is still connected to the internet
  * Start VirtualBox
  * Choose New > Create new virtual machine
  * Enter Tamt as name, choose Linux as operating system and Ubuntu as Version
  * Use the recommended base memory size given by the dialog
  * Put check mark in “Boot hard disc”, select “use existing hard disc” and select the “ubuntu-10.04.vmdk” from the location in which tamt-assign.zip was unzipped.
  * Select “finish”
  * Using the “Settings” button, configure shared folder (and other options as later may be specified)

## Start TAMT Appliance ##

  * Start the TAMT Appliance from VirtualBox
  * Once booted, log in with username "tamt" and password "tamt"

## Start the TAMT application ##

This release of the TAMT application has been deployed to a web server (within the TAMT appliance), so the instructions for starting the TAMT application has changed.

On log in, double-click on the "TAMT" application with the World Bank icon on the middle of the screen. It will launch a web browser and take you to the home page of the TAMT application at: http://localhost:8080.

## Testing the Assign tool ##

### Create a study region ###

  * Draw a polygon around Accra (since that is where our test GPS points have been captured)
  * Be sure to include the area north west of the Ring Road where Winneba Road meets Dansoman, located here: http://goo.gl/maps/bxBn
  * Name the study region and check the box so that it is the current study region
  * Select the default zone type as Residential (should be selected by default)
  * Click Save

### Create a few tags ###

  * From the Tag screen (and the Tags panel), create a few tags
  * Name one of them HWY001
  * Name the other HWY002

### Create a few roads ###

Use the following screen shot to create the following roads:

![http://tamt.googlecode.com/files/double-carriage-way-nearest-of-near-roads-with-similar-bearing.png](http://tamt.googlecode.com/files/double-carriage-way-nearest-of-near-roads-with-similar-bearing.png)

  * Create a short road segment along Dansoman Road near Winneba Rd
  * Using the different map views (map, satellite and hybrid) create two routes along Winneba Rd using the centerlines of each side of the double-carriageway as your guide.
  * Name the roads: Dansoman, Winneba A, Winneba B and apply a tag to each road (it does not matter which)
  * Don't forget to save each road!

### Create a few zones ###

  * Switch to the Zones tab
  * Create a zone around the two Winneba Roads (not realistic, but good for the demo). Be sure to extend the zone further along Winneba Rd -- to the north-west and south-east. The point is to have the zone contain some of the points that aren't close enough to the roads you've drawn.
  * Name it the Winneba Commercial District and change the zone type to Commercial. Save it.
  * Create a zone around the Dansoman Road, but do not overlap the zone surrounding the Winneba routes. Again, extend the zone further that the road segment for Dansoman along south west line. This will capture some points that are along Dansoman, but not close enough to the road you have drawn.
  * Name it the Dansoman Residential District, select Residential zone type and save it

## Import / Assign GPS Records ##

With the study region, tags, roads and zones created, you are ready to import GPS records and assign each point to a road tag, a zone type, or the study region's default zone type.

### Import a GPS archive ###

  * Provide a name and (optional) description
  * Click 'browse' to locate a ZIP file containing GPS logs to upload
    * small.zip (`/home/tamt/Desktop/Data/gps/small.zip`) has just less than 8,000 records

### Assign the points ###

This is what you have been waiting for!

  * Click on the checkbox of the GPS archive you just uploaded
  * Click on the "Assign GPS Records" button above the list of GPS archives
  * A dialog box appears indicating that the assignment process is executing
  * Once the assignment process is complete, click OK

What happened?

After you clicked OK, the dialog box will close and the list will refresh, indicating how many GPS points were tagged along with the time they were tagged. Though not apparent to the user at this stage, all the points were either tagged with a road tag (if near enough to the roads you drew), or a zone type (if within the zones you drew) or with the default zone type for the study region (which you selected when you saved the study region).

Is there any way to check?

Well, not in the TAMT application. I have been considering adding a link to the number of tagged records which will pop up a dialog box and offer a basic report on the number of points which received road tags, zone types, or the default study region zone type. Captured as [Issue 21](https://code.google.com/p/tamt/issues/detail?id=21) in the issue tracker.

## Demo (watch or play) ##

You can operate Assign yourself through the browser you opened onto the application, or you can watch the Assign demo at:

  * Part 1: http://www.youtube.com/watch?v=7dVWIKafmSE
  * Part 2: http://www.youtube.com/watch?v=jsZInYVxHj8