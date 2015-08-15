http://www.paolocorti.net/2008/01/30/installing-postgis-on-ubuntu/

```
sudo apt-get install postgresql postgresql-contrib pgadmin3
sudo apt-get install postgresql-8.4-postgis

sudo su - postgres
createdb postgistemplate
createlang plpgsql postgistemplate

psql -d postgistemplate -f /usr/share/postgresql/8.4/contrib/postgis.sql 
psql -d postgistemplate -f /usr/share/postgresql/8.4/contrib/spatial_ref_sys.sql 
```