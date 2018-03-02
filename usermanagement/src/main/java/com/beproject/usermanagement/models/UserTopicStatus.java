package com.beproject.usermanagement.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "UserTopicStatus")
public class UserTopicStatus implements Serializable 
{

	public enum topicstatus {interested,expertise};
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long topicstatusid;
	
	private long userid;
	private long topicid;
	private topicstatus status;
	
	public long getTopicstatusid() {
		return topicstatusid;
	}
	public void setTopicstatusid(long topicstatusid) {
		this.topicstatusid = topicstatusid;
	}
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
	public long getTopicid() {
		return topicid;
	}
	public void setTopicid(long topicid) {
		this.topicid = topicid;
	}
	public topicstatus getStatus() {
		return status;
	}
	public void setStatus(topicstatus status) {
		this.status = status;
	}

}
