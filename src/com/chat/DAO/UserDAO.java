package com.chat.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.chat.DAO.ConnectionManager;
import com.chat.model.User;

public class UserDAO {

	public User alimUser(long id, String pwd) {
		Connection connection = null;
		try {
			connection = ConnectionManager.getInstance().getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(
					"SELECT * FROM user WHERE id=? AND password=?");
			preparedStatement.setLong(1, id);
			preparedStatement.setString(2, pwd);
			ResultSet result = preparedStatement.executeQuery();

			if(result != null){
				result.first();
				return new User(result.getInt("id"), result.getString(""));
			}else
				return null;
			
		} catch (Exception e) {
			return null;
		}
	}
}
