package GUI;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/*Das ist die ausführbare Main-Datei*/
public class testMain {

	public static void main(String[] args) {
		login_View l = new login_View();
		l.setVisible(false);

		Raumplaner_View r = new Raumplaner_View();
		r.setVisible(true);

	}

}
