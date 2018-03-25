package com.beproject.chat.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.beproject.chat.configuration.URLConstants;
import com.beproject.chat.models.*;
import com.beproject.chat.service.ChatAnswerService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class ChatAnswerController 
{
	
	@Autowired
	ChatAnswerService answerServe;
	
	
	@MessageMapping(value=URLConstants.POST_CHAT_SESSION_URL)
	public void createSession(String u, @DestinationVariable("userid") long uid) {
		 
		System.out.println("in post session rest");
		u = u.replaceAll("\\\\", "");
		ObjectMapper mapper = new ObjectMapper();
		ChatSession session;
		try {
			session = mapper.readValue(u, ChatSession.class);
			answerServe.createsession(session,uid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
		 }

	
	@MessageMapping(value=URLConstants.POST_CHAT_ANSWER_URL)
	public void createAnswer(String u) {
		System.out.println("in post db chat answer controller");
		u = u.replaceAll("\\\\", "");
		ObjectMapper mapper = new ObjectMapper();
		ChatAnswer msg;
		try {
			msg = mapper.readValue(u, ChatAnswer.class);
			answerServe.createAnswer(msg);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	
	@RequestMapping(value=URLConstants.GET_END_SESSION_URL,method=RequestMethod.GET)
	public void createAnswer(@PathVariable("sessionid") long sid,@PathVariable("userid") long uid) {
		System.out.println("in change user session status rest");
		 answerServe.changestatus(sid,uid);
		 }
	
	@RequestMapping(value=URLConstants.GET_SESSION_ID_URL,method=RequestMethod.GET)
	public @ResponseBody long getsessionid(@PathVariable("userid") long sid,@PathVariable("otheruserid") long eid,@PathVariable("questionid") long qid) {
		System.out.println("in change user session status rest");
		 return answerServe.getsessionid(sid,eid,qid);
		 }
	
	@RequestMapping(value=URLConstants.GET_SESSION_ANSWERS_URL,method=RequestMethod.GET)
	public @ResponseBody List<ChatAnswer> getsessionanswers(@PathVariable("sessionid") long sid) {
		System.out.println("in change user session status rest");
		 return answerServe.getsessionanswers(sid);
		 }

}
