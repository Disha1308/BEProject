package com.beproject.topicmanagement.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beproject.topicmanagement.models.Topic;
import com.beproject.topicmanagement.repository.TopicRepository;

@Service
public class TopicService {
	@Autowired(required = true)
	TopicRepository topicRepo;

	public List<Topic> getAllTopics() {

		List<Topic> topicList = new ArrayList<Topic>();
		System.out.println("in get topic service");
		Iterator<Topic> topicIterator = topicRepo.findAll().iterator();
		while (topicIterator.hasNext()) {
			topicList.add(topicIterator.next());
		}
		return topicList;
	
	}

	public Topic getOneTopic(long id) {
		try {
			Topic u = topicRepo.findOne(id);
			System.out.println("in get one topic service");
			return u;
		} catch (Exception e) {
			return null;
		}

	}

	public boolean createTopic(Topic u) {
		System.out.println("in posttopic service");
		try {
			topicRepo.save(u);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean updateTopic(Topic t, long id) {
		System.out.println("in put topic db service");
		if(t.getId() == id)
		{
			Topic registertopic=topicRepo.findOne(id);
			if(registertopic == null)
				return false;
			topicRepo.save(t);
			return true;
		}
			return false;
	}

	public boolean deleteTopic(long id) {
		try {

			System.out.println("in delete topic service");
			topicRepo.delete(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	
	public boolean validatetopicid(long topicid)
	{
		if(topicRepo.findOne(topicid) != null)
			return true;
		return false;
	}
	
	public List<Topic> searchtopic(String str)
	{
		return topicRepo.search(str);
	}

	public List<String> getdetails(List<Long> topicidlist) {
		int i = 0;
		List<String> slist = new ArrayList<String>();
		while(i<topicidlist.size())
		{
			slist.add(topicRepo.getname(topicidlist.get(i++)));
		}
		return slist;
	}

}
