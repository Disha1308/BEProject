package com.beproject.chat.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "ChatSession")
public class ChatSession {
	
	@Id
	@GeneratedValue
	private long chatsessionid;
	
	private long seekerid;
	
	private long expertid;
	
	private long questionid;
	private boolean seekerstatus;
	private boolean expertstatus;
	
	public long getChatsessionid() {
		return chatsessionid;
	}
	public void setChatsessionid(long chatsessionid) {
		this.chatsessionid = chatsessionid;
	}
	
	public long getQuestionid() {
		return questionid;
	}
	public void setQuestionid(long questionid) {
		this.questionid = questionid;
	}
	public boolean isSeekerstatus() {
		return seekerstatus;
	}
	public void setSeekerstatus(boolean seekerstatus) {
		this.seekerstatus = seekerstatus;
	}
	public boolean isExpertstatus() {
		return expertstatus;
	}
	public void setExpertstatus(boolean expertstatus) {
		this.expertstatus = expertstatus;
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
}
