package com.st.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionService {
	//TODO: make connection service out
	//TODO: make connection service singletone
	
	private static Connection connection = null;
	public static Connection connectDb()
	{
		if(connection == null)
		{
			try
			{  
				Class.forName("oracle.jdbc.driver.OracleDriver"); 
				GetDbCredentials credentials =  new GetDbCredentials();
				connection=DriverManager.getConnection(  
						credentials.getAddr(),credentials.getId(),credentials.getPass());   
 
				System.out.println("Connection established");
				
			}
			catch(Exception e)
			{ 
				System.out.println(e);
				System.out.println("Connection failed");
			} 
		}
//		else
//			System.out.println("COnnection already exists");
		return connection;
	}
	private DatabaseConnectionService() {}
	public static boolean closeConnection()
	{
		//System.out.println("Inside close con fn");
		if(connection != null) 
		{
			//System.out.println("inside if");
			try
			{
				connection.close();
				connection = null;
				System.out.println("Connection closed");
				return true;
			} 
			catch (SQLException e) 
			{
				System.out.println("Connection close failed");
				e.printStackTrace();
				return false;
				
			}
		}
		System.out.println("COnnection is already closed");
		return true;
	}
	
}