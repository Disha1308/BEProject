package com.beproject.QAmanagement.dto;

import java.util.List;

import com.beproject.QAmanagement.models.Question;

public class QADTO 
{
	Question question;	
	long questionupvote;
	long questiondownvote;
	List<String> qtagname;
	List<AnswerDTO> answers;
	
	public List<String> getQtagname() {
		return qtagname;
	}
	public void setQtagname(List<String> qtagname) {
		this.qtagname = qtagname;
	}
	public long getQuestionupvote() {
		return questionupvote;
	}
	public void setQuestionupvote(long questionupvote) {
		this.questionupvote = questionupvote;
	}
	public long getQuestiondownvote() {
		return questiondownvote;
	}
	public void setQuestiondownvote(long questiondownvote) {
		this.questiondownvote = questiondownvote;
	}
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	public List<AnswerDTO> getAnswers() {
		return answers;
	}
	public void setAnswers(List<AnswerDTO> answers) {
		this.answers = answers;
	}
	
	
}
