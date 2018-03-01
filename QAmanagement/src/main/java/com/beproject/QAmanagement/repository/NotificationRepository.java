package com.beproject.QAmanagement.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.beproject.QAmanagement.models.*;

@Repository
public interface NotificationRepository extends CrudRepository<Notification,Long>
{
	List<Notification> findByuserid(long uid);
}
