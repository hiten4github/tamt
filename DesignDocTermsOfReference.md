

&lt;hr/&gt;


**Topics on this page**



&lt;hr/&gt;



# Background #

In July 2004 the Clean Air Initiative in Latin America Cities (CAI-LAC) Steering Committee decided to set up the Clean Air Institute to manage and oversee CAI-LAC.  Subsequently, the institute was hosted within Breakthrough Technologies Institute (BTI) Clean Air Institute. It is currently implementing the World Bank Regional Project of the Sustainable Transport and Air Quality Program for Latin America (the STAQ Program), financed by the Global Environment Facility (GEF).

The STAQ program includes a Regional Project and three country projects (Argentina, Mexico and Brazil) in eleven cities in Latin America (in Brazil: Belho Horizonte, Curitiba and Sao Paulo; in Argentina: Posadas, Córdoba, Rosario and Tucuman; and in México: Ciudad Juárez, León, Monterrey and Puebla). Within the Regional Project, the STAQ Program includes a component on methodologies to assess climate change and air quality impacts of transport interventions. The overall objective of the methodologies component is to gather, assess and enhance existing models to quantify greenhouse gases emissions and air quality impacts of different transport interventions. Specifically, the methodologies component is designed to address:

  * "the lack of coherent and consistent methodologies for estimating GHG emission reductions from transport measures that would assist decision making; and
  * "the absence of consistent data to evaluate climate and air pollution impacts of transport investments and land-use strategies, and data collection systems."

The assignment under these ToR will complement the methodology component of the STAQ program. Indeed, on-road transport is one of the fastest growing sub-sectors in terms of fossil fuel use and greenhouse gas emissions but is one of the most difficult to evaluate. Whereas in other sectors fuel-use and local emissions may be directly measured at each source, on-road transport involves large numbers of distinct vehicles where differences in activity, technology, maintenance and operating conditions directly affect these parameters. Since it is practically impossible to evaluate each source, different measurement and estimation techniques are required.
At the national level, top-down fuel sales data for gasoline can be almost completely attributed to on-road vehicles, but it is more difficult for other fuels such as diesel, LPG, and CNG where significant use in other sectors1 can be also supplied through the same fuel distribution network.
At the local or municipal levels, defining on-road vehicle GHG emissions only from fuel sales data may imply substantial errors because of the “boundary leakages” due to fuel being bought in the locality and used elsewhere, or bought outside the area and consumed within. Examples can include long-distance trucks and coaches, and private cars that fill their tanks before leaving on an inter-city trip, or the development of service centers outside the area changing end-users’ purchase habits. Typically, these “boundary leakages” are greater in smaller study areas (such as medium or small cities and municipalities). If only one route is being studied, these leakages become totally unmanageable since the traffic on that route often bears no relationship to the vehicles that are registered as residing on the route.

Even where reliable fuel sales data for on-road transport can be obtained, vehicle activity data will be needed to apportion the fuel consumed to different vehicle types and uses to enable the impact of different policy interventions to be tracked. Diesel for example can be consumed by buses, coaches, passenger cars and both heavy- and light-duty freight vehicles.  Without taking into account the size of the populations of each vehicle type and their respective usage patterns, the impact of interventions to reduce mass-transit fuel consumption and emissions, for example, can be completely masked by changes in the usage of other vehicle categories.

Vehicle activity measurements are the basis not only of mobile source emissions inventories and GHG evaluations;   they are fundamental to most road-transport indicators and system monitoring. The inclusion of on-road transport in Nationally Acceptable Mitigation Actions (NAMAs) will require bottom-up data on vehicle activity to be systematically collected to measure, record, and verify (MRV) how it changes over time and this toolkit is a first step towards structuring this data collection and monitoring process.

**The software tools to be developed under this TOR will form an integral part of the Transport Activity Measurement toolkit that is being developed.**

The complete toolkit will consist of:

  * Practitioners’ guide
  * Road hierarchy assignment tool
  * Relational Database and calculation tool
  * Front end

