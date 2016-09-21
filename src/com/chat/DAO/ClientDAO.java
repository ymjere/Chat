package com.chat.DAO;

import org.bson.Document;

import com.chat.model.User;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class ClientDAO {
	MongoDatabase db = null;

	public ClientDAO() {
		MongoClient mongoClient = new MongoClient();
		db = mongoClient.getDatabase("Chat");
	}

	public void addClient(String pseudo, String mdp) {
		db.getCollection("User")
				.insertOne(new Document().append("_id", 0).append("pseudo", pseudo).append("password", mdp));
	}

	public boolean loginClient(String pseudo, String mdp) {
		MongoCursor<Document> iterable = db.getCollection("User")
				.find(new Document().append("pseudo", pseudo).append("password", mdp)).iterator();
		if (iterable.hasNext())
			return true;
		else
			return false;
	}

	public void getClient() {
		MongoCursor<Document> iterable = db.getCollection("User").find().iterator();
		while (iterable.hasNext())
			System.out.println(iterable.next());
	}

	public User getClient(String pseudo) {
		MongoCursor<Document> iterable = db.getCollection("User").find(new Document().append("pseudo", pseudo))
				.iterator();
		while (iterable.hasNext()) {
			Document doc = iterable.next();
			User user = new User(doc.getString("pseudo"));
			return user;
		}
		return null;
	}

	public int changePseudo(String actualPseudo, String newPseudo) {
		if (getClient(newPseudo) == null) {
			db.getCollection("User").updateOne(new Document("pseudo", actualPseudo),
					new Document("$set", new Document("pseudo", newPseudo)));
			return 0;
		} else
			return 1;
	}
}
