package com.galive.logic.model;

public class Live {

	private String sid = "";
	
	private String name = "";
	
	private String ownerSid = "";
	
	private long createAt = System.currentTimeMillis();
	
	private long latestLiveAt = System.currentTimeMillis();

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOwnerSid() {
		return ownerSid;
	}

	public void setOwnerSid(String ownerSid) {
		this.ownerSid = ownerSid;
	}

	public long getCreateAt() {
		return createAt;
	}

	public void setCreateAt(long createAt) {
		this.createAt = createAt;
	}

	public long getLatestLiveAt() {
		return latestLiveAt;
	}

	public void setLatestLiveAt(long latestLiveAt) {
		this.latestLiveAt = latestLiveAt;
	}
	
	
}
