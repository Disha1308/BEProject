package com.beproject.usermanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.beproject.usermanagement.models.UserPreference;
import com.beproject.usermanagement.repository.UserPreferenceRepository;


@Service
public class UserPreferenceService 
{
	@Autowired
	UserPreferenceRepository preferenceRepo;
	
	@Autowired
	UserMgmtService uservice;
	//t
	public UserPreference getByUserid(long userid)
	{
		if(uservice.validateuserid(userid))
		{
			try
			{
				UserPreference u = preferenceRepo.findByuserid(userid);
				return u;
			}
			catch(Exception e)
		{
				System.out.println("exception in get preference by userid");
				return null;
		}
		}		
		return null;
	}
	
	/*public boolean createUserPreference(UserPreference u)
	{
		if(uservice.validateuserid(u.getUserid()))
		{	
			try
			{
				preferenceRepo.save(u);
				return true;
			}
			catch(Exception e)
		{
				System.out.println("exception in get preference by userid");
				return false;
		}
		}
		return false;
	}*/
	
	//t
	public boolean updateUserPreference(UserPreference u, long userid)
	{
		if(uservice.validateuserid(userid))
		{	
			if(u.getUserid() == userid)
			{
			UserPreference registerpreference =preferenceRepo.findByuserid(userid);
			if(registerpreference != null)
			{
				u.setPreferenceid(registerpreference.getPreferenceid());
			}
			preferenceRepo.save(u);
			return true;
			}
			System.out.println("no same userid");
		return false;
		}
		System.out.println("invalid userid");
		return false;
	}
}
