package gui;

import javax.swing.JOptionPane;

import de.dhbw.java.Benutzer;
import de.dhbw.java.SQL_Schnittstelle;

public class Error_Message_Box {

	public static void laufzeitfehler(Exception e, String methode) {
		JOptionPane
				.showMessageDialog(
						null,
						"Ein Laufzeitfehler ist aufgetreten. \nSollte der Fehler dauerhaft auftreten, bitte dem Admin melden:\nKlasse: "
								+ e.getClass()
								+ "\nMethode: "
								+ methode
								+ "\nLocalizedMessage: "
								+ e.getLocalizedMessage()
								+ "\nMessage: "
								+ e.getMessage(), "Laufzeitfehler",
						JOptionPane.ERROR_MESSAGE);
		SQL_Schnittstelle.insertLogging(e.getClass().toString(), methode,
				e.getLocalizedMessage(), e.getMessage(), "Laufzeitfehler",
				Benutzer.getBenutzerID());

	}
}
