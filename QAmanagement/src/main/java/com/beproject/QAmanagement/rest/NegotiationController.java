package com.beproject.QAmanagement.rest;

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

import com.beproject.QAmanagement.configuration.URLConstants;
import com.beproject.QAmanagement.dto.*;
import com.beproject.QAmanagement.models.*;
import com.beproject.QAmanagement.service.*;


@Named
@Path("/v1.0/")
public class NegotiationController 
{
	@Autowired
	NegotiationService nserve;
	
	@Autowired
	NotificationDTOService notifyserve;
	
	@POST
	@Path(URLConstants.POST_NEGOTIATION_URL)
	public boolean postnegotiationmessage(@RequestBody NegotiationMessage m) {
		System.out.println("in post negotiation message controller");	
		return nserve.createNegotiation(m);
	}
	
	@POST
	@Path(URLConstants.POST_NEGOTIATION_MSGS_URL)
	public boolean postnegotiationmessages(@RequestBody List<NegotiationMessage> mlist) {
		System.out.println("in post multiple negotiation message controller");	
		int i=0;
		while(i<mlist.size())
		{
		 if(!nserve.createNegotiation(mlist.get(i++)))
			 return false;
		}
		return true;
	}
	
	@PUT
	@Path(URLConstants.PUT_NEGOTIATION_URL)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean changestatus(@RequestBody NegotiationMessage m) {
		System.out.println("in put negotiation message controller");	
		return nserve.changestatus(m);
	}
	
	@GET
	@Path(URLConstants.GET_QUESTION_NEGOTIATED_EXPERT_URL)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Long> getNegotiatedExperts(@PathParam("questionid") long qid) {
		System.out.println("in get negotiated question controller");	
		return nserve.getExperts(qid);
	}
	
	@GET
	@Path(URLConstants.GET_NOTIFICATION_URL)
	@Produces(MediaType.APPLICATION_JSON)
	public List<NotificationDTO> getNotification(@PathParam("userid") long uid,@PathParam("pageno") int pageno) {
		System.out.println("in get notification controller");	
		return notifyserve.getnotifications(uid,pageno);
	}
	
	@GET
	@Path(URLConstants.CHANGE_NOTIFCATION_STATUS_URL)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean changeNotificationstatus(@PathParam("notificationid") long nid) {
		System.out.println("in change notification status controller");	
		return notifyserve.changenotificationstatus(nid);
	}
}
