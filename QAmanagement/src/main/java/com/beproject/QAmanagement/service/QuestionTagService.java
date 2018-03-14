package com.beproject.QAmanagement.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.beproject.QAmanagement.models.*;
import com.beproject.QAmanagement.repository.QuestionTagRepository;


@Service
public class QuestionTagService 
{
	@Autowired 
	QuestionTagRepository qtRepo;
	
	@Autowired
	QuestionService qservice;
	
	//used tested
	public boolean addtag(QuestionTag t)
	{
		System.out.println("in add one tag service");
		if(qservice.validatequestionid(t.getQuestionid()))
		{
			//validation for topicid
			try{
				RestTemplate restT = new RestTemplate();
			ResponseEntity<Boolean> response = restT.exchange("http://localhost:8081/v1.0/validatetopic/"+t.getTagid(),
				    HttpMethod.GET, null, boolean.class);
			if(response.getBody()==true)
			{
			qtRepo.save(t);
			return true;
			}
			}
			catch(Exception e)
			{
				System.out.println("Topic management not available");//no topic added
			}
			return false; //invalid topicid
		}		
		return false; //invalid questionid
	}
	
	//used tested 
	public boolean addmultipletopics(long qid, List<Long> tagidlist)
	{
		int i = 0;
		if(tagidlist == null)
			return false;
		while(i < tagidlist.size())
		{
			QuestionTag qt = new QuestionTag();
			qt.setQuestionid(qid);
			qt.setTagid(tagidlist.get(i++));
			addtag(qt);
		}
		return true;
	}
	
	//used t  for frontend also
	public List<Long> gettagids(long qid)
	{
		System.out.println("in get tags of question service");
		List<Long> tagidlist = new ArrayList<Long>();
		if(qservice.validatequestionid(qid))
		{
			tagidlist = qtRepo.findbyQuestionid(qid);
			return tagidlist;
		}		
		return null;
	}
	//used tested
	public List<Long> getquestions(long tagid)
	{
		System.out.println("in get questionid for a tag service");
		List<Long> qidlist = new ArrayList<Long>();
		//validation for topicid
		try{
		RestTemplate restT = new RestTemplate();
		ResponseEntity<Boolean> response = restT.exchange("http://localhost:8081/v1.0/validatetopic/"+tagid,
			    HttpMethod.GET, null, boolean.class);
		if(response.getBody()==true)
		{
			qidlist = qtRepo.findbyTopicid(tagid);
			return qidlist;
		}
		return null; //invalid topicid
		}
		catch(Exception e)
		{
			System.out.println("topic management not available");
			return null; //resource not available
		}	
	}
	
	//used tested
	public List<Long> getquestionsofmultipletopic(List<Long> tagidlist)
	{
		System.out.println("in get questionid for multiple tag service");
		List<Long> qidlist = new ArrayList<Long>();
		int i=0;
		while(i<tagidlist.size())
		{
			List<Long> qlist = getquestions(tagidlist.get(i));
			if(qlist != null)
				qidlist.addAll(qlist);
			i++;
		}
		return qidlist;		
	}
	
	//used tested
	public List<Long> getInterestedTopicQuestions(long uid) {
		//todo validation for user id
		
		List<Long> u = new ArrayList<Long>();
			try{
			RestTemplate restT = new RestTemplate();
			ResponseEntity <List<Long>> response = restT.exchange("http://localhost:8080/v1.0/interestedtags/"+uid,
				    HttpMethod.GET, null, new ParameterizedTypeReference <List<Long>> () {});

				u = response.getBody();
			if(u==null)
			{
				return null; //if user not sucsbribed to any topic
			}
			}
			catch(Exception e)
			{
				System.out.println("invalid url in rest template");
				return null;
			}
		return getquestionsofmultipletopic(u);
		
	}
	
	//used tested
	public List<Long> getExpertiseTopicQuestions(long uid) {
		//todo validation for user id

		List<Long> u = new ArrayList<Long>();
			try{
			RestTemplate restT = new RestTemplate();
			ResponseEntity <List<Long>> response = restT.exchange("http://localhost:8080/v1.0/expertisetags/"+uid,
				    HttpMethod.GET, null, new ParameterizedTypeReference <List<Long>> () {});

				u = response.getBody();
				if(u==null)
				{
					return null;
				}}
			catch(Exception e)
			{
				System.out.println("invalid url in rest template");
				return null;
			}
		return getquestionsofmultipletopic(u);	
	}
	
	//used tested
	public List<String> gettagsname(List<Long> tagidlist) {
		List<String> namelist = new ArrayList<String>();
		try{
			RestTemplate restT = new RestTemplate();
			
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			
			HttpEntity<List<Long>> request = new HttpEntity<List<Long>>(tagidlist, headers);
			
			ResponseEntity <List<String>> response = restT.exchange("http://localhost:8081/v1.0/topicdetails/",
				    HttpMethod.POST, request, new ParameterizedTypeReference <List<String>> () {});

				namelist = response.getBody();
				return namelist;
			}
			catch(Exception e)
			{
				System.out.println("invalid url in rest template");
				return null;
			}
	}
}
