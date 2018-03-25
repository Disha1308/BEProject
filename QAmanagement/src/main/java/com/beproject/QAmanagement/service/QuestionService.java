package com.beproject.QAmanagement.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.beproject.QAmanagement.models.Question;
import com.beproject.QAmanagement.models.Question.status;
import com.beproject.QAmanagement.repository.*;


@Service
public class QuestionService {
	
	@Autowired(required = true)
	QuestionRepository questionRepo;
	
	//used tested
	public Question getOneQuestionService(long id) {
		try {
			Question u = questionRepo.findOne(id);
			System.out.println("in get one question service");
			return u;
		} catch (Exception e) {
			return null;
		}
	}
	 
	//used t
	public long createQuestion(Question q)
	{
		System.out.println("in create question service");
		try {
			if(questionRepo.findunique(q.getUserid(), q.getTitle()) == null )
			{
			q.setTimestamp(new Date());
			questionRepo.save(q);
			System.out.println(q.getUserid()+q.getTitle());
			q = questionRepo.findunique(q.getUserid(), q.getTitle());
			if(q != null)
				{
				System.out.println("question created with id"+q.getQuestionid());
				return q.getQuestionid();
				}
			}
			return -1;
		} catch (Exception e) {
			return -1;
		}
	}
	
	
	
	//used
	public boolean validatequestionid(long qid)
	{
		if(questionRepo.findOne(qid) != null)
			return true;
		return false;
	}
	
	//used t
	public List<Long> getaskedQuestions(long uid)
	{		try{
		RestTemplate restT = new RestTemplate();
	ResponseEntity<Boolean> response = restT.exchange("http://localhost:8080/v1.0/validateuser/"+uid,
		    HttpMethod.GET, null, boolean.class);
	if(response.getBody()==true)
	{
		return questionRepo.findByuserid(uid);
	}
	}
	catch(Exception e)
	{
		System.out.println("user management not available");
		
	}
	return null;
	}

	public void changestatus(long qid) {
		Question q = questionRepo.findOne(qid);
		if(q!=null)
		{
			q.setState(status.Close);
			questionRepo.save(q);
		}
	}
}
