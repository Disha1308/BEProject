package com.beproject.topicmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.beproject.topicmanagement.models.Topic;

@Repository
public interface TopicRepository extends CrudRepository<Topic, Long> 
{
	@Query("select t from Topic t where name like %:str%")
	List<Topic> search(@Param("str") String str);
}
