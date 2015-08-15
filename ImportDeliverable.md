## Download "split" files ##

The TAMT Appliance is a 3GB VirtualBox virtual machine. In order to host the appliance on this website, it was necessary to split the 3GB file into a number of smaller files (20 files at ~158MB each).

Each of these files needs to be downloaded, then "joined" using the HJSplitter application

The files are:

  * http://tamt.googlecode.com/files/tamt-import.zip.001
  * http://tamt.googlecode.com/files/tamt-import.zip.002
  * http://tamt.googlecode.com/files/tamt-import.zip.003
  * http://tamt.googlecode.com/files/tamt-import.zip.004
  * http://tamt.googlecode.com/files/tamt-import.zip.005
  * http://tamt.googlecode.com/files/tamt-import.zip.006
  * http://tamt.googlecode.com/files/tamt-import.zip.007
  * http://tamt.googlecode.com/files/tamt-import.zip.008
  * http://tamt.googlecode.com/files/tamt-import.zip.009
  * http://tamt.googlecode.com/files/tamt-import.zip.010
  * http://tamt.googlecode.com/files/tamt-import.zip.011
  * http://tamt.googlecode.com/files/tamt-import.zip.012
  * http://tamt.googlecode.com/files/tamt-import.zip.013
  * http://tamt.googlecode.com/files/tamt-import.zip.014
  * http://tamt.googlecode.com/files/tamt-import.zip.015
  * http://tamt.googlecode.com/files/tamt-import.zip.016
  * http://tamt.googlecode.com/files/tamt-import.zip.017
  * http://tamt.googlecode.com/files/tamt-import.zip.018
  * http://tamt.googlecode.com/files/tamt-import.zip.019
  * http://tamt.googlecode.com/files/tamt-import.zip.020

Save each file to the same folder.

## Download HJSplit ##

To join the split files, you must use the HJSplit application found at http://www.freebyte.com/hjsplit/
There is a Windows, Linux or Java version available. Choose the version that's best for you.

## Join the files ##

  * Start HJSplit
  * Select "Join"
  * Use the file selector to choose tamt-tag.zip.001 (the rest will be found automatically if they are in the same folder)
  * For the output file, choose tamt-tag.zip in a new folder. You will need 3GB of space for this file.

## Unzip tamt-import.zip ##

Use 7z, WinZip, or other archive program to unzip tamt-tag.zip

You will end up with a folder with these contents:

```
tamt-import-2010-07-12.mf
tamt-import-2010-07-12.ovf
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
  * Put check mark in “Boot hard disc”, select “use existing hard disc” and select the “ubuntu-10.04.vmdk” from the location in which tamt-import.zip was unzipped.
  * Select “finish”
  * Using the “Settings” button, configure shared folder (and other options as later may be specified)

<a href='Hidden comment: 

jarogers had issues when following the import process below for Tag, so he provided the "Create a new virtual machine" steps provided above for the TagDeliverable. I have kept this comment in the ImportDeliverable page

== Import the TAMT Appliance ==

* Start VirtualBox
* Choose File > Import Appliance (these instructions may vary slightly depending on the version of VirtualBox you are using)
* Select the tamt-tag-2010-06-22.ovf file from where you unzipped tamt-tag.zip
* The current appliance is set to use 2GB of memory. You may change this if your host computer has more or less memory.
* Click done to being the import.

'></a>

## Start TAMT Appliance ##

  * Start the TAMT Appliance from VirtualBox
  * Once booted, log in with username "tamt" and password "tamt"

## Start the TAMT application ##

Because we are still in development, we will run Import from the development environment within Eclipse.

  * Double-click on the Eclipse icon on the desktop.
  * Once Eclipse opens, choose Run > Run Configurations
  * A window appears. In the left window pane, scroll until you see Web Application.
  * Open the arrow beside Web Application until you see TransportActivityMeasureToolkit
  * Select TransportActivityMeasureToolkit and click Run (bottom right of open dialog box)
  * Minimize (but don't exit) Eclipse
  * From the desktop, start Firefox and go to: http://127.0.0.1:8888/TransportActivityMeasurementToolkit.html?gwt.codesvr=127.0.0.1:9997
  * Depending on the speed of your computer, and how much memory you allocated the TAMT Appliance, the TAMT web application appears. The Tag tab is selected by default. (Note: because we are running in development mode, it may take some time for Tag loads properly. The execution of the program will speed up once we run the TAMT applications outside of development mode.

## Testing the Import tool ##

There isn't much to see in the Import tool as there is in Tag. However, a few test files have been provided to import so you can watch the import process.

From the Import tab:

  * Provide a name and (optional) description
  * Click 'browse' to locate a ZIP file containing GPS logs to upload
    * small.zip (`/home/tamt/Desktop/gps/small.zip`) has just less than 8,000 records
    * large.zip (`/home/tamt/Desktop/gps/large.zip`) has almost 175,000 records
    * nongps.zip (`/home/tamt/Desktop/gps/nongps.zip`) is a ZIP file that contains two text files that are NOT GPS logs. This is used to test the case of uploading a zip file with incorrect contents.
    * nongps.DAT (`/home/tamt/Desktop/gps/nongps.DAT`) is a proper GPS log file, but log files cannot be uploaded into Import without being contained in a ZIP archive. This file is used to test that case.

The `small.zip` and `large.zip` will import correctly and report the number of GPS records along with the date the records were imported. The `nongps.*` files will provide error messages that they could not be imported. Note: the large.zip file took about a minute to import on a development machine.

## Required GPS format ##

The log files in the ZIP archive must be text files in a specific NMEA format. TAMT Import requires field users to configure the GPS loggers to record only the RMC and GGA NMEA sentences. A sample is provided:

```
                             GD30L MMC DATA LOGGER
+-----------------------------------------------------------------------------+
  UPDATE rate: 01 second(s)
  RECORD sentence(s): RMC, GGA
+-----------------------------------------------------------------------------+
$GPRMC,150002.360,A,0533.8705,N,00014.0790,W,24.85,121.40,220410,,*2E
$GPGGA,150002.360,0533.8705,N,00014.0790,W,1,09,0.8,38.6,M,20.4,M,0.0,0000*5D
$GPRMC,150004.360,A,0533.8631,N,00014.0669,W,25.44,121.08,220410,,*29
$GPGGA,150004.360,0533.8631,N,00014.0669,W,1,09,0.8,38.5,M,20.4,M,0.0,0000*59
$GPRMC,150005.360,A,0533.8593,N,00014.0608,W,25.57,121.93,220410,,*24
<... more records ...>
```

A single GPS point in TAMT Import is derived from a pair of lines. GPRMC provides latitude, longitude, bearing and speed. The line following each GPRMC entry is the GPGGA sentence, which provides altitude. If any of the data points are missing, the entire pair is ignored and processing proceeds to the next two-line pair.

If any other NMEA sentences are recorded in the log file, or if the RMC and GGA sentences are not in the correct order, processing the records may fail.

## Storing and processing imported data ##

The records are imported into the PostGIS database with the following attributes:

  * The ZIP file is stored directly in the database with an ID and date created field
  * The GPS trace meta data (name, description) are stored in a separate table
  * After successful upload, the ZIP file is processed, creating individual records for GPS points. These points have latitude, longitude, bearing, speed, and altitude, and are related to the GPS trace information provided during the upload process.

<a href='Hidden comment: 

== Demo (watch or play) ==

You can operate Tag yourself through the browser you opened onto the application, or you can watch the Tag demo at http://www.youtube.com/watch?v=tt-15Hnb054

'></a>