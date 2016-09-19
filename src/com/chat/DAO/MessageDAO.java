package com.chat.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.chat.model.User;

public class MessageDAO {

	public void ecrireMessage(long idUser, String message) {
		Connection connection = null;
		try {
			connection = ConnectionManager.getInstance().getConnection();
			PreparedStatement preparedStatement = connection
					.prepareStatement("INSERT INTO message VALUES(?,?,?)");
			preparedStatement.setLong(1, idUser);
			preparedStatement.setString(2, message);
			ResultSet result = preparedStatement.executeQuery();

			if (result != null) {
				result.first();
				return new User(result.getInt("id"), result.getString(""));
			} else
				return null;

		} catch (Exception e) {
			return null;
		}

	}
}
