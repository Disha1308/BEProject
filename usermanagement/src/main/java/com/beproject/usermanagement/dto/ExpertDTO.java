package com.beproject.usermanagement.dto;

import com.beproject.usermanagement.models.UserPreference;

public class ExpertDTO 
{
	private long id;	
	private String username;
	private UserPreference preference;
	
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
