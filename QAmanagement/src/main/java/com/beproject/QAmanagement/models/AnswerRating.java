package com.beproject.QAmanagement.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.beproject.QAmanagement.models.QuestionRating.type;


@Entity
@Table(name = "AnswerRating")
public class AnswerRating implements Serializable
{
	@Id
	@GeneratedValue
	private long aratingid;
	
	private long answerid;
	private type vote;
	private long userid;
	
	
	public long getAratingid() {
		return aratingid;
	}
	public void setAratingid(long aratingid) {
		this.aratingid = aratingid;
	}
	public long getAnswerid() {
		return answerid;
	}
	public void setAnswerid(long answerid) {
		this.answerid = answerid;
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
