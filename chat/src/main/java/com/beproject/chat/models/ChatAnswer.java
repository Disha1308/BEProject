package com.beproject.chat.models;

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

import com.beproject.chat.configuration.JsonDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;


@Entity
@Table(name = "ChatAnswer")
public class ChatAnswer
{
	@Id
	@GeneratedValue
	private long chatanswerid;
	private long userid;
	
	private long chatsessionid;
	@Lob
	@Type(type = "org.hibernate.type.TextType")
	private String answerText;
	
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date timestamp;

	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

	public long getChatanswerid() {
		return chatanswerid;
	}

	public void setChatanswerid(long chatanswerid) {
		this.chatanswerid = chatanswerid;
	}

	
	public long getChatsessionid() {
		return chatsessionid;
	}

	public void setChatsessionid(long chatsessionid) {
		this.chatsessionid = chatsessionid;
	}

	public String getAnswerText() {
		return answerText;
	}

	public void setAnswerText(String answerText) {
		this.answerText = answerText;
	}

	@JsonSerialize(using=JsonDateSerializer.class)
	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	}
