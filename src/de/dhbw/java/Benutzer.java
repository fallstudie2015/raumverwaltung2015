package de.dhbw.java;


public abstract class Benutzer {

	
	
	private static String email;
	private static int benutzerID;
	private static String Vorname;
	private static String Nachname;
	private static char benutzertyp;
	
	/**
	 * @param email
	 * @param benutzerID
	 * @param vorname
	 * @param nachname
	 * @param benutzertyp
	 */

	public static void setEmail(String email) {
		Benutzer.email = email;
	}

	public static void setVorname(String vorname) {
		Vorname = vorname;
	}

	public static void setNachname(String nachname) {
		Nachname = nachname;
	}

	public static void setBenutzertyp(char benutzertyp) {
		Benutzer.benutzertyp = benutzertyp;
	}

	public static String getEmail() {
		return email;
	}

	public static int getBenutzerID() {
		return benutzerID;
	}

	public static void setBenutzerID(int id) {
		benutzerID = id;
	}


	public static String getVorname() {
		return Vorname;
	}

	public static String getNachname() {
		return Nachname;
	}

	public static char getBenutzertyp() {
		return benutzertyp;
	}

	public static int getPersnr() {
		return benutzerID;
	}

	public static void setBenutzerGesamt(int benutzerId, String email,
		String vorname, String nachname, char benutzertyp) {
		Benutzer.benutzerID = benutzerId;
		Benutzer.email = email;
		Benutzer.Vorname = vorname;
		Benutzer.Nachname = nachname;
		Benutzer.benutzertyp = benutzertyp;
	}
	

	public static boolean ausloggen() {
		
		return true;
	}
}
