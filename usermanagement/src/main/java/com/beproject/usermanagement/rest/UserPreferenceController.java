package com.beproject.usermanagement.rest;


import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import com.beproject.usermanagement.configurations.URLConstants;
import com.beproject.usermanagement.models.*;
import com.beproject.usermanagement.service.*;


@Named
@Path("/v1.0/")
public class UserPreferenceController {
	@Autowired
	UserPreferenceService preferenceServe;

	//for frontend. t
	@GET
	@Path(URLConstants.GET_USER_PREFERENCE_URL)
	@Produces(MediaType.APPLICATION_JSON)
	public UserPreference getUserpreference(@PathParam("userid") long userid) {
		System.out.println("in get user preference rest");
		return preferenceServe.getByUserid(userid);
		}
	
	/*//may be required.
	@POST
	@Path(URLConstants.POST_USER_PREFERENCE_URL)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean createUserpreference(@RequestBody UserPreference u) {
		System.out.println("in post user preference rest");
		return preferenceServe.createUserPreference(u);
	}*/

	//for frontend. t
	@POST
	@Path(URLConstants.POST_USER_PREFERENCE_URL)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean updateUser(@RequestBody UserPreference u, @PathParam("userid") long id) {
		System.out.println("in post user preference rest");
		return preferenceServe.updateUserPreference(u,id);
	}

	
}
