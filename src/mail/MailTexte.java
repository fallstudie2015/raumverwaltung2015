package mail;

import de.dhbw.java.Buchung;
import de.dhbw.java.SQL_Schnittstelle;

public abstract class MailTexte {

	public static String getBetreffBestaetigen(Buchung buchung) {

		String betreff = new String(
				"Buchung vom " + buchung.getDatum() + " wurde bestätigt");
		return betreff;
	}

	public static String getBetreffAbgelehnt(Buchung buchung) {

		String betreff = new String(
				"Buchung vom " + buchung.getDatum() + " wurde abgelehnt");
		return betreff;
	}

	public static String getTextBestaetigen(Buchung buchung) {

		String antwort = new String("Ihre Buchung vom " + buchung.getDatum()
				+ " von " + buchung.getZeitVon() + " bis "
				+ buchung.getZeitBis() + " für den Raum "
				+ SQL_Schnittstelle.getRaumName(buchung.getRaumID())
				+ " wurde bestätigt.\n Mit freundlichen Grüßen\n Ihre Raumverwaltung");

		return antwort;
	}

	public static String getTextAbgelehnt(Buchung buchung) {

		String antwort = new String("Ihre Buchung vom " + buchung.getDatum()
				+ " von " + buchung.getZeitVon() + " bis "
				+ buchung.getZeitBis() + " für den Raum "
				+ SQL_Schnittstelle.getRaumName(buchung.getRaumID())
				+ " wurde abgelehnt.\n Mit freundlichen Grüßen\n Ihre Raumverwaltung");

		return antwort;

	}
}
