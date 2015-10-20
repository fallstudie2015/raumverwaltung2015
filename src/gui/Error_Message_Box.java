package gui;

import javax.swing.JOptionPane;

public class Error_Message_Box {

	public static void errorBox(String fehlertyp, String beschreibung,
			String methode) {
		JOptionPane.showMessageDialog(null,
				"Hier ist die Beschreibung: " + beschreibung
						+ ". Ist aufgetreten in der Methode: " + methode,
				fehlertyp, JOptionPane.ERROR_MESSAGE);

	}

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

	}
}
