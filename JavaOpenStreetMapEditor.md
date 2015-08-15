### JOSM Overview ###

JOSM is the Java OpenStreetMap editor. It is a downloadable Java archive (JAR) file that can be run on cross-platform computer systems that have Java 1.5 installed. JOSM lives at http://josm.openstreetmap.de/

### How JOSM can be used for TAMT ###

  * Download the JOSM jar
  * Run it
  * Use JOSM to get road network data for a selected area of the globe
  * Save the JOSM layer to a local .osm file (an XML file formatted for OSM data)
  * Add TAMT tags to existing roads in the local OSM file
  * Use osm2pgsql script to import into PostGIS database

### Issues ###

  * JOSM requires Java 1.5 to be installed on the host machine
  * There is limited [international language support for JOSM](https://translations.launchpad.net/josm/trunk/+pots/josm)
  * May require specific skills to download, run, tag, save, and convert JOSM software and/or data

### Workaround ###

  * There is a mechanism to run [JOSM from a USB stick](http://wiki.openstreetmap.org/wiki/JOSM/HOWTO/Run_from_flash_disk_with_Java) but it requires a number of other applications, and some detailed setup. This kind of installation is error prone.
  * If VirtualBox is used, a lightweight Java Runtime Engine (JRE) could easily be pre-installed in the virtual OS.