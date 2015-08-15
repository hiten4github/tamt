## From Eclipse ##

  1. Compile the GWT Application in Eclipse
    * Hit the GWT Compile Project button (red box)
    * Watch for any errors
    * Wait for it to finish with something like:
```
   Compiling 6 permutations
      Compiling permutation 0...
      Compiling permutation 1...
      Compiling permutation 2...
      Compiling permutation 3...
      Compiling permutation 4...
      Compiling permutation 5...
   Compile of permutations succeeded
Linking into /home/tamt/eclipse/workspace/tamt/war/tamt.
   Link succeeded
   Compilation succeeded -- 166.498s
```
  1. Shift-select the following 5 items in the war folder:
    * `META-INF`
    * `tamt`
    * `WEB-INF`
    * `TransportActivityMeasurementToolkit.css`
    * `TransportActivityMeasurementToolkit.html`
  1. With these selected, right-click and choose Export
  1. Select Archive File and click Next
  1. To archive file: /home/tamt/Desktop/ROOT.war
    * Save in zip format (checked)
    * Compress contents of file (checked)
    * Create only selected directories (checked)
  1. Click Finish

## From pgAdmin III ##
  1. Open pgAdmin III from the Applications folder on the desktop
  1. Double-click on tamt to connect to the database server
  1. Click the SQL button to open an SQL editor
  1. Open the following file (click No to save the current text):
    * `/home/tamt/eclipse/workspace/tamt/design/database/sp_TAMT_updateAssignStatus-prod.sql`
  1. Click the red "play" arrow in pgAdmin III to execute this script, which updates the stored procedure with the production value of the HTTP webhook

## From Tomcat ##

  1. Login to the manager: http://127.0.0.1:8080/manager/html (tamt/tamt)
  1. Stop and undeploy the current TAMT Application at the '/' context
  1. Select war file to upload: /home/tamt/Desktop/ROOT.war
  1. Deploy

## Run TAMT ##

  1. Open a browser to: http://127.0.0.1:8080
