## Download "split" files ##

The TAMT Appliance is a 3GB VirtualBox virtual machine. In order to host the appliance on this website, it was necessary to split the 3GB file into a number of smaller files (21 files at ~154MB each).

Each of these files needs to be downloaded, then "joined" using the HJSplitter application

The files are:

  * http://tamt.googlecode.com/files/tamt-tag.zip.001
  * http://tamt.googlecode.com/files/tamt-tag.zip.002
  * http://tamt.googlecode.com/files/tamt-tag.zip.003
  * http://tamt.googlecode.com/files/tamt-tag.zip.004
  * http://tamt.googlecode.com/files/tamt-tag.zip.005
  * http://tamt.googlecode.com/files/tamt-tag.zip.006
  * http://tamt.googlecode.com/files/tamt-tag.zip.007
  * http://tamt.googlecode.com/files/tamt-tag.zip.008
  * http://tamt.googlecode.com/files/tamt-tag.zip.009
  * http://tamt.googlecode.com/files/tamt-tag.zip.010
  * http://tamt.googlecode.com/files/tamt-tag.zip.011
  * http://tamt.googlecode.com/files/tamt-tag.zip.012
  * http://tamt.googlecode.com/files/tamt-tag.zip.013
  * http://tamt.googlecode.com/files/tamt-tag.zip.014
  * http://tamt.googlecode.com/files/tamt-tag.zip.015
  * http://tamt.googlecode.com/files/tamt-tag.zip.016
  * http://tamt.googlecode.com/files/tamt-tag.zip.017
  * http://tamt.googlecode.com/files/tamt-tag.zip.018
  * http://tamt.googlecode.com/files/tamt-tag.zip.019
  * http://tamt.googlecode.com/files/tamt-tag.zip.020
  * http://tamt.googlecode.com/files/tamt-tag.zip.021

Save each file to the same folder.

## Download HJSplit ##

To join the split files, you must use the HJSplit application found at http://www.freebyte.com/hjsplit/
There is a Windows, Linux or Java version available. Choose the version that's best for you.

## Join the files ##

  * Start HJSplit
  * Select "Join"
  * Use the file selector to choose tamt-tag.zip.001 (the rest will be found automatically if they are in the same folder)
  * For the output file, choose tamt-tag.zip in a new folder. You will need 3GB of space for this file.

## Unzip tamt-tag.zip ##

Use 7z, WinZip, or other archive program to unzip tamt-tag.zip

You will end up with a folder with these contents:

```
tamt-tag-2010-06-22.mf
tamt-tag-2010-06-22.ovf
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
  * Put check mark in “Boot hard disc”, select “use existing hard disc” and select the “ubuntu-10.04.vmdk” from the location in which tamt-tag.zip was unzipped.
  * Select “finish”
  * Using the “Settings” button, configure shared folder (and other options as later may be specified)

<a href='Hidden comment: 

jarogers had issues when following the import process below, so he provided the "Create a new virtual machine" steps provided above

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

## Start Tag ##

Because we are still in development, we will run Tag from the development environment within Eclipse.

  * Double-click on the Eclipse icon on the desktop.
  * Once Eclipse opens, choose Run > Run Configurations
  * A window appears. In the left window pane, scroll until you see Web Application.
  * Open the arrow beside Web Application until you see TransportActivityMeasureToolkit
  * Select TransportActivityMeasureToolkit and click Run (bottom right of open dialog box)
  * Minimize (but don't exit) Eclipse
  * From the desktop, start Firefox and go to: http://127.0.0.1:8888/TransportActivityMeasurementToolkit.html?gwt.codesvr=127.0.0.1:9997
  * Depending on the speed of your computer, and how much memory you allocated the TAMT Appliance, the TAMT web application appears. The Tag tab is selected by default. (Note: because we are running in development mode, it may take some time for Tag loads properly. The execution of the program will speed up once we run the TAMT applications outside of development mode.

## Demo (watch or play) ##

You can operate Tag yourself through the browser you opened onto the application, or you can watch the Tag demo at http://www.youtube.com/watch?v=tt-15Hnb054