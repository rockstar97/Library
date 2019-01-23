package com.cg.library.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;



public class libraryDB {
	String name, author;
	int bookID, personID, issueID;
	
	
	Connection conn = null;
	
	{
	
		try {
			Class.forName("com.ibm.db2.jcc.DB2Driver");
		} 
		
		catch (ClassNotFoundException e) {
			System.out.println("class not found");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {
			conn = DriverManager.getConnection("jdbc:db2://localhost:50000/MDMDB","DB2ADMIN","db3Admin");
		} catch (SQLException e) {
			System.out.println("connection unsuccessful");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(conn != null)
		{
			//System.out.println("Connection successfull!!");
		}
		else {
			System.out.println("sorry!!");
	

		}
	
}



	public libraryDB(String name, String author) {
		super();
		this.name = name;
		this.author = author;
		
	}
	public void write()
	{
		String sql1 = "INSERT INTO db2admin.library(bookID, bookName, author) values (?,?,?)";
		String sql2 = "SELECT MAX(bookID) from db2admin.library";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql1);
			Statement stmt2 = conn.createStatement();
			java.sql.ResultSet rs = stmt2.executeQuery(sql2);
			rs.next();
			bookID = rs.getInt(1);
			bookID++;
			stmt.setInt(1, bookID);
			stmt.setString(2, name);
			stmt.setString(3, author);
			stmt.executeUpdate();
			System.out.println("Book Added!!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public String issue()
	
	{
		
		String message = "Error Inbetween";
		String sql1 = "SELECT * from db2admin.library where bookId = ?";
		String sql2 = "SELECT active FROM issue WHERE bookID = ? AND returnDate is null";
		String sql3 = "INSERT INTO issue (bookID, issueID, personID, issueDate) values(?, ?, ?, ?)";
		String sql4 = "SELECT MAX(issueID) from db2admin.issue";
		
		try {
			PreparedStatement stmt1 = conn.prepareStatement(sql1);
			stmt1.setInt(1, bookID);
			PreparedStatement stmt2 = conn.prepareStatement(sql2);
			stmt2.setInt(1, bookID);
			ResultSet rs1 = stmt1.executeQuery();
			if(rs1.next())
			{
				ResultSet rs2 = stmt2.executeQuery();
				if(rs2.next())
				{
					message= "Sorry! the book is already issued.";
					return message;
				}
				
				else
				{
					Statement stmt4 = conn.createStatement();
					ResultSet rs = stmt4.executeQuery(sql4);
					rs.next();
					issueID = rs.getInt(1);
					issueID++;
					//DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd"); 
					LocalDate localDate = LocalDate.now(); 
					Date date = java.sql.Date.valueOf(localDate);
					PreparedStatement stmt3 = conn.prepareStatement(sql3);
					stmt3.setInt(1, bookID);
					stmt3.setInt(2, issueID);
					stmt3.setInt(3, personID);
					stmt3.setDate(4, date);
					//stmt3.setLong(5, 'y');
					stmt3.executeUpdate();
					message = "Book Issued.";
					return message;
				}
			}
			else
			{
				message = "No such book";
				return message;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;
		
	}
	
	public String bookR()
	{
		String message = "Another Day Another Problem";
		String sql1 = "Select bookid from issue where returndate is NULL AND bookID = ?";
		String sql2 = "update issue set returnDate = ? where bookID = ? and returndate is null";
		try {
			PreparedStatement stmt1 = conn.prepareStatement(sql1);
			stmt1.setInt(1, bookID);
			ResultSet rs = stmt1.executeQuery();
			if(rs.next())
			{
				LocalDate localDate = LocalDate.now();
				Date date = Date.valueOf(localDate);
				PreparedStatement stmt2 = conn.prepareStatement(sql2);
				stmt2.setDate(1, date);
				stmt2.setInt(2, bookID);
				stmt2.executeUpdate();
				message = "Successfully Returned!!";
				return message;
			
			}
			else
			{
				message = "Book currently not issued!!";
				return message;
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;
	}
	public int getPersonID() {
		return personID;
	}
	public void setPersonID(int personID) {
		this.personID = personID;
	}
	public libraryDB() {
		super();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getBookID() {
		return bookID;
	}
	public void setBookID(int bookID) {
		this.bookID = bookID;
	}

}