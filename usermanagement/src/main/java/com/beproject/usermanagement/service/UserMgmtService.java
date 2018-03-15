package com.beproject.usermanagement.service;

import java.sql.Time;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beproject.usermanagement.models.*;
import com.beproject.usermanagement.models.UserPreference.languages;
import com.beproject.usermanagement.models.UserPreference.mode;
import com.beproject.usermanagement.repository.*;

@Service
public class UserMgmtService 
{
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	UserPreferenceRepository userpRepo;
	
	public User getUserbyEmail(String email)
	{
		try {
			User u = userRepo.findByemail(email);
			System.out.println("in get one user by email service");
			return u;
		} catch (Exception e) {
			return null;
		}
	}
	
	public User getUserbyUsername(String uname)
	{
		try {
			User u = userRepo.findByusername(uname);
			System.out.println("in get one user by username service");
			return u;
		} catch (Exception e) {
			return null;
		}
	}
	public User getUserbyId(long id)
	{
		try {
			User u = userRepo.findOne(id);
			System.out.println("in get one db user by id service");
			return u;
		} catch (Exception e) {
			return null;
		}
	}
	
	public boolean createUser(User u)//t
	{
		System.out.println("in create user service");
		if(u.getEmail() != null && u.getUsername()!=null && u.getPassword()!=null)
		{
		try {
			userRepo.save(u);
			
			long uid = userRepo.findByemail(u.getEmail()).getUserid();
			UserPreference up = new UserPreference();
			up.setCommunicationLang(languages.English);
			up.setCommunicationMode(mode.Offline);
			up.setStartTime(new Time(0));
			up.setEndTime(new Time(0));
			up.setUserid(uid);
			userpRepo.save(up);
			
			return true;
		} catch (Exception e) {
			return false;
		}
		}
		return false;
	}
	
	//t
	public boolean updateUserdetails(User u,long id)
	{
		System.out.println("in put user db service");
		if(u.getUserid() == id)
		{
		User registeruser=userRepo.findOne(id);
		if(registeruser == null)
			return false;
		if(u.getEmail() != null && u.getUsername()!=null && u.getPassword()!=null)
		{
		userRepo.save(u);
		return true;
		}
		return false;
		}
		return false;		
	}
	
	//t
	public boolean validateuserid(long userid)
	{
		if(userRepo.findOne(userid) != null)
			return true;
		return false;
	}
	
	//t
	public List<User> searchuser(String str)
	{
		return userRepo.search(str);
	}

	public String getUsername(long uid) {
		return userRepo.findOne(uid).getUsername();
	}
}
