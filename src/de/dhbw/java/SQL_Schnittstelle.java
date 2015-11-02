/* Programmiert von: Alexander Finkbeiner + Severin Klug
 * Programmiert für: Schnittstelle zwischen Oberfläche und Datenbank
 * Beschreibung: Regelt den Zugriff auf die Datenbank zwischen Anwendung und hinterlegter Datenbank
 */

package de.dhbw.java;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.EmptyStackException;

import de.dhbw.gui.Error_Message_Box;

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
			System.out.println(
					"Treiber gefunden\n--------------------------------");
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
	} // end method createConnection

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
	} // end method sqlAbfrage

	/**
	 * Fügt der Datenbank einen neuen Satz hinzu und gibt die ID der
	 * entsprechenden neuen Zeile aus.
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
			Error_Message_Box.laufzeitfehler(e, "de.dhbw.java.sqlInsert");
		}

		return autoIncKeyFromApi; // Rueckgabe wert jetzt der generierte //
									// Schluessel
	} // end method sqlInsert

	/**
	 * Fügt eine neue Verknüpfung zwischen buchung und Ausstattung hinzu.
	 * 
	 * @param abfrage
	 * @return
	 */
	public static int sqlInsertBuchungAusstattung(String abfrage) {
		Statement stmt = null;
		ResultSet rs = null;
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
			Error_Message_Box.laufzeitfehler(e,
					"de.dhbw.java.sqlInsertBuchungAusstattung");
		}

		return rowAffected; // Rueckgabe wert jetzt der generierte
							// Schluessel
	} // end method sqlInsertBuchungAusstattung

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
	} // end sqlUpdateDelete

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
			// Räume werden in einer Schleife zur Liste hinzugefügt
			while (rs.next()) {
				raumListe.add(new Raum(rs.getInt("raumid"),
						rs.getString("name"), rs.getString("strasse"),
						rs.getString("stock"), rs.getInt("maxAnzPersonen")));
			}
		} catch (Exception e) {
			Error_Message_Box.laufzeitfehler(e,
					"de.dhbw.java.SQL_Schnittstelle.getRooms");
		}
		return raumListe;
	} // end getRooms

	/**
	 * Liest alle für die Anzeige relevanten Buchungen aus der Datenbank v -
	 * vorgemerkt, g - genehmigt, p - puffer
	 * 
	 * @return ArrayList mit Buchungsobjekten
	 */
	public static ArrayList<Buchung> getBuchung() {
		ArrayList<Buchung> buchungListe = new ArrayList<Buchung>();
		try {
			String abfrageString = "SELECT * FROM buchung WHERE status = 'v' OR status = 'g' OR status = 'p'";
			ResultSet rs = SQL_Schnittstelle.sqlAbfrage(abfrageString);
			// Alle Datensätze werden zu einer ArrayListe hinzugefügt
			while (rs.next()) {
				buchungListe.add(new Buchung(rs.getInt("buchungid"),
						rs.getString("telefon"), rs.getDate("datum"),
						rs.getTime("zeitvon"), rs.getTime("zeitbis"),
						rs.getString("kommentar"), rs.getString("bestuhlung"),
						rs.getInt("benutzerid"), rs.getInt("raumid"),
						rs.getString("status")));
			}

		} catch (Exception e) {
			Error_Message_Box.laufzeitfehler(e,
					"de.dhbw.java.SQL_Schnittstelle.getVerwaltungBuchung");
		}
		return buchungListe;
	} // end getBuchung

	/**
	 * <<<<<<< HEAD Findet alle BUchungen des aktuellen Benutzers
	 * 
	 * @return ArrayList von Buchungen ======= Liest alle für die Anzeige
	 *         relevanten Buchungen aus der Datenbank v - vorgemerkt, g -
	 *         genehmigt, p - puffer BuchungPlus enthält noch zusätzliche
	 *         Informationen um unnötige Datenzugriffe zu vermeiden (Name,
	 *         Ausstattungsgegenstände)
	 * 
	 * @return ArrayList mit Buchungsobjekten >>>>>>> branch 'Speicherpunkt' of
	 *         https://github.com/fallstudie2015/raumverwaltung2015.git
	 */
	public static ArrayList<BuchungPlus> getBuchungPlus() {
		ArrayList<BuchungPlus> buchungListe = new ArrayList<BuchungPlus>();
		try {
			String abfrageString = "SELECT buchungid, telefon, datum, zeitvon, zeitbis, kommentar, bestuhlung, buchung.benutzerid, raumid, status, vorname, nachname, bereich, veranstaltungsBezeichnung FROM buchung, benutzer WHERE buchung.benutzerid = benutzer.benutzerid AND (status = 'v' OR status = 'g' OR status = 'p');";
			ResultSet rs = SQL_Schnittstelle.sqlAbfrage(abfrageString);

			// Ausstattungsgegnstände werden ausgelesen weil diese zur Anzeige
			// im Raumplaner_View benötigt werden
			abfrageString = "SELECT ba.buchungid, aal.bezeichnung FROM ausstattungsArtenLager aal, buchungAusstattung ba, buchung  WHERE ba.ausstattungsArtenLagerid = aal.ausstattungsArtenLagerid AND buchung.buchungid = ba.buchungid AND (buchung.status = 'v' OR buchung.status = 'g')";
			ResultSet rsa = SQL_Schnittstelle.sqlAbfrage(abfrageString);
			String ausstattung = "";
			// Ausstattungsgegenstände werden einer Buchung zugeordnet und in
			// einen String geschrieben
			while (rs.next()) {
				ausstattung = "";
				while (rsa.next()) {
					if (rs.getInt("buchungid") == rsa.getInt("buchungid")) {
						ausstattung = ausstattung + rsa.getString("bezeichnung")
								+ ", ";
					}
				}
				rsa.beforeFirst();
				if (ausstattung.contains(",")) {
					ausstattung = ausstattung.substring(0,
							ausstattung.length() - 2);
				}

				buchungListe.add(new BuchungPlus(rs.getInt("buchungid"),
						rs.getString("telefon"), rs.getDate("datum"),
						rs.getTime("zeitvon"), rs.getTime("zeitbis"),
						rs.getString("kommentar"), rs.getString("bestuhlung"),
						rs.getInt("benutzerid"), rs.getInt("raumid"),
						rs.getString("status"),
						rs.getString("vorname") + " "
								+ rs.getString("nachname"),
						ausstattung, rs.getString("bereich"),
						rs.getString("veranstaltungsBezeichnung")));
			}
		} catch (Exception e) {
			Error_Message_Box.laufzeitfehler(e,
					"de.dhbw.java.SQL_Schnittstelle.getBuchungPlus");
		}
		return buchungListe;
	} // end method getBuchungPlus

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
			ArrayList<String> ausstattungList, boolean externeTeilnehmer,
			String veranstaltungsbezeichnung) {

		int intExterneTeilnehmer = 0;
		try {
			if (externeTeilnehmer == true) {
				intExterneTeilnehmer = 1;
			}
			String updateString = "INSERT INTO buchung (telefon, datum, zeitvon, zeitbis, kommentar, bestuhlung, benutzerid, raumid, status, anzPersonen, externeTeilnehmer, veranstaltungsbezeichnung) VALUES('"
					+ telefon + "', '" + datum + "', '" + zeitVon + "', '"
					+ zeitBis + "', '" + kommentar + "', '" + bestuhlung + "', "
					+ benutzerId + ", " + raumId + ", '" + status + "', "
					+ anzPersonen + ", " + intExterneTeilnehmer + ", '"
					+ veranstaltungsbezeichnung + "')";
			int buchungId = SQL_Schnittstelle.sqlInsert(updateString);
			String ausstattung = null;
			// austattungsList wird auf null geprüft um die methode Liste.size()
			// verwenden zu können
			if (ausstattungList != null) {
				for (int i = 0; i < ausstattungList.size(); i++) {
					ausstattung = ausstattungList.get(i);
					int ausstattungId = getAusstatungsArtenID(ausstattung);
					insertBuchungAusstattung(buchungId, ausstattungId);

				}
			}
		} catch (Exception e) {
			Error_Message_Box.laufzeitfehler(e,
					"de.dhbw.java.SQL_Schnittstelle.insertBuchung");
			return false;
		}
		return true;
	} // end method insertBuchung

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
				buchungListe.add(new Buchung(rs.getInt("buchungid"),
						rs.getString("telefon"), rs.getDate("datum"),
						rs.getTime("zeitvon"), rs.getTime("zeitbis"),
						rs.getString("kommentar"), rs.getString("bestuhlung"),
						rs.getInt("benutzerid"), rs.getInt("raumid"),
						rs.getString("status")));
			}

		} catch (Exception e) {
			Error_Message_Box.laufzeitfehler(e,
					"de.dhbw.java.SQL_Schnittstelle.getBestellerBuchung");
		}
		return buchungListe;
	} // end method getAlleVorgemerktenBuchungen

	/**
	 * <<<<<<< HEAD Findet alle zu genehmigenden Buchungen
	 * 
	 * @return ======= Liest alle unbestätigten bzw. vorgemerkten Buchungen aus
	 *         der Datenbank Wird für die KLasse panelBuchung benötigt, zur
	 *         Ausgabe in Tabelle
	 * 
	 * @return ResultSet mit allen vorgemerkten Buchungen ('v') >>>>>>> branch
	 *         'Speicherpunkt' of
	 *         https://github.com/fallstudie2015/raumverwaltung2015.git
	 */
	public static ResultSet getBuchungenZuGenehmigung() {
		ResultSet rs = null;
		try {
			String abfrageString = "SELECT b.buchungid AS 'ID', CONCAT(vorname ,' ', nachname) AS Name, r.name AS 'Raumbez.', b.datum AS 'Datum' "
					+ "FROM buchung b JOIN benutzer u ON u.benutzerid = b.benutzerid "
					+ "JOIN raum r ON r.raumid = b.raumid WHERE b.status LIKE 'v' AND b.datum >= DATE(NOW()) ORDER BY b.datum ";
			rs = SQL_Schnittstelle.sqlAbfrage(abfrageString);
			rs.last();

		} catch (Exception e) {
			Error_Message_Box.laufzeitfehler(e,
					"de.dhbw.java.SQL_Schnittstelle.getBuchungenZuGenehmigung");
		}

		return rs;
	} // end method getBuchungenZuGenehmigung

	/**
	 * <<<<<<< HEAD Findet alle eigenen Buchungen ======= Liest alle Buchungen
	 * eines bestimmten Benutzers aus Diese werden in der Klasse
	 * panelMeineBuchung benötigt
	 * 
	 * >>>>>>> branch 'Speicherpunkt' of
	 * https://github.com/fallstudie2015/raumverwaltung2015.git
	 * 
	 * @param benutzerid
	 *            Fremdschlüssel in der Buchungstabelle
	 * @return ResultSet mit allen Buchungen des eingeloggten Benutzers
	 */
	public static ResultSet getMyBuchungen(int benutzerid) {
		ResultSet rs = null;
		try {
			String abfrageString = "SELECT buchungid as 'ID', r.name as 'Raumbez.', datum as Datum ,zeitvon as 'Zeit von', zeitbis as 'Zeit bis', status AS Status "
					+ "FROM buchung b JOIN raum r ON r.raumid = b.raumid "
					+ "WHERE benutzerid = " + benutzerid
					+ " AND b.datum >= DATE(NOW()) ORDER BY b.datum; ";
			rs = SQL_Schnittstelle.sqlAbfrage(abfrageString);

		} catch (Exception e) {
			Error_Message_Box.laufzeitfehler(e,
					"de.dhbw.java.SQL_Schnittstelle.getMyBuchungen");
		}

		return rs;
	} // end method getMyBuchungen

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
			String abfrageString = "SELECT ausstattungsArtenLagerid FROM ausstattungsArtenLager a WHERE a.bezeichnung = '"
					+ ausstattung + "'";
			ResultSet rs = SQL_Schnittstelle.sqlAbfrage(abfrageString);

			if (rs.next()) {
				ausstattungid = rs.getInt("ausstattungsArtenLagerid");

				System.out.println("ausstattungid " + ausstattungid);
			}

		} catch (Exception e) {
			Error_Message_Box.laufzeitfehler(e,
					"de.dhbw.java.SQL_Schnittstelle.getAusstattungsArtenID");
		}
		return ausstattungid;
	} // ende get AusstattungsArtenID

	/**
	 * Lädt jede Zusatzaussattung die bei einer Buchung ausgewählt wurde in die
	 * Datenbank und weißt sie einer Buchung zu
	 * 
	 * @param buchungId
	 *            ID der zugehörigen Buchung
	 * @param ausstattungId
	 *            ID der zugehörigen Ausstattung
	 */
	public static void insertBuchungAusstattung(int buchungId,
			int ausstattungId) {
		// TODO Auto-generated method stub
		try {

			String updateString = "INSERT INTO buchungAusstattung (buchungid, ausstattungsArtenLagerid) VALUES ('"
					+ buchungId + "', '" + ausstattungId + "')";

			SQL_Schnittstelle.sqlInsertBuchungAusstattung(updateString);
		} catch (Exception e) {
			Error_Message_Box.laufzeitfehler(e,
					"de.dhbw.java.SQL_Schnittstelle.insertBuchungAusstattung");
		}
	} // end method insertBuchungAusstattung

	/**
	 * Lädt auftretende Fehlermeldung in die Datenbank mit ensprechenden
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
					+ klasse.replace("'", "''") + "', '"
					+ methode.replace("'", "''") + "', '"
					+ localMessage.replace("'", "''") + "', '"
					+ message.replace("'", "''") + "', '"
					+ type.replace("'", "''") + "', now(), '" + benutzerID
					+ "')";

			int rowAffected = SQL_Schnittstelle.sqlInsert(updateString);

		} catch (Exception e) {
			// Error_Message_Box.laufzeitfehler(e,
			// "de.dhbw.java.SQL_Schnittstelle.insertLogging");
			return false;
		}
		return true;
	} // end method insertLogging

	/**
	 * Lädt neuen Raum in die Datenbank
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
					+ name + "', '" + strasse + "', '" + stock + "', '"
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
	} // end method insertRaum

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

			String updateString = "INSERT INTO raumAusstattung (raumid, bezeichnung) VALUES ('"
					+ raumId + "', '" + grundAusstattungBezeichnung + "')";

			SQL_Schnittstelle.sqlInsert(updateString);
		} catch (Exception e) {
			Error_Message_Box.laufzeitfehler(e,
					"de.dhbw.java.SQL_Schnittstelle.insertRaumAusstattung");
		}
	} // end methood insertRaumGrundAusstattung

	/**
	 * Läd einen neue Zusatzausstattungsart in die Datenbank
	 * 
	 * @param ausstattungsartBezeichnung
	 *            Name der Ausstattungsart
	 * @return wurde erfolgreich in die Datenbank eingetragen oder nicht
	 */
	public static boolean insertAusstattungsArtenLager(
			String ausstattungsartBezeichnung) {
		// TODO Auto-generated method stub
		try {

			String updateString = "INSERT INTO ausstattungsArtenLager ( bezeichnung) VALUES ('"
					+ ausstattungsartBezeichnung + "')";

			SQL_Schnittstelle.sqlInsert(updateString);
		} catch (Exception e) {
			Error_Message_Box.laufzeitfehler(e,
					"de.dhbw.java.SQL_Schnittstelle.insertAusstattungsArtenLager");
			return false;
		}
		return true;
	} // end method insertAusstattungsArtenLager

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
	} // end method setDeleteFlagRaum

	/**
	 * Setzt bei dem Raum mit gegebener ID das "gelöscht"-flag auf 1 (gelöscht)
	 * 
	 * @param raumid
	 * @return ======= }
	 * 
	 *         /** Ein Flag wird in die Raumtabelle in der Datenbank, sodass der
	 *         Raum nicht mehr in Programm angezeigt wird, über den
	 *         Primärschlüssel raumid
	 * 
	 * @param raumid
	 *            Welcher Raum soll "gelöscht" werden
	 * @return hat der Update in der Datenbank funktioniert oder nicht
	 *         (true/false) >>>>>>> branch 'Speicherpunkt' of
	 *         https://github.com/fallstudie2015/raumverwaltung2015.git
	 */
	public static boolean setDeleteFlagRaumByID(int raumid) {
		try {

			String updateString = "Update raum set entfernt = 1 where raumid = '"
					+ raumid + "'";
			System.out.println("updateString " + updateString);
			int rowsAffacted = SQL_Schnittstelle.sqlUpdateDelete(updateString);
			if (rowsAffacted == 0) {
				return false;
			}
		} catch (Exception e) {
			Error_Message_Box.laufzeitfehler(e,
					"de.dhbw.java.SQL_Schnittstelle.setDeleteFlagRaumByID");
		}
		return true;
	} // end method setDeleteFlagRaumByID

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
			Error_Message_Box.laufzeitfehler(e,
					"de.dhbw.java.SQL_Schnittstelle.updateBuchungStatus(int, char)");
			return false;
		}
		return true;
	} // end method upadteBuchungStatus

	/**
	 * Alle angelegten aktiven Räume werden ausgelesen Wird benötigt für die
	 * Klasse PanelRaum
	 * 
	 * @return ResultSet mit Raum-ID, Name, Strasse, Stock
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

	/**
	 * Aktualisiert den STATUS der gegebenen Buchung.
	 * 
	 * @param datum
	 * @param zeitVon
	 * @param zeitBis
	 * @param raumbezeichnung
	 * @param status
	 * @return
	 */
	public static boolean updateBuchungStatus(Date datum, Time zeitVon,
			Time zeitBis, String raumbezeichnung, char status) {
		try {

			int raumId = getRaumID(raumbezeichnung);
			String updateString = "Update buchung set status = '" + status
					+ "' where datum = '" + datum + "'and zeitvon = '" + zeitVon
					+ "' and zeitbis = '" + zeitBis + "' and raumid = '"
					+ raumId + "'";

			System.out.println("updateString " + updateString);
			SQL_Schnittstelle.sqlUpdateDelete(updateString);

		} catch (Exception e) {
			Error_Message_Box.laufzeitfehler(e,
					"de.dhbw.java.SQL_Schnittstelle.getRaumID");
			return false;
		}
		return true;
	} // end method updateBuchungStatus

	/**
	 * Findet die RaumID zu dem raum mit gegebener Bezeichnung.
	 * 
	 * @param raumbezeichnung
	 * @return
	 */
	public static ResultSet getAllRooms() {
		String abfrageString = "SELECT raumid AS 'Raum-ID', name AS Name, strasse AS Strasse, stock AS Stock from raum WHERE entfernt = 0;";
		ResultSet rs = SQL_Schnittstelle.sqlAbfrage(abfrageString);
		return rs;
	} // end method getAllRooms

	/**
	 * Alle angelegten aktiven Ausstattungen werden ausgelesen Wird benötigt für
	 * die Klasse PanelAusstattung
	 * 
	 * @return ResultSet mit ID, Bezeichnung, Art
	 */
	public static ResultSet getAllAusstattung() {
		String abfrageString = "SELECT ausstattungsArtenLagerid AS ID, bezeichnung AS Bezeichnung FROM ausstattungsArtenLager;";
		ResultSet rs = SQL_Schnittstelle.sqlAbfrage(abfrageString);
		return rs;
	}

	/**
	 * RaumID wird zurückgegeben über den Raumnamen
	 * 
	 * @return Int >>>>>>> branch 'Speicherpunkt' of
	 *         https://github.com/fallstudie2015/raumverwaltung2015.git
	 */
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
	} // end method getRaumID

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
					"de.dhbw.java.SQL_Schnittstelle.getRaumName");
		}
		return raumName;
	} // end method getRaumName

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
	} // end method getBenutzerEmail

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
					"de.dhbw.java.SQL_Schnittstelle.getBenutzerName");
		}
		return benutzerName;
	} // end method getBenutzerName

	/**
	 * Liest alle Ausstattungsartenbezeichnungen aus der Datenbank und schreibt
	 * sie in ein ArrayList als Objekt von Ausstattung
	 * 
	 * @return gibt Das ArrayList an Ausstatungsarten zurück
	 */
	public static ArrayList<Ausstattung> getAusstattungsArtenLager() {
		ArrayList<Ausstattung> ausstattungListe = new ArrayList<Ausstattung>();
		try {
			String abfrageString = "SELECT * FROM ausstattungsArtenLager";
			ResultSet rs = SQL_Schnittstelle.sqlAbfrage(abfrageString);

			while (rs.next()) {
				ausstattungListe.add(
						new Ausstattung(rs.getInt("ausstattungsArtenLagerid"),
								rs.getString("bezeichnung")));
			}

		} catch (Exception e) {
			Error_Message_Box.laufzeitfehler(e,
					"de.dhbw.java.SQL_Schnittstelle.getAusstattungArtenLager");
		}
		return ausstattungListe;
	} // end method getAusstattungsArtenLager

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
			String abfrageString = "SELECT * FROM raumAusstattung WHERE raumid = '"
					+ raumId + "'";
			ResultSet rs = SQL_Schnittstelle.sqlAbfrage(abfrageString);

			while (rs.next()) {
				grundAusstattungListe
						.add(new Ausstattung(rs.getInt("raumAusstattungid"),
								rs.getString("bezeichnung")));
			}

		} catch (Exception e) {
			Error_Message_Box.laufzeitfehler(e,
					"de.dhbw.java.SQL_Schnittstelle.getGrundAusstattungRaum");
		}
		return grundAusstattungListe;
	} // end method getGrundAusstattungRaum

	/**
	 * Liste alle zur Buchung dazugehörige Ausstattung als String
	 * 
	 * @param raumId
	 *            von welchem Raum soll die Grundausstatung geladen werden
	 * @return Gibt ein ArrayList zurück von Ausstattungsobjekten
	 */
	public static String getAusstattungBuchung(int buchungId) {
		String ausstattungListe = "";
		try {
			String abfrageString = "SELECT aal.bezeichnung FROM ausstattungsArtenLager aal "
					+ "JOIN buchungAusstattung ba ON ba.ausstattungsArtenLagerid = aal.ausstattungsArtenLagerid "
					+ "WHERE buchungid = '" + buchungId + "'";
			ResultSet rs = SQL_Schnittstelle.sqlAbfrage(abfrageString);

			while (rs.next()) {
				ausstattungListe = ausstattungListe
						+ rs.getString("bezeichnung");
				if (!rs.isLast()) {
					ausstattungListe = ausstattungListe + ", ";
				}
				System.out.println("ausstattungListe " + ausstattungListe);
			}

		} catch (Exception e) {
			Error_Message_Box.laufzeitfehler(e,
					"de.dhbw.java.SQL_Schnittstelle.getAusstattungBuchung");
		}
		return ausstattungListe;
	} // end method getAusstattungBuchung

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

			String updateString = "UPDATE benutzer SET passwort = '"
					+ neuesPasswort + "' where benutzerid = '" + benutzerId
					+ "'";

			System.out.println("updateString " + updateString);
			SQL_Schnittstelle.sqlUpdateDelete(updateString);

		} catch (Exception e) {
			Error_Message_Box.laufzeitfehler(e,
					"de.dhbw.java.SQL_Schnittstelle.passwortAendern");

		}
		return "Passwort wurde erfolgreich geandert!";
	} // end method passwortAendern

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
	} // end method getAktuellesPasswort

	/**
	 * Prüft, ob die Buchung sich mit anderen Buchungen überschneidet.
	 * 
	 * @param raumbezeichnung
	 * @param datum
	 * @param zeitVon
	 * @param zeitBis
	 * @return Gibt eine Meldung zurück falls es sich um einen besonderen 
	 * Raum handelt und falls nicht wird ein leerer String zurück gegeben
	 *         
	 */
	public static String pruefeBuchungskonflikt(String raumbezeichnung,
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
			if (!buchungen.isEmpty() && (raumbezeichnung.equals("Gewölbekeller"))) {
				return "Der Gewölbekeller kann nur einmal am Tag gebucht werden.";
			}
			if (!buchungen.isEmpty() && raumbezeichnung.equals("Kegelbahn")) {
				return "Die Kegelbahn kann nur einmal am Tag gebucht werden.";
			}
			// prüft jede Buchung an einem bestimmten Tag in einem bestimmten
			// Raum
			// ob die eingebene Zeit sich mit einer bestehenden Buchung
			// überschneidet
			for (int i = 0; i < buchungen.size(); i++) {
				Time zeitVonDb = buchungen.get(i).getZeitVon();
				Time zeitBisDb = buchungen.get(i).getZeitBis();

				if (zeitBis.after(zeitVonDb) && zeitVon.before(zeitBisDb)) {
					return " ";
				}

				if (zeitVon.equals(zeitVonDb) || zeitBis.equals(zeitBisDb)) {
					return " ";
				}
			}

		} catch (Exception e) {
			Error_Message_Box.laufzeitfehler(e,
					"de.dhbw.java.SQL_Schnittstelle.pruefeBuchungskonflikt");
		}
		return null;
	} // end method pruefeBuchungskonflikt

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
					+ raumId + "' AND datum = '" + datum
					+ "' AND status <> 'a' AND status <> 's'";
