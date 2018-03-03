package com.beproject.usermanagement.models;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name = "UserPreference")
public class UserPreference implements Serializable
{
	enum mode {offline,online}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long preferenceid;
	
	private String communicationLang;
	private mode communicationMode;
	
	private Time startTime;
	
	private Time endTime;
	private long userid;
	
	public long getPreferenceid() {
		return preferenceid;
	}
	public void setPreferenceid(long preferenceid) {
		this.preferenceid = preferenceid;
	}
	public String getCommunicationLang() {
		return communicationLang;
	}
	public void setCommunicationLang(String communicationLang) {
		this.communicationLang = communicationLang;
	}
	public mode getCommunicationMode() {
		return communicationMode;
	}
	public void setCommunicationMode(mode communicationMode) {
		this.communicationMode = communicationMode;
	}
	public Time getStartTime() {
		return startTime;
	}
	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
	
	
	
}
