### Overview ###

The Tag component allows a user to assign a "road section type" to each homogenous segment of the road network that covers the study area. Tagging is required in order to analyze GPS vehicle data against road section types as part of the GHG model.

### Data issues related to tagging ###

There are a number of alternative technologies that we may use to tag the road network. There are trade-offs for each option.

  1. Google Maps - Using the Google Maps "My Maps" feature, a user may create their own map and add their own segment overlay to the existing road network. It is not clear how much coverage Google Maps has internationally, or what resolution ("zoom factor") is available for accurately creating a segment overlay. Certainly this works in North America, but some investigation is needed for other locales. Study region extents would need to be known prior to creating a custom My Map. Custom tags would have to be documented and applied correctly to road segments -- possibility for error is high.<br /><br />
  1. Open Street Map - Using the online Potchlatch editor or the offline JOSM editor. JOSM can read NMEA files directly, but may be too complicated for an average end user to tag segments of a road network. Connecting to a WMS server for aerial imagery may be a complicated setup (which lends itself well to the option of a VirtualBox distribution, but may increase the initial download). Potlatch online has Yahoo aerial imagery built in and allows you to create custom tags. A user can download OSM data with their tags, and [convert it to GML](http://wiki.openstreetmap.org/wiki/Converting_OSM_to_GML) (which can then be converted to various formats for proper input to PostGIS).

### Ideas on how to tag ###

  * [JavaOpenStreetMapEditor](JavaOpenStreetMapEditor.md)