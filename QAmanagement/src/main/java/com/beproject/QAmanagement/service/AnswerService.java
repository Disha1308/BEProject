package com.beproject.QAmanagement.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.beproject.QAmanagement.models.*;
import com.beproject.QAmanagement.models.Notification.notificationstatus;
import com.beproject.QAmanagement.models.Notification.notificationtype;
import com.beproject.QAmanagement.repository.*;


@Service
public class AnswerService 
{
	@Autowired
	AnswerRepository answerRepo;
	
	@Autowired
	QuestionService qservice;
	
	@Autowired
	NotificationRepository nRepo;
	
	Notification n;
	
	AnswerService()
	{
		n = new Notification();
	}
	
	// t
	public boolean validateanswerid(long aid) {
		if(answerRepo.findOne(aid) != null)
			return true;
		return false;

	}
	
	//used 
	//tested
	public boolean createAnswer(Answers a)
	{
		System.out.println("in create answer service");
	try{
		RestTemplate restT = new RestTemplate();
		ResponseEntity<Boolean> response = restT.exchange("http://localhost:8080/v1.0/validateuser/"+a.getUserid(),
			    HttpMethod.GET, null, boolean.class);
		if(response.getBody()==true)
		{
		try {
			a.setTimestamp(new Date());
			answerRepo.save(a);
			
			a = answerRepo.findunique(a.getUserid(), a.getQuestionid());
			Question q = qservice.getOneQuestionService(a.getQuestionid());
			//create Notification
			n.setType(notificationtype.answer);
			n.setAttributeid(a.getAnswerid());
			n.setTimestamp(new Date());
			n.setState(notificationstatus.unread);
			n.setUserid(q.getUserid());
			nRepo.save(n);			
			
			return true;
		} catch (Exception e) {
			return false;
		}
		}
	}
	catch(Exception e)
	{
		System.out.println("user management error");
	}
	return false;
	}

	//used t
	public List<Answers> getbyquestionid(long qid)
	{
		if(qservice.validatequestionid(qid))
		{
			return answerRepo.findByquestionid(qid);
		}
		return null;
	}
	
	//used t
	public List<Answers> getuseranswers(long uid)
	{
		try{
			RestTemplate restT = new RestTemplate();
			ResponseEntity<Boolean> response = restT.exchange("http://localhost:8080/v1.0/validateuser/"+uid,
				    HttpMethod.GET, null, boolean.class);
			if(response.getBody()==true)
		{
			return answerRepo.findByuserid(uid);
		}
		}
		catch (Exception e) {
			System.out.println("user management not available");
		}
		return null;
	}
	
	
}
