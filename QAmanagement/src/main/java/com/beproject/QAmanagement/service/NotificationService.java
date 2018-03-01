package com.beproject.QAmanagement.service;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.beproject.QAmanagement.models.*;
import com.beproject.QAmanagement.repository.*;


@Service
public class NotificationService 
{
	@Autowired
	NotificationRepository nRepo;
	
	@Autowired 
	NegotiationService negoservice;
	@Autowired
	AnswerService aservice;
	
	public boolean createNotification(Notification n)
	{
		//validate userid
		if(n.getType().equals("answer"))
		{
			if(aservice.validateanswerid(n.getAttributeid()))
			{
				nRepo.save(n);
				return true;
			}
			return false;
		}
		else
		{
			if(negoservice.validateNegotiationId(n.getAttributeid()))
			{
				nRepo.save(n);
				return true;
			}
			return false;
		}
	}
	
	public List<Notification> fetchByuserid(long uid)
	{
		//validate uid
		List<Notification> nlist = nRepo.findByuserid(uid);
		nlist.sort(Comparator.comparing(Notification::getTimestamp).reversed());
		return nlist;
	}
	
	public boolean changestatus(Notification n)
	{
		if(nRepo.findOne(n.getNotificationid()) != null)
		{
			nRepo.save(n);
			return true;
		}
		return false;
	}
}
