package de.dhbw.java;

public class Raumbuchungssystem {

	
	

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		GUI_Schnittstelle gui = new GUI_Schnittstelle();
		SQL_Schnittstelle sql = new SQL_Schnittstelle();
		int benutzerId = gui.einloggen("fallstudie@gmx.de", "fallstudie2015");
		
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
		gui.initialisiereStartbildschirm(benutzer);
	
	}

}
