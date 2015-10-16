package de.dhbw.java;

import java.sql.SQLException;
import java.util.ArrayList;

import standardklassen.Buchung;
import standardklassen.Raum;

public class Main_Raumbuchungssystem {

	// Global Variablen
	public static char benutzertyp = ' ';
	public static ArrayList meineBuchungen = new ArrayList<Buchung>();
	public static ArrayList raumListe = new ArrayList<Raum>();
	public static int benutzerId;

	
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		


		
		GUI_Schnittstelle gui = new GUI_Schnittstelle();
		SQL_Schnittstelle sql = new SQL_Schnittstelle();
		benutzerId = gui.einloggen("maxima.fallstudie@gmx.de", "fallstudie2015");
		gui.ladeStartbildschirm();
		Benutzer benutzer = null;
		Benutzer pruefBenutzer;
		
		// suche das benutzer-objekt in der liste
		for(int i=0; i<Benutzer.benutzerListe.size(); i++)
		{
			pruefBenutzer = (Benutzer) Benutzer.benutzerListe.get(i);
			if(pruefBenutzer.getPersnr() == benutzerId){
				benutzer = pruefBenutzer;
			}
		}
		
		// GUI wird Benutzerspezifisch aufgebaut
//		gui.checkBenutzertyp(benutzer);
	
	}

}
