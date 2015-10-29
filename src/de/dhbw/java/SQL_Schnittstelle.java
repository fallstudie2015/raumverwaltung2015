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

public abstract class SQL_Schnittstelle {

	// methode coinncetionaufbauen
	// methode connection schließen
	// methode batch verarbeitung
	// methode einzelne query
	static Connection con; // Verbindung zur Datenbank

	// //////////////////////////////////////////////////////////////////
	// ACHTUNG: Datenbank-Zugriffsdaten sind hart eingeschrieben.
	// Müssen also bei Änderung auch hier im Quellcode angepasst werden!
	/**
	 * Stellt eine Verbindung zur Datenbank her.
	 */
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

	/**
	 * Arbeitet die übergebene Abfrage ab und übergibt das Resultset.
	 * 
	 * @param abfrage
	 * @return
	 */
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

	/**
	 * Fügt der Datenbank einen neuen Satz hinzu.
	 * 
	 * @param abfrage
	 * @return
	 */
	public static int sqlInsert(String abfrage) {
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
			rowAffected = stmt.executeUpdate(abfrage,
					Statement.RETURN_GENERATED_KEYS);

			rs = stmt.getGeneratedKeys();

			if (rs.next()) {
				autoIncKeyFromApi = rs.getInt(1);
				System.out.println("Buchung PK " + autoIncKeyFromApi);
			} else {

				throw new EmptyStackException();
			}

		} catch (Exception e) {
			System.out.println("Update/Insert/Delete " + e.toString());
			Error_Message_Box.laufzeitfehler(e, "de.dhbw.java.sqlUpdate");
		}

