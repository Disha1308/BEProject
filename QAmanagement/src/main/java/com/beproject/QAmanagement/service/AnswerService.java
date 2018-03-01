package com.beproject.QAmanagement.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	public boolean validateanswerid(long aid) {
		if(answerRepo.findOne(aid) != null)
			return true;
		return false;

	}
	//used
	public boolean createAnswer(Answers a)
	{
		System.out.println("in create question service");
		try {
			answerRepo.save(a);
			
			a = answerRepo.findunique(a.getUserid(), a.getQuestionid());
			Question q = qservice.getOneQuestionService(a.getQuestionid());
			//create Notification
			Notification n = new Notification();
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

	//used
	public List<Answers> getbyquestionid(long qid)
	{
		if(qservice.validatequestionid(qid))
		{
			return answerRepo.findByquestionid(qid);
		}
		return null;
	}
	
	//used
	public List<Answers> getuseranswers(long uid)
	{
		//todo validation for userid
		{
			return answerRepo.findByuserid(uid);
		}		
	}
	
	
}
