package de.dhbw.java;

import java.sql.Date;
import java.sql.Time;

public class BuchungPlus extends Buchung {
	private String Name;
	private String ausstattung;
	private String bereich;

	public BuchungPlus(int buchungsID, String telefon, Date datum,
			Time zeitVon, Time zeitBis, String kommentar, String bestuhlung,
			int benutzerID, int raumID, String status, String Name,
			String ausstattung, String bereich) {
		super(buchungsID, telefon, datum, zeitVon, zeitBis, kommentar,
				bestuhlung, benutzerID, raumID, status);
		this.Name = Name;
		this.ausstattung = ausstattung;
		this.bereich = bereich;
		// TODO Auto-generated constructor stub
	}

	public String getBenutzerName() {
		return Name;
	}

	public void setBenutzerName(String benutzerName) {
		this.Name = benutzerName;
	}

	public String getAusstattung() {
		return ausstattung;
	}

	public String getBereich() {
		return bereich;
	}
}
