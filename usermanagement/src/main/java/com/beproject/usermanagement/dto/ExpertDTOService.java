package com.beproject.usermanagement.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.beproject.usermanagement.dto.ExpertDTO.expertStatus;
import com.beproject.usermanagement.models.User;
import com.beproject.usermanagement.models.UserPreference;
import com.beproject.usermanagement.service.UserMgmtService;
import com.beproject.usermanagement.service.UserPreferenceService;
import com.beproject.usermanagement.service.UserTopicService;


@Component
public class ExpertDTOService 
{
	@Autowired(required =true)
	UserTopicService tagservice;
	
	@Autowired(required =true)
	UserMgmtService uservice;
	
	@Autowired(required =true)
	UserPreferenceService upservice;
	
	
	public List<ExpertDTO> getexperts(long qid) {
		System.out.println("in get experts dto service");
			
		try{
			RestTemplate restT = new RestTemplate();
		ResponseEntity<List<Long>> response = restT.exchange("http://localhost:8082/v1.0/question/"+qid+"/tags",
			    HttpMethod.GET, null, new ParameterizedTypeReference <List<Long>>(){} );
		
		List<Long> topicidlist = response.getBody();
		if(topicidlist == null)
			return null; 	// question has no topics
		
	
		List<Long> useridlist = tagservice.getexpertsmultipleTopics(topicidlist);
		if(useridlist.size() == 0)
			return null;	//no experts available
		
		//get list of expertid to whom already message is sent
		 response = restT.exchange("http://localhost:8082/v1.0/negotiate/"+qid,
			    HttpMethod.GET, null, new ParameterizedTypeReference <List<Long>>(){} );
		
		List<Long> unavailableexperts = response.getBody();
		
		List<ExpertDTO> expertlist = new ArrayList<ExpertDTO>();
		int i=0;
		while(i < useridlist.size())
		{
			long id = useridlist.get(i++);
			if(unavailableexperts != null && unavailableexperts.contains(id))
			{
			}
			else
			{
				ResponseEntity<Long> cnt = restT.exchange("http://localhost:8082/v1.0/"+qid+"/expertanswers/"+id,
					    HttpMethod.GET, null, Long.class );
			
			User u = uservice.getUserbyId(id);
			UserPreference p = upservice.getByUserid(id);
			ExpertDTO d = new ExpertDTO();
			d.setId(u.getUserid());
			d.setUsername(u.getUsername());
			d.setPreference(p);
			d.setAnswercount(cnt.getBody());
			d.setAvailability(expertStatus.available);
			expertlist.add(d);
			}
		}
		return expertlist;
	}
		catch(Exception e)
		{
			System.out.println("question management not available");
			return null;
		}
}
}
