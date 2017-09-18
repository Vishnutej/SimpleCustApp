package com.sample;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class SimpleCustDAO {
	
	public static void createTables()
	{
		try{
			Connection connection = getConnection();
			Statement stmt = connection.createStatement();
			stmt.executeUpdate("CREATE TABLE users(userid varchar(20) primary key, password varchar(20) NOT NULL)");
			stmt.executeUpdate("CREATE TABLE customers(userid varchar(20) primary key, name varchar(30), address varchar(40), mobile bigint, email varchar(50))");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static boolean signUpUser(String userId, String password) throws Exception
	{
		Connection connection = getConnection();
		String insertQuery = "insert into users"
				+ "(userid,password) values"
				+ "(?,?)";
		PreparedStatement ps = connection.prepareStatement(insertQuery);
		ps.setString(1,userId);
		ps.setString(2,password);
		ps.executeUpdate();
		ps.close();
		connection.close();
		return true;
		
	}
	
	public static boolean validateUser(String user,String pass) throws Exception
	{
		
			boolean isValid=false;
			Connection connection = getConnection();
			if(connection!=null)
			{
				System.err.println("Connected!!!");
//				Statement stmt = connection.createStatement();
//				stmt.executeUpdate("CREATE TABLE users(userid varchar(20) primary key, password varchar(20) NOT NULL)");
//				stmt.executeUpdate("INSERT INTO users values('user1','helloworld')");
//				stmt.executeUpdate("INSERT INTO users values('user2','helloworld2')");
				String query = "select count(*) from users where userid=? and password=?";
				PreparedStatement ps = connection.prepareStatement(query);
				ps.setString(1,user);
				ps.setString(2,pass);
				ResultSet rs = ps.executeQuery();
				if(rs!=null)
				{
					while(rs.next())
					{
						int count = rs.getInt(1);
						if(count == 1)
						{
							isValid = true;
						}
					}
				}
				rs.close();
				ps.close();
			}
			connection.close();
			return isValid;
		
	}
	
	public static boolean insertCustomer(Customer customer) throws Exception
	{
		
//		Statement stmt = connection.createStatement();
//		stmt.executeUpdate("CREATE TABLE customers(userid varchar(20) primary key, name varchar(30), address varchar(40), mobile bigint, email varchar(50))");
		Customer customerDb = getCustomer(customer.getUserid());
//		if(isCustomerAlreadyInserted(customer.getUserid()))
		if(customerDb!=null)
		{
			System.out.println("%%%%CUSTOMER ALREADY INSERTED%%%%%");
			updateCustomer(customer);
		}
		else
		{
		Connection connection = getConnection();
		String insertQuery = "insert into customers"
				+ "(userid,name,address,mobile,email) values"
				+ "(?,?,?,?,?)";
		PreparedStatement ps = connection.prepareStatement(insertQuery);
		ps.setString(1,customer.getUserid());
		ps.setString(2,customer.getName());
		ps.setString(3,customer.getAddress());
		ps.setLong(4,customer.getMobile());
		ps.setString(5,customer.getEmailid());
		ps.executeUpdate();
		ps.close();
		connection.close();
		}
		return true;
	}
	
	public static Customer getCustomer(String userid) throws Exception {
		boolean isInserted = false;
		Customer customer = null;
		Connection connection = getConnection();
//		String query = "select count(*) from customers where userid=?";
		String query = "select * from customers where userid=?";
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setString(1,userid);
		ResultSet rs = ps.executeQuery();
		if(rs!=null)
		{
			int count = 0;
			while(rs.next())
			{
				System.out.println("checking if customer is inserted");
				/*int count = rs.getInt(1);
				if(count>0)
				{
					isInserted = true;
				}*/
				count++;
				String uid = rs.getString("userid");
				String name = rs.getString("name");
				String address = rs.getString("address");
				Long mobile = rs.getLong("mobile");
				String email = rs.getString("email");
				customer = new Customer(uid,name,address,mobile,email);
			}
		  rs.close();
		}
		ps.close();
		connection.close();
		return customer;
//		return isInserted;
	}

	private static void updateCustomer(Customer customer) throws Exception {
		
		System.out.println("%%%% UPDATE CUSTOMER %%%%%");
		Connection connection = getConnection();
		String updateQuery = "update customers set name=? , address = ? , mobile = ?, email = ? where userid = ?";
		PreparedStatement ps = connection.prepareStatement(updateQuery);
		ps.setString(1,customer.getName());
		ps.setString(2,customer.getAddress());
		ps.setLong(3,customer.getMobile());
		ps.setString(4,customer.getEmailid());
		ps.setString(5,customer.getUserid());
		ps.executeUpdate();
		ps.close();
		connection.close();
		
	}

	private static Connection getConnection() throws URISyntaxException, SQLException {
        URI dbUri = new URI(System.getenv("DATABASE_URL"));

        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + dbUri.getPath();
//        String dbUrl ="jdbc:postgres://bgcgekrinwxpsn:182cf4110f66c59511804b19581cd594922dd7d8d43496eaa24496a10f80cb7c@ec2-54-221-212-208.compute-1.amazonaws.com:5432/d7femmmtf6id0u";

        return DriverManager.getConnection(dbUrl,username,password);
    }


}
