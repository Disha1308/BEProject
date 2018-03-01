package com.beproject.usermanagement.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.beproject.usermanagement.models.*;


@Repository
public interface UserTopicStatusRepository extends CrudRepository<UserTopicStatus, Long> 
{
	 @Query("SELECT t FROM UserTopicStatus t where t.userid = :userid and t.topicid = :topicid")
	 UserTopicStatus findTopic(@Param("userid") long userid, @Param("topicid") long topicid);
	 
	 @Query("SELECT t.topicid FROM UserTopicStatus t where t.userid = :userid and t.status = :status")
	 List<Long> findByuseridnstatus(@Param("userid") long userid,@Param("status") String s);
	 
	 @Query("SELECT t.userid FROM UserTopicStatus t where t.topicid = :topicid and t.status = :status")
	 List<Long> findBytopicidnstatus(@Param("topicid") long userid,@Param("status") String s);
}
