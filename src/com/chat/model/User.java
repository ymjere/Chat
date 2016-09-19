package com.chat.model;

public class User {
	private long id;
	private String pseudo;
	
	public User(long id, String pseudo) {
		this.id = id;
		this.pseudo = pseudo;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getPseudo() {
		return pseudo;
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	
	
}