This TOR covers the road hierarchy assignment tool, the relational database and calculation tool, and the front end. The other elements will be developed by different teams and are subject, each to its own TOR.

The toolkit shall be easy to use and practical for project staff, city personnel and other stakeholders to generate the data needed to assess fuel efficiency, greenhouse gas (GHG) and air pollutant emissions, based on vehicle characteristics and measured activity. It will use modern tools, such as low cost GPS loggers to generate precise data with a minimum of effort.

The toolkit will:

  * Improve the quality and consistency of data used in evaluating the impacts of specific interventions.
  * Proportion a unified framework to facilitate cross-country and region assessment.
  * Encourage involvement of different experts and institutions in data collection process and modeling activities.
  * Provide support to the GEF in the evaluation of GHG emissions from road transport projects and to the “Knowledge Partnership for measuring Air Pollution and GHG Emissions in Asia”.
  * Integrate with the ESMAP/WBI funded e-learning process and toolkit for on-road transport emissions modeling.
  * Assist task managers in assigning data collection activities to local institutions.

## Scope and methodology of the toolkit ##

The toolkit (illustrated in figure 1) shall:

  1. Provide guidance to the user in the selection of the accounting framework (model) that best suits his project’s needs
  1. Provide guidance in the field survey design and selection of sample sizes in accordance with the needs of the selected model and size of city or region
  1. Generate the data collection documents that the user will require in the local language and provide guidance on their use
  1. Provide open source applications able to read all valid GPS data collected and to assign a road category or hierarchy to each segment of road (arcs). The applications need to work together with Google Earth, Google Maps, and/or Openstreetmap
  1. Provide guidance to the user on quality control/quality assurance (QA/QC) for field data survey work
  1. Provide an open source database to house and validate the collected activity data
  1. Generate output files that can be used as input to the selected emissions models.

TODO: insert figure 1 here
**Figure 1 -  Diagram of the Transport Activity Measurement Toolkit**


---


# Objective of this TOR #

The objective of this TOR is to develop the software tools needed to simplify and mechanize the processing of GPS data into speed (or speed-load) bins for different road sections in a standardized and consistent manner (see box).

Currently this is performed manually using programs such as Excel by distinct practitioners and the lack of a guide and standardized and simplified tools make it difficult for different projects to develop consistent information. Having the software tools covered by this TOR will assist the project practitioners with M&E data processing.


---


# Introduction #

In many vehicle emissions models (such as Copert42) emission factors are speed sensitive (others are speed and load sensitive) and vehicle emissions need to be calculated separately for each of 5 kph or 10 kph speed-bins (from 0 to max speed) for different homogeneous sections of road and multiplied by the veh/hr on that section to get an accurate estimation3.

Vehicle emissions also need to be calculated separately for (i) each of up to 250+ vehicle type/technology mixes, (ii) for each hour of the day (weekday plus weekend) on (iii) each "typical road section type" (which is has homogeneous traffic patterns and for which traffic counts in veh/hr exist). The number of "typical road section types" depending on the model will go from 3 to around 20 plus any routes for which specific treatment is needed such as a proposed BRT route. This gives a big matrix of calculations that are integrated to get the GHG (and other) emissions within the defined boundary. Whilst none of the calculations are complex, the sheer volume of calculations make it difficult for the project practitioner to generate the dataset needed for the selected emissions model.
The objective of this TOR is to mechanize the speed bin calculation for different road types from GPS logs. This will simplify the work for the project practitioner and allow him/her to scatter a relatively large number of cheap GPS loggers around a city to collect second-by-second data from road users going about their normal daily business.

Each logger4 could be collected every 1 to 3 days with up to 120,000 second-by-second data points in a csv file. With up to 100 loggers operating in a city over a couple of months quite a substantial amount  of data can be collected which should also give a good idea of traffic flows (route, time of day and day of week) and help identify where traffic counts should be made.

