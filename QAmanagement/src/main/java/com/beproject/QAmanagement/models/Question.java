package com.beproject.QAmanagement.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

import com.beproject.QAmanagement.configuration.JsonDateDeserializer;
import com.beproject.QAmanagement.configuration.JsonDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "Questions")
public class Question implements Serializable {

	public enum mode{offline, online};
	public enum status{open,close};
	@Id
	@GeneratedValue
	private long questionid;
	
	@Lob
	@Type(type = "org.hibernate.type.TextType")
	private String questionText;
	
	private String title;
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date preferredTime;
	private String preferredLanguage;
	private mode communicationMode;
	private long userid;
	private status state;
	
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date timestamp;
	
	@JsonSerialize(using=JsonDateSerializer.class)
	public Date getTimestamp() {
		return timestamp;
	}
	
	@JsonDeserialize(using = JsonDateDeserializer.class)
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public long getQuestionid() {
		return questionid;
	}
	public void setQuestionid(long questionid) {
		this.questionid = questionid;
	}
	public String getQuestionText() {
		return questionText;
	}
	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPreferredLanguage() {
		return preferredLanguage;
	}
	public void setPreferredLanguage(String preferredLanguage) {
		this.preferredLanguage = preferredLanguage;
	}
	public mode getCommunicationMode() {
		return communicationMode;
	}
	public void setCommunicationMode(mode communicationMode) {
		this.communicationMode = communicationMode;
	}
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
	public status getState() {
		return state;
	}
	public void setState(status state) {
		this.state = state;
	}
	@JsonSerialize(using=JsonDateSerializer.class)
	public Date getPreferredTime() {
		return preferredTime;
	}
	
	@JsonDeserialize(using = JsonDateDeserializer.class)
	public void setPreferredTime(Date preferredTime) {
		this.preferredTime = preferredTime;
	}
	
 
}
