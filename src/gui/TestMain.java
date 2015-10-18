package gui;

/*Das ist die ausführbare Main-Datei*/
public class TestMain {

	public static void main(String[] args) {
		Login_View l = new Login_View();
		l.setVisible(false);

		Raumplaner_View r = new Raumplaner_View();
		r.setVisible(true);

	}

}
