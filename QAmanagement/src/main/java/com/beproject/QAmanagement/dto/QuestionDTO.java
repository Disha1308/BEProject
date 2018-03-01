package com.beproject.QAmanagement.dto;

import java.util.Date;
import java.util.List;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.beproject.QAmanagement.models.Question.status;

public class QuestionDTO 
{

	
	private long questionid;
	private String title;
	private long upvote;
	private long downvote;
	private status state;
	private List<Long> tagidlist;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date timestamp;
	
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public List<Long> getTagidlist() {
		return tagidlist;
	}
	public void setTagidlist(List<Long> tagidlist) {
		this.tagidlist = tagidlist;
	}
	public long getQuestionid() {
		return questionid;
	}
	public void setQuestionid(long questionid) {
		this.questionid = questionid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public status getState() {
		return state;
	}
	public void setState(status state) {
		this.state = state;
	}
}
