package com.chat.model;

import java.util.Date;

public class Message {
	private User user;
	private String message;
	private Date date;
	
	public Message(User user, String message, Date date) {
		this.user = user;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
