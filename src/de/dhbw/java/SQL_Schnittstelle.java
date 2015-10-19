package de.dhbw.java;

import gui.Error_Message_Box;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class SQL_Schnittstelle {

	// methode coinncetionaufbauen
	// methode connection schließen
	// methode batch verarbeitung
	// methode einzelne query
	static Connection con; // Verbindung zur Datenbank

	public static void createConnection() {
		try {
			// Datenbanktreiber fuer MySQL laden
			Class.forName("com.mysql.jdbc.Driver");
			System.out
					.println("Treiber gefunden\n--------------------------------");
		} catch (ClassNotFoundException e) {
			System.out.println("Treiber nicht gefunden");
		}
		// Connection zur Datenbank aufbauen
		try {
			con = DriverManager.getConnection(
					"jdbc:mysql://sql2.freemysqlhosting.net:3306/sql293125",
					"sql293125", "dG2!gP3!");
		} catch (SQLException s) {
			System.out.println("SQL-Exception aufgetreten");
		}
	}

	public static ResultSet sqlAbfrage(String abfrage) {
		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = con.createStatement();
		} catch (Exception e) {
			System.out.println(e.toString());
		}

		try {
			rs = stmt.executeQuery(abfrage);
		} catch (Exception e) {
			System.out.println("select " + e.toString());
		}

		return rs;
	}

	public static Raum[] getRooms() {
		Raum[] rooms;
		try {
			String abfrageString = "SELECT * FROM raum";
			ResultSet rs = sqlAbfrage(abfrageString);
			rs.last();
			rooms = new Raum[rs.getRow()];
			rs.beforeFirst();
			while (rs.next()) {
				rooms[rs.getRow() - 1].setRaumID(rs.getInt("raumid"));
				rooms[rs.getRow() - 1].setAnzPersonen(rs.getInt("anzPersonen"));
				rooms[rs.getRow() - 1].setName(rs.getString("name"));
				rooms[rs.getRow() - 1].setStrasse(rs.getString("strasse"));
				rooms[rs.getRow() - 1].setStock(rs.getString("stock"));
			}
		} catch (Exception e) {
			Error_Message_Box.errorBox("Laufzeitfehler", e.getMessage(),
					"de.dhbw.java.SQL_Schnittstelle_getRooms");
			rooms = new Raum[1]; // Dummiwert im Fehlerfall, weil Rückgabe ist
									// pflicht
		}
		return rooms;
	}
}
