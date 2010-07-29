package org.worldbank.transport.tamt.server.bo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.worldbank.transport.tamt.server.dao.RegionDAO;
import org.worldbank.transport.tamt.server.dao.TagDAO;
import org.worldbank.transport.tamt.shared.StudyRegion;
import org.worldbank.transport.tamt.shared.TagDetails;

public class TagBO {

	private TagDAO tagDAO;
	private RegionDAO regionDAO;
	static Logger logger = Logger.getLogger(TagBO.class);
	
	private static TagBO singleton = null;
	public static TagBO get()
	{
		if(singleton == null)
		{
			singleton = new TagBO();
		}
		return singleton;		
	}
	
	public TagBO()
	{
		tagDAO = TagDAO.get();
		regionDAO = RegionDAO.get();
	}
	
	public ArrayList<TagDetails> getTagDetails(StudyRegion region) throws Exception
	{
		if ( region == null ) // possible on initial load
		{
			ArrayList<StudyRegion> regions = regionDAO.getStudyRegions();
			for (Iterator iterator = regions.iterator(); iterator.hasNext();) {
				StudyRegion studyRegion = (StudyRegion) iterator.next();
				if(studyRegion.isCurrentRegion())
				{
					region = studyRegion;
					break;
				}
			}
		}
		return tagDAO.getTagDetails(region);
	}

	public TagDetails saveTagDetails(TagDetails tagDetails) throws Exception {
		//TODO: validate tagDetails
		if( tagDetails.getName().equalsIgnoreCase(""))
		{
			throw new Exception("Tag must have a name");
		}
		
		try {
			if( tagDetails.getId() == null )
			{
				// create an id, and save it
				tagDetails.setId( UUID.randomUUID().toString() );
				return tagDAO.saveTagDetails(tagDetails);
			} else {
				// use the existing id to update it
				return tagDAO.updateTagDetails(tagDetails);
			}			
		} catch (SQLException e)
		{
			if( e.getMessage().indexOf("duplicate key value violates unique constraint") != -1 )
			{
				throw new Exception("A tag with that name already exists");
			} else {
				throw new Exception(e.getMessage());
			}
		} catch (Exception e)
		{
			throw new Exception("An unknown error occured while trying to save a tag");
		}
		
	}

	public void deleteTagDetails(ArrayList<String> tagDetailIds) throws Exception {
		//TODO: validate tagDetailIds
		try {
			tagDAO.deleteTagDetails(tagDetailIds);
		} catch (SQLException e) {
			if( e.getMessage().indexOf("violates foreign key constraint \"roadDetails_tag_id_fkey\" on table \"roadDetails\"") != -1)
			{
				String singularPlural = "The selected tag is";
				if( tagDetailIds.size() > 1)
				{
					singularPlural = "One or more of the selected tags are";
				}
				throw new Exception( singularPlural + " associated with a road and cannot be deleted");
			} else {
				throw new Exception("Could not delete tag details");
			}
		} catch (Exception e)
		{
			logger.error("Unknown DAO error: " + e.getMessage());
		}
	}
}
