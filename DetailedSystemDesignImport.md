

&lt;hr/&gt;


**Topics on this page**



&lt;hr/&gt;



# Component: Import #

| **Component attribute** | **Description** |
|:------------------------|:----------------|
| Classification          | Module          |
| Definition              | The Import application is a GWT-based web application module that permits users to import road network data GPS data from the field. |
| Responsibilities        | The primary responsibility of this component is to provide a facility for importing GIS data (road network and GPS traces) into the TAMT Platform database. It provides a user interface to select and upload files. It also provides a data integrity check to make sure uploaded files are properly inserted into the spatial database. Lastly, it provides a timestamped listing of uploaded GIS data that users may manage (i.e, add to and delete from) |
| Constraints             | The Import application will not support all known GIS file types or formats. It will support standard NMEA files provided by the GPS devices in the field. There may be file size constraints on the imports which may require modifying the user-interface. For instance, if a GPS is too large to be "uploaded" via a web form into the Import user-interface, the UI may need to provide a text field with the local pathname of where the file resides on the TAMT Platform server. If size is an issue, it is possible that all tagging and GPS uploads must be done by a user logged into the TAMT Appliance, and not via another computer on the network.|
| Composition             | The system imports GPS traces. A description of the use and design of the subcomponents are provided below. |
| Uses/Interactions       | Class diagrams will be provided as scheduled work begins on the Import application.|
| Resources               | It is expected that the Import application will cause a spike in both application and database memory requirements. The Import application will make use of the Hibernate, Hibernate-Spatial, and Java Topology Suite libraries, and may be subject to limitations due to using those libraries. Very complex GIS data may disrupt the processing of the data in these libraries, which may prevent the correct import of the data.|
| Processing              | The Import UI allows a user to upload a file or, in the case of larger files, provide a pathname to the file on the local filesystem on the TAMT Appliance. The ImportAPI will delegate the handling of the file to the ImportBO (BO means Business Object and is the provider of rules or business logic that should be triggered). The ImportBO will provide any validation or data manipulation (including simplification, pre-processing, etc. with the Java Topology Suite library) to the file contents prior to inserting into the database. The ImportDAO factory will provide an ImportHibernateDAO instance (using the Hibernate or Hibernate-Spatial libraries) to save the object instance to the database. For example, if a GPS trace file is selected by the user for import, an instance of the GPSTrace class may wrap the actual GPS trace data, and include other attributes like timestamp, owner, bounding box, etc. When the GPSTrace object is persisted via JDBC or object-relational mapping, the persistence layer will save the objects meta-data and actual GPS information to the correct tables in the database. **TODO:** Provide class diagrams and sequence diagrams demonstrating this workflow.|
| Interface/Exports       | **TODO:** provide links to Java Docs when the classes mentioned in the Processing description have been documented.|

# Sub-component: Import GPS #

| **Component attribute** | **Description** |
|:------------------------|:----------------|
| Classification          | Sub-module      |
| Definition              | A GWT module allowing a user to upload an NMEA-compliant file created by the GPS devices in the field. **TODO:** obtain the NMEA spec and sample data from the project lead to include in this document.|
| Responsibilities        | The primary responsibility of this component is to provide a facility for importing GPS trace data into the TAMT Platform database. It provides a user interface to select and upload files. It also provides validation and pre-processing of the GPS data. Lastly, it provides a timestamped listing of uploaded GPS traces that users may manage (i.e, add or delete). |
| Constraints             | The Import GPS application will only support GPS files created by the field devices. Other GIS / GPS files will not be permitted.|
| Composition             | n/a             |
| Uses/Interactions       | Class diagrams will be provided as scheduled work begins on the Import application.|
| Resources               | Because of the high number of data points contained in a GPS trace file, the Import GPS sub-component may cause memory increases in the TAMT Platform. The sub-component will attempt to simplify the data without losing resolution (using the JTS library), which may increase memory or CPU requirements. It will also access the database, and may use significant memory alloted to the database during the import process.|
| Processing              | The Import GPS sub-component will validate that the imported file is a GPS trace file created by the field devices. The Java Topology Suite library will be used to pre-process the GPS data into JTS-compatible data structures. Once all pre-processing is complete, the Import GPS sub-component will persist that data to the database, along with any necessary meta-data for more efficient querying and data management.  **TODO:** Provide class and sequence diagrams demonstrating this workflow.|
| Interface/Exports       | **TODO:** provide links to Java Docs as the Import code is documented.|