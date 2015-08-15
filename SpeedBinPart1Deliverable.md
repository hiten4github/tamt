# Introduction #

The Speed Bin sub-component of the Analysis module is a set of complex routines to reduce the GPS and traffic-flow data to a statistical data set suitable for export from TAMT and import into other software for further analysis.

# Details #

There are 3 basic deliverables for the Speed Bin sub-component:

  1. The configuration and report UI
  1. The speed distribution table
  1. Several iterations of the speed distribution table
    * Without day type
    * Without tags
    * Fractionalized

The Part 1 deliverable for this sub-component contains code for:

  1. The speed distribution table
  1. The speed distribution table without day type

# Deliverables #

Since this component is not feature-complete, there are no updates to the TAMT appliance or deployed applications (server-side or UI). These will be available when the Speed Bin sub-component is complete. However, a number of deliverables support the latest work effort, including:

  * Schemas: http://goo.gl/eSlY (these may be adjusted based on last minute changes)
  * Psuedo-code design documents to produce the new tables: http://goo.gl/2EZ5
  * Checked-in source code changes for Part 1:
    * http://goo.gl/eSlY
    * http://goo.gl/UbSc
    * http://goo.gl/Y1sf
    * http://goo.gl/21Jk
  * A [complete Eclipse project](http://tamt.googlecode.com/files/tamt-speedbins-deliverable-source-code-2010-10-23-1505.zip) with up-to-date source code changes