

&lt;hr/&gt;


**Topics on this page**



&lt;hr/&gt;



# TAMT Appliance #

## Operating System ##

The TAMT Appliance runs Ubuntu 10.04, the latest stable Ubuntu release as of the start of the TAMT project. Other Linux distributions were investigated (Linux Mint, Puppy Linux) but the standard Ubuntu distribution was favored for its stability and hardware / software support.

No software development is required to deploy Ubuntu as the operating system for the TAMT Appliance.

## Database ##

The TAMT Platform requires a spatially enabled database. The defacto choice in open-source for a robust, spatial database is PostgreSQL with the PostGIS extensions. The TAMT Appliance will have PostgreSQL 8.4, PostGIS 1.5.1 and pgAdmin (a desktop administration tool for PostgreSQL) installed. Support for custom stored procedures that will potentially be used by TAMT Platform Applications (such as Assign) require a choice in stored procedure language.

Since Java is already being used for the bulk of the TAMT software component development, the PostgreSQL database will also have PL/Java installed and configured. Additionally, a the pgAdmin3 desktop GUI

Installation and configuration of PostgreSQL, PostGIS, pgAdmin3 and PL/Java will require minimal effort.

No software development is required to deploy PostgreSQL, PostGIS, or PL/Java as the database layer for the TAMT Appliance.

## Web container ##

The TAMT Platform will be a suite of web-based applications running in the TAMT Appliance. The web container (an HTTP server and a Java servlet container) will be Apache Tomcat 6.0, which supports the Java Servlet 2.5 and Java Server Pages (JSP) 2.1 standards.

Installation of Apache Tomcat will minimal effort, however, configuration of the web container will depend on TAMT Platform configuration and performance requirements.

No software development is required to deploy the web container for the TAMT Appliance.

## Programming language ##

Several programming languages were evaluated, but Java 1.5 was chosen as the preferred software development kit (SDK) because of excellent third party software and library availability. Java is the language for Google Web Toolkit (a major component of the TAMT Platform application development) and JOSM, which is key to the Tag application.

No software development is required to deploy the Java 1.5 SDK for the TAMT Appliance.

## Development environment ##

The TAMT Appliance will be deployed to run the TAMT Platform, but it will also contain the open-source Eclipse integrated development environment (IDE). The Google Plugin for Eclipse will be included in the IDE, along with a number of other plugins to aid in the software development process.

TAMT Platform applications be developed in Eclipse from within the TAMT Appliance, adding a measure of confidence in the development versus the runtime environment for TAMT Platform applications and ensuring continuity for future deployments. Other developers may download the TAMT Appliance and begin development on new TAMT Platform features.

Developers who choose to add or modify features of the TAMT Platform applications may use Eclipse to connect to the TAMT Google Code repository, checkout code, and rebuild the platform. This is an optional activity and is not intended for general use of the TAMT system. Please see the GettingStartedForDevelopers guide.

No software development is required to deploy the Eclipse IDE for the TAMT Appliance.

## Internet browsers ##

The Google Chrome and Mozilla Firefox Internet browsers will be installed on the TAMT Appliance. In a single-system deployment, these browsers can be used from within the appliance to operate the applications in the TAMT Platform. If users choose, they may also operate browsers from the computer hosting the appliance (in the host computer's own operating system instead of the appliance's guest operating system), or from another computer than can reach the appliance over HTTP.

# TAMT Platform #

## Third-Party Components ##

The TAMT Platform leverages open-source technologies to build the suite of TAMT applications.

### Object-relational mapping system ###

  * Hibernate

### Spatial libraries ###

  * Java Topology Suite (JTS)
  * Hibernate Spatial

### Other libraries ###

  * Google Web Toolkit (GWT) Remote Procedure Call (RPC) mechanism
  * Google API Libraries for Google Web Toolkit

### Mapping utilities ###

  * JOSM (Java OpenStreetMap application)
  * Google Earth

# TAMT Applications #

(Note: links below go to detailed designs for each application)

  * [Tag](DetailedSystemDesignTag.md) is the TAMT application that allows a user to classify a road network.
  * [Import](DetailedSystemDesignImport.md) is the TAMT application that allows a user to import tagged road networks and GPS data from the field.
  * [Assign](DetailedSystemDesignAssign.md) is the TAMT application that allows a user to trigger a long-running task to assign each GPS data point to its nearest road network, and assume the tagged road network attributes of that classification.
  * [Query](DetailedSystemDesignQuery.md) is the TAMT application that allows a user to query the database to prepare data for export.
  * [Export](DetailedSystemDesignExport.md) is the TAMT application that allows a user to export queried data for use in the greenhouse gas emissions model.