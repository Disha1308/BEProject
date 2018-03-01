package com.beproject.usermanagement.configurations;

public interface URLConstants {

		static final String GET_USER_URL = "/users/{email}";
		static final String GET_USER_ID_URL = "/users/id/{id}";
		static final String GET_USER_USERNAME_URL = "/users/username/{username}";
		static final String POST_USER_URL = "/users";
		static final String PUT_USER_URL = "/users/{id}";
		static final String VALIDATE_USER_URL = "/validateuser/{id}";
		static final String SEARCH_USER_URL = "/searchuser/{str}";	
		
		static final String PUT_USER_PREFERENCE_URL = "/userprefernce/{userid}";
		static final String POST_USER_PREFERENCE_URL = "/userpreference";
		static final String GET_USER_PREFERENCE_URL = "/userprefernce/{userid}";
		
		static final String SUBSCRIBE_TAG_URL = "/subscribe";
		static final String UNSUBSCRIBE_TAG_URL = "/{userid}/unsubscribe/{tagid}";
		static final String GET_USER_INTERESTED_TAG_URL = "/interestedtags/{userid}";
		static final String GET_USER_EXPERTISE_TAG_URL = "/expertisetags/{userid}";
		static final String EXPERTS_URL = "/experts";


}
