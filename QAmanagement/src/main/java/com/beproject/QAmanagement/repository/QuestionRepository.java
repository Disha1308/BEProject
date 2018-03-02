package com.beproject.QAmanagement.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.beproject.QAmanagement.models.*;

@Repository
public interface QuestionRepository extends CrudRepository<Question,Long>
{
	@Query("select q from Question q where title like %:str% or description like %:str%")
	List<Question> search(@Param("str") String str);
	
	@Query("select q.questionid from Question q where q.userid=:uid")
	List<Long> findByuserid(@Param("uid")long uid);
	
	@Query("select q.questionid from Question q where q.userid=:uid and q.title=:str")
	Question findunique(@Param("uid")long uid,@Param("str")String str);

}