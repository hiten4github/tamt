

&lt;hr/&gt;


**Topics on this page**



&lt;hr/&gt;



# Component: Query #

| **Component attribute** | **Description** |
|:------------------------|:----------------|
| Classification          | Module          |
| Definition              | The Query application is a GWT-based web application module that permits users to query tagged or assigned GPS data points by various stored or computed attributes.|
| Responsibilities        | The primary responsibility of this component is to analyze the data according to preset and user-defined queries.|
| Constraints             | In general, some of the sub-components may have long-running queries. See each sub-component below for details. |
| Composition             |  There are six main sub-components in the Query application:  vehicle flow (vkt) weighting, interpolation of missing data, speed bin fractional distance, engine soak times, database export, data visualization. A description of each sub-component is listed in a separate section below. |
| Uses/Interactions       | Query results are tightly coupled to the Export application, which permits query results to be exported to CSV files.|
| Resources               | This component depends heavily on database resources and memory. It is expected that the Java Topology Suite library will be used for interpolation of missing data. The Hibernate library will be used for querying and reporting in the other sub-components. |
| Processing              | In general, the user will manipulate HTML forms to submit a query to the TAMT Platform server. Some of the queries will also be preset (and only require the user to click a link or a button). The TAMT Platform will receive the query, invoke the appropriate application programming interface (API) and return the results. Details of these processes are listed for each sub-component below.|
| Interface/Exports       | See each sub-component below. |

# Sub-component: VKT weighting (vehicle flow) #

| **Component attribute** | **Description** |
|:------------------------|:----------------|
| Classification          | Sub-module      |
| Definition              | A GWT module allowing a user to generate vehicle flow weighting |
| Responsibilities        | This component provides a query mechanism for the user to a) specify how to calculate vehicle flow weighting and b) calculate vehicle flow weighting. Vehicle flow weighting is required as part of the TAMT application output in order to properly calculate GHG emissions. |
| Constraints             | The Assign module must have been run as a pre-condition to using this module as vehicle flow weighting must be based on vehicle speed outputs. <br /> **It is unclear in the TOR what database performance or data storage impacts this module may generate.**|
| Composition             |  a) day type selection, b) vehicle type selection, c) road section, d) traffic flow, e) speed bin types, f) vehicle specific power, g) maximum engine idling time |
| Uses/Interactions       | There is a mix of user-interface and calculation routines, as well as interaction with other modules. The road section, traffic flow, speed bin type, and maximum engine idling time components interact with data from the Tag application. |
| Resources               | Database access will increase according to the number of GPS traces in the system |
| Processing              | **More investigation is needed based on the current TOR**<br />**TODO:** Provide class and sequence diagrams demonstrating this workflow. |
| Interface/Exports       | **TODO:** provide links to Java Docs as the Import code is documented.|

## Sub-component: VKT - day type selection ##

**TODO: Clarification on TOR is required**

| **Component attribute** | **Description** |
|:------------------------|:----------------|
| Classification          | Sub-module of VKT weighting module |
| Definition              |                 |
| Responsibilities        |                 |
| Constraints             |                 |
| Composition             |                 |
| Uses/Interactions       |                 |
| Resources               |                 |
| Processing              |                 |
| Interface/Exports       |                 |

## Sub-component: VKT - vehicle type selection ##

**TODO: Clarification on TOR is required**

| **Component attribute** | **Description** |
|:------------------------|:----------------|
| Classification          | Sub-module of VKT weighting module |
| Definition              |                 |
| Responsibilities        |                 |
| Constraints             |                 |
| Composition             |                 |
| Uses/Interactions       |                 |
| Resources               |                 |
| Processing              |                 |
| Interface/Exports       |                 |

## Sub-component: VKT - road section ##

**TODO: Clarification on TOR is required**

