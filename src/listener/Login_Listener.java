package listener;

import gui.Error_Message_Box;
import gui.Login_View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.dhbw.java.GUI_Schnittstelle;

public class Login_Listener implements ActionListener {
	private Login_View loginView;

	public Login_Listener(Login_View lf) {
		loginView = lf;
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		try {
			GUI_Schnittstelle.check(loginView);

		} catch (Exception e) {
			Error_Message_Box.errorBox("Laufzeitfehler", e.getMessage(), this
					.getClass().getName());
		}
		// TODO Auto-generated method stub

	}

}
