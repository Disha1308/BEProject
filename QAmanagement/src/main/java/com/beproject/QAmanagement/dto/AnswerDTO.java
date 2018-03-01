package com.beproject.QAmanagement.dto;


import com.beproject.QAmanagement.models.Answers;

public class AnswerDTO 
{
	private Answers answer;
	private long upvote;
	private long downvote;
	
	public Answers getAnswer() {
		return answer;
	}
	public void setAnswer(Answers answer) {
		this.answer = answer;
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
