package de.dhbw.java;

import java.util.ArrayList;

public class Benutzer {

	
	public static ArrayList benutzerListe = new ArrayList<Benutzer>();
	private String email;
	private int benutzerID;
	private String Vorname;
	private String Nachname;
	private char benutzertyp;
	
	
	/**
	 * @param email
	 * @param benutzerID
	 * @param vorname
	 * @param nachname
	 * @param benutzertyp
	 */
	public Benutzer(String email, int benutzerID, String vorname,
			String nachname, char benutzertyp) {
		super();
		this.email = email;
		this.benutzerID = benutzerID;
		Vorname = vorname;
		Nachname = nachname;
		this.benutzertyp = benutzertyp;
	}

	public String getEmail() {
		return email;
	}

	public int getBenutzerID() {
		return benutzerID;
	}

	public String getVorname() {
		return Vorname;
	}

	public String getNachname() {
		return Nachname;
	}

	public char getBenutzertyp() {
		return benutzertyp;
	}

	public int getPersnr() {
		return benutzerID;
	}


	

	public boolean ausloggen(){
		
		return true;
	}
}
