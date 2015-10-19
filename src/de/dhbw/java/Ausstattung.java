package de.dhbw.java;

public class Ausstattung {
	private int ausstattungID;
	private String abkuerzung;
	private String bezeichnung;
	private int beweglich;
	private int raumID;

	public Ausstattung(int ausstattungID, String abkuerzung,
			String bezeichnung, int beweglich, int raumID) {
		this.ausstattungID = ausstattungID;
		this.abkuerzung = abkuerzung;
		this.bezeichnung = bezeichnung;
		this.beweglich = beweglich;
		this.raumID = raumID;
	}

	public int getAusstattungID() {
		return ausstattungID;
	}

	public void setAusstattungID(int ausstattungID) {
		this.ausstattungID = ausstattungID;
	}

	public String getAbkuerzung() {
		return abkuerzung;
	}

	public void setAbkuerzung(String abkuerzung) {
		this.abkuerzung = abkuerzung;
	}

	public String getBezeichnung() {
		return bezeichnung;
	}

	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}

	public int getBeweglich() {
		return beweglich;
	}

	public void setBeweglich(int beweglich) {
		this.beweglich = beweglich;
	}

	public int getRaumID() {
		return raumID;
	}

	public void setRaumID(int raumID) {
		this.raumID = raumID;
	}

}
