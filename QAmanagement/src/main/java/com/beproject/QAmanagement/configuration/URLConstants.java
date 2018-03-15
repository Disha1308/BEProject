package com.beproject.QAmanagement.configuration;

public interface URLConstants {

	String GET_USER_INTRESTED_QUESTIONS_URL = "/user/{userid}/interestedquestions/{pageno}";
	String GET_USER_EXPERTISE_QUESTIONS_URL = "/user/{userid}/expertisequestions/{pageno}";
	String GET_PAGENO_URL = "/pageno/{topictype}/{userid}";
	
	String GET_USER_ASKED_URL = "/user/{userid}/askedquestions/{pageno}";
	String GET_USER_ANSWERS_URL = "/user/{userid}/answers/{pageno}";	
	
	String GET_QUESTION_ANSWERS_URL = "/question/{questionid}";
	
	String POST_ANSWER_URL = "/answer";	
	String POST_ANSWER_VOTE_URL = "/answer/vote";
	String POST_QUESTION_VOTE_URL = "/question/vote";
	
	String POST_QUESTION_URL = "/question";
	String GET_QUESTION_TAG_URL = "/question/{questionid}/tags"; 
	String GET_EXPERTANSWER_URL = "/{questionid}/expertanswers/{userid}";
	
	String POST_NEGOTIATION_URL = "/negotiate";
	String POST_NEGOTIATION_MSGS_URL = "/negotiatemsgs/";
	String PUT_NEGOTIATION_URL = "/negotiate/changestatus";
	String GET_QUESTION_NEGOTIATED_EXPERT_URL = "/negotiate/{questionid}";
	
	String GET_NOTIFICATION_URL ="/notification/{userid}/{pageno}";
	String CHANGE_NOTIFCATION_STATUS_URL ="/notification/markread/{notificationid}";
	
	String SEARCH_QUESTION_URL = "/search/{keywords}";
}
