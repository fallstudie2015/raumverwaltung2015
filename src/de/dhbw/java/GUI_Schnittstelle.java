package de.dhbw.java;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GUI_Schnittstelle {

	/**
	 * Gibt intfuer die GUI zurueck
	 * 
	 * @param benutzername
	 *            : name des benutzers, egal ob Verwalter oder Besteller.
	 * @param _passwort
	 *            : passwort des Benutzers
	 * @return
	 * @return: -1 oder persnur , je nach ergebnis der abfrage * -1: alles
	 *          scheisse , * persnr: alles gut gelaufen - persnr wird
	 *          zurueckggeben
	 */
	public void einloggen(String email, String _passwort) {
		// sql abfrage ob nutzer in tabelle und typ(besteller/verwalter/admin)
		// herauslesen, sowie ID

		// passwort verschluesseln
		_passwort = SHA512_Encrypt.encrypt(_passwort);
		// abfrageString erstellen
		String abfrageString =
			"select * from benutzer where email = '" + email +
				"' and passwort = '" + _passwort + "'";
		ResultSet rs = SQL_Schnittstelle.sqlAbfrage(abfrageString);

		// WIR m�s�sen von de rDatenbankgruppe wissern, wie die einloggdaten
		// zurueckgegeben wrerden. also die infos.
		// dementsprechend machen wir dann hier den spezifischen benutzer als ID
		// oder als namen-string und der typ als int oder sonstiges erfahren.
		// also arbeiten wir hier wahrscheinlich mit rs.getString <--- darum
		// gehts naemlich
		// wir benutzen danach nurnoch den typ des benutzers, damit wir ein
		// entsprechendes BEnutzer-Objekt erstellen koennen.
		// Dieses traegt dann als namen "benutzername"
		String RSvorname = "";
		String RSnachname = "";
		String RSbenutzertypString = "";
		String RSemail = "";
		int RSbenutzerID = 0;
		char RSbenutzertyp = ' ';
		int antwort = -1;

		try {
			rs.first();

			RSvorname = rs.getString("vorname");
			RSnachname = rs.getString("nachname");
			RSemail = rs.getString("email");
			RSbenutzertypString = rs.getString("rolle");
			RSbenutzerID = rs.getInt("benutzerid");
			RSbenutzertyp = RSbenutzertypString.charAt(0);

			Benutzer.setBenutzerGesamt(RSbenutzerID, RSemail, RSvorname,
				RSnachname, RSbenutzertyp);

		} catch (Exception e) {
			System.out.println("Ausgabe " + e.toString());
			Benutzer.setBenutzerID(-1);
		}

	}

	public void ladeStartbildschirm() throws SQLException {
		Main_Raumbuchungssystem.raumListe.clear();
		switch (Main_Raumbuchungssystem.benutzertyp) {
		case 'v':
			ladeVerwalterStartbildschirm();
			break;
		case 'b':
			ladeBestellerStartbildschirm();
			break;
		case 'a':
			System.out.println("laeuft A");
			ladeBestellerStartbildschirm();
			break;
		default:
			break;
		}
	}

	private void ladeBestellerStartbildschirm() throws SQLException {
		// setz den ganzen scheiss visible
		String abfrage1String = "SELECT * FROM raum";
		ResultSet rs1 = SQL_Schnittstelle.sqlAbfrage(abfrage1String);

		while (rs1.next()) {
			Main_Raumbuchungssystem.raumListe.add(new Raum(
				rs1.getInt("raumid"), rs1.getString("name"), rs1
					.getString("strasse"), rs1.getString("stock"), rs1
					.getInt("anzPersonen")));
		}

		String abfrage2String =
			"SELECT * FROM buchung b WHERE b.benutzerid = " +
				Main_Raumbuchungssystem.benutzerId;
		ResultSet rs2 = SQL_Schnittstelle.sqlAbfrage(abfrage2String);

		while (rs2.next()) {
			Main_Raumbuchungssystem.meineBuchungen.add(new Buchung(rs2
				.getInt("buchungid"), rs2.getString("telefon"), rs2
				.getDate("datum"), rs2.getTime("zeitvon"), rs2
				.getTime("zeitbis"), rs2.getString("kommentar"), rs2
				.getString("bestuhlung"), rs2.getInt("benutzerid"), rs2
				.getInt("raumid"), rs2.getString("status")));
		}

	}

	private void ladeVerwalterStartbildschirm() throws SQLException {
		// setz den ganzen scheiss visible
		String abfrage1String = "SELECT * FROM raum";
		ResultSet rs1 = SQL_Schnittstelle.sqlAbfrage(abfrage1String);

		while (rs1.next()) {
			Main_Raumbuchungssystem.raumListe.add(new Raum(
				rs1.getInt("raumid"), rs1.getString("name"), rs1
					.getString("strasse"), rs1.getString("stock"), rs1
					.getInt("anzPersonen")));
		}

		String abfrage2String = "SELECT * FROM buchung";
		ResultSet rs2 = SQL_Schnittstelle.sqlAbfrage(abfrage2String);

		while (rs2.next()) {
			Main_Raumbuchungssystem.meineBuchungen.add(new Buchung(rs2
				.getInt("buchungid"), rs2.getString("telefon"), rs2
				.getDate("datum"), rs2.getTime("zeitvon"), rs2
				.getTime("zeitbis"), rs2.getString("kommentar"), rs2
				.getString("bestuhlung"), rs2.getInt("benutzerid"), rs2
				.getInt("raumid"), rs2.getString("status")));
			Buchung b = (Buchung) Main_Raumbuchungssystem.meineBuchungen.get(0);
			String s = b.getTelefon();
			System.out.println("laeuft teflon: " + s);
		}

	}

}
