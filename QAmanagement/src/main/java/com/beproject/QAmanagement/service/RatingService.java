package com.beproject.QAmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beproject.QAmanagement.models.*;
import com.beproject.QAmanagement.repository.*;


@Service
public class RatingService
{
	@Autowired
	QuestionRatingRepository qrateRepo;
	
	@Autowired
	QuestionService qservice;
	
	@Autowired
	AnswerRatingRepository arateRepo;
	
	@Autowired
	AnswerService aservice;
	//used
	public boolean votequestion(QuestionRating r)
	{
		if(qservice.validatequestionid(r.getQuestionid()))
		{
			//todo validate userid
			long uid = r.getUserid();
			long qid = r.getQuestionid();
			QuestionRating qr = qrateRepo.findByUseridQuestionid(qid, uid);
			if(qr.getVote().equals(r.getVote()))
			{
				//delete row
				qrateRepo.delete(qr);
				return false;
			}				
			else{
				r.setQratingid(qr.getQratingid());
			qrateRepo.save(r);
			return true;
			}
		}
		return false;
	}
	
	//used
	public long getquestionupvotecount(long qid)
	{
		if(qservice.validatequestionid(qid))
		{
			return qrateRepo.getvote(qid, "upvote");
		}	
		return -1;
	}
	
	//used
	public long getquestiondownvotecount(long qid)
	{
		if(qservice.validatequestionid(qid))
		{
			return qrateRepo.getvote(qid, "downvote");
		}	
		return -1;
	}
	
	//used
	public boolean voteanswer(AnswerRating r)
	{
		if(aservice.validateanswerid(r.getAnswerid()))
		{
			//todo validate userid
			long aid = r.getAnswerid();
			long uid = r.getUserid();
			AnswerRating ar = arateRepo.findByUseridQuestionid(aid, uid);
			if(ar.getVote().equals(r.getVote()))
			{
				//delete row
				arateRepo.delete(ar);
				return false;
			}
			else
			{
				r.setAratingid(ar.getAratingid());
			arateRepo.save(r);
			return true;
			}
		}
		return false;
	}
	
	//used
	public long getanswerupvotecount(long aid)
	{
		if(aservice.validateanswerid(aid))
		{
			return arateRepo.getvote(aid, "upvote");
		}	
		return -1;
	}
	
	//used
	public long getanswerdownvotecount(long aid)
	{
		if(aservice.validateanswerid(aid))
		{
			return arateRepo.getvote(aid, "downvote");
		}	
		return -1;
	}
}
