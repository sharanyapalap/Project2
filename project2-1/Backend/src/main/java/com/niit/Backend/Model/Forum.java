package com.niit.Backend.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "C_FORUM")
@Component
public class Forum extends ErrorDomain
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int forum_id;
	private String forum_title;
	@Lob
	private String description;
	private String username;
	private String status;
	private String date_time;
	@Lob
	private String rejected;
	public int getForum_id() {
		return forum_id;
	}
	public void setForum_id(int forum_id) {
		this.forum_id = forum_id;
	}
	public String getForum_title() {
		return forum_title;
	}
	public void setForum_title(String forum_title) {
		this.forum_title = forum_title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDate_time() {
		return date_time;
	}
	public void setDate_time(String date_time) {
		this.date_time = date_time;
	}
	public String getRejected() {
		return rejected;
	}
	public void setRejected(String rejected) {
		this.rejected = rejected;
	}
	
}