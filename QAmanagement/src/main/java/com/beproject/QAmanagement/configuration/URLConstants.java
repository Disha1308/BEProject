package com.beproject.QAmanagement.configuration;

public interface URLConstants {

	String GET_USER_INTRESTED_QUESTIONS_URL = "/user/{userid}/interestedquestions/{pageno}"; //t
	String GET_USER_EXPERTISE_QUESTIONS_URL = "/user/{userid}/expertisequestions/{pageno}"; //t
	
	String GET_USER_ASKED_URL = "/user/{userid}/askedquestions/{pageno}";//t
	String GET_USER_ANSWERS_URL = "/user/{userid}/answers/{pageno}";	//t
	
	String GET_QUESTION_ANSWERS_URL = "/question/{questionid}"; //t
	
	String POST_ANSWER_URL = "/answer";	//t
	String POST_ANSWER_VOTE_URL = "/answer/vote"; //t
	String POST_QUESTION_VOTE_URL = "/question/vote";//t
	
	String POST_QUESTION_URL = "/question"; //t
	String GET_QUESTION_TAG_URL = "/question/{questionid}/tags"; //t
	
	String POST_NEGOTIATION_URL = "/negotiate";
	String PUT_NEGOTIATION_URL = "/negotiate/changestatus";
	
	String GET_NOTIFICATION_URL ="/notification/{userid}";
		
}
