package com.beproject.QAmanagement.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
		while(tagidlist.iterator().hasNext())
		{
			QuestionTag qt = new QuestionTag();
			qt.setQuestionid(qid);
			qt.setTagid(tagidlist.iterator().next());
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
	//used
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
	//used
	public List<Long> getquestionsofmultipletopic(List<Long> tagidlist)
	{
		System.out.println("in get questionid for multiple tag service");
		List<Long> qidlist = new ArrayList<Long>();
		while(tagidlist.iterator().hasNext())
		{
			qidlist.addAll(getquestions(tagidlist.iterator().next()));
		}
		return qidlist;		
	}
	
	//used
	public List<Long> getInterestedTopicQuestions(long uid) {
		//todo validation for user id
		
		List<Long> u = new ArrayList<Long>();
		
		RestTemplate restT = new RestTemplate();
		u = restT.getForObject("http://localhost:8080/interestedtags/"+uid,null);
		
		return getquestionsofmultipletopic(u);
	}
	
	//used
	public List<Long> getExpertiseTopicQuestions(long uid) {
		//todo validation for user id
		
		List<Long> u = new ArrayList<Long>();
		
		RestTemplate restT = new RestTemplate();
		u = restT.getForObject("http://localhost:8080/expertisetags/"+uid,null);
		
		return getquestionsofmultipletopic(u);		
	}
}
