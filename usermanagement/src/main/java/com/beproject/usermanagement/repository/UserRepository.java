package com.beproject.usermanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.beproject.usermanagement.models.User;

@Repository
public interface UserRepository extends CrudRepository<User,Long>
{
	User findByemail(String email);
	User findByusername(String username);
	
	@Query("select u from User u where username like %:str% or email like %:str%")
	List<User> search(@Param("str") String str);
}
