package de.dhbw.java;

import java.util.ArrayList;

public class Verwalter extends Benutzer {



	/**
	 * @param email
	 * @param benutzerID
	 * @param vorname
	 * @param nachname
	 * @param benutzertyp
	 */
	public Verwalter(String email, int benutzerID, String vorname,
			String nachname, char benutzertyp) {
		super(email, benutzerID, vorname, nachname, benutzertyp);
		// TODO Auto-generated constructor stub
	}

	public boolean stornieren(Veranstaltung v1){
		
		return true;
	}
	
	
}
