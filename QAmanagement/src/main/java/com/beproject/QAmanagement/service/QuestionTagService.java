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
	
	QuestionTag qt; 
	
	QuestionTagService()
	{
		qt = new QuestionTag();
	}
	
	//used 
	public boolean addtag(QuestionTag t)
	{
		System.out.println("in add one tag service");
		if(qservice.validatequestionid(t.getQuestionid()))
		{
			//todo validation for topicid
			qtRepo.save(t);
			return true;
		}		
		return false;
	}
	
	//used
	public boolean addmultipletopics(long qid, List<Long> tagidlist)
	{
		int i = 0;
		while(i < tagidlist.size())
		{
			qt.setQuestionid(qid);
			qt.setTagid(tagidlist.get(i++));
			if(!addtag(qt))
				return false;
		}
		return true;
	}
	
	//used
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
		//todo validation for topicid
		{
			qidlist = qtRepo.findbyTopicid(tagid);
			return qidlist;
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
			qidlist.addAll(getquestions(tagidlist.get(i)));
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
				return null;
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
