

&lt;hr/&gt;


**Topics on this page**



&lt;hr/&gt;



# Component: Assign #

| **Component attribute** | **Description** |
|:------------------------|:----------------|
| Classification          | Module          |
| Definition              | Assign is a Google Web Toolkit-based web application module that triggers a long-running process to assign all points in a GPS trace (or multiple GPS traces) to the nearest tagged road network in the study area.|
| Responsibilities        | The primary responsibility of Assign is to ensure that each GPS data point has an associated tagged road network attribute so that analysis on the GPS data point can include the road network attributes which spatially coincide with the data point. The greenhouse gas model requires the correct TAMT-tagged road network attributes to be assigned to every data point.|
| Constraints             | It is expected that a user can run Assign on a selected number of uploaded GPS traces, or all GPS traces in a study area. Depending on the average number of nodes in GPS trace, an Assign run may take a significant amount of time to complete. The design of Assign will take this into account, and include a background processing concept like [Tasks on Google App Engine](http://code.google.com/appengine/docs/python/taskqueue/) (without using App Engine) or [PgQ](http://wiki.postgresql.org/wiki/Skytools#PgQ) (PostgreSQL Queue, part of the Skype Tools package).|
| Composition             | n/a             |
| Uses/Interactions       | The Assign UI provides a list of uploaded and unprocessed GPS traces. A user can select one or more GPS traces for processing with Assign. Since background processing is likely required for Assign to complete the work for each GPS trace, a UI indicator will alert the user to the status of the Assign process. It is assumed that the Tag application has already provided the tagged road networks which GPS traces will be assigned to. |
| Resources               | A significant amount of database processing will occur in Assign. Some of this will be efficiently handled via PL/Java stored procedures combined with PgQ, but the sheer amount of GPS trace data to be processed indicates that careful design and planning is necessary.|
| Processing              | The main Assign algorithm will operate on all of the nodes of GPS trace. For every node, the nearest tagged road network will be located, and TAMT-tagged attributes from the road network will be assigned to nodes in the GPS trace for export to and analytical processing in the greenhouse gas model. Once processed, the GPS trace meta-data in the database will be marked as processed. Bulk or batch algorithms will be developed to handle groups of nodes in a GPS trace or multiple GPS traces.|
| Interface/Exports       | **TODO:** provide links to Java Docs when the classes mentioned in the Processing description have been documented.|