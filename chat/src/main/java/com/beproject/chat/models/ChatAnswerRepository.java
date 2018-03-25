package com.beproject.chat.models;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ChatAnswerRepository extends CrudRepository<ChatAnswer,Long>
{
	List<ChatAnswer> findBychatsessionid(long sid);
}
