package com.cg.library.pl;


import java.util.Scanner;

import com.cg.library.bo.libraryImpl;


public class libraryMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		
		String name;
		int bookID, personID;
		String author;
		libraryImpl obj = new libraryImpl();
		int ch=0;
		do
		{
			Scanner sc = new Scanner(System.in);
			System.out.println("Please select from following:\n1)Register book\n2)Book Issue\n3)Book Return\n4)Exit");
			ch = sc.nextInt();
			sc.nextLine();
			switch(ch)
			{
				case 1:
					
					System.out.println("Please enter the book name: ");
					name = sc.nextLine();
					
					System.out.println("\nPlease enter the name of the author: ");
					author = sc.nextLine();
					obj.setName(name);
					obj.setAuthor(author);
					obj.addBook();
					break;
					
				case 2:
					System.out.println("\nEnter the book id to be issued: ");
					bookID = sc.nextInt();
					System.out.println("\nEnter the PersonID: ");
					personID = sc.nextInt();
					obj.setPersonID(personID);
					obj.setBookID(bookID);
					obj.issueBook();
					break;
					
				case 3:
					System.out.println("\nEnter the bookID: ");
					bookID = sc.nextInt();
					obj.setBookID(bookID);
					obj.bookReturn();
					break;
				case 4:
					break;
			}
		
		}
		while(ch!=4);
		
		
	}

}
