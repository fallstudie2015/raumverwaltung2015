package gui;

import de.dhbw.java.SQL_Schnittstelle;

public class Bestaetigungs_View_Test {

	public static void main(String[] args) {

		SQL_Schnittstelle.createConnection();
		Bestaetigungs_View meineBestaetigung = new Bestaetigungs_View(
				SQL_Schnittstelle.getBuchung().get(10));

	}

}
