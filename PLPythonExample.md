## Basic example ##

So we can setup stored procedures like this:
```
CREATE OR REPLACE FUNCTION sp_pythonTest003()
RETURNS void AS
$BODY$

# setup logging
import os, sys, logging
LOG_FILENAME = '/tmp/plpython.log'
logging.basicConfig(filename=LOG_FILENAME,level=logging.DEBUG)
logging.debug('============================================')
logging.debug('python version: %s' % sys.version)

# query table
rv = plpy.execute("SELECT AsText(geometry) g FROM gpspoints", 5)
for r in rv:
	logging.debug(r["g"]);
	
$BODY$
LANGUAGE 'plpythonu';
```

And execute them like this:
```
select * from sp_pythonTest003()
```

And `tail -f /tmp/plpython.log` to watch this go by:
```
DEBUG:root:============================================
DEBUG:root:python version: 2.6.5 (r265:79063, Apr 16 2010, 13:28:26) 
[GCC 4.4.3]
DEBUG:root:POINT(-0.204291666666667 5.57267)
DEBUG:root:POINT(-0.20429 5.57267166666667)
DEBUG:root:POINT(-0.204293333333333 5.57267166666667)
DEBUG:root:POINT(-0.204296666666667 5.57267)
DEBUG:root:POINT(-0.204298333333333 5.57267)
```

## Return row data ##

Set up a custom type to mimic some of the `gpspoints` schema
```
CREATE TYPE tamt_point_test001 AS (
  pid integer,
  id text,
  gpstraceid text,
  geometry geometry
);
```

Return the entire dataset as a set of the new custom type:

```
CREATE OR REPLACE FUNCTION sp_pythonTest005()
RETURNS SETOF tamt_point_test001 AS
$BODY$

# setup logging
import os, sys, logging
LOG_FILENAME = '/tmp/plpython.log'
logging.basicConfig(filename=LOG_FILENAME,level=logging.DEBUG)
logging.debug('============================================')
logging.debug('python version: %s' % sys.version)

# query the table
# note that 'txtgeom' is not part of the custom type,
# so is not returned, but we have access to it in the string inside the loop that we send to the logger
rv = plpy.execute("SELECT pid, id, gpstraceid, geometry, AsText(geometry) txtgeom FROM gpspoints LIMIT 10")
for r in rv:
	logging.debug(str(r["pid"]) + "--" + r["txtgeom"])
return rv
$BODY$
LANGUAGE 'plpythonu';
```

And execute the stored procedure:

```
select * from sp_pythonTest005()
```

The results (that last column is the pgAdminIII output of the PostGIS geometry type):
```
pid(integer), id(text), gpstraceid(text), geometry(geometry)
198015;"efd71bb9-28fb-4c90-8f0d-9f912605fb83";"fe5317d5-0224-44e3-9c75-bf4f126ee8db";"0101000020E61000008DDE96B53A26CABFEE9925016A4A1640"
198016;"8ae75129-5546-440c-a9fe-cc7a28c18715";"fe5317d5-0224-44e3-9c75-bf4f126ee8db";"0101000020E6100000F62D73BA2C26CABF72B7FE706A4A1640"
198017;"b9ceb43e-19a3-49b0-8f46-012d338df0b9";"fe5317d5-0224-44e3-9c75-bf4f126ee8db";"0101000020E6100000248FBAB04826CABF72B7FE706A4A1640"
198018;"d6acf70e-f8a0-4f71-92e3-639307df38d8";"fe5317d5-0224-44e3-9c75-bf4f126ee8db";"0101000020E610000053F001A76426CABFEE9925016A4A1640"
198019;"988dc2ea-1ae6-4bc3-b726-d8bff975611d";"fe5317d5-0224-44e3-9c75-bf4f126ee8db";"0101000020E6100000EAA025A27226CABFEE9925016A4A1640"
198020;"1e9647cf-c7b6-40b7-a512-6d98c5e8ffe6";"fe5317d5-0224-44e3-9c75-bf4f126ee8db";"0101000020E6100000EAA025A27226CABF72B7FE706A4A1640"
198021;"c75088e6-44a4-4d08-959d-a5800a01cf3f";"fe5317d5-0224-44e3-9c75-bf4f126ee8db";"0101000020E6100000EAA025A27226CABF72B7FE706A4A1640"
198022;"16e30f00-fef5-4fe4-b108-074dfd2b6501";"fe5317d5-0224-44e3-9c75-bf4f126ee8db";"0101000020E6100000EAA025A27226CABFF7D4D7E06A4A1640"
198023;"4c917847-b4f4-4384-9cc3-b62ab1d7db50";"fe5317d5-0224-44e3-9c75-bf4f126ee8db";"0101000020E6100000EAA025A27226CABF7CF2B0506B4A1640"
198024;"94831ef9-7dcb-46c1-afbb-9e5c0b6e6923";"fe5317d5-0224-44e3-9c75-bf4f126ee8db";"0101000020E610000053F001A76426CABF00108AC06B4A1640"
```

And we still get logger output in `tail -f /tmp/plpython.log`:
```
DEBUG:root:============================================
DEBUG:root:python version: 2.6.5 (r265:79063, Apr 16 2010, 13:28:26) 
[GCC 4.4.3]
DEBUG:root:198015--POINT(-0.204291666666667 5.57267)
DEBUG:root:198016--POINT(-0.20429 5.57267166666667)
DEBUG:root:198017--POINT(-0.204293333333333 5.57267166666667)
DEBUG:root:198018--POINT(-0.204296666666667 5.57267)
DEBUG:root:198019--POINT(-0.204298333333333 5.57267)
DEBUG:root:198020--POINT(-0.204298333333333 5.57267166666667)
DEBUG:root:198021--POINT(-0.204298333333333 5.57267166666667)
DEBUG:root:198022--POINT(-0.204298333333333 5.57267333333333)
DEBUG:root:198023--POINT(-0.204298333333333 5.572675)
DEBUG:root:198024--POINT(-0.204296666666667 5.57267666666667)
```