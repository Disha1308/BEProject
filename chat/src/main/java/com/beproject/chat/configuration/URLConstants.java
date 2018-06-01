package com.beproject.chat.configuration;

public interface URLConstants
{

	String POST_CHAT_ANSWER_URL = "/chatanswer";
	String POST_CHAT_SESSION_URL = "/chatsession/{userid}";
	String GET_END_SESSION_URL = "/endsession/{sessionid}/{userid}";
	String GET_SESSION_ID_URL = "/sessionid/{userid}/{otheruserid}/{questionid}";
	String GET_SESSION_ANSWERS_URL = "/session/{sessionid}";
	String GET_QUESTION_SESSION_URL ="/questionsession/{questionid}";

}