Each datapoint in the CSV file has NMEA-0183 - GGA, GSA, GSV, RMC output to which a "road section type" identifier needs to be added before further processing. Then the speed (or speed and load) bin information needs to be generated for each road section type and day/time-of-day.


---


# Scope/Activities #

The consultant shall develop the following tasks:

## Task 1 ##

Select or develop open source tools that simplify identifying (tagging) in Google Earth, Google Maps, or Openstreetmaps the road section types (numbered 1 to 99) identified by the project practitioner within the geographical boundary he has established for that project.

A practitioners’ guide is being developed under a separate TOR to assist the project practitioner select roads that needs to be individually identified within the geographical boundary he has established. These will consist of highways5, main roads and arterials, and other lower speed and lower density traffic streets which will be identified by default6 within one of up to three development types (residential, commercial, and industrial) inside the boundary.

This toolkit will be used principally in cities in developing countries, not all of which are currently covered by Openstreetmaps or by the “Map” view in Google Earth and Google Maps. It is assumed however that all will be visible in the “Satellite” view. In many of these cities, existing printed maps do not correspond well to on-the-ground reality. Whilst using Openstreetmaps can be a very attractive option for many cities, the consultant would have to include the option of a Google “Satellite” view approach for others. Whilst not included in the scope of this project, thought should be given to how our GPS data could be used to enrich Openstreetmaps.

Google Earth and Google Maps allow multiple-route track data to be assembled from freehand drawing using the “Map” or “Satellite” views. The consultant may use this to create the GPS track data for the road identification within the project boundary. It should be noted that each road classification (that is: highways, main roads and arterials, in residential, commercial, and industrial zones) is likely to include multiple different routes that are not necessarily interconnected.

Different areas also need to be identified within the geographical boundary established for that project that approximate to the residential, commercial, and industrial zones of the city. Within each of these identified zones, all GPS coordinates that do not correlate to an identified road section will assume default identification according to the zone type. In some projects due to the specific city or size of the geographical boundary, there may be only one default zone type. It is acceptable to identify each zone with rectangles; however there may be more than one of each within the geographical boundary established for the project7.

The objective of Task one is to simplify this process in such a way that project practitioners (often transport engineers) may perform the task with limited or no previous hands-on training. Where existing tools (such as Google Earth or Google Maps) are to be used, the consultant will prepare a guide that leads the project practitioner through the steps to be followed in their use.


## Task 2 ##

Develop an open source database to (i) Upload all the valid GPS data collected, checking consistency. (ii) Upload the tagged road section link data and zone data from Task one (iii) Assign the road section and type identifier numbers (from Task three) to each valid GPS data point (iv) Calculate outputs (Task four).

The GPS data extracted from each log file shall contain coordinates, data and time stamp, and altitude data (where available). The database shall calculate vehicle speed, acceleration, and grade change from prior and subsequent consecutive data points and record this against each valid register.

The database shall record in each valid register the road segment and type assignment calculated in task three.

If we use http://www.gzbrt.org/en/maps-i-ii.asp as an example, Task one shall enable the project practitioner to assign a different identifying number to each homogeneous traffic section of (i) the BRT line, (ii) the blue road traces, and (iii) each of the yellow and (iv) beige main roads. It would allow (v) different rectangles of the map image to be selected as predominantly residential, commercial, and industrial zones.

The total lengths of the identified road sections (by classification type) will be determined. For the default road types (lower speed and lower density traffic streets within residential, commercial and industrial developments) the user will be expected to input  a density factor (road length in km per km2 of development area) which will be used to assign total lengths.

## Task 3 ##

Develop an open source application or utility8 able to read the valid GPS second-by-second data (from Task two database) and assign the corresponding road type (including defaults) to each datapoint.

