package edu.adias.powermock.easymock.staticmethod;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseReader {

	public static final String CONNECTION = "jdbc:mysql://localhost/testdb";
	
	public static String getById(int id) throws SQLException {
		String query = "SELECT * FROM Foo WHERE Id = ?";
		Connection connection = DriverManager.getConnection(CONNECTION);
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();
		
		resultSet.next();
		
		String result = resultSet.getString(0);
		
		resultSet.close();
		preparedStatement.close();
		connection.close();
		
		return result;
	}
}
