/* Programmiert von: Kai Kleefisch
 * Programmiert für: Alle Try Catch Blöcke müssen diesen Fehlerdialog verwenden
 * Beschreibung: zeigt eine spezifische Fehlermeldung an ung gibt hinweise wo der Fehler passiert ist
 */
package de.dhbw.gui;

import java.sql.Timestamp;

import javax.swing.JOptionPane;

import de.dhbw.java.Benutzer;
import de.dhbw.java.SQL_Schnittstelle;

public class Error_Message_Box {

	/* Ausgabe für die Fehleroberfläche erstellen */
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

	public static void dbVerbindungsfehler(Exception e, String methode) {
		JOptionPane
				.showMessageDialog(
						null,
						"Das Programm konnte nicht gestartet werden, da ein Verbindungsproblem aufgetreten ist."
								+ "\nBitte überprüfen Sie Ihre Netzwerkverbindung.\n"
								+ "\nMethode: "
								+ methode
								+ "\nMessage: "
								+ e.getMessage(), "Datenbankverbindungsfehler",
						JOptionPane.ERROR_MESSAGE);
	}

	public static void programmstart(String klasse, String methode) {
		Timestamp now = new Timestamp(System.currentTimeMillis());
		System.out.println(now.toString());
		SQL_Schnittstelle.insertLogging(klasse, methode,
				"Programm wurde gestartet", now.toString(), "Programmstart",
				Benutzer.getBenutzerID());
	}
}
