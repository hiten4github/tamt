### Background ###

Because TAMT has many component libraries or frameworks (python + UI toolkits, PostGIS, etc), it will be necessary to have either a) very technical people install all of the required components or b) a very simple distribution of all required components. If TAMT installation is unsuccessful because of a failed installation of a 3rd party component, then we didn't do our jobs. However, the competing factor to simplified distribution is low latency / low technology at the client site. These two factors lead me to recommend a virtualized distribution mechanism.


### VirtualBox ###

Sun's [VirtualBox](http://www.virtualbox.org/) is a robust virtualization product that allows a host operating system to run a number of guest operating systems. While detailing all of VirtualBox's features is beyond the scope of this explanation, I note that VirtualBox configurations can be published and shared with other VirtualBox users. Thus, a client that wants to install TAMT could be running Windows, Mac, Linux or Unix, and be able to open the TAMT virtual appliance on their system and run the entire software package. This setup reduces the risk of misconfigured databases and improperly installed libraries.