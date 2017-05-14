package com.planb.table_model;

public class FriendTableModel {
	private String name;
	private String gender;
	private String alternateName;
	private String timelineUri;
	
	public FriendTableModel(String name, String gender, String alternateName, String timelineUri) {
		this.setName(name);
		this.setGender(gender);
		this.setAlternateName(alternateName);
		this.setTimelineUri(timelineUri);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getAlternateName() {
		return alternateName;
	}
	public void setAlternateName(String alternateName) {
		this.alternateName = alternateName;
	}
	
	public String getTimelineUri() {
		return timelineUri;
	}
	public void setTimelineUri(String timelineUri) {
		this.timelineUri = timelineUri;
	}
}
