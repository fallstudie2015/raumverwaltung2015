package startpunkt;

import gui.Error_Message_Box;
import gui.Login_View;

public class Start {
	public static void main(String[] args) {
		try {
			Login_View lf = new Login_View();
		} catch (Exception e) {
			Error_Message_Box.errorBox("Laufzeitfehler", e.getMessage(),
					"Start.Main");
		}

	}
}
