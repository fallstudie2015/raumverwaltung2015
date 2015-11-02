/* Programmiert von: Kai Kleefisch
 * Programmiert für: SQL_Schnittstelle
 * Beschreibung: Wird verwendet um Datensätze in Objekte einzulesen
 */

package de.dhbw.java;

public class Raum {
	private int raumID;
	private String name;
	private String strasse;
	private String stock;
	private int anzPersonen;

	/* Anlegen des Objekts Raum mit allen nötigen Attributen */
	public Raum(int raumID, String name, String strasse, String stock,
			int anzPersonen) {
		this.raumID = raumID;
		this.name = name;
		this.strasse = strasse;
		this.stock = stock;
		this.anzPersonen = anzPersonen;
	}

	public int getRaumID() {
		return raumID;
	}

	public void setRaumID(int raumID) {
		this.raumID = raumID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStrasse() {
		return strasse;
	}

	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}

	public String getStock() {
		return stock;
	}

	public void setStock(String stock) {
		this.stock = stock;
	}

	public int getAnzPersonen() {
		return anzPersonen;
	}

	public void setAnzPersonen(int anzPersonen) {
		this.anzPersonen = anzPersonen;
	}

}
