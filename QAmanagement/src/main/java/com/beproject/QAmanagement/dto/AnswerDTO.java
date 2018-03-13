package com.beproject.QAmanagement.dto;

import java.util.Date;

import com.beproject.QAmanagement.configuration.JsonDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;


public class AnswerDTO 
{
	private long answerid;
	private long questionid;
	private String answertext;
	private String username;
	private String questionTitle;
	private long upvote;
	private long downvote;
	private Date timestamp; //question timestamp
	
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
	public long getAnswerid() {
		return answerid;
	}
	public void setAnswerid(long answerid) {
		this.answerid = answerid;
	}
	public String getAnswertext() {
		return answertext;
	}
	public void setAnswertext(String answertext) {
		this.answertext = answertext;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getQuestionTitle() {
		return questionTitle;
	}
	public void setQuestionTitle(String questionTitle) {
		this.questionTitle = questionTitle;
	}
	public long getUpvote() {
		return upvote;
	}
	public void setUpvote(long upvote) {
		this.upvote = upvote;
	}
	public long getDownvote() {
		return downvote;
	}
	public void setDownvote(long downvote) {
		this.downvote = downvote;
	}
		
}
