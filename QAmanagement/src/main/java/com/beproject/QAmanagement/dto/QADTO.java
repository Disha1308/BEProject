package com.beproject.QAmanagement.dto;

import java.util.List;

public class QADTO 
{
	
	QuestionDTO question;
	List<AnswerDTO> answers;
	
	
	public QuestionDTO getQuestion() {
		return question;
	}
	public void setQuestion(QuestionDTO question) {
		this.question = question;
	}
	
		public List<AnswerDTO> getAnswers() {
		return answers;
	}
	public void setAnswers(List<AnswerDTO> answers) {
		this.answers = answers;
	}
	
	
}
