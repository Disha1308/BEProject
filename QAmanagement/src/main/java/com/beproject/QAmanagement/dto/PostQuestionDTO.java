package com.beproject.QAmanagement.dto;

import java.util.List;

import com.beproject.QAmanagement.models.*;

public class PostQuestionDTO 
{
	private Question question;
	private List<Long> tagid;
	
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	public List<Long> getTagid() {
		return tagid;
	}
	public void setTagid(List<Long> tagid) {
		this.tagid = tagid;
	}
	
	
}
