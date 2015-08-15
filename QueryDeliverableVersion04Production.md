# Introduction #

We have a new way to download updates to TAMT if you have already downloaded the latest virtual machine. Rather than downloading the entire virtual machine again, you can follow these shortcut instructions to download and install the latest application and database.

## Media ##

  * The installation instructions below are also demonstrated on this screencast: [The World Bank TAMT Project: Analysis Application (DEMO Part 1)](http://www.youtube.com/watch?v=pQWebSpGySg)
  * The Query Module (now called the Analysis Module) screencast is available here: [The World Bank TAMT Project: Analysis Application (DEMO Part 2)](http://www.youtube.com/watch?v=vjn69XHr724)

Note: when opened in a new window you can switch to HD and go full-screen for the best viewing quality

# Query Production Release #

The "production" release is kind of a misnomer right now, because Query is only partially completed. We mean "production" because you are not downloading the entire VM with all the dev tools, etc (because you probably already have).

## Get the release ##

  1. Login to the TAMT virtual machine
  1. Open your browser (Applications > Internet > Google Chrome OR Firefox)
  1. Download the archive http://tamt.googlecode.com/files/tamt-query-production-201008241659.zip
  1. Unzip the archive. Depending on what browser you used, the downloaded file might be in /home/tamt/Downloads or on your desktop. To unzip it, right click and select the first context menu item: "Open with Archive Manager"
  1. A new window opens to show the contents of the archive. Drag the tamt-query-production folder to your Desktop. Close the Archive Manager window.
  1. Open the tamt-query-production folder on your Deskop. It contains two files: ROOT.war and tamt15\_201008241617.backup

## Stop the application server ##

  1. Open your browser to application server console at http://localhost:8080/manager/html
  1. Username is `tamt` and password is `tamt`
  1. In the first row of Applications, you'll see "TAMT Application"
  1. In this row, but on the far right, you'll see a set of commands: Start, Stop, Reload, Undeploy.
  1. Click Stop, then click OK
  1. Click Undeploy, then click OK
  1. You have just deleted the previous TAMT application

Keep this window open (minimize it if you prefer). After you upgrade the database, you will come back to this application server console to redeploy TAMT.

## Upgrade the database schema ##

The tamt15\_201008241617.backup file is a backup of the PostGIS database. It is an empty database, but has all the schema changes made for this release of Query. You will be using this file to wipe out the previous database and schema and preparing the database for the new application.

  1. From your Desktop, open the Applications folder (the folder on your desktop, not the menu item at the top of the screen)
  1. Open pgAdminIII
  1. In the tree on the left, double-click on tamt (localhost:5432)
  1. Under the Databases node, find the tamt15 database
  1. Right-click on the tamt15 database select Delete/Drop. Click OK to confirm. The database is deleted and the tree refreshes.
  1. Right-click on Databases and select New Database...
  1. A new window appears. Name the database `tamt15` (that tamt-one-five) and select `gis` in the Owner drop-down menu. Click OK
  1. The tree refreshes again, and a new tamt15 database appears.
  1. Right-click on the tamt15 database and select Restore. A new window appears
  1. Click the ... button next to the Filename box to find and select the tamt15\_201008241617.backup file. It should be on your Desktop/tamt-query-production. Select the file and click Open
  1. Make sure the only checkbox that is checked is Verbose messages
  1. Click OK
  1. After a pause, a number of messages will fill the message window. The last message should be `Process returned exit code 0.` Click Done.
  1. Close the main pgAdminIII window

You have just upgraded the database for the TAMT Query application. Now you will deploy the new version of TAMT 0.4 which has the first part of the Query module complete.

## Deploy the new application ##

  1. Return to your browser window. If you closed it, return to http://localhost:8080/manager/html
  1. Scroll down to the Deploy section of the admin console
  1. You will use the second deploy option: "WAR file to deploy"
  1. Click Choose File and navigate to the folder on your Desktop/tamt-query-production. Select the ROOT.war file and click Open. You are returned to the admin console with the ROOT.war file ready for deployment.
  1. Click Deploy. The ROOT.war file is uploaded, deployed, and started.
  1. Scroll back up to the Applications section
  1. The first application should be TAMT Application
  1. Now open a new tab or window and go to http://localhost:8080/

You have deployed the TAMT Application and are ready to use the new features of the Query module. Note that in this release we have renamed Query to Analysis on the left menu of the main TAMT screen.