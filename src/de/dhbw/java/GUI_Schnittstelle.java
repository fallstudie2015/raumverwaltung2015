package de.dhbw.java;

import gui.Error_Message_Box;
import gui.Login_View;
import gui.Raumplaner_View;

import java.sql.ResultSet;

public abstract class GUI_Schnittstelle {
	private static Raumplaner_View raumplanerView;

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
						rs.getString("rolle").charAt(0));
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
			System.out.println(pw + " " + loginView.getUserIDField().getText());
			einloggen(loginView.getUserIDField().getText(), pw);
			// loginView.getUserIDField().getText(), "maxima.fallstudie@gmx.de"
			// pw "fallstudie2015

			if (Benutzer.getBenutzerID() != -1) {
				loginView.setVisible(false);
				raumplanerView = new Raumplaner_View(
						SQL_Schnittstelle.getRooms(),
						SQL_Schnittstelle.getVerwaltungBuchung());
				/*
				 * 
				 * Hier müssen die Buchungen benutzerspezifisch geladen werden
				 */
				raumplanerView.setVisible(true);

			} else {
				loginView.getLoginWrongLabel().setVisible(true);
				// sag dem sackgesicht, dass er was flasch eingegeben hat und
				// zähle wie oft er was falsch macht...
				// code
			}

		} catch (Exception e) {
			Error_Message_Box.laufzeitfehler(e,
					"de.dhbw.java.GUI_Schnittstelle.check");
		}
	}
	

}
