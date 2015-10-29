package de.dhbw.java;

import java.sql.Date;
import java.sql.Time;

public class Buchung {
	private int buchungsID;
	private String telefon;
	private Date datum;
	private Time zeitVon;
	private Time zeitBis;
	private String kommentar;
	private String bestuhlung;
	private int benutzerID;
	private int raumID;
	private String status;

	/*
	 * Anlegen des Objekts Buchung mit alle für die Buchung relevanten
	 * Attributen
	 */
	public Buchung(int buchungsID, String telefon, Date datum, Time zeitVon,
			Time zeitBis, String kommentar, String bestuhlung, int benutzerID,
			int raumID, String status) {

		this.buchungsID = buchungsID;
		this.telefon = telefon;
		this.datum = datum;
		this.zeitVon = zeitVon;
		this.zeitBis = zeitBis;
		this.kommentar = kommentar;
		this.bestuhlung = bestuhlung;
		this.benutzerID = benutzerID;
		this.raumID = raumID;
		this.status = status;
	}

	public int getBuchungsID() {
		return buchungsID;
	}

	public void setBuchungsID(int buchungsID) {
		this.buchungsID = buchungsID;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public Time getZeitVon() {
		return zeitVon;
	}

	public void setZeitVon(Time zeitVon) {
		this.zeitVon = zeitVon;
	}

	public Time getZeitBis() {
		return zeitBis;
	}

	public void setZeitBis(Time zeitBis) {
		this.zeitBis = zeitBis;
	}

	public String getKommentar() {
		return kommentar;
	}

	public void setKommentar(String kommentar) {
		this.kommentar = kommentar;
	}

	public String getBestuhlung() {
		return bestuhlung;
	}

	public void setBestuhlung(String bestuhlung) {
		this.bestuhlung = bestuhlung;
	}

	public int getBenutzerID() {
		return benutzerID;
	}

	public void setBenutzerID(int benutzerID) {
		this.benutzerID = benutzerID;
	}

	public int getRaumID() {
		return raumID;
	}

	public void setRaumID(int raumID) {
		this.raumID = raumID;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