Each GPS coordinate shall be assigned to one of the road segments or to the default value depending on the area in which it is contained, as identified in task one. In determining its corresponding road section: (i) the direction of travel (identified from prior and subsequent consecutive data points) shall be taken into account and (ii) a user modifiable tolerance shall be used to accommodate errors in the freehand selection of each road segment and uncertainty in the GPS readings.

## Task 4 ##

Develop an open source application or utility8 able to analyze the data and generate outputs:

### Vehicle flow (vkt) weighting ###

For use in calculating vehicle emissions, the vehicle speed outputs need to be weighted by traffic flow (on an annual vehicle-kilometer travelled (vkt) basis) and thus this vehicle count data need to be entered into the database. The project practitioner should be given the option of selecting:

  * **Day types** - To use only equivalent working day data (in which Saturday, Sunday and holiday speed and traffic flow data are ignored). The practitioner should be allowed to manually enter the equivalent working day annual multiplier to account for these missing days in the yearly totals <br /><br />**OR**<br /><br /> To use equivalent working day data together with equivalent festive day (Saturday, Sunday and holiday) data with manual entry of the equivalent number of each per year.

  * **Vehicle types** - To use any combination of the following vehicle classifications:
    * Two-wheeler (2W)
    * Three-wheeler (3W)
    * Passenger Car (PC)
    * Taxi (Taxi)
    * Pickups, Vans, and SUV people-movers (LDV)
    * Light Duty Commercial vehicles freight-movers (LDC)
    * Heavy Duty Commercial vehicles (HDC)
    * Medium Duty minibuses (MDB)
    * Heavy Duty Buses (HDB)

  * **Road Section** - Automatically input the length of each road section and type in each zone (in km) as calculated in task one.

  * **Traffic flow** - Manually input the average measured traffic flow per hour:
    * for each road section
    * for each day type
    * for each vehicle type
    * for each of 24 hours per day

<blockquote>Whilst traffic flow data is entered on a per-hour basis, it will usually be derived from a shorter samples (say 10 or 15 minutes) and adjusted to a standard mean sample period time (ie: such as 30 minutes past the hour) and this standard mean sample period time needs to be entered.</blockquote>

  * **Speed bin type** - The practitioner should be allowed to select for the output, speed data in (i) 5 km/h bins; (ii) in 10 km/h bins; or (iii) average operating speed (km/h) for each road type9. The speed bins cover 0 kph to a user defined maximum speed bin <br /><br />**OR**<br /><br />The practitioner should be allowed to choose to output Vehicle Specific Power (VSP) and Engine Stress for the IVE model. Here, driving patterns are characterized using two parameters:
    * Vehicle Specific Power (VSP)
    * Engine Stress

<blockquote>
Both of these parameters can be obtained from general knowledge of the vehicle type and a second-by-second velocity trace. If road grade is included, second-by-second altitude is also required. If road grade is not to be included, grade can be assumed to be zero in equation 1. Equations 1 and 2 show how to estimate VSP and engine stress.<br>
<br>
<pre><code>VSP = v[1.1a + 9.81 (atan(sin(grade)))+0.132] + 0.000302v3  (Eq. 1)  <br>
      grade = (ht=0 – ht=-1)/ v (t=-1to0seconds)<br>
      v = velocity (m/s)<br>
      a = acceleration (m/s2)<br>
      h = Altitude (m) <br>
<br>
Engine Stress (unitless) = RPMIndex + (0.08 ton/kW)*PreaveragePower  (Eq. 2) <br>
      PreaveragePower = Average(VSPt=-5sec to –25 sec) (kW/ton)<br>
      RPMIndex = Velocityt=0/SpeedDivider (unitless)<br>
      Minimum RPMIndex = 0.9 <br>
</code></pre>

