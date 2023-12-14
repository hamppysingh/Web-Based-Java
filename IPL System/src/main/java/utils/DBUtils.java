package utils;

import java.sql.*;

public class DBUtils {
	private static Connection connection;
	public static Connection openConnection() throws SQLException{
		String url="jdbc:mysql://localhost:3306/ipl?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true";
		connection=DriverManager.getConnection(url,"root","Happy");
		return connection;
	}
	public static void closeConnection() throws SQLException{
		if(connection!=null)
			connection.close();
	}
}
