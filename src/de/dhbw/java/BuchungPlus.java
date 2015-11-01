/* Programmiert von: Kai Kleefisch
 * Programmiert für: Raumplaner_View
 * Beschreibung: Um Datenbankzugriffe zu minimieren wurde diese KLasse ins Leben gerufen. Die Klasse beinhaltet alle Infos die zur anzeige auf Raumplaner_View benötigt werden
 * 
 */

package de.dhbw.java;

import java.sql.Date;
import java.sql.Time;

//Erbt von der Buchungsklasse, enthält weitere Informationen (siehe Attribute)
public class BuchungPlus extends Buchung {
	private String Name;
	private String ausstattung;
	private String bereich;
	private String veranstaltungsBezeichnung;

	public BuchungPlus(int buchungsID, String telefon, Date datum, Time zeitVon,
			Time zeitBis, String kommentar, String bestuhlung, int benutzerID,
			int raumID, String status, String Name, String ausstattung,
			String bereich, String veranstaltungsBezeichnung) {
		super(buchungsID, telefon, datum, zeitVon, zeitBis, kommentar,
				bestuhlung, benutzerID, raumID, status);
		this.Name = Name;
		this.ausstattung = ausstattung;
		this.bereich = bereich;
		this.veranstaltungsBezeichnung = veranstaltungsBezeichnung;
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

	public String getVeranstaltungsBezeichnung() {
		return veranstaltungsBezeichnung;
	}
}
