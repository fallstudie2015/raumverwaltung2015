package de.dhbw.java;

import java.sql.ResultSet;

public class GUI_Schnittstelle {

	/**
	 * Gibt intfür die GUI zurück
	 * @param benutzername: name des benutzers, egal ob Verwalter oder Besteller.
	 * @param _passwort: passwort des Benutzers
	 * @return: -1 oder persnur , je nach ergebnis der abfrage 	  * -1: alles scheiße      , * persnr: alles gut gelaufen - persnr wird zurückggeben 
	 */
	public int einloggen(String email, String _passwort){		
		//sql abfrage ob nutzer in tabelle und typ(besteller/verwalter/admin) herauslesen, sowie ID
		SQL_Schnittstelle sqlKlasse = new SQL_Schnittstelle();
		
		//passwort verschlüsseln
		_passwort = SHA512_Encrypt.encrypt(_passwort);
		// abfrageString erstellen
		String abfrageString = "select * from benutzer where email = '"+ email +"' and passwort = '"+ _passwort +"'" ;
		ResultSet rs = sqlKlasse.sqlAbfrage(abfrageString);
		
		// WIR müsüsen von de rDatenbankgruppe wissern, wie die einloggdaten zurückgegeben wrerden. also die infos.
		// dementsprechend machen wir dann hier den spezifischen benutzer als ID oder als namen-string und der typ als int oder sonstiges erfahren. 
		// also arbeiten wir hier wahrscheinlich mit rs.getString   <--- darum gehts nämlich
		// wir benutzen danach nurnoch den typ des benutzers, damit wir ein entsprechendes BEnutzer-Objekt erstellen können.
		// Dieses trägt dann als namen "benutzername"
		String Rückgabe ="";
		String RSvorname = "";
		String RSnachname = "";
		String RSbenutzertyp = "";
		String RSemail = "";
		int RSbenutzerID = 0;
		char switchChar = ' ';
		int antwort = -1;
		try
		{
			while(rs.next())
			{

				RSvorname = rs.getString("vorname");
				RSnachname = rs.getString("nachname");
				RSemail = rs.getString("email");
				RSbenutzertyp = rs.getString("rolle");
				RSbenutzerID = rs.getInt("benutzerid");
				switchChar = RSbenutzertyp.charAt(0);
				
				
				
				switch (switchChar) {
				case 'v':
					Benutzer.benutzerListe.add(new Verwalter(RSemail, RSbenutzerID, RSvorname, RSnachname, switchChar));
					break;
				case 'b':
					Benutzer.benutzerListe.add(new Besteller(RSemail, RSbenutzerID, RSvorname, RSnachname, switchChar));
					break;
				case 'a': 
					// hier müsste unter umständen noch dein objekt der klasse "admin" erstellt werden können
					Benutzer.benutzerListe.add(new Admin(RSemail, RSbenutzerID, RSvorname, RSnachname, switchChar));
					break;

				default: 
					break;
				}
				antwort = RSbenutzerID;
			}
				
		}
		catch(Exception e)
		{
			System.out.println("Ausgabe "+e.toString());
			antwort = 1;
		}
		return antwort;
	}
	
	
	
	/**
	 * nächste methode
	 */
	public void initialisiereStartbildschirm(Benutzer benutzer)
	{
		char benutzertyp = benutzer.getBenutzertyp();
		switch (benutzertyp) {
			case'v':
				System.out.println("läauft V");
			
					break;
			case 'b':
				System.out.println("läauft B");
		
				break;
			case 'a':
				System.out.println("läauft A");
				break;
			default:
				break;		
		}
		
	}
	
}
