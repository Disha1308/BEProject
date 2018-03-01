package com.beproject.usermanagement.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.beproject.usermanagement.models.*;


@Repository
public interface UserPreferenceRepository extends CrudRepository<UserPreference, Long> 
{
	UserPreference findByuserid(long userid);
}
