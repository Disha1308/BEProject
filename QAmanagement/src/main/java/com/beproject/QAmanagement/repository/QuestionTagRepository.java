package com.beproject.QAmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.beproject.QAmanagement.models.*;

@Repository
public interface QuestionTagRepository extends CrudRepository<QuestionTag,Long>
{
	@Query("select q.tagid from QuestionTag q where q.questionid = :qid")
	List<Long> findbyQuestionid(@Param("qid") long qid);

	@Query("select q.questionid from QuestionTag q where q.tagid = :tid")
	List<Long> findbyTopicid(@Param("tid") long tid);
}