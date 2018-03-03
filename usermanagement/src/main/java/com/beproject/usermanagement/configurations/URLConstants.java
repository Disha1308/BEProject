package com.beproject.usermanagement.configurations;

public interface URLConstants {

		static final String GET_USER_URL = "/users/{email}"; //t
		static final String GET_USER_ID_URL = "/users/id/{id}"; //t
		static final String GET_USER_USERNAME_URL = "/users/username/{username}";//t
		static final String POST_USER_URL = "/users"; //t
		static final String PUT_USER_URL = "/users/{id}";//t
		static final String VALIDATE_USER_URL = "/validateuser/{id}";//t
		static final String SEARCH_USER_URL = "/searchuser/{str}";	//t
		
		static final String PUT_USER_PREFERENCE_URL = "/userpreference/{userid}"; //t
		static final String GET_USER_PREFERENCE_URL = "/userpreference/{userid}"; //t
		
		static final String SUBSCRIBE_TAG_URL = "/subscribe";//t
		static final String UNSUBSCRIBE_TAG_URL = "/{userid}/unsubscribe/{tagid}";//t
		static final String GET_USER_INTERESTED_TAG_URL = "/interestedtags/{userid}"; //t
		static final String GET_USER_EXPERTISE_TAG_URL = "/expertisetags/{userid}"; //t
		static final String EXPERTS_URL = "/experts/{questionid}";


}
