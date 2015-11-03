/* Programmiert von: Kai Kleefisch
 * Programmiert für: Einstiegspunkt in die Anwendung
 * Beschreibung: Dient als Startpunkt vom Programm, hier kann auch der UIManager mitgegeben werden um das LookAndFeel anzupassen
 */

package de.dhbw.startpunkt;

import javax.swing.UIManager;

import de.dhbw.gui.Error_Message_Box;
import de.dhbw.gui.Login_View;
import de.dhbw.java.SQL_Schnittstelle;

public class Start {

	public static void main(String[] args) {
		try {
			// Die Oberfläche wird auf ein Crossplattform UI Manager geändert,
			// um auf allen Systemen die gleiche Oberfläche zu gewährleisten
			try {
				UIManager.setLookAndFeel(UIManager
						.getCrossPlatformLookAndFeelClassName());
			} catch (Exception e) {
				e.printStackTrace();
			}
			// SQL-Connection zum Server wird aufgebaut
			boolean erfolgreich = SQL_Schnittstelle.createConnection();
			// Login Fenster wird erstellt und angezeigt
			if (erfolgreich) {
				Login_View lf = new Login_View();
				lf.setVisible(true);
			}
		} catch (Exception e) {
			Error_Message_Box.laufzeitfehler(e, "startpunkt.start.main");
		}
	}
}
