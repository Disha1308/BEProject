package com.beproject.usermanagement.rest;


import java.util.List;

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
public class UserController {
	@Autowired
	UserMgmtService userServe;

	//for frontend t
	@GET
	@Path(URLConstants.GET_USER_URL)
	@Produces(MediaType.APPLICATION_JSON)
	public User getUserbyEmail(@PathParam("email") String email) {
		System.out.println("in get one db user service");
		User u = userServe.getUserbyEmail(email);
		return u;

	}
	
	//may be required t
	@GET
	@Path(URLConstants.GET_USER_ID_URL)
	@Produces(MediaType.APPLICATION_JSON)
	public User getUserbyId(@PathParam("id") long uid) {
		System.out.println("in get one db user service");
		User u = userServe.getUserbyId(uid);

		return u;
	}
	
	//may be required t
	@GET
	@Path(URLConstants.GET_USER_USERNAME_URL)
	@Produces(MediaType.APPLICATION_JSON)
	public User getUserbyUsername(@PathParam("username") String username) {
		System.out.println("in get one db user service");
		User u = userServe.getUserbyUsername(username);
		return u;
	}
	
	@GET
	@Path(URLConstants.GET_USERNAME_URL)
	@Produces(MediaType.APPLICATION_JSON)
	public String getUsername(@PathParam("userid") long uid) {
		System.out.println("in get username controller");
		return userServe.getUsername(uid);
	}

	//for frontend t
	@POST
	@Path(URLConstants.POST_USER_URL)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean createUser(@RequestBody User u) {
		System.out.println("in post db user service");
		return userServe.createUser(u);
	}

	//for frontend t
	@PUT
	@Path(URLConstants.PUT_USER_URL)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean updateUser(@RequestBody User u, @PathParam("id") long id) {
		System.out.println("in put user db service");
		return userServe.updateUserdetails(u, id);
	}

	//for interservice t
	@GET
	@Path(URLConstants.VALIDATE_USER_URL)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean validateUser(@PathParam("id") long id) {
		System.out.println("in validate topic service");
		return userServe.validateuserid(id);
	}
	
	//for frontend t
	@GET
	@Path(URLConstants.SEARCH_USER_URL)
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> searchTopic(@PathParam("str") String str) {
		System.out.println("in validate topic service");
		return userServe.searchuser(str);
	}
}
