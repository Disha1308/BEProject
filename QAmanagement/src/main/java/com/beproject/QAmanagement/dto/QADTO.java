package com.beproject.QAmanagement.dto;

import java.util.Date;
import java.util.List;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.beproject.QAmanagement.configuration.JsonDateDeserializer;
import com.beproject.QAmanagement.configuration.JsonDateSerializer;
import com.beproject.QAmanagement.models.Question.languages;
import com.beproject.QAmanagement.models.Question.mode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class QADTO 
{
	
	QuestionDTO question;
	String questiontext;
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date preferredTime;
	private languages preferredLanguage;
	private mode communicationMode;
	List<AnswerDTO> answers;
	
	
	public String getQuestiontext() {
		return questiontext;
	}
	public void setQuestiontext(String questiontext) {
		this.questiontext = questiontext;
	}
	@JsonSerialize(using=JsonDateSerializer.class)
	public Date getPreferredTime() {
		return preferredTime;
	}
	
	@JsonDeserialize(using = JsonDateDeserializer.class)
	public void setPreferredTime(Date preferredTime) {
		this.preferredTime = preferredTime;
	}
	public languages getPreferredLanguage() {
		return preferredLanguage;
	}
	public void setPreferredLanguage(languages preferredLanguage) {
		this.preferredLanguage = preferredLanguage;
	}
	public mode getCommunicationMode() {
		return communicationMode;
	}
	public void setCommunicationMode(mode communicationMode) {
		this.communicationMode = communicationMode;
	}
	
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
