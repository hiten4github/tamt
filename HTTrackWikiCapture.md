# Background #

For filing purposes, the World Bank needed a design document submitted before releasing payment of Deliverable #1. However, the design document is a living document on the wiki at: http://code.google.com/p/tamt/wiki/DesignDocIntroduction

A solution was needed to create an offline version of the design doc to be submitted as the first deliverable.

# Solution #

In the TAMT Appliance, I installed httrack, an open-source offline browser utility.

## Initial Capture ##

To capture the design docs from the wiki, I opened a Terminal in the TAMT Appliance and ran the following commands:

```
$ mkdir /home/tamt/Desktop/tamt-design-doc-wiki
$ cd /home/tamt/Desktop/tamt-design-doc-wiki
$ httrack http://code.google.com/p/tamt/wiki/DesignDocIntroduction -r6
```

## Subsequent Captures ##

After editing some wiki pages, I was able to update the offline version by running the following command:

```
$ cd /home/tamt/Desktop/tamt-design-doc-wiki
$ httrack update
```

## Archive ##

Archiving the updated offline design doc wiki was a simple step:

```
$ cd /home/tamt/Desktop
$ zip -r tamt-design-doc-wiki-`date +%Y%m%d%H%M%S`.zip tamt-design-doc-wiki
```

You will have a timestamped zip file like:

```
$ ls *.zip
tamt-design-doc-wiki-20100602130108.zip
```

These zip files may be uploaded to the repository for archiving:
http://code.google.com/p/tamt/downloads/list?q=label:Type-Docs

## README ##

There is a README file in the archive under <archive.zip>/tamt-design-doc-wiki/README.txt

The contents of the README are:

```
This is the offline browser version of the TAMT design document
as found on http://code.google.com/p/tamt/wiki/DesignDocIntroduction.

For an updated version, please see the online version.

To open in a web browser on your local computer, load index.html
into your browser (File > Open; or drag and drop). If the page
seems to "get stuck", then open this file instead:

file:///<path-to-unzipped-folder>/tamt-design-doc-wiki/code.google.com/p/tamt/wiki/DesignDocIntroduction.html
You will need to supply the <path-to-unzipped-folder>.
```