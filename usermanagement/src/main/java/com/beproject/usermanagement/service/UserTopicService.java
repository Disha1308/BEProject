package com.beproject.usermanagement.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.beproject.usermanagement.models.UserTopicStatus;
import com.beproject.usermanagement.models.UserTopicStatus.topicstatus;
import com.beproject.usermanagement.repository.UserTopicStatusRepository;


@Service
public class UserTopicService 
{
	@Autowired
	UserTopicStatusRepository statusRepo;
	
	@Autowired
	UserMgmtService uservice;
	
	//tested
	public boolean subscribetoTopic(UserTopicStatus t)
	{
		long userid= t.getUserid();
		long topicid = t.getTopicid();
		if(uservice.validateuserid(userid))
		{
			try{
				RestTemplate restT = new RestTemplate();
			ResponseEntity<Boolean> response = restT.exchange("http://localhost:8081/v1.0/validatetopic/"+topicid,
				    HttpMethod.GET, null, boolean.class);
			if(response.getBody()==true)
			{
				UserTopicStatus registeredstatus = statusRepo.findTopic(userid, topicid);
				if( registeredstatus != null)
				{
					//need update 
					t.setTopicstatusid(registeredstatus.getTopicstatusid());
				}
				statusRepo.save(t);	//save to update or create
				return true;
			}
			}
			catch(Exception e)
			{
				System.out.println("Topic management not available");//unable to get topic
			}
			return false; //invalid topicid
			
		}
		return false; //invalid userid
	}
	
	//tested
	public boolean unsubscribetoTopic(long uid, long tid)
	{
		if(uservice.validateuserid(uid))
		{
			try{
				RestTemplate restT = new RestTemplate();
			ResponseEntity<Boolean> response = restT.exchange("http://localhost:8081/v1.0/validatetopic/"+tid,
				    HttpMethod.GET, null, boolean.class);
			if(response.getBody()==true)
			{
				UserTopicStatus registeredstatus = statusRepo.findTopic(uid, tid);
				if( registeredstatus != null)
				{
					statusRepo.delete(registeredstatus.getTopicstatusid());	//delete entry
					return true;
				}
				return false; //no valid entry for uid and tid
			}
			}
			catch(Exception e)
			{
				System.out.println("Topic management not available");//unable to get topic
			}
			return false; //invalid topicid cannot delete entry

		}
		return false; //invalid userid
	}
	
	/*public boolean managemultipleTopics(List<UserTopicStatus> statuslist)
	{
		Iterator<UserTopicStatus> statusit = statuslist.iterator();
		while(statusit.hasNext())
		{
			if(!manageTopic(statusit.next()))
				return false;
		}
		return true;
	}*/
	
	//used tested
	public List<Long> getinterestedtopics(long userid)
	{
		if(uservice.validateuserid(userid))
		{
			return statusRepo.findByuseridnstatus(userid, topicstatus.interested);
		}
		return null;	
	}
	
	//used tested
	public List<Long> getexpertisetopics(long userid)
	{
		if(uservice.validateuserid(userid))
		{
			return statusRepo.findByuseridnstatus(userid, topicstatus.expertise);
		}
		return null;	
	}
	
	//t
	public List<Long> getexperts(long topicid)
	{
		try{
			RestTemplate restT = new RestTemplate();
		ResponseEntity<Boolean> response = restT.exchange("http://localhost:8081/v1.0/validatetopic/"+topicid,
			    HttpMethod.GET, null, boolean.class);
		if(response.getBody()==true)
		{
			return statusRepo.findBytopicidnstatus(topicid, topicstatus.expertise);	
		}
		}
		catch(Exception e)
		{
			System.out.println("Topic management not available");//unable to get topic
		}
		return null; //invalid topicid
			
	}
	//t
	public List<Long> getexpertsmultipleTopics(List<Long> topicsid)
	{
		Iterator<Long> topicsit = topicsid.iterator();
		List<Long> expertlist = new ArrayList<Long>();
		while(topicsit.hasNext())
		{
			expertlist.addAll(getexperts(topicsit.next()));
		}
		return expertlist;			
	}
	
	
	public List<Long> getinterestedusers(long topicid)
	{
		//todo validation of topic id
		return statusRepo.findBytopicidnstatus(topicid, topicstatus.interested);		
	}
}
