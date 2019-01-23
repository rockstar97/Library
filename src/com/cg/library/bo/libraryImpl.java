package com.cg.library.bo;

import com.cg.library.dao.libraryDB;

public class libraryImpl {
	
	String name;
	int bookID, personID;
	public int getPersonID() {
		return personID;
	}
	public void setPersonID(int personID) {
		this.personID = personID;
	}

	String author;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getBookID() {
		return bookID;
	}
	public void setBookID(int bookID) {
		this.bookID = bookID;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public void addBook()
	{
		libraryDB db = new libraryDB(name, author);
		db.write();
		
	}
	
	public void issueBook()
	{
		libraryDB db = new libraryDB();
		db.setBookID(bookID);
		db.setPersonID(personID);
		System.out.println(db.issue());
	}
	
	public void bookReturn()
	{
		libraryDB db = new libraryDB();
		db.setBookID(bookID);
		System.out.println(db.bookR());
	}

}
