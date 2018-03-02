package com.beproject.QAmanagement.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beproject.QAmanagement.models.Question;
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
	
	//used
	public boolean createQuestion(Question q)
	{
		System.out.println("in create question service");
		try {
			if(questionRepo.findunique(q.getUserid(), q.getTitle()) == null )
			{
			q.setTimestamp(new Date());
			questionRepo.save(q);
			return true;
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}
	
	public List<Question> searchquestion(String s)
	{
		return questionRepo.search(s);
	}
	
	//used
	public boolean validatequestionid(long qid)
	{
		if(questionRepo.findOne(qid) != null)
			return true;
		return false;
	}
	
	//used
	public List<Long> getaskedQuestions(long uid)
	{		//todo validate user id
		return questionRepo.findByuserid(uid);
	}
}
