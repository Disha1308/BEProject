package com.beproject.QAmanagement.dto;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.beproject.QAmanagement.models.*;
import com.beproject.QAmanagement.service.*;

@Component
public class DTOService 
{

	@Autowired 
	QuestionService qservice;	

	@Autowired 
	AnswerService aservice;

	@Autowired(required=true)
	QuestionTagService qtservice;

	@Autowired 
	RatingService rservice;
	
	//tested
	public List<QuestionDTO> getuserinterestedtopicQuestions(long userid, int pageno) 
	{
		List<Long> qidlist = qtservice.getInterestedTopicQuestions(userid);
		List<QuestionDTO> qdtolist = new ArrayList<QuestionDTO>();
		if(qidlist == null)
			return null;
		int i = 0;
		while(i<qidlist.size())
		{
			long qid = qidlist.get(i);
			Question q = qservice.getOneQuestionService(qid);
			if(q != null){
			QuestionDTO qto = new QuestionDTO();
			qto.setQuestionid(qid);
			qto.setTitle(q.getTitle());
			qto.setState(q.getState());
			qto.setTimestamp(q.getTimestamp());
			qto.setTagnamelist(qtservice.gettagsname(qtservice.gettagids(q.getQuestionid())));
			qto.setUpvote(rservice.getquestionupvotecount(qid));
			qto.setDownvote(rservice.getquestiondownvotecount(qid));
			qdtolist.add(qto);
			}
			i++;
		}
		qdtolist.sort(Comparator.comparing(QuestionDTO::getTimestamp, Comparator.nullsLast(Comparator.naturalOrder())).reversed());
		int start = (pageno - 1)*10;
		if(start >= qdtolist.size())
			return null;
		List<QuestionDTO> page = new ArrayList<QuestionDTO>();
		for(int s = start; s <(start+10)  && s < qdtolist.size();s++)
			{page.add(qdtolist.get(s));}
		return page;
	}
	
	//tested
	public List<QuestionDTO> getuserexpertisetopicQuestions(long userid,int pageno) {
		List<Long> qidlist = qtservice.getExpertiseTopicQuestions(userid);
		List<QuestionDTO> qdtolist = new ArrayList<QuestionDTO>();
		if(qidlist == null)
			return null;
		int i = 0;
		while(i<qidlist.size())
		{
			long qid = qidlist.get(i);
			Question q = qservice.getOneQuestionService(qid);
			if(q!=null)
			{
			QuestionDTO qto = new QuestionDTO();
			qto.setQuestionid(qid);
			qto.setTitle(q.getTitle());
			qto.setState(q.getState());
			qto.setTimestamp(q.getTimestamp());
			qto.setTagnamelist(qtservice.gettagsname(qtservice.gettagids(q.getQuestionid())));
			qto.setUpvote(rservice.getquestionupvotecount(qid));
			qto.setDownvote(rservice.getquestiondownvotecount(qid));
			qdtolist.add(qto);
			}
			i++;
			
		}
		qdtolist.sort(Comparator.comparing(QuestionDTO::getTimestamp, Comparator.nullsLast(Comparator.naturalOrder())).reversed());
		int start = (pageno - 1)*10;
		if(start >= qdtolist.size())
			return null;
		List<QuestionDTO> page = new ArrayList<QuestionDTO>();
		for(int s = start; s <(start+10) && s < qdtolist.size();s++)
			{page.add(qdtolist.get(s));}
		return page;
	}
	
