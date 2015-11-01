/* Programmiert von: Kai Kleefisch
 * Programmiert für: SQL_Schnittstelle
 * Beschreibung: Wird verwendet um Datensätze in Objekte einzulesen
 */
package de.dhbw.java;

public class Ausstattung {
	private int ausstattungID;
	private String bezeichnung;

	/*
	 * Anlegen des Objektes Ausstattung mit den Attributen ausstattungID und
	 * bezeichnung
	 */
	public Ausstattung(int ausstattungID, String bezeichnung) {
		this.ausstattungID = ausstattungID;
		this.bezeichnung = bezeichnung;
	}

	public int getAusstattungID() {
		return ausstattungID;
	}

	public void setAusstattungID(int ausstattungID) {
		this.ausstattungID = ausstattungID;
	}

	public String getBezeichnung() {
		return bezeichnung;
	}

	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}

}
