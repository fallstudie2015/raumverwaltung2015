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

		String antwort = new String("Hallo "
				+ SQL_Schnittstelle.getBenutzerName(buchung.getBenutzerID())
				+ ",\n\n" + "Ihre Buchung wurde bestätigt. \n\n Datum:  \t "
				+ buchung.getDatum() + " \n Uhrzeit:\t " + buchung.getZeitVon()
				+ " bis " + buchung.getZeitBis() + " \n Raum:   \t"
				+ SQL_Schnittstelle.getRaumName(buchung.getRaumID())
				+ "\n\n Mit freundlichen Grüßen\n\n Ihre Raumverwaltung");

		return antwort;
	}

	public static String getTextAbgelehnt(Buchung buchung) {

		String antwort = new String("Hallo "
				+ SQL_Schnittstelle.getBenutzerName(buchung.getBenutzerID())
				+ ",\n\n" + "Ihre Buchung wurde abgelehnt. \n\n Datum:  \t "
				+ buchung.getDatum() + " \n Uhrzeit:\t " + buchung.getZeitVon()
				+ " bis " + buchung.getZeitBis() + " \n Raum:   \t"
				+ SQL_Schnittstelle.getRaumName(buchung.getRaumID())
				+ "\n\n Mit freundlichen Grüßen\n\n Ihre Raumverwaltung");

		return antwort;

	}
}
