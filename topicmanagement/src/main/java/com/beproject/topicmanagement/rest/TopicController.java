package com.beproject.topicmanagement.rest;


import java.util.List;

import javax.inject.Named;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import com.beproject.topicmanagement.configuration.URLConstants;
import com.beproject.topicmanagement.models.Topic;
import com.beproject.topicmanagement.service.TopicService;


@Named
@Path("/v1.0/")
public class TopicController {
	@Autowired
	TopicService topicServe;

	@GET
	@Path(URLConstants.GET_TOPICS_URL)
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Topic> getAllTopics() {
		List<Topic> topicList = topicServe.getAllTopics();
		return topicList;

	}

	@GET
	@Path(URLConstants.GET_TOPIC_URL)
	@Produces(MediaType.APPLICATION_JSON)
	public Topic getTopic(@PathParam("id") long id) {
		System.out.println("in get one db topic service");
		Topic u = topicServe.getOneTopic(id);

		return u;

	}
	
	@POST
	@Path(URLConstants.TOPIC_NAME_URL)
	@Produces(MediaType.APPLICATION_JSON)
	public List<String> getTopicDetails(@RequestBody List<Long> topicidlist) {
		System.out.println("in topic details rest");
		return topicServe.getdetails(topicidlist);
	}

	@POST
	@Path(URLConstants.POST_TOPIC_URL)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean createTopic(@RequestBody Topic u) {
		System.out.println("in post db topic service");
		return topicServe.createTopic(u);
	}

	@PUT
	@Path(URLConstants.PUT_TOPIC_URL)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean updateTopic(@RequestBody Topic u, @PathParam("id") long id) {
		System.out.println("in put topic db service");
		return topicServe.updateTopic(u, id);

	}

	@DELETE
	@Path(URLConstants.DELETE_TOPIC_URL)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean deleteTopic(@PathParam("id") long id) {
		System.out.println("in delete topic service");
		return topicServe.deleteTopic(id);
	}
	
	@GET //tested
	@Path(URLConstants.VALIDATE_TOPIC_URL)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean validateTopic(@PathParam("id") long id) {
		System.out.println("in validate topic service");
		return topicServe.validatetopicid(id);
	}
	
	@GET
	@Path(URLConstants.SEARCH_TOPIC_URL)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Topic> searchTopic(@PathParam("str") String str) {
		System.out.println("in validate topic service");
		return topicServe.searchtopic(str);
	}
}
