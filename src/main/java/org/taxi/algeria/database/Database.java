package org.taxi.algeria.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	String unicode= "?useUnicode=yes&characterEncoding=UTF-8";
	public Connection Get_Connection() throws Exception
	{
		try
		{
		String connectionURL = "jdbc:mysql://localhost:3306/carpooling";
		Connection connection = null;
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		connection = DriverManager.getConnection(connectionURL, "root", "");
	    return connection;
		}
		catch (SQLException e)
		{
		throw e;	
		}
		catch (Exception e)
		{
		throw e;	
		}
	}

}