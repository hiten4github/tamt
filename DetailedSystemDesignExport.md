

&lt;hr/&gt;


**Topics on this page**



&lt;hr/&gt;



# Component: Export #

| **Component attribute** | **Description** |
|:------------------------|:----------------|
| Classification          | Module          |
| Definition              | A GWT module allowing a user to export raw and viewable data from the database  |
| Responsibilities        | The user will be able to export data in two ways: a) in a raw format suitable for import on another computer system, b) in a format suitable for visualizing spatial data |
| Constraints             | n/a             |
| Composition             | See the database export and the data visualization components below |
| Uses/Interactions       | See sub-components|
| Resources               | Each of the sub-components requires significant database processing. Memory settings for the database and the appliance will need to be sufficiently configured. |
| Processing              | See sub-components |
| Interface/Exports       | See sub-components|

# Sub-component: database export #

| **Component attribute** | **Description** |
|:------------------------|:----------------|
| Classification          | Sub-module      |
| Definition              | A command line utility to export (dump) the entire database |
| Responsibilities        | This component allows the user to export the entire datastore via a PostgreSQL command line utility  |
| Constraints             | The potential in this project for large datasets is very high. Exporting the entire dataset will require sufficient space in the virtual machine or the host computer |
| Composition             | n/a             |
| Uses/Interactions       | No user-interface, no interactions with other application modules. |
| Resources               | This component requires significant database processing |
| Processing              | Handled internally by PostgreSQL |
| Interface/Exports       | **TODO:** will document steps to export |

# Sub-component: data visualization #

| **Component attribute** | **Description** |
|:------------------------|:----------------|
| Classification          | Sub-module      |
| Definition              | A GWT module to export selected spatial data to KML |
| Responsibilities        | This component allows the user to export a subset of spatial data as KML (Keyhole Markup Language) for viewing in Google Earth |
| Constraints             | Only a limited number of GPS traces can be exported at a single time due to database processing and Google Earth capacity to handle large geometry sets|
| Composition             | n/a             |
| Uses/Interactions       |  A query mechanism in the user-interface will be provided allowing the user to select a subset of GPS traces for export. |
| Resources               | This component requires significant database processing |
| Processing              | KML export is handled internally by PostgreSQL. |
| Interface/Exports       | **TODO:** will document steps to export as KML|