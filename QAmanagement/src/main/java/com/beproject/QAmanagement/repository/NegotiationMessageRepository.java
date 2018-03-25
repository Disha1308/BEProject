package com.beproject.QAmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.beproject.QAmanagement.models.*;

@Repository
public interface NegotiationMessageRepository extends CrudRepository<NegotiationMessage,Long>
{
	@Query("select n from NegotiationMessage n where n.seekerid=:sid and n.expertid=:eid and n.questionid=:qid")
	public NegotiationMessage findunique(@Param("sid")long sid, @Param("eid") long eid,@Param("qid")long qid);
	
	@Query("select n.expertid from NegotiationMessage n where n.questionid=:qid")
	public List<Long> getExperts(@Param("qid")long qid);

	@Query("select n from NegotiationMessage n where n.expertid=:eid and n.questionid=:qid")
	public NegotiationMessage findunique( @Param("eid") long eid,@Param("qid")long qid);
	
	List<NegotiationMessage> findByquestionid(long qid);
	
}