;
			ResultSet rs = SQL_Schnittstelle.sqlAbfrage(abfrageString);

			while (rs.next()) {
				buchungListe.add(new Buchung(rs.getInt("buchungid"),
						rs.getString("telefon"), rs.getDate("datum"),
						rs.getTime("zeitvon"), rs.getTime("zeitbis"),
						rs.getString("kommentar"), rs.getString("bestuhlung"),
						rs.getInt("benutzerid"), rs.getInt("raumid"),
						rs.getString("status")));
			}

		} catch (Exception e) {
			Error_Message_Box.laufzeitfehler(e,
					"de.dhbw.java.SQL_Schnittstelle.getBuchungAnTagX");
		}
		return buchungListe;
	} // end method getBuchungAnTagX

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
				for (int i = 1; i < rs.getMetaData().getColumnCount()
						+ 1; i++) {
					System.out.print(rs.getString(i) + "\t");
				}
				zeile++;
				System.out.println();
			}
			rs.beforeFirst();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			Error_Message_Box.laufzeitfehler(e,
					"de.dhbw.java.SQL_Schnittstelle.rsAusgabe");
		}
	} // end method rsAusgabe

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
			rueckgabeBenutzerID = SQL_Schnittstelle.sqlInsert(
					"INSERT INTO benutzer (nachname, vorname, email, passwort, rolle, bereich)"
							+ " VALUES ('" + nachname + "', '" + vorname
							+ "', '" + email + "', '" + passwort + "', '"
							+ rolle + "', '" + bereich + "')");
			if (rueckgabeBenutzerID != -1) {
				antwort = true;
			}

		} catch (Exception e) {
			antwort = false;
			Error_Message_Box.laufzeitfehler(e,
					"de.dhbw.java.SQL_Schnittstelle.insertBenutzer");
		}
		return antwort;
	} // end method insertBenutzer

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
					"de.dhbw.java.SQL_Schnittstelle.deleteBenutyer");

		}
		return true;
	} // end method deleteBenutzer

	/**
	 * Löscht den übergebene Ausstattungstyp aus der Datenbank.
	 * 
	 * @param bezeichnung
	 * @return true, wenn es funktioniert hat; false, wenn der Typ nicht in der
	 *         Datenbank vorhanden war.
	 */
	public static boolean deleteAusstattungArt(String bezeichnung) {
		try {

			int rowAffected = SQL_Schnittstelle.sqlUpdateDelete(
					"DELETE FROM ausstattungsArtenLager WHERE bezeichnung = '"
							+ bezeichnung + "'");

			if (rowAffected == 0) {
				return false;
			}
		} catch (Exception e) {
			Error_Message_Box.laufzeitfehler(e,
					"de.dhbw.java.SQL_Schnittstelle.deleteAusstattungArt");

		}
		return true;
	} // end method deleteAusstattungArt

	public static boolean deleteAusstattungArtByID(int id) {
		try {

			int rowAffected = SQL_Schnittstelle.sqlUpdateDelete(
					"DELETE FROM ausstattungsArtenLager WHERE ausstattungsArtenLagerid = '"
							+ id + "'");

			if (rowAffected == 0) {
				return false;
			}
		} catch (Exception e) {
			Error_Message_Box.laufzeitfehler(e,
					"de.dhbw.java.SQL_Schnittstelle.deleteAusstattungArtByID");

		}
		return true;
	} // end method deleteAusstattungArtByID
} // end class SQL_Schnittstelle
