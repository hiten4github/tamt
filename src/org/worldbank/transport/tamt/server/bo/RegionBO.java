package org.worldbank.transport.tamt.server.bo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.worldbank.transport.tamt.server.bo.RoadBO;
import org.worldbank.transport.tamt.server.bo.TagBO;
import org.worldbank.transport.tamt.server.bo.ZoneBO;
import org.worldbank.transport.tamt.server.dao.GPSTraceDAO;
import org.worldbank.transport.tamt.server.dao.RegionDAO;
import org.worldbank.transport.tamt.server.dao.RoadDAO;
import org.worldbank.transport.tamt.server.dao.TagDAO;
import org.worldbank.transport.tamt.server.dao.ZoneDAO;
import org.worldbank.transport.tamt.shared.GPSTrace;
import org.worldbank.transport.tamt.shared.RoadDetails;
import org.worldbank.transport.tamt.shared.StudyRegion;
import org.worldbank.transport.tamt.shared.TagDetails;
import org.worldbank.transport.tamt.shared.Vertex;
import org.worldbank.transport.tamt.shared.ZoneDetails;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LinearRing;

public class RegionBO {

	private RegionDAO dao;
	private GPSTraceDAO gpsTraceDAO;
	private TagDAO tagDAO;
	private RoadDAO roadDAO;
	private ZoneDAO zoneDAO;
	static Logger logger = Logger.getLogger(RegionBO.class);
	
	private static RegionBO singleton = null;
	public static RegionBO get()
	{
		if(singleton == null)
		{
			singleton = new RegionBO();
		}
		return singleton;		
	}
	
	public RegionBO()
	{
		dao = RegionDAO.get();
		gpsTraceDAO = GPSTraceDAO.get();
		tagDAO = TagDAO.get();
		roadDAO = RoadDAO.get();
		zoneDAO = ZoneDAO.get();
	}

	public void deleteStudyRegions(ArrayList<String> studyRegionIds) throws Exception {
		try {
			for (Iterator iterator = studyRegionIds.iterator(); iterator
					.hasNext();) {
				String studyRegionId = (String) iterator.next();
				deleteStudyRegion(studyRegionId);
			}
		} catch (SQLException e) {
			logger.error("Could not delete study regions:" + e.getMessage());
			throw new Exception("Could not delete study regions");
		}
	}

	public void deleteStudyRegion(String studyRegionId) throws Exception {
		
		// test for null string
		if( studyRegionId == null)
		{
			throw new Exception("Cannot delete a study region with null ID");
		}
		
		//TODO: eventually, this should be wrapped in a transaction
		
		try {
			
			// first, delete the study region
			logger.debug("Deleting study region: " + studyRegionId);
			dao.deleteStudyRegionById(studyRegionId);
			
			// create a StudyRegion to be used in deleting other entities
			StudyRegion region = new StudyRegion();
			region.setId(studyRegionId);
			
			// delete gps traces (including gpsfiles and gpspoints)
			ArrayList<GPSTrace> gpsTraces = gpsTraceDAO.getGPSTraces(region);
			ArrayList<String> gpsTraceIds = new ArrayList<String>();
			for (Iterator iterator = gpsTraces.iterator(); iterator.hasNext();) {
				GPSTrace gpsTrace = (GPSTrace) iterator.next();
				gpsTraceIds.add(gpsTrace.getId());
			}
			logger.debug("Deleting GPS traces for study region: " + studyRegionId);
			gpsTraceDAO.deleteGPSTraces(gpsTraceIds);
			
			// delete tag
			ArrayList<TagDetails> tags = tagDAO.getTagDetails(region);
			ArrayList<String> tagIds = new ArrayList<String>();
			for (Iterator iterator = tags.iterator(); iterator.hasNext();) {
				TagDetails tag = (TagDetails) iterator.next();
				tagIds.add(tag.getId());
			}
			logger.debug("Deleting tags for study region: " + studyRegionId);
			tagDAO.deleteTagDetails(tagIds);
			
			// delete roads
			ArrayList<RoadDetails> roads = roadDAO.getRoadDetails(region);
			ArrayList<String> roadIds = new ArrayList<String>();
			for (Iterator iterator = roads.iterator(); iterator.hasNext();) {
				RoadDetails road = (RoadDetails) iterator.next();
				roadIds.add(road.getId());
			}
			logger.debug("Deleting roads for study region: " + studyRegionId);
			roadDAO.deleteRoadDetails(roadIds);
			
			// delete zones
			ArrayList<ZoneDetails> zones = zoneDAO.getZoneDetails(region);
			ArrayList<String> zoneIds = new ArrayList<String>();
			for (Iterator iterator = zones.iterator(); iterator.hasNext();) {
				ZoneDetails zone = (ZoneDetails) iterator.next();
				zoneIds.add(zone.getId());
			}
			logger.debug("Deleting zones for study region: " + studyRegionId);
			zoneDAO.deleteZoneDetails(zoneIds);		
		
		} catch (Exception e)
		{
			logger.error("Error trying to delete a study region: " + e.getMessage());
			throw new Exception("The study region was not completely deleted");
		}
		
		
	}
	
