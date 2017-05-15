package com.planb.table_model;

public class FriendTableModel {
	private long uid;
	private String name;
	private String alternateName;
	private String isFriend;
	private String gender;
	private String timelineUri;
	
	public FriendTableModel(long uid, String name, String alternateName, String isFriend, String gender, String timelineUri) {
		this.setUid(uid);
		this.setName(name);
		this.setAlternateName(alternateName);
		this.setIsFriend(isFriend);
		this.setGender(gender);
		this.setTimelineUri(timelineUri);
	}
	
	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAlternateName() {
		return alternateName;
	}
	public void setAlternateName(String alternateName) {
		this.alternateName = alternateName;
	}
	
	public String getIsFriend() {
		return isFriend;
	}

	public void setIsFriend(String isFriend) {
		this.isFriend = isFriend;
	}
	
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getTimelineUri() {
		return timelineUri;
	}
	public void setTimelineUri(String timelineUri) {
		this.timelineUri = timelineUri;
	}
}
