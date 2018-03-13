package com.beproject.usermanagement.dto;

import com.beproject.usermanagement.models.UserPreference;

public class ExpertDTO 
{
	public enum expertStatus{available, unavailable};
	
	private long id;	
	private String username;
	private UserPreference preference;
	private expertStatus availability;
	private long answercount;
	
	public long getAnswercount() {
		return answercount;
	}
	public void setAnswercount(long answercount) {
		this.answercount = answercount;
	}
	public expertStatus getAvailability() {
		return availability;
	}
	public void setAvailability(expertStatus availability) {
		this.availability = availability;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public UserPreference getPreference() {
		return preference;
	}
	public void setPreference(UserPreference preference) {
		this.preference = preference;
	}
}
