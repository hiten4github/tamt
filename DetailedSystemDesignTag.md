

&lt;hr/&gt;


**Topics on this page**



&lt;hr/&gt;



# Component: Tag #

The TAMT Tag application is a web-based, limited use map editor that allows a user to create tags, roads and zones. Tags are user-defined attributes that can be applied to roads. Tags and zone classifications (residential, commercial and industrial) contribute to the calculations made in the greenhouse gas emissions model.

| **Component attribute** | **Description** |
|:------------------------|:----------------|
| Classification          | Module          |
| Definition              | Tag is an integrated Google Web Toolkit-based and Google Maps web application module. Tag allows a user to specify roads and zones for the TAMT study area and attribute segments of the road network according to user-defined TAMT classifications or "tags". |
| Responsibilities        | The primary responsibility of this component is to prepare a tagged road network for import into the database for further processing and analysis. |
| Constraints             | The TAMT appliance must have an internet connection in order to use TAMT Tag |
| Composition             | Tags / Roads / Zones.  |
| Uses/Interactions       | Users can create Tags, Roads and Zones. Tags are applied to Roads. Tags, Roads and Zones are saved into spatially-enabled tables in the PostGIS TAMT Platform database. |
| Resources               | TBD             |
| Processing              | The user's browser is responsible for rendering the application, including drawing roads and zones on the embedded Google Map. |
| Interface/Exports       | TBD             |

# Screencast Demo of Tag (Beta) #

<a href='http://www.youtube.com/watch?feature=player_embedded&v=tt-15Hnb054' target='_blank'><img src='http://img.youtube.com/vi/tt-15Hnb054/0.jpg' width='500' height=280 /></a>