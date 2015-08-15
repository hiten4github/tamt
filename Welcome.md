## The World Bank Transport Activity Measurement Toolkit ##

The World Bank Transport Cluster is sponsoring the development of the Transport Activity Measurement Toolkit, an open-source toolkit to process GPS log files for vehicle emissions drive cycle calculations to support greenhouse gas emission modeling for on-road transport.

**[Software Design Document (organized)](DesignDocIntroduction.md)** ([list format](http://code.google.com/p/tamt/w/list?q=label:DesignDoc))

### Project Goals ###

  1. Be open-source and freely available to all audiences.
  1. Be easy to download, install and operate in high-latency, low-bandwidth environments.
  1. Provide a simplified map-based road type classification system.
  1. Import GPS point and track information from field data.
  1. Provide a function to assign road types to GPS data points.
  1. Categorize GPS point data according to speed for simplified analysis.
  1. Analyze GPS point data according to road type, day of week, time of day, and proportionate vehicle activity by speed categories.
  1. Prepare output files suitable for further processing in existing vehicle emissions models.
  1. Have well-documented source code for easier maintenance and future development.

### Software Components ###

  1. A road network classification utility (“tagger”)
  1. A data import utility
  1. Two data manipulation utilities (point assignments based on proximity to network segment, and point attribute based on speed categorization)
  1. Analysis utility (generate data views for point data by road type, day of week, time of day, vehicle activity by speed category)
  1. A data export utility

### Contacts ###

John Rogers<br />
Senior Climate Change Specialist<br />
Climate Change<br />
Environment Department<br />
World Bank<br />

Stuart Moffatt<br />
Software architect<br />
EmCODE.org<br />