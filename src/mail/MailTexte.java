package mail;

import java.sql.Date;

import de.dhbw.java.Buchung;
import de.dhbw.java.SQL_Schnittstelle;

public abstract class MailTexte {

	public static String verwalterPostfach = new String(
			"fallstudie2015@gmx.de");

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

	public static String getBetreffNeueReservierung() {

		String text = new String("Neue Raumreservierung eingetroffen");

		return text;
	}

	public static String getTextNeueReservierung(Date datum) {

		String text = new String("Eine Neue Reservierung für den " + datum
				+ " ist eingetroffen");
		return text;
	}

}
