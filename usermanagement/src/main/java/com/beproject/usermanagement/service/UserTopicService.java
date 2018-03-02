package com.beproject.usermanagement.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	public boolean subscribetoTopic(UserTopicStatus t)
	{
		long userid= t.getUserid();
		long topicid = t.getTopicid();
		if(uservice.validateuserid(userid))
		{
			//todo validate topicid
			UserTopicStatus registeredstatus = statusRepo.findTopic(userid, topicid);
			if( registeredstatus != null)
			{
				//need update 
				t.setTopicstatusid(registeredstatus.getTopicstatusid());
			}
			statusRepo.save(t);	//save to update or create
			return true;
		}
		return false; //invalid userid
	}
	
	public boolean unsubscribetoTopic(long uid, long tid)
	{
		if(uservice.validateuserid(uid))
		{
			//todo validate topicid
			UserTopicStatus registeredstatus = statusRepo.findTopic(uid, tid);
			if( registeredstatus != null)
			{
				statusRepo.delete(registeredstatus.getTopicstatusid());	//delete entry
				return true;
			}
			
			return false;	//no entry for topic found.
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
	
	public List<Long> getinterestedtopics(long userid)
	{
		if(uservice.validateuserid(userid))
		{
			return statusRepo.findByuseridnstatus(userid, topicstatus.interested);
		}
		return null;	
	}
	
	public List<Long> getexpertisetopics(long userid)
	{
		if(uservice.validateuserid(userid))
		{
			return statusRepo.findByuseridnstatus(userid, topicstatus.expertise);
		}
		return null;	
	}
	
	public List<Long> getexperts(long topicid)
	{
		//todo validation of topic id
		return statusRepo.findBytopicidnstatus(topicid, topicstatus.expertise);		
	}
	
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
