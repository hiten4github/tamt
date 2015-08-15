

&lt;hr/&gt;


**Topics on this page**



&lt;hr/&gt;



# Purpose of this document #

The purpose of this document is to provide documentation for the design and implementation of the Transport Activity Measurement Toolkit, or TAMT (pronounced "tam T"). Both high-level requirements and implementation details are documented here to ensure successful completion of the project and continuity for future project development.

# Scope of this document #

This document is intended to be a detailed design supplement to the _Terms of Reference for the development of software tools for the Transport Activity Measurement Toolkit_ (TOR), and therefore does not replace it. This document will provide detailed specifications for designing, implementing, and configuring software for TAMT, but will not include end-user documentation for TAMT. As a software design document,  it will not provide significant details regarding the greenhouse gas emissions models which will operate on the output of the TAMT software, nor will it provide details on the practitioner's guide outlined in the TOR.

# Intended audience #

The intended audience of this specification includes project managers from the World Bank and software developers that may use or extend TAMT in the future. Details in this document may be helpful for technically-oriented end-users of the TAMT software.

# Product identification #

The entire system produced by this project should be referred to as the Transport Activity Measurement Toolkit (or TAMT, pronounced "tam T").

A major-minor release identification number (e.g. version x.y) will be used to track the completion of software features. X refers to the major release. Y refers to the minor release. During development the major release will be '0' (zero), indicating a beta or incomplete package. As features are added the minor release will increment (e.g. 0.1 to 0.2 indicates a certain level of feature completion).

## TAMT component release names ##

The completed release under this project will be TAMT 1.0. It is comprised of two releases: the TAMT Platform and the TAMT Appliance.

## _TAMT Platform_ ##

The TAMT Platform is the software that allows users to collect, manipulate, and output GPS data on road networks for their study area. TAMT Platform will adhere to the same incremented major-minor definition.

For more information on this component, please review the [TAMT Platform system architecture](DesignDocSystemArchitecture#TAMT_Platform.md).

## _TAMT Appliance_ ##

The TAMT Appliance is a standards-compliant x86 virtualization distribution based on the Oracle/Sun VirtualBox software package. The TAMT Appliance is a single file that packages the Ubuntu operating system and the TAMT Platform to allow TAMT to be run on any system that supports the VirtualBox client software (Windows, Mac OS X, various Linux distributions).

The TAMT Appliance will adhere the same major-minor definition, but these release numbers will likely not coincide with releases of TAMT Platform. For instance, the TAMT Appliance release 0.2 might provide all of the operating system and software libraries for the Tag, Assign, and Import features of the TAMT Platform. The TAMT Platform at that point in development might be at release 0.3, but the TAMT Appliance would only need to be at release 0.2. However, prior to  the final TAMT 1.0 release, all TAMT Platform and TAMT Appliance releases will be synchronized to 1.0 as well. Future development may re-introduce asynchronous releases based on differing requirements for the Platform or the Appliance.

For more information on this component, please review the [TAMT Appliance system architecture](DesignDocSystemArchitecture#TAMTAppliance.md).

# Reference documents #

## Terms of Reference ##
  * [TAMT Terms of Reference](DesignDocTermsOfReference.md)
## Other ##
  * [VirtualBox](http://en.wikipedia.org/wiki/VirtualBox) description on Wikipedia

# Important terms, acronyms, or abbreviations #

  * TAMT - Transport Activity Measurement Toolkit
  * TOR - Terms of Reference for the development of software tools for the Transport Activity Measurement Toolkit
  * TAMT Platform - the software that makes up the TAMT suite of applications
  * TAMT Appliance - the virtualized operating system that hosts TAMT Platform

# Summary #

This introduction has provided the purpose, scope and some introductory details regarding the TAMT software. For more information, please see the [system overview](DesignDocSystemOverview.md) and other related pages of the design document.