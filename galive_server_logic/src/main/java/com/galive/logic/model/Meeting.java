package com.galive.logic.model;

import java.util.ArrayList;
import java.util.List;


public class Meeting extends BaseModel {

	private String room;
	
	private MeetingMember holder;
	
	private List<MeetingMember> members = new ArrayList<>();
	
	private MeetingOptions options;

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public List<MeetingMember> getMembers() {
		return members;
	}

	public void setMembers(List<MeetingMember> members) {
		this.members = members;
	}

	public MeetingOptions getOptions() {
		return options;
	}

	public void setOptions(MeetingOptions options) {
		this.options = options;
	}

	public MeetingMember getHolder() {
		return holder;
	}

	public void setHolder(MeetingMember holder) {
		this.holder = holder;
	}
	
	
	
}
