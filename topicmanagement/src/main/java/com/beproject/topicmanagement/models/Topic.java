package com.beproject.topicmanagement.models;


import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Topic")
public class Topic implements Serializable {

	@Id
	@GeneratedValue
	private long Id;
	private String name;
	private String photopath;
	private String topicdesciption;
	

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		this.Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhotopath() {
		return photopath;
	}

	public void setPhotopath(String photopath) {
		this.photopath = photopath;
	}

	public String getTopicdesciption() {
		return topicdesciption;
	}

	public void setTopicdesciption(String topicdesciption) {
		this.topicdesciption = topicdesciption;
	}



}
