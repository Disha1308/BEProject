package com.beproject.QAmanagement.dto;

import java.util.Date;

import com.beproject.QAmanagement.configuration.JsonDateSerializer;
import com.beproject.QAmanagement.models.Notification.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class NotificationDTO 
{
	private long notificationid;
	private notificationtype ntype;
	private String username;
	private String questiontitle;
	private notificationstatus state;
	private String negotiationStatus;
	private long questionid;
	private Date timestamp; //question timestamp
	
	public long getNotificationid() {
		return notificationid;
	}
	public void setNotificationid(long notificationid) {
		this.notificationid = notificationid;
	}
	
	public long getQuestionid() {
		return questionid;
	}
	public void setQuestionid(long questionid) {
		this.questionid = questionid;
	}
	
	@JsonSerialize(using=JsonDateSerializer.class)
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
	public notificationtype getNtype() {
		return ntype;
	}
	public void setNtype(notificationtype ntype) {
		this.ntype = ntype;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getQuestiontitle() {
		return questiontitle;
	}
	public void setQuestiontitle(String questiontitle) {
		this.questiontitle = questiontitle;
	}
	public notificationstatus getState() {
		return state;
	}
	public void setState(notificationstatus state) {
		this.state = state;
	}
	public String getNegotiationStatus() {
		return negotiationStatus;
	}
	public void setNegotiationStatus(String negotiationStatus) {
		this.negotiationStatus = negotiationStatus;
	}
}
