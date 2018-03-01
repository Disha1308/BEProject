package com.beproject.QAmanagement.repository;

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
}