| **Component attribute** | **Description** |
|:------------------------|:----------------|
| Classification          | Sub-module of VKT weighting module |
| Definition              |                 |
| Responsibilities        |                 |
| Constraints             |                 |
| Composition             |                 |
| Uses/Interactions       |                 |
| Resources               |                 |
| Processing              |                 |
| Interface/Exports       |                 |

## Sub-component: VKT - traffic flow ##

**TODO: Clarification on TOR is required**

| **Component attribute** | **Description** |
|:------------------------|:----------------|
| Classification          | Sub-module of VKT weighting module |
| Definition              |                 |
| Responsibilities        |                 |
| Constraints             |                 |
| Composition             |                 |
| Uses/Interactions       |                 |
| Resources               |                 |
| Processing              |                 |
| Interface/Exports       |                 |

## Sub-component: VKT - speed bins ##

**TODO: Clarification on TOR is required**

| **Component attribute** | **Description** |
|:------------------------|:----------------|
| Classification          | Sub-module of VKT weighting module |
| Definition              |                 |
| Responsibilities        |                 |
| Constraints             |                 |
| Composition             |                 |
| Uses/Interactions       |                 |
| Resources               |                 |
| Processing              |                 |
| Interface/Exports       |                 |

## Sub-component: VKT - vehicle specific power ##

**TODO: Clarification on TOR is required**

| **Component attribute** | **Description** |
|:------------------------|:----------------|
| Classification          | Sub-module of VKT weighting module |
| Definition              |                 |
| Responsibilities        |                 |
| Constraints             |                 |
| Composition             |                 |
| Uses/Interactions       |                 |
| Resources               |                 |
| Processing              |                 |
| Interface/Exports       |                 |

## Sub-component: VKT - maximum engine idling time ##

**TODO: Clarification on TOR is required**

| **Component attribute** | **Description** |
|:------------------------|:----------------|
| Classification          | Sub-module of VKT weighting module |
| Definition              |                 |
| Responsibilities        |                 |
| Constraints             |                 |
| Composition             |                 |
| Uses/Interactions       |                 |
| Resources               |                 |
| Processing              |                 |
| Interface/Exports       |                 |

# Sub-component: interpolation of missing data #

| **Component attribute** | **Description** |
|:------------------------|:----------------|
| Classification          | Sub-module      |
| Definition              | A database module with a UI trigger to interpolate missing traffic flow data  |
| Responsibilities        | This component interpolates missing traffic flow data to provide continuous data for the TAMT application outputs. |
| Constraints             | Sufficient traffic flow data must be present in the database before this component may be run.  |
| Composition             | n/a             |
| Uses/Interactions       | **Clarification required based on original TOR** |
| Resources               | Significant database resources may be used in running this component. |
| Processing              | An algorithm to process this data must be developed  |
| Interface/Exports       | **TODO**        |

# Sub-component: speed bin fractional distance #

| **Component attribute** | **Description** |
|:------------------------|:----------------|
| Classification          | Sub-module      |
| Definition              | A GWT module allowing a user to generate reports for speed bin fractional distances |
| Responsibilities        | This component allows the user to query for each vehicle type the annual weighted fraction of distance in each speed bin |
| Constraints             | **TODO**        |
| Composition             | n/a             |
| Uses/Interactions       | **Clarification required based on original TOR** |
| Resources               | This component requires significant database processing |
| Processing              | **TODO**        |
| Interface/Exports       | **TODO**        |

# Sub-component: engine soak times #

| **Component attribute** | **Description** |
|:------------------------|:----------------|
| Classification          | Sub-module      |
| Definition              | A GWT module allowing a user to generate reports for maximum engine idling time |
| Responsibilities        | This component allows the user to query for each vehicle type the annual weighted time that the vehicle is stationary (by road section type and overall) |
| Constraints             | **TODO**        |
| Composition             | n/a             |
| Uses/Interactions       | **Clarification required based on original TOR** |
| Resources               | This component requires significant database processing |
| Processing              | **TODO**        |
| Interface/Exports       | **TODO**        |