package com.beproject.QAmanagement.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "NegotiationMessage")
public class NegotiationMessage {
	
	public enum status{unread,accept,reject};
	@Id
	@GeneratedValue
	private long messageid;
	private long seekerid;
	private long expertid;
	private long questionid;
	
	private status messagestatus;
	
	public status getMessagestatus() {
		return messagestatus;
	}
	public void setMessagestatus(status messagestatus) {
		this.messagestatus = messagestatus;
	}
	public long getMessageid() {
		return messageid;
	}
	public void setMessageid(long messageid) {
		this.messageid = messageid;
	}
	public long getSeekerid() {
		return seekerid;
	}
	public void setSeekerid(long seekerid) {
		this.seekerid = seekerid;
	}
	public long getExpertid() {
		return expertid;
	}
	public void setExpertid(long expertid) {
		this.expertid = expertid;
	}
	public long getQuestionid() {
		return questionid;
	}
	public void setQuestionid(long questionid) {
		this.questionid = questionid;
	}
	

}
