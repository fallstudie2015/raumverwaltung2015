package de.dhbw.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQL_Schnittstelle {
	
	// methode coinncetionaufbauen
	// methode connection schließen
	// methode batch verarbeitung
	// methode einzelne query
	static Connection con; 		// Verbindung zur Datenbank

	public SQL_Schnittstelle()
	{
	try{
	// Datenbanktreiber fuer MySQL laden
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("Treiber gefunden\n--------------------------------");
	}
	catch(ClassNotFoundException e){
		System.out.println("Treiber nicht gefunden");
	}
	// Connection zur Datenbank aufbauen
	try{
		con=DriverManager.getConnection("jdbc:mysql://sql2.freemysqlhosting.net:3306/sql293125","sql293125","dG2!gP3!");}
	catch(SQLException s){System.out.println("SQL-Exception aufgetreten");}
	} /* end Konstruktor */

	public static ResultSet sqlAbfrage(String abfrage)
	{ 
		Statement stmt = null;
		ResultSet rs = null;
		
		try {stmt=con.createStatement();}
		catch(Exception e){System.out.println(e.toString());}
		
		try 
		{
			rs = stmt.executeQuery(abfrage);
		} 
		catch (Exception e) 
		{
			System.out.println("select " + e.toString());
		}

    return rs;
	}
	

}
