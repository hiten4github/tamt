package org.worldbank.transport.tamt.shared;

import java.io.Serializable;

@SuppressWarnings("serial")
public class StudyRegion implements Serializable {

	private String id;
	private String name;
	private String description;
	
	public StudyRegion()
	{
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("StudyRegion [description=");
		builder.append(description);
		builder.append(", id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append("]");
		return builder.toString();
	}
}
