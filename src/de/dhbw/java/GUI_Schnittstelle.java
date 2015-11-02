/* Programmiert von: Alexander Finkbeiner
 * Programmiert für: Login-View und Raumplaner-View
 * Beschreibung: Dient zur Verbindung zwischen der Login-View, der Prüfung des Passworts und der Raumplaner-View
 */

package de.dhbw.java;

import java.sql.ResultSet;

import de.dhbw.gui.Error_Message_Box;
import de.dhbw.gui.Login_View;
import de.dhbw.gui.Raumplaner_View;

public abstract class GUI_Schnittstelle {
	private static Raumplaner_View raumplanerView;

	/**
	 * Gibt int für die GUI zurück
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
	public static void einloggen(String email, String _passwort) {
		// sql abfrage ob nutzer in tabelle und typ(besteller/verwalter/admin)
		// herauslesen, sowie ID

		// passwort verschluesseln
		_passwort = SHA512_Encrypt.encrypt(_passwort);
		// abfrageString erstellen
		String abfrageString = "select * from benutzer where email = '" + email
				+ "' and passwort = '" + _passwort + "'";
		ResultSet rs = SQL_Schnittstelle.sqlAbfrage(abfrageString);

		try {
			if (rs.next()) {
				Benutzer.setBenutzerGesamt(rs.getInt("benutzerid"),
						rs.getString("email"), rs.getString("vorname"),
						rs.getString("nachname"),
						rs.getString("rolle").charAt(0),
						rs.getString("bereich"));
			} else {
				Benutzer.setBenutzerID(-1);
			}

		} catch (Exception e) {
			System.out.println("Ausgabe " + e.toString());
		}

	}

	public static void loginCheck(Login_View loginView) {
		try {
			String pw = "";
			char[] charArray = loginView.getPasswordField().getPassword();
			for (int i = 0; i < charArray.length; i++) {
				pw += charArray[i];
			}
			// System.out.println(pw + " " +
			// loginView.getUserIDField().getText());
			einloggen(loginView.getUserIDField().getText(), pw);
			// loginView.getUserIDField().getText(), "maxima.fallstudie@gmx.de"
			// pw "fallstudie2015

			if (Benutzer.getBenutzerID() != -1) {
				loginView.setVisible(false);
				raumplanerView = new Raumplaner_View(
						SQL_Schnittstelle.getRooms(),
						SQL_Schnittstelle.getBuchung());
				/*
				 * 
				 * Hier müssen die Buchungen benutzerspezifisch geladen werden
				 */
				raumplanerView.setVisible(true);

			} else {
				loginView.getLoginWrongLabel().setVisible(true);
			}

		} catch (Exception e) {
			Error_Message_Box.laufzeitfehler(e,
					"de.dhbw.java.GUI_Schnittstelle.check");
		}
	}
	public static String preventSQLInjection(String sqlBefehl){
		
		sqlBefehl= sqlBefehl.replaceAll(">", " ");
		sqlBefehl= sqlBefehl.replaceAll("<", " ");
		sqlBefehl= sqlBefehl.replaceAll("'", " ");
		sqlBefehl.trim();
		return sqlBefehl;
	}

}
