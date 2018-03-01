package com.beproject.QAmanagement.rest;

import javax.inject.Named;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import com.beproject.QAmanagement.configuration.URLConstants;
import com.beproject.QAmanagement.models.*;
import com.beproject.QAmanagement.service.*;


@Named
@Path("/v1.0/")
public class NegotiationController 
{
	@Autowired
	NegotiationService nserve;
	
	@POST
	@Path(URLConstants.POST_NEGOTIATION_URL)
	public boolean postnegotiationmessage(@RequestBody NegotiationMessage m) {
		System.out.println("in post negotiation message controller");	
		return nserve.createNegotiation(m);
	}
	
	@PUT
	@Path(URLConstants.PUT_NEGOTIATION_URL)
	public boolean changestatus(@RequestBody NegotiationMessage m) {
		System.out.println("in put negotiation message controller");	
		return nserve.changestatus(m);
	}
	
}
