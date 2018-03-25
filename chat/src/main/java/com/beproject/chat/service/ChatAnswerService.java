package com.beproject.chat.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.beproject.chat.models.*;

@Service
public class ChatAnswerService {
	
	@Autowired(required = true)
	ChatAnswerRepository answerRepo;


	@Autowired(required = true)
	ChatSessionRepository sessionRepo;

	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;
	
	public void createAnswer(ChatAnswer u) 
	{
		System.out.println("in post db chat answer service");
		try {
			u.setTimestamp(new Date());
			answerRepo.save(u);
			//u = answerRepo.findOne(u.getId());
			
			long receiver = -1;
			ChatSession s = sessionRepo.findOne(u.getChatsessionid());
			if(s.getSeekerid() == u.getUserid())
				receiver = s.getExpertid();
			else
				receiver = s.getSeekerid();
			
			//get other username
			RestTemplate restT = new RestTemplate();
			ResponseEntity<String> response = restT.exchange("http://localhost:8080/v1.0/username/"+receiver,
				    HttpMethod.GET, null, String.class);
			
			String otherusername = response.getBody();
			
			simpMessagingTemplate.convertAndSendToUser(otherusername, "/queue/reply", u);			

		} catch (Exception e) {
			System.out.println("error in sending answer");
		}
	}

	public long createsession(ChatSession session, long uid)
	{
		ChatSession s = sessionRepo.findunique(session.getSeekerid(), session.getExpertid(), session.getQuestionid());
		long sid = -1;
		if(s==null)
			s= sessionRepo.findunique(session.getExpertid(), session.getSeekerid(),session.getQuestionid());
		if(s==null){
			s = session;
			sid = -2;
		}
		if(session.getSeekerid() == uid)
		{
			s.setSeekerstatus(true);
		}
		else
		{
			s.setExpertstatus(true);
		}
		sessionRepo.save(s);
		
		if(sid == -2)
		{ 
			s = sessionRepo.findunique(session.getSeekerid(), session.getExpertid(), session.getQuestionid());
		}
		sid = s.getChatsessionid();
		return sid;
			
	}

	public void changestatus(long sid, long uid) 
	{
		ChatSession s = sessionRepo.findOne(sid);
		if(s != null)
		{
			if(s.getSeekerid() == uid)
			{
				s.setSeekerstatus(false);
			}
			else
			{
				s.setExpertstatus(false);
			}
			sessionRepo.save(s);
		}
		else
		{ 
			System.out.println("session not found");
		}
	}

	public long getsessionid(long sid, long eid, long qid) 
	{
		ChatSession s = sessionRepo.findunique(sid, eid, qid);
		if (s==null)
			s= sessionRepo.findunique(eid, sid, qid);
		if(s == null)
			return -1;
		else
			return s.getChatsessionid();
	}

	public List<ChatAnswer> getsessionanswers(long sid) 
	{
		List<ChatAnswer> answerlist = answerRepo.findBychatsessionid(sid);
		return answerlist;
	}

}
