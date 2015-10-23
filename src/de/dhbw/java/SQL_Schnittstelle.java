package de.dhbw.java;

import gui.Error_Message_Box;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.EmptyStackException;

import com.mysql.jdbc.UpdatableResultSet;

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
			Error_Message_Box.laufzeitfehler(s,
					"de.dhbw.java.SQL_Schnittstelle_createConnection");
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
			Error_Message_Box.laufzeitfehler(e, "de.dhbw.java.sqlAbfrage");
		}

		return rs;
	}

	public static int sqlUpdate(String abfrage) {
		Statement stmt = null;
		ResultSet rs = null;
		int autoIncKeyFromApi = -1;
		int rowAffected = 0;

		try {
			stmt = con.createStatement();
		} catch (Exception e) {
			System.out.println(e.toString());
		}

		try {
			rowAffected = stmt.executeUpdate(abfrage, Statement.RETURN_GENERATED_KEYS);
			
			rs = stmt.getGeneratedKeys();

		    if (rs.next()) {
		        autoIncKeyFromApi = rs.getInt(1);
		        System.out.println( "Buchung PK "+autoIncKeyFromApi );
		    } 
		    else {

		    	throw new EmptyStackException();
		    }
			
		} catch (Exception e) {
			System.out.println("Update/Insert/Delete " + e.toString());
			Error_Message_Box.laufzeitfehler(e, "de.dhbw.java.sqlUpdate");
		}

		return autoIncKeyFromApi; //Rueckgabe wert jetzt der generierte Schluessel
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
						.getString("stock"), rs.getInt("maxAnzPersonen")));
			}
		} catch (Exception e) {
			Error_Message_Box.laufzeitfehler(e,
					"de.dhbw.java.SQL_Schnittstelle.getRooms");
		}
		return raumListe;
	}

	public static ArrayList<Buchung> getBestellerBuchung() {
		ArrayList<Buchung> buchungListe = new ArrayList<Buchung>();
		try {
			String abfrageString = "SELECT * FROM buchung b WHERE b.benutzerid = "
					+ Benutzer.getBenutzerID();
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
			Error_Message_Box.laufzeitfehler(e,
					"de.dhbw.java.SQL_Schnittstelle.getBestellerBuchung");
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
			Error_Message_Box.laufzeitfehler(e,
					"de.dhbw.java.SQL_Schnittstelle.getVerwaltungBuchung");
		}
		return buchungListe;
	}

	public static boolean insertBuchung(String telefon,
 Date datum,
		Time zeitVon, Time zeitBis, String kommentar,
 String bestuhlung,
		int benutzerId, int raumId, char status, int anzPersonen,
		ArrayList<String> ausstattungList,
		boolean externeTeilnehmer) {
		int intExterneTeilnehmer = 0;
		try {
			if (externeTeilnehmer == true) {
				intExterneTeilnehmer = 1;
			}
			String updateString =
				"INSERT INTO buchung (telefon, datum, zeitvon, zeitbis, kommentar, bestuhlung, benutzerid, raumid, status, anzPersonen, externeTeilnehmer) VALUES('"
					+ telefon
					+ "', '"
					+ datum
					+ "', '"
					+ zeitVon
					+ "', '"
					+ zeitBis
					+ "', '"
					+ kommentar
					+ "', '"
					+ bestuhlung
					+ "', "
					+ benutzerId
					+ ", "
					+ raumId
					+ ", '"
					+ status
 +
					"', " +
					anzPersonen + ", '" + intExterneTeilnehmer
					+ "')";
			System.out.println( "updateString "+updateString );
			int buchungId = SQL_Schnittstelle.sqlUpdate(updateString);
			String ausstattung = null;
			for (int i = 0; i < ausstattungList.size(); i++) {
				ausstattung = ausstattungList.get(i);
				int ausstattungId = getAusstatungsID(ausstattung);
				insertBuchungAusstattung(buchungId, ausstattungId);

			}
		} catch (Exception e) {
			Error_Message_Box.laufzeitfehler(e,
					"de.dhbw.java.SQL_Schnittstelle.insertBuchung");
			return false;
		}
		return true;
	}

	private static int getAusstatungsID(String ausstattung) {
		// TODO Auto-generated method stub
		int ausstattungid = 0;
		try {
			String abfrageString =
				"SELECT ausstattungid FROM ausstattung a WHERE a.bezeichnung = '" +
					ausstattung + "'";
			ResultSet rs = SQL_Schnittstelle.sqlAbfrage(abfrageString);

			if (rs.next()) {
				ausstattungid = rs.getInt("ausstattungid");
			}

		} catch (Exception e) {
			Error_Message_Box.laufzeitfehler(e,
				"de.dhbw.java.SQL_Schnittstelle.getAusstattungsID");
		}
		return ausstattungid;
	}

	public static void insertBuchungAusstattung(int buchungId,
 int ausstattungId) {
		// TODO Auto-generated method stub
		try {

			String updateString =
				"INSERT INTO buchungAusstattung (buchungid, ausstattungid) VALUES ('" +
					buchungId + "', '" + ausstattungId + "')";

			SQL_Schnittstelle.sqlUpdate(updateString);
		} catch (Exception e) {
			Error_Message_Box.laufzeitfehler(e,
				"de.dhbw.java.SQL_Schnittstelle.insertBuchungAusstattung");

		}

	}

	public static boolean insertLogging(String klasse, String methode,
			String localMessage, String message, String type, int benutzerID) {

		try {
			String updateString = "INSERT INTO logging (klasse, methode, lokalMessage, message, typ, dateTime, benutzerid) VALUES('"
					+ klasse.replace("'", "''")
					+ "', '"
					+ methode.replace("'", "''")
					+ "', '"
					+ localMessage.replace("'", "''")
					+ "', '"
					+ message.replace("'", "''")
					+ "', '"
					+ type.replace("'", "''")
					+ "', now(), '"
					+ benutzerID
					+ "')";

			int rowAffected = SQL_Schnittstelle.sqlUpdate(updateString);

		} catch (Exception e) {
			// Error_Message_Box.laufzeitfehler(e,
			// "de.dhbw.java.SQL_Schnittstelle.insertLogging");
			return false;
		}
		return true;
	}

	public static void rsAusgabe(ResultSet rs) {
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
			Error_Message_Box.laufzeitfehler(e,
					"de.dhbw.java.SQL_Schnittstelle.rsAusgabe");

		}
	}
	
	public static boolean insertBenutzer(int benutzerid, String nachname,
			String vorname, String email, String passwort, String rolle) {
		boolean antwort = false;
		int rueckgabeBenutzerID;
		try {

			rueckgabeBenutzerID = SQL_Schnittstelle
					.sqlUpdate("INSERT INTO benutzer (benutzerid, nachname, vorname, email, passwort, rolle)"
							+ " VALUES ('"
							+ benutzerid
							+ "', '"
							+ nachname
							+ "', '"
							+ vorname
							+ "', '"
							+ email
							+ "', '"
							+ passwort + "', '" + rolle + "')");
			if (rueckgabeBenutzerID != -1) {
				antwort = true;
			}

		} catch (Exception e) {
			antwort = false;
			Error_Message_Box.laufzeitfehler(e,
					"de.dhbw.java.SQL_Schnittstelle.insertBenutyer");
		}
		return antwort;
	}
	
	public static boolean deleteBenutzer(String email, String vorname,
			String nachname) {
		try {

			SQL_Schnittstelle
					.sqlUpdate("DELETE FROM benutzer WHERE email = '" + email
							+ "' and vorname = '" + vorname
							+ "' and nachname = '" + nachname + "'");
			return true;

		} catch (Exception e) {
			Error_Message_Box.laufzeitfehler(e,
					"de.dhbw.java.SQL_Schnittstelle.insertBenutyer");
			return false;
		}
	}
}
