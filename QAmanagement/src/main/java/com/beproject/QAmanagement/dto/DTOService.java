package com.beproject.QAmanagement.dto;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.beproject.QAmanagement.models.*;
import com.beproject.QAmanagement.service.*;

public class DTOService 
{

	@Autowired 
	QuestionService qservice;	

	@Autowired 
	AnswerService aservice;

	@Autowired 
	QuestionTagService qtservice;

	@Autowired 
	RatingService rservice;

	public List<QuestionDTO> getuserinterestedtopicQuestions(long userid, int pageno) 
	{
		List<Long> qidlist = qtservice.getInterestedTopicQuestions(userid);
		List<QuestionDTO> qdtolist = new ArrayList<QuestionDTO>();
		while (qidlist.iterator().hasNext())
		{
			long qid = qidlist.iterator().next();
			Question q = qservice.getOneQuestionService(qid);
			QuestionDTO qto = new QuestionDTO();
			qto.setQuestionid(qid);
			qto.setTitle(q.getTitle());
			qto.setState(q.getState());
			qto.setTimestamp(q.getTimestamp());
			qto.setTagidlist(qtservice.gettagids(q.getQuestionid()));
			qto.setUpvote(rservice.getquestionupvotecount(qid));
			qto.setDownvote(rservice.getquestiondownvotecount(qid));
			qdtolist.add(qto);
		}
		qdtolist.sort(Comparator.comparing(QuestionDTO::getTimestamp).reversed());
		int start = (pageno - 1)*10;
		List<QuestionDTO> page = new ArrayList<QuestionDTO>();
		for(int s = start; s <=(start+9);s++)
			{page.add(qdtolist.get(s));}
		return page;
	}

	public List<QuestionDTO> getuserexpertisetopicQuestions(long userid,int pageno) {
		List<Long> qidlist = qtservice.getExpertiseTopicQuestions(userid);
		List<QuestionDTO> qdtolist = new ArrayList<QuestionDTO>();
		while (qidlist.iterator().hasNext())
		{
			long qid = qidlist.iterator().next();
			Question q = qservice.getOneQuestionService(qid);
			QuestionDTO qto = new QuestionDTO();
			qto.setQuestionid(qid);
			qto.setTitle(q.getTitle());
			qto.setState(q.getState());
			qto.setTimestamp(q.getTimestamp());
			qto.setTagidlist(qtservice.gettagids(q.getQuestionid()));
			qto.setUpvote(rservice.getquestionupvotecount(qid));
			qto.setDownvote(rservice.getquestiondownvotecount(qid));
			qdtolist.add(qto);
		}		
		qdtolist.sort(Comparator.comparing(QuestionDTO::getTimestamp).reversed());
		int start = (pageno - 1)*10;
		List<QuestionDTO> page = new ArrayList<QuestionDTO>();
		for(int s = start; s <=(start+9);s++)
			{page.add(qdtolist.get(s));}
		return page;
	}

	public List<QuestionDTO> getuseraskedQuestions(long userid) 
	{
		List<Long> qidlist = qservice.getaskedQuestions(userid);
		List<QuestionDTO> qdtolist = new ArrayList<QuestionDTO>();
		while (qidlist.iterator().hasNext())
		{
			long qid = qidlist.iterator().next();
			Question q = qservice.getOneQuestionService(qid);
			QuestionDTO qto = new QuestionDTO();
			qto.setQuestionid(qid);
			qto.setTitle(q.getTitle());
			qto.setState(q.getState());
			qto.setTimestamp(q.getTimestamp());
			qto.setTagidlist(qtservice.gettagids(qid));
			qto.setUpvote(rservice.getquestionupvotecount(qid));
			qto.setDownvote(rservice.getquestiondownvotecount(qid));
			qdtolist.add(qto);
		}		
		return qdtolist;
	}

	public List<AnswerDTO> getuseranswer(long userid) {
		List<AnswerDTO> adtolist = new ArrayList<AnswerDTO>();
		List<Answers> alist = aservice.getuseranswers(userid);
		while(alist.iterator().hasNext())
		{
			Answers a = alist.iterator().next();
			AnswerDTO ato = new AnswerDTO();
			ato.setAnswer(a);
			ato.setUpvote(rservice.getanswerupvotecount(a.getAnswerid()));
			ato.setDownvote(rservice.getanswerdownvotecount(a.getAnswerid()));
			adtolist.add(ato);
		}		
		return adtolist;
	}

	public QADTO getquestionanswer(long qid) 
	{
		Question q = qservice.getOneQuestionService(qid);
		List<Answers> alist = aservice.getbyquestionid(qid);
		List<AnswerDTO> adtolist = new ArrayList<AnswerDTO>();
		QADTO qa = new QADTO();
		qa.setQuestion(q);
		while(alist.iterator().hasNext())
		{
			AnswerDTO adto = new AnswerDTO();
			Answers a = alist.iterator().next();
			adto.setAnswer(a);
			adto.setUpvote(rservice.getanswerupvotecount(a.getAnswerid()));
			adto.setDownvote(rservice.getanswerdownvotecount(a.getAnswerid()));
			adtolist.add(adto);
		}
		qa.setAnswers(adtolist);
		return qa;
	}

	public boolean postanswer(Answers a) {
		return aservice.createAnswer(a);
	}

	public boolean postanswervote(AnswerRating a) {
		return rservice.voteanswer(a);
		
	}

	public boolean postquestionvote(QuestionRating r) {
		return rservice.votequestion(r);
	}

	public boolean postquestion(PostQuestionDTO q) {
		if(!qservice.createQuestion(q.getQuestion()))
			return false;
		if(!qtservice.addmultipletopics(q.getQuestion().getQuestionid(), q.getTagid()))
			return false;
		return true;
	}	
}
