# Purpose #

The TAMT system is a suite of web applications that support the collection and pre-processing of vehicle GPS data for analysis in a greenhouse gas emissions model.

# Design #

The TAMT applications run in a local virtual machine installed on any desktop system that support the Oracle VirtualBox client. This configuration minimizes end-user set up and ensures proper configuration of the TAMT architecture and components.

The TAMT applications are developed in Java using the Google Web Toolkit, a sophisticated framework "for building and optimizing complex browser-based applications" http://code.google.com/webtoolkit/

# System Overview #

![http://tamt.googlecode.com/hg/design/uml/design-system-overview-complete.png](http://tamt.googlecode.com/hg/design/uml/design-system-overview-complete.png)

# Contents #

  * [Introduction](DesignDocIntroduction.md)
    * [Terms of Reference (TOR)](DesignDocTermsOfReference.md)
  * [System Overview](DesignDocSystemOverview.md)
  * [Design Considerations](DesignDocDesignConsiderations.md)
    * [Assumptions](DesignDocDesignConsiderations#Assumptions.md)
    * [Dependencies](DesignDocDesignConsiderations#Dependencies.md)
    * [General constraints](DesignDocDesignConsiderations#General_constraints.md)
    * [Goals and guidelines](DesignDocDesignConsiderations#Goals_and_guidelines.md)
    * [Development methods](DesignDocDesignConsiderations#Development_methods.md)
  * [System Architecture](DesignDocSystemArchitecture.md)
    * [TAMT System](DesignDocSystemArchitecture#TAMT_System.md)
    * [TAMT Appliance](DesignDocSystemArchitecture#TAMT_Appliance.md)
    * [TAMT Platform](DesignDocSystemArchitecture#TAMT_Platform.md)
    * [TAMT Applications](DesignDocSystemArchitecture#TAMT_Applications.md)
  * [Detailed System Design](DesignDocDetailedSystemDesign.md)
    * [TAMT Appliance](DesignDocDetailedSystemDesign#TAMT_Appliance.md)
      * [Operating System](DesignDocDetailedSystemDesign#Operating_System.md)
      * [Database](DesignDocDetailedSystemDesign#Database.md)
      * [Web container](DesignDocDetailedSystemDesign#Web_container.md)
      * [Programming language](DesignDocDetailedSystemDesign#Programming_language.md)
      * [Development environment](DesignDocDetailedSystemDesign#Development_environment.md)
      * [Internet browsers](DesignDocDetailedSystemDesign#Internet_browsers.md)
    * [TAMT Platform](DesignDocDetailedSystemDesign#TAMT_Platform.md)
      * [Third-Party Components](DesignDocDetailedSystemDesign#Third-Party_Components.md)
        * [Object-relational mapping system](DesignDocDetailedSystemDesign#Object-relational_mapping_system.md)
        * [Spatial libraries](DesignDocDetailedSystemDesign#Spatial_libraries.md)
        * [Other libraries](DesignDocDetailedSystemDesign#Other_libraries.md)
        * [Mapping utilities](DesignDocDetailedSystemDesign#Mapping_utilities.md)
    * [TAMT Applications](DesignDocDetailedSystemDesign#TAMT_Applications.md)
      * [Tag](DetailedSystemDesignTag.md)
      * [Import](DetailedSystemDesignImport.md)
      * [Assign](DetailedSystemDesignAssign.md)
      * [Query](DetailedSystemDesignQuery.md)
      * [Export](DetailedSystemDesignExport.md)
  * [Glossary](DesignDocGlossary.md)
  * [Bibliography](DesignDocBibliography.md)