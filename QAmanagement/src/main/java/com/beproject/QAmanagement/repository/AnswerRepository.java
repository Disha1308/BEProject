package com.beproject.QAmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.beproject.QAmanagement.models.*;

@Repository
public interface AnswerRepository extends CrudRepository<Answers,Long>
{
	List<Answers> findByquestionid(long qid);
	List<Answers> findByuserid(long uid);
	
	@Query("select a from Answers a where a.userid=:uid and a.questionid=:qid")
	Answers findunique(@Param("uid") long uid, @Param("qid") long qid);
}