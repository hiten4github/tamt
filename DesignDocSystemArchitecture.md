

&lt;hr/&gt;


**Topics on this page**



&lt;hr/&gt;



# The TAMT System #

The TAMT system requires a user to downoad and install the Oracle VirtualBox client software and the TAMT Appliance (described below). The overall system deployment looks like this:

![http://tamt.googlecode.com/hg/design/uml/deployment-system-overview.png](http://tamt.googlecode.com/hg/design/uml/deployment-system-overview.png)


---


# TAMT Appliance #

The TAMT Appliance is a virtual machine packaged as a single file and deployed on client computers.

![http://tamt.googlecode.com/hg/design/uml/deployment-tamt-appliance.png](http://tamt.googlecode.com/hg/design/uml/deployment-tamt-appliance.png)

## Overview ##

The TAMT Appliance is a virtual machine created with VirtualBox. It is a single disk image containing the Ubuntu operating system pre-installed with all of the open-source packages and libraries required to support the TAMT Platform software, including:

  * Operating system: Ubuntu 10.04
  * Database: PostgreSQL with PostGIS extensions and PL/Java for stored procedures; pgAdmin3 as database administration user interface
  * Web Container: Apache Tomcat 6.0 - HTTP server and Servlet container supporting Java Servlet 2.5 and JavaServer Pages (JSP) 2.1.
  * Software development kits: Java 1.5
  * Integrated development environments: Eclipse 3.5.1 with Google Plugin for Eclipse
  * Internet browsers: Google Chrome, Mozilla Firefox

## Appliance Development ##

A note of clarification: there is no software "development" required for the TAMT Appliance. It is an instance of a  virtual operating system created with VirtualBox (which is itself an open-source software project). For architecture, design and defect issues relating to TAMT Appliance, we are referring to pre-packaged software installation and configuration, not software development.

Therefore, the issue tracker below is not tracking issues with the development of the VirtualBox software, but it is tracking installation and configuration issues within the TAMT Appliance (for example, the installation of the TAMT Platform, PostgreSQL, PostGIS, etc.)

It is anticipated once the TAMT Platform is successfully integrated in the TAMT Appliance, the only requirement for the end-user is to download the TAMT Appliance, import the virtual machine to the VirtualBox client on the host computer, and then within the VM run the TAMT Platform Applications (which are all pre-installed).

## Issue tracker for TAMT Appliance ##

All [issues](http://code.google.com/p/tamt/issues/list?q=can=1&label:Product-Appliance) pertaining to the TAMT Appliance


---


# TAMT Platform #

The TAMT Platform is an open-source stack to support the TAMT Applications suite and the TAMT Dev tools.

![http://tamt.googlecode.com/hg/design/uml/deployment-tamt-platform.png](http://tamt.googlecode.com/hg/design/uml/deployment-tamt-platform.png)

  * Web browsers: Google Chrome, Mozilla Firefox
  * Java 1.5 SDK
  * Apache Tomcat
  * PostgreSQL with PostGIS

## Issue tracker for TAMT Platform ##

All [issues](http://code.google.com/p/tamt/issues/list?can=1&q=label:Product-Platform) pertaining to TAMT Platform


---


# TAMT Dev Tools #

The TAMT Dev Tools are included in the TAMT Appliance so future development is supported right in the virtual machine.

![http://tamt.googlecode.com/hg/design/uml/deployment-tamt-dev.png](http://tamt.googlecode.com/hg/design/uml/deployment-tamt-dev.png)

  * Eclipse 3.5 IDE (configured with Mercurial plugin to TAMT source repository at Google Code)
  * Inkscape - vector illustration package for design diagrams
  * OpenOffice.org suite for document editing
  * pgAdmin III for administering the PostgreSQL database
  * Quantum GIS for viewing / manipulating GIS files

## Issue tracker for TAMT Dev Tools ##

All [issues](http://code.google.com/p/tamt/issues/list?q=can=1&label:Product-DevTools) pertaining to the TAMT Dev Tools


---


# TAMT Applications #

![http://tamt.googlecode.com/hg/design/uml/deployment-tamt-applications.png](http://tamt.googlecode.com/hg/design/uml/deployment-tamt-applications.png)

  * [Tag](DetailedSystemDesignTag.md) allows a user to classify a road network.
  * [Import](DetailedSystemDesignImport.md) allows a user to import tagged road networks and GPS data from the field.
  * [Assign](DetailedSystemDesignAssign.md) allows a user to trigger a long-running task to assign each GPS data point to its nearest road network, and assume the attributes of that network segment.
  * [Query](DetailedSystemDesignQuery.md) allows a user to query the database to prepare data for export.
  * [Export](DetailedSystemDesignExport.md) allows a user to export queried data for use in the greenhouse gas emissions model.


## Issue tracker for TAMT Applications ##

All [issues](http://code.google.com/p/tamt/issues/list?q=can=1&label:Product-Applications) pertaining to the TAMT Applications