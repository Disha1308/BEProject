package com.beproject.QAmanagement.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.beproject.QAmanagement.models.*;

@Repository
public interface AnswerRatingRepository extends CrudRepository<AnswerRating,Long>
{

	@Query("select count(a) from AnswerRating a where a.answerid=:aid and type= :t ")
	long getvote(@Param("aid") long aid, @Param("t") String s);
	
	@Query("select a from AnswerRating a where a.answerid=:aid and a.userid= :uid ")
	AnswerRating findByUseridQuestionid(@Param("aid") long aid, @Param("uid") long uid);
	


}