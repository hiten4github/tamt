## Introduction ##

Map-matching is the process of correlating GPS points with links on a road network. The issues are well-defined in GIS transportation. The solutions vary, usually depending on the amount, accuracy and type of GPS data collected, along with the robustness of meta-data associated with the road network.

For TAMT Assign, we do not have the luxury of a fully-robust map-matching system. But we will have to do the best we can with the GPS data collected and roads drawn and defined by users in the field.

An email thread on the wb-tamt group is worthwhile to read: http://groups.google.ca/group/wb-tamt/browse_thread/thread/5deb104f59bdacd (Also read the linked paper on the subject)

## Basic Assumptions ##

  * The purpose of our map-matching effort is to assign each GPS point the "tag" attribute of the road it is on or the zone which contains it. We do not need any other road attributes transferred over to the GPS point.

  * Roads in TAMT Tag are simple. There are no network constraints (direction, speed, obstructions, etc). Roads on the Tag map UI are drawn by users in the field, and may be subject to inaccuracy (in node position, topology, etc).

  * Given the time constraints of this phase of the project, we need a decent working solution, not a perfect one.

  * The number of roads will be less than the number of points.

  * Many points may be invalid due to simplified network topology (e.g. not digitizing a traffic circle or on/off ramps)

## Ideas ##

  * Not all GPS points must be matched to a road. Depending on the layout of the study region, most points could be located in zones rather than assigned to specific roads. (A question whose answer may determine the analytical method could be: what percentage of GPS points are within 20 m of a road? If the percentage is high, we might analyze every point. If the percentage is low, we might start by looping over roads and searching for points near them in order to create a point subset from which to proceed).

  * Provide an aggregate of all points as lines, simplified for display on the map UI. All points with the same gpstraceid are analyzed. Make a new linestring every time the temporal gap exceeds 20s (or other arbitrary value).  Store the linestrings in another table and visualize as necessary.