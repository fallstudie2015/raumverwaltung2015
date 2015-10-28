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

import gui.Error_Message_Box;

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

		return rowAffected; // Rueckgabe wert jetzt der generierte Schluessel
	}

	public static ArrayList<Raum> getRooms() {
		ArrayList<Raum> raumListe = new ArrayList<Raum>();
		try {
			String abfrageString = "SELECT * FROM raum where entfernt = 0";
			ResultSet rs = sqlAbfrage(abfrageString);
			rsAusgabe(rs);
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
	}

	// public static ArrayList<Buchung> getBestellerBuchung() {
	// ArrayList<Buchung> buchungListe = new ArrayList<Buchung>();
	// try {
	// String abfrageString = "SELECT * FROM buchung b WHERE b.benutzerid = "
	// + Benutzer.getBenutzerID();
	// ResultSet rs = SQL_Schnittstelle.sqlAbfrage(abfrageString);
	//
	// while (rs.next()) {
	// buchungListe.add(new Buchung(rs.getInt("buchungid"), rs
	// .getString("telefon"), rs.getDate("datum"), rs
	// .getTime("zeitvon"), rs.getTime("zeitbis"), rs
	// .getString("kommentar"), rs.getString("bestuhlung"), rs
	// .getInt("benutzerid"), rs.getInt("raumid"), rs
	// .getString("status")));
	// }
	//
	// } catch (Exception e) {
	// Error_Message_Box.laufzeitfehler(e,
	// "de.dhbw.java.SQL_Schnittstelle.getBestellerBuchung");
	// }
	// return buchungListe;
	// }

	public static ArrayList<Buchung> getBuchung() {
		ArrayList<Buchung> buchungListe = new ArrayList<Buchung>();
		try {
			String abfrageString = "SELECT * FROM buchung";
			ResultSet rs = SQL_Schnittstelle.sqlAbfrage(abfrageString);
			rsAusgabe(rs);
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
	}

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
					+ telefon + "', '" + datum + "', '" + zeitVon + "', '"
					+ zeitBis + "', '" + kommentar + "', '" + bestuhlung + "', "
					+ benutzerId + ", " + raumId + ", '" + status + "', "
					+ anzPersonen + ", '" + intExterneTeilnehmer + "')";
			System.out.println("updateString " + updateString);
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
	}

	public static ResultSet getBuchungenZuGenehmigung() {
		ResultSet rs = null;
		try {
			String abfrageString = "SELECT b.buchungid, CONCAT(vorname ,' ', nachname) AS benutzerName, r.name AS raumName, b.datum "
					+ "FROM buchung b JOIN benutzer u ON u.benutzerid = b.benutzerid "
					+ "JOIN raum r ON r.raumid = b.raumid WHERE b.status LIKE 'v' ORDER BY b.datum ";
			rs = SQL_Schnittstelle.sqlAbfrage(abfrageString);
			rs.last();
			System.out.println("getBuchungenZuGenehmigung " + rs.getRow());

		} catch (Exception e) {
			Error_Message_Box.laufzeitfehler(e,
					"de.dhbw.java.SQL_Schnittstelle.getBuchungenZuGenehmigung");
		}

		return rs;
	}

	private static int getAusstatungsArtenID(String ausstattung) {
		// TODO Auto-generated method stub
		int ausstattungid = 0;
		try {
			String abfrageString = "SELECT ausstattungsArtenid FROM ausstattungsArten a WHERE a.bezeichnung = '"
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

	public static void insertBuchungAusstattung(int buchungId,
			int ausstattungId) {
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
	}

	/**
	 * Fügt neuen Raum hinzu
	 * 
	 * @param name
	 * @param strasse
	 * @param stock
	 * @param maxAnzPersonen
	 * @param grundAusstattungList
	 * @return
	 */
	public static boolean insertRaum(String name, String strasse, String stock,
			int maxAnzPersonen, ArrayList<String> grundAusstattungList) {

		try {

			String updateString = "INSERT INTO raum (name, strasse, stock, maxAnzPersonen, entfernt) VALUES('"
					+ name + "', '" + stock + "', '" + stock + "', '"
					+ maxAnzPersonen + "', '0')";
			System.out.println("updateString " + updateString);
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

	public static boolean insertAusstattungArt(
			String ausstattungsartBezeichnung) {
		// TODO Auto-generated method stub
		try {

			String updateString = "INSERT INTO AusstattungArten ( bezeichnung) VALUES ('"
					+ ausstattungsartBezeichnung + "')";

			SQL_Schnittstelle.sqlInsert(updateString);
		} catch (Exception e) {
			Error_Message_Box.laufzeitfehler(e,
					"de.dhbw.java.SQL_Schnittstelle.insertAusstattungArt");
			return false;
		}
		return true;
	}

	public static boolean setDeleteFlagRaum(String raumbezeichnung) {
		try {

			String updateString = "Update raum set entfernt = 1 where name = "
					+ raumbezeichnung + "'";
			System.out.println("updateString " + updateString);
			int raumId = SQL_Schnittstelle.sqlUpdateDelete(updateString);

		} catch (Exception e) {
			Error_Message_Box.laufzeitfehler(e,
					"de.dhbw.java.SQL_Schnittstelle.setDeleteFlagRaum");
			return false;
		}
		return true;
	}

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
	}

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

	public static ArrayList<Ausstattung> getAusstattungArten() {
		ArrayList<Ausstattung> ausstattungListe = new ArrayList<Ausstattung>();
		try {
			String abfrageString = "SELECT * FROM ausstattungArten";
			ResultSet rs = SQL_Schnittstelle.sqlAbfrage(abfrageString);

			while (rs.next()) {
				ausstattungListe
						.add(new Ausstattung(rs.getInt("ausstattungArtenid"),
								rs.getString("bezeichnung")));
			}

		} catch (Exception e) {
			Error_Message_Box.laufzeitfehler(e,
					"de.dhbw.java.SQL_Schnittstelle.getAusstattungArten");
		}
		return ausstattungListe;
	}

	public static ArrayList<Ausstattung> getGrundAusstattungRaum(int raumId) {
		ArrayList<Ausstattung> grundAusstattungListe = new ArrayList<Ausstattung>();
		try {
			String abfrageString = "SELECT * FROM raumAusstattung where raumid = '"
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
	}

	public static String passwortAendern(String aktuellesPasswort,
			String neuesPasswort, String neuesPasswortWiederholt) {
		try {
			aktuellesPasswort = SHA512_Encrypt.encrypt(aktuellesPasswort);
			neuesPasswort = SHA512_Encrypt.encrypt(neuesPasswort);
			neuesPasswortWiederholt = SHA512_Encrypt
					.encrypt(neuesPasswortWiederholt);

			String aktuellesPasswortDB = getAktuellesPasswort();
			if (!aktuellesPasswort.equals(aktuellesPasswortDB)) {
				return "Aktuelles Passwort wurde falsch eingegeben!";
			}
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

	public static boolean pruefeBuchungskonflikt(String raumbezeichnung,
			Date datum, Time zeitVon, Time zeitBis) {
		// TODO Auto-generated method stub
		int raumId = 0;
		try {

			raumId = getRaumID(raumbezeichnung);
			ArrayList<Buchung> buchungen = getBuchungAnTagX(datum, raumId);
			if (!buchungen.isEmpty() && (raumbezeichnung.equals("Gewölbekeller")
					|| raumbezeichnung.equals("Kegelbahn"))) {
				return false;
			}
			for (int i = 0; i < buchungen.size(); i++) {
				Time zeitVonDb = buchungen.get(i).getZeitVon();
				Time zeitBisDb = buchungen.get(i).getZeitVon();

				if (zeitBis.after(zeitVonDb) && zeitVon.before(zeitBisDb)) {
					return false;
				}

				if (zeitVon.equals(zeitVonDb) || zeitBis.equals(zeitBis)) {
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

	public static ArrayList<Buchung> getBuchungAnTagX(Date datum, int raumId) {
		ArrayList<Buchung> buchungListe = new ArrayList<Buchung>();
		try {
			String abfrageString = "SELECT * FROM buchung b WHERE b.raumid = '"
					+ raumId + "' and datum = '" + datum + "'";
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
				for (int i = 1; i < rs.getMetaData().getColumnCount()
						+ 1; i++) {
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
					"de.dhbw.java.SQL_Schnittstelle.insertBenutyer");
		}
		return antwort;
	}

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

	public static boolean deleteAusstattungArt(String bezeichnung) {
		try {

			int rowAffected = SQL_Schnittstelle.sqlUpdateDelete(
					"DELETE FROM ausstattungArten WHERE bezeichnung = '"
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