	public ArrayList<StudyRegion> getStudyRegions() throws Exception {
		return dao.getStudyRegions();
	}
	
	public StudyRegion getCurrentStudyRegion() throws Exception {
		ArrayList<StudyRegion> regions = getStudyRegions();
		StudyRegion currentStudyRegion = null;
		for (Iterator iterator = regions.iterator(); iterator.hasNext();) {
			StudyRegion studyRegion = (StudyRegion) iterator.next();
			if( studyRegion.isCurrentRegion())
			{
				currentStudyRegion = studyRegion;
				break;
			}
		}
		return currentStudyRegion;
	}	

	public StudyRegion saveStudyRegion(StudyRegion studyRegion) throws Exception {
		
		logger.debug("Preparing to save study region:" + studyRegion);
		if( studyRegion.getName().equalsIgnoreCase(""))
		{
			throw new Exception("Study region must have a name");
		}
		
		// we want to store the Vertex array list as a JTS linestring
		ArrayList<Vertex> vertices = studyRegion.getVertices();
		if( vertices == null )
		{
			throw new Exception("Study region does not have an associated shape");
		}
		
		int vertexCount = vertices.size();
		//TODO: handle null vertices
		Coordinate[] coords = new Coordinate[vertexCount];
		for (int i = 0; i < vertexCount; i++) {
			Vertex v = vertices.get(i);
			Coordinate c = new Coordinate(v.getLng(), v.getLat());
			coords[i] = c;
		}
		
		// now create a line string from the coordinates array where null = no holes in the polygon
		LinearRing ring = new GeometryFactory().createLinearRing(coords);
		Geometry geometry = new GeometryFactory().createPolygon(ring, null);
		
		// create a point from the mapCenter vertex
		// TODO: mapCenter is null when creating a new study region.
		Vertex mapCenterVertex = studyRegion.getMapCenter();
		logger.debug("mapCenter=" + studyRegion.getMapCenter());
		Coordinate mapCenterCoord = new Coordinate(mapCenterVertex.getLng(), mapCenterVertex.getLat());
		Geometry mapCenter = new GeometryFactory().createPoint(mapCenterCoord);
		
		try {
			if( studyRegion.getId().indexOf("TEMP") != -1 )
			{
				// create an id, and save it
				studyRegion.setId( UUID.randomUUID().toString() );
				return dao.saveStudyRegion(studyRegion, geometry, mapCenter);
			} else {
				// use the existing id to update it
				return dao.updateStudyRegion(studyRegion, geometry, mapCenter);
			}			
		} catch (SQLException e)
		{
			if( e.getMessage().indexOf("duplicate key value violates unique constraint") != -1 )
			{
				throw new Exception("A study region with that name already exists");
			} else {
				throw new Exception(e.getMessage());
			}
		} catch (Exception e)
		{
			logger.error("An unknown error occured while trying to save a study region");
			throw new Exception("An unknown error occured while trying to save a study region");
		}
	}
	
}
