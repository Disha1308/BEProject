package com.beproject.chat.configuration;

public interface URLConstants
{

	String POST_CHAT_ANSWER_URL = "/chatanswer";
	String POST_CHAT_SESSION_URL = "/chatsession/{userid}";
	String GET_END_SESSION_URL = "/endsession/{sessionid}/{userid}";

}
