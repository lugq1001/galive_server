package com.galive.logic.model;

import java.util.*;

/**
 * 房间
 * @author luguangqing
 *
 */
public class Room extends BaseModel {
	
	/**
	 * 房间主持人
	 */
	private String ownerSid = "";
	
	/**
	 * 房间内成员
	 */
	private Set<String> members = new HashSet<>();

	private Map<String, Object> extraInfo = new HashMap<>();


	public String getOwnerSid() {
		return ownerSid;
	}

	public void setOwnerSid(String ownerSid) {
		this.ownerSid = ownerSid;
	}

	public Set<String> getMembers() {
		return members;
	}

	public void setMembers(Set<String> members) {
		this.members = members;
	}

	public Map<String, Object> getExtraInfo() {
		return extraInfo;
	}

	public void setExtraInfo(Map<String, Object> extraInfo) {
		this.extraInfo = extraInfo;
	}
}
