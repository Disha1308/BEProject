package com.beproject.QAmanagement.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "QuestionsRating")
public class QuestionRating implements Serializable
{
	public enum type{upvote,downvote}
	@Id
	@GeneratedValue
	private long qratingid;
	
	private long questionid;
	private type vote;
	private long userid;
	
	public long getQratingid() {
		return qratingid;
	}
	public void setQratingid(long qratingid) {
		this.qratingid = qratingid;
	}
	public long getQuestionid() {
		return questionid;
	}
	public void setQuestionid(long questionid) {
		this.questionid = questionid;
	}
	public type getVote() {
		return vote;
	}
	public void setVote(type vote) {
		this.vote = vote;
	}
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
	
}
