package gui;
import javax.swing.*;

public class Error_Message_Box {
	

	public static void errorBox (String fehlertyp, String beschreibung, String methode)
	    {
		 JOptionPane.showMessageDialog(null,"Hier ist die Beschreibung: " +beschreibung+ ". Ist aufgetreten in der Methode: " +methode, fehlertyp, JOptionPane.ERROR_MESSAGE);
		 
	    }
}
