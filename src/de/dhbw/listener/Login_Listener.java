/* Programmiert von: Kai Kleefisch
 * Programmiert für: Login Ansicht
 * Beschreibung: Wird ausgeführt bei Enter oder Login-Button im Login_View
 */

package de.dhbw.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.dhbw.gui.Error_Message_Box;
import de.dhbw.gui.Login_View;
import de.dhbw.java.GUI_Schnittstelle;

public class Login_Listener implements ActionListener {
	private Login_View loginView;

	public Login_Listener(Login_View lf) {
		loginView = lf;
	}

	/*
	 * ActionListener um das Einloggen auszulösen. Wenn das Einloggen nicht
	 * funktioniert wird dies ausgegeben
	 */
	public void actionPerformed(ActionEvent ae) {
		try {
			GUI_Schnittstelle.loginCheck(loginView);
		} catch (Exception e) {
			Error_Message_Box.laufzeitfehler(e,
					"gui.Error_Message_Box.Login_Listener.actionPerformed");
		}

	}

}
