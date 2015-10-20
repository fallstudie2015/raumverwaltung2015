package listener;

import gui.Error_Message_Box;
import gui.Login_View;
import gui.Raumplaner_View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.dhbw.java.Benutzer;
import de.dhbw.java.GUI_Schnittstelle;
import de.dhbw.java.SQL_Schnittstelle;

public class Login_Listener implements ActionListener {
	private Login_View loginView;
	private Raumplaner_View raumplanerView;
	private int benutzerId;
	private GUI_Schnittstelle gui = new GUI_Schnittstelle();


	public Login_Listener(Login_View lf) {
		loginView = lf;
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		try {
			String pw = "";
			char[] charArray = loginView.getPasswordField().getPassword();
			for (int i = 0; i < charArray.length; i++) {
				pw += charArray[i];
			}
			System.out.println(pw + " " + loginView.getUserIDField().getText());
			gui.einloggen(loginView.getUserIDField().getText(), pw);
			// loginView.getUserIDField().getText(), "maxima.fallstudie@gmx.de"
			// pw "fallstudie2015

			if (Benutzer.getBenutzerID() != -1) {
				loginView.setVisible(false);
				raumplanerView = new Raumplaner_View(
						SQL_Schnittstelle.getRooms());
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
			Error_Message_Box.errorBox("Laufzeitfehler", e.getMessage(), this
					.getClass().getName());
		}
		// TODO Auto-generated method stub

	}

}
