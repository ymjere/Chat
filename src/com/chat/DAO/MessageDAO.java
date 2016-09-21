package com.chat.DAO;

import java.lang.reflect.Array;
import java.util.ArrayList;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class MessageDAO {
	MongoDatabase db = null;

	public MessageDAO() {
		MongoClient mongoClient = new MongoClient();
		db = mongoClient.getDatabase("Chat");
	}

	public void addMessage(String pseudo, String message) {
		String format = "dd/MM/yy H:mm:ss"; 
		java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat( format ); 
		java.util.Date date = new java.util.Date();
		
		db.getCollection("Message")
		.insertOne(new Document().append("pseudo", pseudo).append("message", message).append("date", date));
	}
	
	public ArrayList<Document> getMessage(){
		ArrayList<Document> messages = new ArrayList<>();
		
		return messages;
	}
}
