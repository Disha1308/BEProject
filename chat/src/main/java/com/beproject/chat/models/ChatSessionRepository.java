package com.beproject.chat.models;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatSessionRepository extends CrudRepository<ChatSession,Long>
{
	@Query("select s from ChatSession s where s.seekerid=:sid and s.expertid=:eid and s.questionid=:qid")
	ChatSession findunique(@Param("sid") long sid,@Param("eid") long eid, @Param("qid") long qid);
	
	@Query("select s.chatsessionid from ChatSession s where s.questionid=:qid")
	List<Long> findbyquestionid(@Param("qid") long qid);
}
