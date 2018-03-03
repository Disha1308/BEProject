package com.beproject.usermanagement.rest;


import java.util.List;

import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import com.beproject.usermanagement.configurations.URLConstants;
import com.beproject.usermanagement.dto.ExpertDTO;
import com.beproject.usermanagement.dto.ExpertDTOService;
import com.beproject.usermanagement.models.*;
import com.beproject.usermanagement.service.*;


@Named
@Path("/v1.0/")
public class UserTopicController 
{
	@Autowired
	UserTopicService tagservice;
	
	@Autowired
	UserMgmtService uservice;
	
	@Autowired
	UserPreferenceService upservice;
	
	@Autowired
	ExpertDTOService eservice ;
	
	
	
	//for frontend. t
	@POST
	@Path(URLConstants.SUBSCRIBE_TAG_URL)
	public boolean subscribe(@RequestBody UserTopicStatus tagstatus) {
		System.out.println("in subscribe rest");
		return tagservice.subscribetoTopic(tagstatus);
	}
	
	//for frontend. t
		@GET
		@Path(URLConstants.UNSUBSCRIBE_TAG_URL)
		public boolean unsubscribe(@PathParam("userid") long uid,@PathParam("tagid") long tid ) {
			System.out.println("in unsubscribe rest");
			return tagservice.unsubscribetoTopic(uid, tid);
		}
	
	//for inter service tested
	@GET
	@Path(URLConstants.GET_USER_INTERESTED_TAG_URL)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Long> getUserinterestedtopicids(@PathParam("userid") long userid) {
		 System.out.println("in get user interested topic rest");
		 return tagservice.getinterestedtopics(userid);
	}
	
	//for interservice tested
	@GET
	@Path(URLConstants.GET_USER_EXPERTISE_TAG_URL)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Long> getUserexpertisetopics(@PathParam("userid") long userid) {
		System.out.println("in get user expertise topic rest");
		 return tagservice.getexpertisetopics(userid);
	}
	
	//for frontend t
	@GET
	@Path(URLConstants.EXPERTS_URL)
	@Produces(MediaType.APPLICATION_JSON)
	public List<ExpertDTO> getexpertsid(@PathParam("questionid") long qid) {
		System.out.println("in get experts rest");
		return eservice.getexperts(qid);
	}
	
}
