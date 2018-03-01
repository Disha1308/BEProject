package com.beproject.QAmanagement.models;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "Notification")
public class Notification 
{
	public enum notificationtype {answer, seekerrequest, requeststatus, discussion}
	public enum notificationstatus{read,unread};
	@Id
	@GeneratedValue
	long notificationid;
	
	private long attributeid;
	private long userid;
	private notificationtype type;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date timestamp;
	private notificationstatus state;
	
	public notificationstatus getState() {
		return state;
	}
	public void setState(notificationstatus state) {
		this.state = state;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public long getNotificationid() {
		return notificationid;
	}
	public void setNotificationid(long notificationid) {
		this.notificationid = notificationid;
	}
	public long getAttributeid() {
		return attributeid;
	}
	public void setAttributeid(long attributeid) {
		this.attributeid = attributeid;
	}
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
	public notificationtype getType() {
		return type;
	}
	public void setType(notificationtype type) {
		this.type = type;
	}
	
	
}
