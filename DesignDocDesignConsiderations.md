

&lt;hr/&gt;


**Topics on this page**



&lt;hr/&gt;



Both TAMT Platform and TAMT Appliance have design considerations which need to be addressed in order to devise a complete design solution.

<a href='Hidden comment: 
Describe any assumptions or dependencies regarding the software and its use. These may concern such issues as:
Related software or hardware
Operating systems
End-user characteristics
Possible and/or probable changes in functionality
'></a>

# Assumptions #

## Users ##

  * Users of the TAMT appliance, platform and applications will be technically competent. While the goal of the appliance is to provide a system with little or no configuration, some general knowledge of web application servers and databases will be helpful
  * Users may require system administration privileges on their own computers to install VirtualBox and the TAMT Appliance. A network must be available to the TAMT appliance so the TAMT Tag application can connect to the Internet.

## Software ##

  * All third-party software is either free or open-source or both. This includes the entire TAMT Appliance and all software contained in it.
  * The TAMT Applications are all web-based. The only _slight_ exception being Tag, which opens the JOSM application via Web Start (and therefore runs JOSM as a desktop Java application).

# Dependencies #

  * The TAMT Appliance is dependent on running within the Oracle VM VirtualBox on a system that has enough resources (in memory and disk space).
  * The TAMT Applications are web-based, but they reside in the local virtual machine. However, the TAMT Tag Application launches JOSM, which requires a network connection to download OSM data for the study area.

# General constraints #

  * Users cannot configure a complex web application server
  * Users are potentially limited by low-bandwidth networks, so most of the work must be done on a local machine (i.e., inside the TAMT Appliance virtual machine).

# Goals and guidelines #

  * Easy installation of the TAMT Appliance, with the TAMT Platform and Applications pre-installed and running.
  * Able to operate effectively in a low-bandwidth network environment.
  * Easy to configure, query and export GPS trace data for analysis in a separate GHG modelling software application

# Development methods #

  * Iterative design
  * Object-oriented
  * Develop and deploy on the same virtual machine