	//t
	public List<QuestionDTO> getuseraskedQuestions(long userid, int pageno) 
	{
		List<Long> qidlist = qservice.getaskedQuestions(userid);
		List<QuestionDTO> qdtolist = new ArrayList<QuestionDTO>();
		if(qidlist == null)
			return null;
		int i=0;
		while (i < qidlist.size())
		{
			long qid = qidlist.get(i++);
			Question q = qservice.getOneQuestionService(qid);
			QuestionDTO qto = new QuestionDTO();
			qto.setQuestionid(qid);
			qto.setTitle(q.getTitle());
			qto.setState(q.getState());
			qto.setTimestamp(q.getTimestamp());
			qto.setTagnamelist(qtservice.gettagsname(qtservice.gettagids(q.getQuestionid())));
			qto.setUpvote(rservice.getquestionupvotecount(qid));
			qto.setDownvote(rservice.getquestiondownvotecount(qid));
			qdtolist.add(qto);
		}		
		qdtolist.sort(Comparator.comparing(QuestionDTO::getTimestamp,Comparator.nullsLast(Comparator.naturalOrder())).reversed());
		int start = (pageno - 1)*10;
		if(start >= qdtolist.size())
			return null;
		List<QuestionDTO> page = new ArrayList<QuestionDTO>();
		for(int s = start; s <(start+10) && s < qdtolist.size();s++)
			{page.add(qdtolist.get(s));}
		return page;
	}
	//t
	public List<AnswerDTO> getuseranswer(long userid,int pageno) {
		List<AnswerDTO> adtolist = new ArrayList<AnswerDTO>();
		List<Answers> alist = aservice.getuseranswers(userid);
		if(alist ==null)
			return null;
		int i=0;
		while(i< alist.size())
		{
			Answers a = alist.get(i++);
			AnswerDTO ato = new AnswerDTO();
			ato.setAnswer(a);
			ato.setUpvote(rservice.getanswerupvotecount(a.getAnswerid()));
			ato.setDownvote(rservice.getanswerdownvotecount(a.getAnswerid()));
			adtolist.add(ato);
		}		
		int start = (pageno - 1)*10;
		if(start >= adtolist.size())
			return null;
		List<AnswerDTO> page = new ArrayList<AnswerDTO>();
		for(int s = start; s <(start+10) && s < adtolist.size();s++)
			{page.add(adtolist.get(s));}
		return page;
	}
	//t
	public QADTO getquestionanswer(long qid) 
	{
		Question q = qservice.getOneQuestionService(qid);
		if(q != null){
		List<Answers> alist = aservice.getbyquestionid(qid);
		List<AnswerDTO> adtolist = new ArrayList<AnswerDTO>();
		QADTO qa = new QADTO();
		qa.setQuestion(q);
		qa.setQuestionupvote(rservice.getquestionupvotecount(qid));
		qa.setQuestiondownvote(rservice.getquestiondownvotecount(qid));
		qa.setQtagname(qtservice.gettagsname(qtservice.gettagids(q.getQuestionid())));		
		int i=0;
		while(i< alist.size())
		{
			AnswerDTO adto = new AnswerDTO();
			Answers a = alist.get(i++);
			adto.setAnswer(a);
			adto.setUpvote(rservice.getanswerupvotecount(a.getAnswerid()));
			adto.setDownvote(rservice.getanswerdownvotecount(a.getAnswerid()));
			adtolist.add(adto);
		}
		qa.setAnswers(adtolist);
		return qa;
		}
		return null;
	}
	
	//tested
	public boolean postanswer(Answers a) {
		if(qservice.validatequestionid(a.getQuestionid()))
			return aservice.createAnswer(a);
		return false;
	}
	//t
	public boolean postanswervote(AnswerRating a) {
		return rservice.voteanswer(a);
		
	}
	
	//tested
	public boolean postquestionvote(QuestionRating r) {
		return rservice.votequestion(r);
	}
	
	//tested
	public boolean postquestion(PostQuestionDTO q) {
		if(!qservice.createQuestion(q.getQuestion()))
			return false;
		if(!qtservice.addmultipletopics(q.getQuestion().getQuestionid(), q.getTagid()))
			return false;
		return true;
	}	
	
	public List<Long> getquestiontags(long qid)
	{
		return qtservice.gettagids(qid);
	}
}
