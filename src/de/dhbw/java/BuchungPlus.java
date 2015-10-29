package de.dhbw.java;

import java.sql.Date;
import java.sql.Time;

public class BuchungPlus extends Buchung {
	String Name;

	public BuchungPlus(int buchungsID, String telefon, Date datum,
			Time zeitVon, Time zeitBis, String kommentar, String bestuhlung,
			int benutzerID, int raumID, String status, String Name) {
		super(buchungsID, telefon, datum, zeitVon, zeitBis, kommentar,
				bestuhlung, benutzerID, raumID, status);
		this.Name = Name;
		// TODO Auto-generated constructor stub
	}

	public String getBenutzerName() {
		return Name;
	}

	public void setBenutzerName(String benutzerName) {
		this.Name = benutzerName;
	}
}
