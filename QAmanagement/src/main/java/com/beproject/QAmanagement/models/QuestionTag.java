package com.beproject.QAmanagement.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "QuestionsTags")
public class QuestionTag implements Serializable
{
	@Id
	@GeneratedValue
	private long id;
	
	private long questionid;
	private long tagid;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getQuestionid() {
		return questionid;
	}
	public void setQuestionid(long questionid) {
		this.questionid = questionid;
	}
	public long getTagid() {
		return tagid;
	}
	public void setTagid(long tagid) {
		this.tagid = tagid;
	}

}