The result of the processed data is the fraction of time spent driving in each of the VSP and stress categories. There are three stress categories and 20 VSP categories, making a total of 60 bins.</blockquote>

  * **Maximum engine idling time** - The practitioner should be allowed to select the maximum engine-on idling time to be used in the calculations. If for example 10 minutes is selected, any continuous period where the GPS reports no movement of the vehicle that exceeds 10 minutes, it will be assumed that the engine was turned off for the whole duration of “no-movement” time, whilst for any continuous period where the GPS reports no movement of the vehicle of less than 10 minutes it will be assumed that the engine continues to idle for the whole duration of “no-movement” period. Since the GPS itself may be turned off when the vehicle’s engine is turned off, care must be taken to interrogate the date-time stamp and not obtain this data from a count of the number of records.

### Interpolation to fill missing data ###

Interpolate to fill any data holes using traffic flow data and assuming no discontinuities in the hourly time distribution profile.

  * Interpolate where necessary to fill any data holes in the traffic flow data by assuming that all road segments of the same type will have a veh/hr vs time profile that is the same as the average veh/hr vs time profile of the measurements obtained for that road type
  * Interpolate where necessary so that the speed data matches the flow data. This is particularly important in the morning ramp-up since flow data averaged at say 0830 cannot be directly associated with speed data collected 20 minutes earlier.

### Output fraction of distance in each speed bin ###

Output for each vehicle type the annual weighted fraction of distance in each speed bin. The collected GPS speed data will assume to apply to all vehicle types however the weighting factors may be different due to differences in vehicle counts per hour of day. Should the project practitioner wish to account for speed differences for different vehicle types, this can be accommodated by assigning different route segment identifiers to the same route section (for example #11 for BRT buses and #12 for all other vehicles on the same route segment). The sum of all the fractions for each vehicle type should total 100.

The program must be able to output intermediate step reports, such as (i) unweighted speed bin fractions for each road section and (ii) weighting factors for each road section to enable the project practitioner to quickly identify any numbers that are missing or seem wrong or out of place.

### Output engine soak times ###

Similar to driving patterns, different kinds of starts can have a profound impact on tailpipe emissions. The most predominate effect is the engine soak period before an engine starts.

This is reported by calculating the “engine idling time” before the GPS detects vehicle motion and binning the total number of starts and the fraction of total engine starts  (ie where the engine was considered to have been turned off)  in 10 bins (0-15 min, 16-30 min, 31-60 min, 61-120 min, 121-180 min, 181-240 min, 241-360 min, 361-480 min, 481-720 min, and over 720 min). The sum of all the fractions should total 100.

In the same way, the program shall output for each vehicle type the annual weighted time that the vehicle is stationary (considered as less than 2.5 kph and discounting the time when the engine has been considered to have been turned off) by road section type and overall.

### Output database ###

The option to export and import the complete dataset so that it can be sent to a different remote computer or added to a repository must be included.

### Output visualization of data ###

The consultant shall provide the option to visualize on Google Earth, Google Maps, or Openstreetmaps the GPS traces that have been inputted into the database. This may require generating a representative subset of data to keep within the limitations of these programs in terms of number of waypoints and/or file size.

## Task 5: Front end ##

The consultant shall provide a simple front end that facilitates access to, and use of, the different modules when all are assembled on a CD for delivery to the local institution (such as a university) or company that is tasked with performing the selected measurements. The front end should supply information to the user on:

  * The contents of the CD,
  * How to access/install the different modules for their use
  * Where to find additional information
  * Steps involved in the process.

Logos, contacts and institutional information will be supplied to the consultant by the World Bank.


---


# Generalities #

This output will be used in an emissions model together with the traffic count data and the vehicle population mix data (collected separately) for each category of vehicle. As additional emissions models are added to the package, the World Bank will need to be able to add data output formats tailored to each model and the consultant should take this into consideration in the way the database/utilities/applications are structured.

This software will be developed using open-source tools/packages. It may be assumed that the user will possess a PC (desktop or laptop) running Microsoft Vista or Windows 7 in any of their publically available configurations that are recommended for the use of Microsoft Office 2007.

The goal is to have a nice little open source toolkit that can be given freely to practitioners and leads them through this process, step by step, automating each one as much as possible.