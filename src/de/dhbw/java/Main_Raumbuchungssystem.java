package de.dhbw.java;

import gui.Login_View;
import gui.Raum_View;
import gui.Raumplaner_View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
		Login_View loginView = new Login_View();
		Raumplaner_View raumplanerView = new Raumplaner_View();
		loginView.setVisible(true);
		loginView.setLoginButtonListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String pw ="";
				char[] charArray = loginView.getPasswordField().getPassword();
				for(int i=0; i<charArray.length; i++)
				{
					pw += charArray[i];
				}
				benutzerId = gui.einloggen(
						loginView.getUserIDField().getText() /*"maxima.fallstudie@gmx.de"*/,
						pw) /*"fallstudie2015"*/;
				if(benutzerId != -1)
				{
					loginView.setVisible(false);
					raumplanerView.setVisible(true);
				} else
				{
					loginView.getLoginWrongLabel().setVisible(true);
					// sag dem sackgesicht, dass er was flasch eingegeben hat und zähle wie oft er was falsch macht...
					// code
				}
			}
		});
		
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
