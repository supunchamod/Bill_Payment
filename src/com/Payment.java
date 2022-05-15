
package com;

import java.sql.*;
import java.util.Base64;

public class Payment {
	//DataBase Connection
	
		private static String url = "jdbc:mysql://localhost:3306/payment_bill";
		private static String userName = "root";
		private static String password = "root";
		
		
		public Connection connect()
		{
		Connection con = null;
		
		try
		{
		  Class.forName("com.mysql.jdbc.Driver");
		  con= DriverManager.getConnection(url,userName,password);
		  //For testing
		  System.out.print("Successfully connected");
		}
		catch(Exception e)
		{
			System.out.println("Database connection is not success!!!");
		}
		
		return con;
		}

		public String AllPayment() {
			// TODO Auto-generated method stub
			String output = "";
			try
			{
				Connection con = connect();
				if (con == null)
				{return "Error while connecting to the database for reading."; }
				// Prepare the html table to be displayed
				output = "<table border=\"1\"><tr><th>Payment ID</th><th>Customer Name</th><th>Description</th><th>Date</th><th>Price</th><th>";




				String query = "select * from payment   ";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);


				// iterate through the rows in the result set
				while (rs.next())
				{
					String U_id = Integer.toString(rs.getInt("U_id"));
					String payName = rs.getString("payName");
					String description = rs.getString("description");
					String date = rs.getString("date");
					String price = rs.getString("price");
					

					// Add into the html table
					output += "<tr><td>" + U_id + "</td>";
					output += "<td>" + payName + "</td>";
					output += "<td>" + description + "</td>";
					output += "<td>" + date + "</td>";
					output += "<td>" + price + "</td>";
					
					// buttons
					output += "<td><input name='btnUpdate' type='button' value='Update' "
							+ "class='btnUpdate btn btn-success' data-itemid='" + U_id + "'></td>"
							+ "<td><input name='btnRemove' type='button' value='Remove' "
							+ "class='btnRemove btn btn-danger' data-itemid='" + U_id + "'></td></tr>";


				}
				con.close();

				// Complete the html table
				output += "</table>";
			}
			catch (Exception e)
			{
				output = "Error while reading the users.";
				System.err.println(e.getMessage());
			}
			return output;
		}


		//view payment
		public String viewPayment() {
			// TODO Auto-generated method stub
			String output = "";
			try
			{
				Connection con = connect();
				if (con == null)
				{return "Error while connecting to the database for reading."; }
				// Prepare the html table to be displayed
				output = "<table border=\"1\"><tr><th>Payment ID</th><th>Customer Name</th><th>Description</th><th>Date</th><th>Price</th><th>";

	                      

				
				String query = "select * from payment ";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);


				// iterate through the rows in the result set
				while (rs.next())
				{
					String U_id = Integer.toString(rs.getInt("U_id"));
					String payName = rs.getString("payName");
					String description = rs.getString("description");
					String date = rs.getString("date");
					String price = rs.getString("price");
					

					// Add into the html table
					output += "<tr><td>" + U_id + "</td>";
					output += "<td>" + payName + "</td>";
					output += "<td>" + description + "</td>";
					output += "<td>" + date + "</td>";
					output += "<td>" + price + "</td>";
					// buttons
					output += "<td><input name='btnUpdate' type='button' value='Update' "
							+ "class='btnUpdate btn btn-success' data-itemid='" + U_id + "'></td>"
							+ "<td><input name='btnRemove' type='button' value='Delete' "
							+ "class='btnRemove btn btn-danger' data-itemid='" + U_id + "'></td></tr>";


				}
				con.close();

				// Complete the html table
				output += "</table>";
			}
			catch (Exception e)
			{
				output = "Error while reading the users.";
				System.err.println(e.getMessage());
			}
			return output;
		}


		//insert payment
		public String insertPayment(String payName, String description, String date, String price)
		{
			String output = "";
			/*
			 * String regex = "^(.+)@(.+)$";
			 * 
			 * // String regex2 ="^\\d{10}$"; String regex3="^[0-9]{9}[vVxX]$";
			 */

			try
			{
				Connection con = connect();
				if (con == null)
				{
					return "Error while connecting to the database for inserting."; 
				}
				// create a prepared statement
				String query = " insert into payment (`U_id`,`payName`,`description`,`date`,`price`)" + " values (?, ?, ?, ?, ?)";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				// binding values
				preparedStmt.setInt(1, 0);
				preparedStmt.setString(2, payName);
				preparedStmt.setString(3, description);
				preparedStmt.setString(4, date);
				preparedStmt.setString(5, price);
				// execute the statement
				preparedStmt.execute();
				con.close();

				String newUsers = viewPayment();
				output = "{\"status\":\"success\", \"data\": \"" + newUsers + "\"}";

				/*
				 * if(password.length() < 6 ) { output = "Password is too short !"; } else
				 * if(!(email.matches(regex))) {
				 * 
				 * output = "Invalid e-mail address !"; }
				 * 
				 * else if(!(nic.matches(regex3))) {
				 * 
				 * output = "Invalid NIC !"; }
				 * 
				 * else { preparedStmt.execute(); con.close(); output =
				 * "You have successfully registered."; }
				 */
			}
			catch (Exception e)
			{
				output = "{\"status\":\"error\", \"data\": \"Error while inserting the user.\"}";
				System.err.println(e.getMessage());
			}
			return output;
		}






		//update payment details

		public String updatePayment(String ID ,String payName, String description, String date, String price)  {  
			String output = ""; 

			try   {    
				Connection con = connect(); 

				if (con == null)   
				{
					return "Error while connecting to the database for updating."; 

				} 

				String query = "UPDATE payment SET payName=?,description=?,date=?,price=? WHERE U_id=?"; 

				PreparedStatement preparedStmt = con.prepareStatement(query); 


				preparedStmt.setString(1, payName);   
				preparedStmt.setString(2, description);   
				preparedStmt.setString(3, date);  
				preparedStmt.setString(4, price); 
				//preparedStmt.setInt(5, Integer.parseInt(ID)); 

				preparedStmt.execute();   
				con.close(); 

				String newUsers = viewPayment();
				output = "{\"status\":\"success\", \"data\": \"" + newUsers + "\"}"; 

			}  
			catch (Exception e)   
			{  
				output = "{\"status\":\"error\", \"data\":\"Error while updating the user.\"}";
				System.err.println(e.getMessage());
			} 

			return output; 
		} 




		//delete profile details

		public String deletepayment(String U_id)  {   

			String output = ""; 

			try   {   

				Connection con = connect(); 

				if (con == null)    

				{
					return "Error while connecting to the database for deleting.";

				} 

				String query = "delete from payment where U_id=?"; 

				PreparedStatement preparedStmt = con.prepareStatement(query); 

				preparedStmt.setInt(1, Integer.parseInt(U_id)); 

				preparedStmt.execute();   
				con.close(); 

				String newUsers = viewPayment();
				output = "{\"status\":\"success\", \"data\": \"" + newUsers + "\"}";


			} 
			catch (Exception e)  
			{    
				output = "{\"status\":\"error\", \"data\":\"Error while deleting the user.\"}";
				System.err.println(e.getMessage()); 
			} 

			return output; 
		} 
}