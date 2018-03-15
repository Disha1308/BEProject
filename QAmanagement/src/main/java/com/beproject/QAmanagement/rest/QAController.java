package com.beproject.QAmanagement.rest;

import java.util.List;
import java.util.Set;

import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.beproject.QAmanagement.configuration.URLConstants;
import com.beproject.QAmanagement.dto.*;
import com.beproject.QAmanagement.models.*;
import com.beproject.QAmanagement.service.CosineSearch;

@RestController
@Named
@Path("/v1.0/")
public class QAController 
{

	@Autowired
	DTOService dservice;
	
	@Autowired
	CosineSearch s;
	
	//for frontend tested
	@GET
	@Path(URLConstants.GET_USER_INTRESTED_QUESTIONS_URL)
	@Produces(MediaType.APPLICATION_JSON)
	public List<QuestionDTO> getUserInterestedtopicQuestions(@PathParam("userid") long userid, @PathParam("pageno")int pageno) {
		System.out.println("in get user interested questions controller");
	
		List<QuestionDTO> qlist = dservice.getuserinterestedtopicQuestions(userid,pageno);
		return qlist;
	}
	
	// for frontend tested
	@GET
	@Path(URLConstants.GET_USER_EXPERTISE_QUESTIONS_URL)
	@Produces(MediaType.APPLICATION_JSON)
	public List<QuestionDTO> getUserexpertisetopicQuestions(@PathParam("userid") long userid, @PathParam("pageno")int pageno) {
		System.out.println("in get user expertise questions controller");
	
		return dservice.getuserexpertisetopicQuestions(userid,pageno);
	}
	
	@GET //t
	@Path(URLConstants.GET_USER_ASKED_URL)
	@Produces(MediaType.APPLICATION_JSON)
	public List<QuestionDTO> getUserAskedQuestions(@PathParam("userid") long userid,@PathParam("pageno") int pageno) {
		System.out.println("in get user asked questions controller");	
		return dservice.getuseraskedQuestions(userid,pageno);
	}
	
	@GET //t
	@Path(URLConstants.GET_USER_ANSWERS_URL)
	@Produces(MediaType.APPLICATION_JSON)
	public List<AnswerDTO> getUserAnswers(@PathParam("userid") long userid,@PathParam("pageno") int pageno) {
		System.out.println("in get user asked questions controller");	
		return dservice.getuseranswer(userid,pageno);
	}
	
	@GET //t
	@Path(URLConstants.GET_QUESTION_ANSWERS_URL)
	@Produces(MediaType.APPLICATION_JSON)
	public QADTO getQuestionAnswers(@PathParam("questionid") long qid) {
		System.out.println("in get question answer controller");	
		return dservice.getquestionanswer(qid);
	}
	
	@GET //t
	@Path(URLConstants.GET_EXPERTANSWER_URL)
	@Produces(MediaType.APPLICATION_JSON)
	public long getExpertsAnswers(@PathParam("questionid") long qid,@PathParam("userid") long uid) {
		System.out.println("in get experts answer controller");	
		return dservice.getanswercount(uid, qid);
	}
	
	@POST // t
	@Path(URLConstants.POST_ANSWER_URL)
	public boolean postAnswer(@RequestBody Answers a) {
		System.out.println("in post answer controller");	
		return dservice.postanswer(a);
	}
	
	@POST // t
	@Path(URLConstants.POST_ANSWER_VOTE_URL)
	public boolean postAnswerVote(@RequestBody AnswerRating a) {
		System.out.println("in post answer vote controller");	
		return dservice.postanswervote(a);
	}
	
	@POST //t
	@Path(URLConstants.POST_QUESTION_VOTE_URL)
	public boolean postQuestionVote(@RequestBody QuestionRating r) {
		System.out.println("in post question vote controller");	
		return dservice.postquestionvote(r);
	}
	
	@POST  //tested
	@Path(URLConstants.POST_QUESTION_URL)
	public long postQuestion(@RequestBody PostQuestionDTO q) {
		System.out.println("in post question controller");	
		return dservice.postquestion(q);
	}
	
	@GET  //tested
	@Path(URLConstants.GET_QUESTION_TAG_URL)
	public List<Long> getQuestionTag(@PathParam("questionid") long qid) {
		System.out.println("in get question tags controller");	
		return dservice.getquestiontags(qid);
		}
	
	
	@GET
	@Path(URLConstants.GET_PAGENO_URL)
	public long getpageno(@PathParam("topictype") String type,@PathParam("userid") long uid)
	{
		System.out.println("in get pageno controller");	
		return dservice.getpageno(type,uid);
	}
	
	
	@GET
	@Path(URLConstants.SEARCH_QUESTION_URL)
	@Produces(MediaType.APPLICATION_JSON)
	public Set<String> searchrelatedquestions(@PathParam("keywords") String keywords)
	{
		System.out.println("in search question controller");
		return s.search(keywords);
	}
}
