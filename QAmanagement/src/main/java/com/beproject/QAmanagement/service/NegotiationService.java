package com.beproject.QAmanagement.service;

import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beproject.QAmanagement.models.*;
import com.beproject.QAmanagement.models.NegotiationMessage.status;
import com.beproject.QAmanagement.models.Notification.notificationstatus;
import com.beproject.QAmanagement.models.Notification.notificationtype;
import com.beproject.QAmanagement.repository.*;


@Service
public class NegotiationService 
{
	@Autowired
	QuestionService qservice;
	
	@Autowired
	NegotiationMessageRepository nRepo;
	
	@Autowired
	NotificationRepository notifyRepo;
	
	@Autowired
	QuestionRepository qRepo;
	
	public boolean createNegotiation(NegotiationMessage m)
	{
		//todo validate seekerid
		//todo validate expertid
		if(qservice.validatequestionid(m.getQuestionid()))
		{
			NegotiationMessage msg = nRepo.findunique(m.getSeekerid(), m.getExpertid(), m.getQuestionid());
			if(msg== null)
			{
			if(m.getMessagestatus().equals(status.unread))
			{
				nRepo.save(m);
				m = nRepo.findunique(m.getSeekerid(), m.getExpertid(), m.getQuestionid());
				
				//set Notification
				Notification n = new Notification();
				n.setType(notificationtype.seekerrequest);
				n.setAttributeid(m.getMessageid());
				n.setTimestamp(new Date()); //set current timestamp
				n.setState(notificationstatus.unread);
				n.setUserid(m.getExpertid());
				notifyRepo.save(n);
				
				//time based event for status change to rejected.
				return true;
			}
			}
			return false;
		}
		return false;		
	}
	
	public boolean changestatus(NegotiationMessage m)
	{
		//todo validate seekerid
		//todo validate expertid
		if(qservice.validatequestionid(m.getQuestionid()))
		{
			NegotiationMessage msg = nRepo.findunique(m.getExpertid(), m.getQuestionid());
			if(msg!= null)
			{
				m.setMessageid(msg.getMessageid());
				m.setSeekerid(msg.getSeekerid());
				nRepo.save(m); //update status
				
				Notification n = notifyRepo.findunique(m.getSeekerid(), m.getMessageid());
				//create notification
				if(n == null){
				 n = new Notification();
				n.setType(notificationtype.requeststatus);
				n.setAttributeid(m.getMessageid());
				n.setTimestamp(new Date()); //set current timestamp
				n.setState(notificationstatus.unread);
				n.setUserid(m.getSeekerid());
				}
				else
				{
					
				}
				notifyRepo.save(n);
				
				Question q = qRepo.findOne(m.getQuestionid());
				//time based notification event for discussion
				if(m.getMessagestatus().equals(status.accept))
				{
					Timer t=new Timer();
					t.schedule(new TimerTask() {
					    public void run() {
					    	addDiscussionnotification(m);
					    }
					}, q.getPreferredTime());
				}
				return true;			
			}
			return false;
		}
		return false;		
	}
	
	//internal
	public boolean validateNegotiationId(long nid)
	{
		NegotiationMessage nmsg = nRepo.findOne(nid);
		if(nmsg ==null)
			return false;
		return true;
	}
	
	//internal
	public void addDiscussionnotification(NegotiationMessage m)
	{
		Notification n = new Notification();
		n.setType(notificationtype.discussion);
		n.setAttributeid(m.getMessageid());
		n.setTimestamp(new Date()); //set current timestamp
		n.setState(notificationstatus.unread);
		n.setUserid(m.getSeekerid());
		notifyRepo.save(n);
		
		Notification n1 = new Notification();
		n1.setType(notificationtype.discussion);
		n1.setAttributeid(m.getMessageid());
		n1.setTimestamp(new Date()); //set current timestamp
		n1.setState(notificationstatus.unread);
		n1.setUserid(m.getExpertid());
		notifyRepo.save(n1);		
	}

	public List<Long> getExperts(long qid)
	{		
		return nRepo.getExperts(qid);
	}
	
}
