### Overview ###

GPS data will have already been collected in the field and supplied in a comma-separated file (CSV). This file will be in the [NMEA-0183 format](http://en.wikipedia.org/wiki/NMEA_0183). With the overall requirement of operating in a low-latency environment, we are exploring the best way to accomplish the detailed work of tagging the road network of the study area.

### General issues ###

  * Workflow: It is unclear if the total extent of the collected GPS data will be used to determine the study area, or if the study area will be created before the GPS data is collected (or, for that matter, concurrently).

  * Network connectivity: We will need to examine in further detail (but short order) what the trade-offs are for online vs. offline tagging. Perhaps online tagging is simpler, but more prone to error and network latency. And perhaps offline tagging is more complicated, less error prone, but increases the initial download size of a VirtualBox distribution

  * The skill level of the user who is tagging the road network is unknown.

  * Significant effort is required to document how and what to tag, especially if the user tagging the roads is not familiar with the either the concept, the tools, or the desired result for the vehicle emissions model

  * Sample data: in order to define the best way to convert NMEA 0183 GPS data into a usable GIS format for the project, we will need sample data, preferably from a target location where TAMT will be deployed. There may be significant anomalies in field data that we need to consider in sample data. Therefore, a wide number of sample GPS traces from target locales would be invaluable.