		return autoIncKeyFromApi; // Rueckgabe wert jetzt der generierte
									// Schluessel
	}

	/**
	 * Bearbeitet einen Satz in der Datenbank; Löschen oder bearbeiten.
	 * 
	 * @param abfrage
	 * @return
	 */
	public static int sqlUpdateDelete(String abfrage) {
		Statement stmt = null;
		int rowAffected = 0;

		try {
			stmt = con.createStatement();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		try {
			rowAffected = stmt.executeUpdate(abfrage);
		} catch (Exception e) {
			System.out.println("Update/Insert/Delete " + e.toString());
			Error_Message_Box.laufzeitfehler(e, "de.dhbw.java.sqlUpdateDelete");
		}

		return rowAffected; // Wieviel Zeilen wurden verändert
	}

	/**
	 * Liest alle Räume aus der Datenbank aus
	 * 
	 * @return ArrayList mit Raumobjekten
	 */
	public static ArrayList<Raum> getRooms() {
		ArrayList<Raum> raumListe = new ArrayList<Raum>();
		try {
			// holt alle nicht "gelöschten" räume aus der Datenbank
			String abfrageString = "SELECT * FROM raum where entfernt = 0";
			ResultSet rs = sqlAbfrage(abfrageString);
			// TODO rsAusgabe wird nicht benoetigt
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

	/**
	 * Liest alle Buchungen aus der Datenbank
	 * 
	 * @return ArrayList mit Buchungsobjekten
	 */
	public static ArrayList<Buchung> getBuchung() {
		ArrayList<Buchung> buchungListe = new ArrayList<Buchung>();
		try {
			String abfrageString = "SELECT * FROM buchung";
			ResultSet rs = SQL_Schnittstelle.sqlAbfrage(abfrageString);
			// TODO rsAusgabe noetig??
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

	public static ArrayList<BuchungPlus> getBuchungPlus() {
		ArrayList<BuchungPlus> buchungListe = new ArrayList<BuchungPlus>();
		try {
			String abfrageString = "select buchungid, telefon, datum, zeitvon, zeitbis, kommentar, bestuhlung, buchung.benutzerid, raumid, status, vorname, nachname from buchung, benutzer where buchung.benutzerid = benutzer.benutzerid;";
			ResultSet rs = SQL_Schnittstelle.sqlAbfrage(abfrageString);
			// TODO rsAusgabe noetig??
			rsAusgabe(rs);

			while (rs.next()) {
				buchungListe.add(new BuchungPlus(rs.getInt("buchungid"), rs
						.getString("telefon"), rs.getDate("datum"), rs
						.getTime("zeitvon"), rs.getTime("zeitbis"), rs
						.getString("kommentar"), rs.getString("bestuhlung"), rs
						.getInt("benutzerid"), rs.getInt("raumid"), rs
						.getString("status"), rs.getString("vorname") + " "
						+ rs.getString("nachname")));
			}
		} catch (Exception e) {
			Error_Message_Box.laufzeitfehler(e,
					"de.dhbw.java.SQL_Schnittstelle.getVerwaltungBuchung");
		}
		return buchungListe;
	}

	/**
	 * Legt eine neue Buchung in der Datenbank an
	 * 
	 * @param telefon
	 *            Telefonnummer des bestellers
	 * @param datum
	 *            Datum der Buchung
	 * @param zeitVon
	 *            Zeit ab wann die Buchung gilt
	 * @param zeitBis
	 *            Zeit bis wann die Buchung gilt
	 * @param kommentar
	 *            Zusätzlicher Kommentar wird an Hausmeister weiter gegeben
	 * @param bestuhlung
	 *            Bestuhlungsart
	 * @param benutzerId
	 *            Benutzerkennung
	 * @param raumId
	 *            Raum in dem gebucht wird
	 * @param status
	 *            Status der Bestellung (unbestätigt/bestätigt/stoniert)
	 * @param anzPersonen
	 *            maximale Anzahl an Personen die im Raum platz haben
	 * @param ausstattungList
	 *            Welche Ausstattung wird bei der Buchung zusätzlich benötigt
	 * @param externeTeilnehmer
	 *            Sind externe Teilnehmer dabei (true/false)
	 * @return
	 */
	public static boolean insertBuchung(String telefon, Date datum,
			Time zeitVon, Time zeitBis, String kommentar, String bestuhlung,
			int benutzerId, int raumId, char status, int anzPersonen,
			ArrayList<String> ausstattungList, boolean externeTeilnehmer) {

		int intExterneTeilnehmer = 0;
		try {
			if (externeTeilnehmer == true) {
				intExterneTeilnehmer = 1;
			}
			String updateString = "INSERT INTO buchung (telefon, datum, zeitvon, zeitbis, kommentar, bestuhlung, benutzerid, raumid, status, anzPersonen, externeTeilnehmer) VALUES('"
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
					+ "', " + anzPersonen + ", '" + intExterneTeilnehmer + "')";
			int buchungId = SQL_Schnittstelle.sqlInsert(updateString);
			String ausstattung = null;
			for (int i = 0; i < ausstattungList.size(); i++) {
				ausstattung = ausstattungList.get(i);
				int ausstattungId = getAusstatungsArtenID(ausstattung);
				insertBuchungAusstattung(buchungId, ausstattungId);

			}
		} catch (Exception e) {
			Error_Message_Box.laufzeitfehler(e,
					"de.dhbw.java.SQL_Schnittstelle.insertBuchung");
			return false;
		}
		return true;
	}

	/**
	 * Liest alle unbestätigten bzw. vorgemerkten Buchungen aus der Datenbank
	 * 
	 * @return ArrayList mit allen Buchungen die noch nicht bestätigt wurden
	 */
	public static ArrayList<Buchung> getAlleVorgemerktenBuchungen() {
		ArrayList<Buchung> buchungListe = new ArrayList<Buchung>();
		try {
			String abfrageString = "SELECT * FROM buchung b WHERE b.status = 'v'";
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

	/**
	 * 
	 * @return
	 */
	public static ResultSet getBuchungenZuGenehmigung() {
		ResultSet rs = null;
		try {
			String abfrageString = "SELECT b.buchungid AS 'Buchungs-ID', CONCAT(vorname ,' ', nachname) AS Name, r.name AS 'Raumbez.', b.datum AS 'Datum' "
					+ "FROM buchung b JOIN benutzer u ON u.benutzerid = b.benutzerid "
					+ "JOIN raum r ON r.raumid = b.raumid WHERE b.status LIKE 'v' AND b.datum >= DATE(NOW()) ORDER BY b.datum ";
			rs = SQL_Schnittstelle.sqlAbfrage(abfrageString);
			rs.last();

		} catch (Exception e) {
			Error_Message_Box.laufzeitfehler(e,
					"de.dhbw.java.SQL_Schnittstelle.getBuchungenZuGenehmigung");
		}

		return rs;
	}

	/**
	 * 
	 * @param benutzerid
	 * @return
	 */
	public static ResultSet getMyBuchungen(int benutzerid) {
		ResultSet rs = null;
		try {
			String abfrageString = "SELECT buchungid as 'Buchungs-ID', r.name as 'Raumbez.', datum as Datum ,zeitvon as 'Zeit von', zeitbis as 'Zeit bis'"
					+ "FROM buchung b JOIN raum r ON r.raumid = b.raumid "
					+ "WHERE benutzerid = " + benutzerid + "; ";
			rs = SQL_Schnittstelle.sqlAbfrage(abfrageString);

		} catch (Exception e) {
			Error_Message_Box.laufzeitfehler(e,
					"de.dhbw.java.SQL_Schnittstelle.getMyBuchungen");
		}

		return rs;
	}

	/**
	 * Liest AusstattungsartID aus der Datenbank anhand der
	 * Ausstattungsbezeichnung
	 * 
	 * @param ausstattung
	 *            Die Ausstattungsbezeichnung der gewollten ID
	 * @return gibt die AusstattungsID zurück
	 */
	private static int getAusstatungsArtenID(String ausstattung) {
		// TODO Auto-generated method stub
		int ausstattungid = 0;
		try {
			String abfrageString = "SELECT ausstattungArtenid FROM ausstattungsArten a WHERE a.bezeichnung = '"
					+ ausstattung + "'";
			ResultSet rs = SQL_Schnittstelle.sqlAbfrage(abfrageString);

			if (rs.next()) {
				ausstattungid = rs.getInt("ausstattungsArtenid");

				System.out.println("ausstattungid " + ausstattungid);
			}

		} catch (Exception e) {
			Error_Message_Box.laufzeitfehler(e,
					"de.dhbw.java.SQL_Schnittstelle.getAusstattungsID");
		}
		return ausstattungid;
	}

	/**
	 * Läd jede Zusatzaussattung die bei einer Buchung ausgewählt wurde in die
	 * Datenbank und weißt sie einer Buchung zu
	 * 
	 * @param buchungId
	 *            ID der zugehörigen Buchung
	 * @param ausstattungId
	 *            ID der zugehörigen Ausstattung
	 */
	public static void insertBuchungAusstattung(int buchungId, int ausstattungId) {
		// TODO Auto-generated method stub
		try {

			String updateString = "INSERT INTO buchungAusstattung (buchungid, ausstattungid) VALUES ('"
					+ buchungId + "', '" + ausstattungId + "')";

			SQL_Schnittstelle.sqlInsert(updateString);
		} catch (Exception e) {
			Error_Message_Box.laufzeitfehler(e,
					"de.dhbw.java.SQL_Schnittstelle.insertBuchungAusstattung");

		}

	}

	/**
	 * Läd auftretende Fehlermeldung in die Datenbank mit ensprechenden
	 * Informationen
	 * 
	 * @param klasse
	 *            Klasse in der die Fehlermedung aufgetreten ist
	 * @param methode
	 *            Methode in der die Fehlermeldung aufgetreten ist
	 * @param localMessage
	 *            Fehlermeldung
	 * @param message
	 *            Fehlermeldung
	 * @param type
	 *            Typ des Fehlers z.b. Laufzeitfehler
	 * @param benutzerID
	 *            Bei welchem Benutzer ist die Fehlermeldung aufgetreten
	 * @return Hat der Datenbankeintrag funktioniert (true/false)
	 */
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

			int rowAffected = SQL_Schnittstelle.sqlInsert(updateString);

		} catch (Exception e) {
			// Error_Message_Box.laufzeitfehler(e,
			// "de.dhbw.java.SQL_Schnittstelle.insertLogging");
			return false;
		}
		return true;
	}

	/**
	 * Läd neuen Raum in die Datenbank
	 * 
	 * @param name
	 *            Bezeichnung des Raums
	 * @param strasse
	 *            In welcher Straße befindet sich der Raum
	 * @param stock
	 *            In welchem Stock befindet sich der Raum
	 * @param maxAnzPersonen
	 *            Maximale Anzahl der Personen die in den Raum pasen
	 * @param grundAusstattungList
	 *            Welche Grundausstattung ist in dem Raumvorhanden
	 * @return ob der Eintrag funktioniert hat oder nicht (true/false)
	 */
	public static boolean insertRaum(String name, String strasse, String stock,
			int maxAnzPersonen, ArrayList<String> grundAusstattungList) {

		try {

			String updateString = "INSERT INTO raum (name, strasse, stock, maxAnzPersonen, entfernt) VALUES('"
					+ name
					+ "', '"
					+ stock
					+ "', '"
					+ stock
					+ "', '"
					+ maxAnzPersonen + "', '0')";
			String grunAusstattungBezeichnung = null;
			int raumId = SQL_Schnittstelle.sqlInsert(updateString);

			for (int i = 0; i < grundAusstattungList.size(); i++) {
				grunAusstattungBezeichnung = grundAusstattungList.get(i);

				insertRaumGrundAusstattung(raumId, grunAusstattungBezeichnung);
			}
		} catch (Exception e) {
			Error_Message_Box.laufzeitfehler(e,
					"de.dhbw.java.SQL_Schnittstelle.insertRaum");
			return false;
		}
		return true;

	}

	/**
	 * Läd jede Grundausstattung für einen speziellen Raum in die Datenbank
	 * 
	 * @param raumId
	 *            In welchen Raum wird die Grundausstattung gespeichert
	 * @param grundAusstattungBezeichnung
	 *            Bezeichnung der Grundausstattung
	 */
	private static void insertRaumGrundAusstattung(int raumId,
			String grundAusstattungBezeichnung) {
		// TODO Auto-generated method stub
		try {

			String updateString = "INSERT INTO raumAusstattung (buchungid, bezeichnung) VALUES ('"
					+ raumId + "', '" + grundAusstattungBezeichnung + "')";

			SQL_Schnittstelle.sqlInsert(updateString);
		} catch (Exception e) {
			Error_Message_Box.laufzeitfehler(e,
					"de.dhbw.java.SQL_Schnittstelle.insertRaumAusstattung");
		}
	}

	/**
	 * Läd einen neue Zusatzausstattungsart in die Datenbank
	 * 
	 * @param ausstattungsartBezeichnung
	 *            Name der Ausstattungsart
	 * @return wurde erfolgreich in die Datenbank eingetragen oder nicht
	 */
	public static boolean insertAusstattungArt(String ausstattungsartBezeichnung) {
		// TODO Auto-generated method stub
		try {

			String updateString = "INSERT INTO ausstattungArten ( bezeichnung) VALUES ('"
					+ ausstattungsartBezeichnung + "')";

			SQL_Schnittstelle.sqlInsert(updateString);
		} catch (Exception e) {
			Error_Message_Box.laufzeitfehler(e,
					"de.dhbw.java.SQL_Schnittstelle.insertAusstattungArt");
			return false;
		}
		return true;
	}

	/**
	 * Ein Flag wird in die Raumtabelle in der Datenbank, sodass der Raum nicht
	 * mehr in Programm angezeigt wird
	 * 
	 * @param raumbezeichnung
	 *            Welcher Raum soll "gelöscht" werden
	 * @return hat der Update in der Datenbank funktioniert oder nicht
	 *         (true/false)
	 */
	public static boolean setDeleteFlagRaum(String raumbezeichnung) {
		try {

			String updateString = "Update raum set entfernt = 1 where name = '"
					+ raumbezeichnung + "'";
			System.out.println("updateString " + updateString);
			int rowsAffacted = SQL_Schnittstelle.sqlUpdateDelete(updateString);
			if (rowsAffacted == 0) {
				return false;
			}
		} catch (Exception e) {
			Error_Message_Box.laufzeitfehler(e,
					"de.dhbw.java.SQL_Schnittstelle.setDeleteFlagRaum");
		}
		return true;
	}

	/**
	 * Ändert den Buchungsstatus (unbestätigt/bestätigt/storniert)
	 * 
	 * @param buchungsID
	 *            Bei welche Buchung soll der Buchungsstatus geändert werden
	 * @param status
	 *            Welcher Status soll die Buchung bekommen
	 * @return wurde der Status erfolgreich geändert (true/false)
	 */
	public static boolean upadteBuchungStatus(int buchungsID, char status) {

		try {

			String updateString = "Update buchung set status = '" + status
					+ "' where buchungid = " + buchungsID;

			System.out.println("updateString " + updateString);
			SQL_Schnittstelle.sqlUpdateDelete(updateString);

		} catch (Exception e) {
			Error_Message_Box
					.laufzeitfehler(e,
							"de.dhbw.java.SQL_Schnittstelle.updateBuchungStatus(int, char)");
			return false;
		}
		return true;
	}

	/**
	 * 
	 * @param datum
	 * @param zeitVon
	 * @param zeitBis
	 * @param raumbezeichnung
	 * @param status
	 * @return
	 */
	// public static boolean updateBuchungStatus(Date datum, Time zeitVon,
	// Time zeitBis, String raumbezeichnung, char status) {
	// try {
	//
	// int raumId = getRaumID(raumbezeichnung);
	// String updateString = "Update buchung set status = '" + status
	// + "' where datum = '" + datum + "'and zeitvon = '"
	// + zeitVon + "' and zeitbis = '" + zeitBis
	// + "' and raumid = '" + raumId + "'";
	//
	// System.out.println("updateString " + updateString);
	// SQL_Schnittstelle.sqlUpdateDelete(updateString);
	//
	// } catch (Exception e) {
	// Error_Message_Box.laufzeitfehler(e,
	// "de.dhbw.java.SQL_Schnittstelle.getRaumID");
	// return false;
	// }
	// return true;
	// }
	/**
	 * Liest die RaumID aus der Datenbank anhand der Raumbezeichnung
	 * 
	 * @param raumbezeichnung
	 * @return gibt die RaumID aus der Datenbank zurück
	 */

	public static boolean updateBuchungStatus(Date datum, Time zeitVon,
			Time zeitBis, String raumbezeichnung, char status) {
		try {

			int raumId = getRaumID(raumbezeichnung);
			String updateString = "Update buchung set status = '" + status
					+ "' where datum = '" + datum + "'and zeitvon = '"
					+ zeitVon + "' and zeitbis = '" + zeitBis
					+ "' and raumid = '" + raumId + "'";

			System.out.println("updateString " + updateString);
			SQL_Schnittstelle.sqlUpdateDelete(updateString);

		} catch (Exception e) {
			Error_Message_Box.laufzeitfehler(e,
					"de.dhbw.java.SQL_Schnittstelle.getRaumID");
			return false;
		}
		return true;
	}

	public static int getRaumID(String raumbezeichnung) {
		// TODO Auto-generated method stub
		int raumId = 0;
		try {
			String abfrageString = "SELECT raumid FROM raum a WHERE a.name = '"
					+ raumbezeichnung + "'";
			ResultSet rs = SQL_Schnittstelle.sqlAbfrage(abfrageString);

			if (rs.next()) {
				raumId = rs.getInt("raumid");
			}

		} catch (Exception e) {
			Error_Message_Box.laufzeitfehler(e,
					"de.dhbw.java.SQL_Schnittstelle.getRaumID");
		}
		return raumId;
	}

	/**
	 * Liest den Raumnamen aus der Datenbank aus, anhand der RaumID
	 * 
	 * @param raumID
	 * @return gibt die Raumbezeichnung zurück
	 */
	public static String getRaumName(int raumID) {
		// TODO Auto-generated method stub
		String raumName = "";
		try {
			String abfrageString = "SELECT name FROM raum a WHERE a.raumid = '"
					+ raumID + "'";
			ResultSet rs = SQL_Schnittstelle.sqlAbfrage(abfrageString);

			if (rs.next()) {
				raumName = rs.getString("name");
			}

		} catch (Exception e) {
			Error_Message_Box.laufzeitfehler(e,
					"de.dhbw.java.SQL_Schnittstelle.getRaumID");
		}
		return raumName;
	}

	/**
	 * Liest die Emailadresse des übergebenen Benutzers aus der Datenbank
	 * 
	 * @param benutzerID
	 * @return Gibt die Emailadresse aus der Datenbank aus
	 */
	public static String getBenutzerEmail(int benutzerID) {
		// TODO Auto-generated method stub
		String emailAdresse = "";
		try {
			String abfrageString = "SELECT email FROM benutzer b WHERE b.benutzerid = '"
					+ benutzerID + "'";
			ResultSet rs = SQL_Schnittstelle.sqlAbfrage(abfrageString);

			if (rs.next()) {
				emailAdresse = rs.getString("email");
			}

		} catch (Exception e) {
			Error_Message_Box.laufzeitfehler(e,
					"de.dhbw.java.SQL_Schnittstelle.getBenutzerEmail");
		}
		return emailAdresse;
	}

	/**
	 * Liest den Benutzername aus der Datenbank aus, anhand der BenutzerID
	 * 
	 * @param benutzerID
	 * @return gibt den Benutzernamen zurück
	 */
	public static String getBenutzerName(int benutzerID) {
		// TODO Auto-generated method stub
		String benutzerName = "";
		try {
			String abfrageString = "SELECT vorname, nachname FROM benutzer b WHERE b.benutzerid = '"
					+ benutzerID + "'";
			ResultSet rs = SQL_Schnittstelle.sqlAbfrage(abfrageString);

			if (rs.next()) {
				benutzerName = rs.getString("vorname");
				benutzerName += " ";
				benutzerName += rs.getString("nachname");
			}

		} catch (Exception e) {
			Error_Message_Box.laufzeitfehler(e,
					"de.dhbw.java.SQL_Schnittstelle.getRaumID");
		}
		return benutzerName;
	}

	/**
	 * Liest alle Ausstattungsartenbezeichnungen aus der Datenbank und schreibt
	 * sie in ein ArrayList als Objekt von Ausstattung
	 * 
	 * @return gibt Das ArrayList an Ausstatungsarten zurück
	 */
	public static ArrayList<Ausstattung> getAusstattungArten() {
		ArrayList<Ausstattung> ausstattungListe = new ArrayList<Ausstattung>();
		try {
			String abfrageString = "SELECT * FROM ausstattungArten";
			ResultSet rs = SQL_Schnittstelle.sqlAbfrage(abfrageString);

			while (rs.next()) {
				ausstattungListe.add(new Ausstattung(rs
						.getInt("ausstattungArtenid"), rs
						.getString("bezeichnung")));
			}

		} catch (Exception e) {
			Error_Message_Box.laufzeitfehler(e,
					"de.dhbw.java.SQL_Schnittstelle.getAusstattungArten");
		}
		return ausstattungListe;
	}

	/**
	 * Liest alle zum Raum dazugehörige Grundausstattung in ein ArrayList
	 * 
	 * @param raumId
	 *            von welchem Raum soll die Grundausstatung geladen werden
	 * @return Gibt ein ArrayList zurück von Ausstattungsobjekten
	 */
	public static ArrayList<Ausstattung> getGrundAusstattungRaum(int raumId) {
		ArrayList<Ausstattung> grundAusstattungListe = new ArrayList<Ausstattung>();
		try {
			String abfrageString = "SELECT * FROM raumAusstattung where raumid = '"
					+ raumId + "'";
			ResultSet rs = SQL_Schnittstelle.sqlAbfrage(abfrageString);

			while (rs.next()) {
				grundAusstattungListe.add(new Ausstattung(rs
						.getInt("raumAusstattungid"), rs
						.getString("bezeichnung")));
			}

		} catch (Exception e) {
			Error_Message_Box.laufzeitfehler(e,
					"de.dhbw.java.SQL_Schnittstelle.getGrundAusstattungRaum");
		}
		return grundAusstattungListe;
	}

	/**
	 * Methode zum ändern des Passwortes.
	 * 
	 * @param aktuellesPasswort
	 * @param neuesPasswort
	 * @param neuesPasswortWiederholt
	 * @return Gibt Meldung (string) ob Passwort erfolgreich geändert wurde und
	 *         falls nicht warum nicht
	 */
	public static String passwortAendern(String aktuellesPasswort,
			String neuesPasswort, String neuesPasswortWiederholt) {
		try {
			// verschlüsselung der Passwörter
			aktuellesPasswort = SHA512_Encrypt.encrypt(aktuellesPasswort);
			neuesPasswort = SHA512_Encrypt.encrypt(neuesPasswort);
			neuesPasswortWiederholt = SHA512_Encrypt
					.encrypt(neuesPasswortWiederholt);
			// Liest aktuells Passwort aus der Datenbank
			String aktuellesPasswortDB = getAktuellesPasswort();
			// prüft akuelles Passwort auf richtigkeit
			if (!aktuellesPasswort.equals(aktuellesPasswortDB)) {
				return "Aktuelles Passwort wurde falsch eingegeben!";
			}
			// prüft ob die das neue Passwort mit seiner Wiederholung
			// übereinstimmt
			if (!neuesPasswort.equals(neuesPasswortWiederholt)) {
				return "Das neue Passwort und dessen Wiederholung sind nicht identisch";
			}
			int benutzerId = Benutzer.getBenutzerID();
			


			String updateString = "Update benutzer set passwort = '"
					+ neuesPasswort + "' where benutzerid = '" + benutzerId
					+ "'";


			System.out.println("updateString " + updateString);
			SQL_Schnittstelle.sqlUpdateDelete(updateString);

		} catch (Exception e) {
			Error_Message_Box.laufzeitfehler(e,
					"de.dhbw.java.SQL_Schnittstelle.passwortAendern");

		}
		return "Passwort wurde erfolgreich geandert!";
	}

	/**
	 * Gibt das aktuelle Passwort aus.
	 * 
	 * @return Aktuelles Passwort.
	 */
	private static String getAktuellesPasswort() {
		// TODO Auto-generated method stub
		String aktuellesPasswort = null;
		try {
			String abfrageString = "SELECT passwort FROM benutzer b WHERE b.benutzerid = '"
					+ Benutzer.getBenutzerID() + "'";
			ResultSet rs = SQL_Schnittstelle.sqlAbfrage(abfrageString);

			while (rs.next()) {
				aktuellesPasswort = rs.getString("passwort");
			}

		} catch (Exception e) {
			Error_Message_Box.laufzeitfehler(e,
					"de.dhbw.java.SQL_Schnittstelle.getAktuellesPasswort");
		}
		return aktuellesPasswort;

	}

	/**
	 * Prüft, ob die Buchung sich mit anderen Buchungen überschneidet.
	 * 
	 * @param raumbezeichnung
	 * @param datum
	 * @param zeitVon
	 * @param zeitBis
	 * @return true, wenn es keine Überschneidung gibt; false, wenn es eine oder
	 *         mehrere Überschneidungen gibt.
	 */
	public static boolean pruefeBuchungskonflikt(String raumbezeichnung,
			Date datum, Time zeitVon, Time zeitBis) {
		// TODO Auto-generated method stub
		int raumId = 0;
		try {

			raumId = getRaumID(raumbezeichnung);
			ArrayList<Buchung> buchungen = getBuchungAnTagX(datum, raumId);
			// prüft ob es eine Buchung in den räumen gewölbekeller und
			// Kegelbahn gibt
			// falls gibt die Methode ein Buchungskonflikt also false zurück
			System.out.println("Anzahl an buchungen: " + buchungen.size());
			if (!buchungen.isEmpty()
					&& (raumbezeichnung.equals("Gewölbekeller") || raumbezeichnung
							.equals("Kegelbahn"))) {
				return false;
			}
			// prüft jede Buchung an einem bestimmten Tag in einem bestimmten
			// Raum
			// ob die eingebene Zeit sich mit einer bestehenden Buchung
			// überschneidet
			for (int i = 0; i < buchungen.size(); i++) {
				Time zeitVonDb = buchungen.get(i).getZeitVon();
				Time zeitBisDb = buchungen.get(i).getZeitBis();

				if (zeitBis.after(zeitVonDb) && zeitVon.before(zeitBisDb)) {
					return false;
				}

				if (zeitVon.equals(zeitVonDb) || zeitBis.equals(zeitBisDb)) {
					return false;
				}
			}
			return true;
		} catch (Exception e) {
			Error_Message_Box.laufzeitfehler(e,
					"de.dhbw.java.SQL_Schnittstelle.pruefeBuchungskonflikt");
		}
		return true;
	}

	/**
	 * Gibt alle Buchungen am übergebenen Tag zu einem übergebenen Raum als
	 * Array aus.
	 * 
	 * @param datum
	 * @param raumId
	 * @return ArrayList(Buchung)
	 */
	public static ArrayList<Buchung> getBuchungAnTagX(Date datum, int raumId) {
		ArrayList<Buchung> buchungListe = new ArrayList<Buchung>();
		try {
			String abfrageString = "SELECT * FROM buchung b WHERE b.raumid = '"
					+ raumId + "' and datum = '" + datum
					+ "' and status <> 'a'";
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
					"de.dhbw.java.SQL_Schnittstelle.getBuchungAnTagX");
		}
		return buchungListe;
	}

	/**
	 * Gibt das Resultset Zeile für Zeile in der Konsole aus.
	 * 
	 * @param rs
	 */
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
				zeile++;
			}
			rs.beforeFirst();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			Error_Message_Box.laufzeitfehler(e,
					"de.dhbw.java.SQL_Schnittstelle.rsAusgabe");

		}
	}

	/**
	 * Fügt einen neuen Benutzer in die Datenbank ein.
	 * 
	 * @param nachname
	 * @param vorname
	 * @param email
	 * @param passwort
	 * @param rolle
	 * @param bereich
	 * @return true, wenn der Benutzer angelegt werden konnte; false, wenn eine
	 *         Exception auftrat.
	 */
	public static boolean insertBenutzer(String nachname, String vorname,
			String email, String passwort, String rolle, String bereich) {
		boolean antwort = false;
		int rueckgabeBenutzerID;
		try {
			passwort = EncryptPassword.SHA512(passwort);
			rueckgabeBenutzerID = SQL_Schnittstelle
					.sqlInsert("INSERT INTO benutzer (nachname, vorname, email, passwort, rolle, bereich)"
							+ " VALUES ('"
							+ nachname
							+ "', '"
							+ vorname
							+ "', '"
							+ email
							+ "', '"
							+ passwort
							+ "', '"
							+ rolle + "', '" + bereich + "')");
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

	/**
	 * Löscht den übergebenen Benutzer aus der Datenbank.
	 * 
	 * @param email
	 * @param vorname
	 * @param nachname
	 * @return true, wenn es funktioniert hat; false, wenn der Benutzer nicht in
	 *         der Datenbank vorhanden war.
	 */
	public static boolean deleteBenutzer(String email, String vorname,
			String nachname) {
		try {

			int rowAffected = SQL_Schnittstelle
					.sqlUpdateDelete("DELETE FROM benutzer WHERE email = '"
							+ email + "' and vorname = '" + vorname
							+ "' and nachname = '" + nachname + "'");
			if (rowAffected == 0) {
				return false;
			}

		} catch (Exception e) {
			Error_Message_Box.laufzeitfehler(e,
					"de.dhbw.java.SQL_Schnittstelle.insertBenutyer");

		}
		return true;
	}

	/**
	 * Löscht den übergebene Ausstattungstyp aus der Datenbank.
	 * 
	 * @param bezeichnung
	 * @return true, wenn es funktioniert hat; false, wenn der Typ nicht in der
	 *         Datenbank vorhanden war.
	 */
	public static boolean deleteAusstattungArt(String bezeichnung) {
		try {

			int rowAffected = SQL_Schnittstelle
					.sqlUpdateDelete("DELETE FROM ausstattungArten WHERE bezeichnung = '"
							+ bezeichnung + "'");

			if (rowAffected == 0) {
				return false;
			}
		} catch (Exception e) {
			Error_Message_Box.laufzeitfehler(e,
					"de.dhbw.java.SQL_Schnittstelle.deleteAusstattungArt");

		}
		return true;
	}
}
