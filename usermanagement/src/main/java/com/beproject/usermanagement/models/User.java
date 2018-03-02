package com.beproject.usermanagement.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Users")
public class User implements Serializable {
	//enum reputation {beginner, intermediate, expert};

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long userid;

	private String contact;
	private String country;
	
	@Column(unique = true)
	private String email;
	private String password;
	
	@Column(unique = true)
	private String username;
	
	private String name;
	/*private reputation status;

	public reputation getStatus() {
		return status;
	}

	public void setStatus(reputation status) {
		this.status = status;
	}*/

	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
		
}