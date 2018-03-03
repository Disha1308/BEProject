package com.beproject.QAmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.beproject.QAmanagement.models.*;
import com.beproject.QAmanagement.models.QuestionRating.type;
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
	//used t
	public boolean votequestion(QuestionRating r)
	{
		if(qservice.validatequestionid(r.getQuestionid()))
		{
			try{
				RestTemplate restT = new RestTemplate();
				ResponseEntity<Boolean> response = restT.exchange("http://localhost:8080/v1.0/validateuser/"+r.getUserid(),
					    HttpMethod.GET, null, boolean.class);
				if(response.getBody()==true)
				{
					long uid = r.getUserid();
					long qid = r.getQuestionid();
					QuestionRating qr = qrateRepo.findByUseridQuestionid(qid, uid);
					if(qr!=null){
					if( qr.getVote().equals(r.getVote()))
					{
						//delete row
						qrateRepo.delete(qr);
						return false;
					}				
					else{
						r.setQratingid(qr.getQratingid());
					}
					}
					qrateRepo.save(r);
					return true;
				}
			}
			catch(Exception e)
			{
				System.out.println("user management not available");
			}
		}
		return false;
	}
	 
	//used tested
	public long getquestionupvotecount(long qid)
	{
		if(qservice.validatequestionid(qid))
		{
			return qrateRepo.getvote(qid, type.upvote);
		}	
		return -1;
	}
	
	//used tested
	public long getquestiondownvotecount(long qid)
	{
		if(qservice.validatequestionid(qid))
		{
			return qrateRepo.getvote(qid, type.downvote);
		}	
		return -1;
	}
	
	//used t
	public boolean voteanswer(AnswerRating r)
	{
		if(aservice.validateanswerid(r.getAnswerid()))
		{
			try{
				RestTemplate restT = new RestTemplate();
				ResponseEntity<Boolean> response = restT.exchange("http://localhost:8080/v1.0/validateuser/"+r.getUserid(),
					    HttpMethod.GET, null, boolean.class);
				if(response.getBody()==true)
				{
					long uid = r.getUserid();
					long aid = r.getAnswerid();
					AnswerRating qr = arateRepo.findByUseridAnswerid(aid, uid);
					if(qr!=null){
					if( qr.getVote().equals(r.getVote()))
					{
						//delete row
						arateRepo.delete(qr);
						return false;
					}				
					else{
						r.setAratingid(qr.getAratingid());
					}
					}
					arateRepo.save(r);
					return true;
				}
			}
			catch(Exception e)
			{
				System.out.println("user management not available");
			}
		}
		return false;
	}
	
	//used t
	public long getanswerupvotecount(long aid)
	{
		if(aservice.validateanswerid(aid))
		{
			return arateRepo.getvote(aid, type.upvote);
		}	
		return -1;
	}
	
	//used t
	public long getanswerdownvotecount(long aid)
	{
		if(aservice.validateanswerid(aid))
		{
			return arateRepo.getvote(aid, type.downvote);
		}	
		return -1;
	}
}
