package com.beproject.QAmanagement.dto;

import java.util.List;

import com.beproject.QAmanagement.models.Question;

public class QADTO 
{
	Question question;
	List<AnswerDTO> answers;
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
