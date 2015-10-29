package startpunkt;

import gui.Error_Message_Box;
import gui.Login_View;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.alee.laf.WebLookAndFeel;

import de.dhbw.java.SQL_Schnittstelle;

public class Start {

	public static void main(String[] args) {
		try {
			//////////////////////////
			SwingUtilities.invokeLater ( new Runnable ()
	        {
	            public void run ()
	            {
	                // Install WebLaF as application L&F
	                WebLookAndFeel.install ();
	            }
	        } );
			///////////////////////
//			try {
//				UIManager.setLookAndFeel(UIManager
//						.getCrossPlatformLookAndFeelClassName());
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
			SQL_Schnittstelle.createConnection();
			Login_View lf = new Login_View();
			lf.setVisible(true);
		} catch (Exception e) {
			Error_Message_Box.laufzeitfehler(e, "startpunkt.start.main");
		}
	}
}
