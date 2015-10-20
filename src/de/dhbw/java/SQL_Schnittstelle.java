package de.dhbw.java;

import gui.Error_Message_Box;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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

	public static ArrayList<Raum> getRooms() {
		ArrayList<Raum> raumListe = new ArrayList<Raum>();
		try {
			String abfrageString = "SELECT * FROM raum";
			ResultSet rs = sqlAbfrage(abfrageString);
			rsAusgabe(rs);
			while (rs.next()) {
				raumListe.add(new Raum(rs.getInt("raumid"), rs
						.getString("name"), rs.getString("strasse"), rs
						.getString("stock"), rs.getInt("anzPersonen")));
			}
		} catch (Exception e) {
			Error_Message_Box.errorBox("Laufzeitfehler", e.getMessage(),
					"de.dhbw.java.SQL_Schnittstelle_getRooms");
		}
		return raumListe;
	}

	public static ArrayList<Buchung> getBestellerBuchung() {
		ArrayList<Buchung> buchungListe = new ArrayList<Buchung>();
		try {
			String abfrageString = "SELECT * FROM buchung b WHERE b.benutzerid = "
					+ Main_Raumbuchungssystem.benutzerId;
			ResultSet rs = SQL_Schnittstelle.sqlAbfrage(abfrageString);

			while (rs.next()) {
				buchungListe.add(new Buchung(rs.getInt("buchungid"), rs
						.getString("telefon"), rs.getDate("datum"), rs
						.getTime("zeitvon"), rs.getTime("zeitbis"), rs
						.getString("kommentar"), rs.getString("bestuhlung"), rs
						.getInt("benutzerid"), rs.getInt("raumid"), rs
						.getString("status")));
			}

		} catch (Exception e) {
			Error_Message_Box.errorBox("Laufzeitfehler", e.getMessage(),
					"de.dhbw.java.SQL_Schnittstelle_getRooms");
		}
		return buchungListe;
	}

	public static ArrayList<Buchung> getVerwaltungBuchung() {
		ArrayList<Buchung> buchungListe = new ArrayList<Buchung>();
		try {
			String abfrageString = "SELECT * FROM buchung";
			ResultSet rs = SQL_Schnittstelle.sqlAbfrage(abfrageString);
			rsAusgabe(rs);
			while (rs.next()) {
				buchungListe.add(new Buchung(rs.getInt("buchungid"), rs
						.getString("telefon"), rs.getDate("datum"), rs
						.getTime("zeitvon"), rs.getTime("zeitbis"), rs
						.getString("kommentar"), rs.getString("bestuhlung"), rs
						.getInt("benutzerid"), rs.getInt("raumid"), rs
						.getString("status")));
			}

		} catch (Exception e) {
			Error_Message_Box.errorBox("Laufzeitfehler", e.getMessage(),
					"de.dhbw.java.SQL_Schnittstelle_getRooms");
		}
		return buchungListe;
	}

	private static void rsAusgabe(ResultSet rs) {
		System.out.println();
		System.out.print("zeile" + "\t");
		try {
			for (int i = 1; i < rs.getMetaData().getColumnCount() + 1; i++) {
				System.out.print(rs.getMetaData().getColumnLabel(i) + "\t");
			}
			System.out.println("");
			int zeile = 1;
			rs.beforeFirst();
			while (rs.next()) {
				System.out.print(zeile + ": " + "\t");
				for (int i = 1; i < rs.getMetaData().getColumnCount() + 1; i++) {
					System.out.print(rs.getString(i) + "\t");
				}
				System.out.println("");
				zeile++;
			}
			rs.beforeFirst();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
