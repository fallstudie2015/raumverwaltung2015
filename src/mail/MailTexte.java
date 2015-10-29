package mail;

import java.sql.Date;

import de.dhbw.java.Buchung;
import de.dhbw.java.SQL_Schnittstelle;

public abstract class MailTexte {

	/*
	 * Zentrale Variable zur Verwaltung des Verwalterpostfaches. Bei Änderungen
	 * am Verwalterpostfach kann dieses hier geändert werden
	 */
	public static String verwalterPostfach = new String(
			"fallstudie2015@gmx.de");

	/* Betreff für die E-Mail bei Reservierungsstornierung */
	public static String getBetreffStornierung(Buchung buchung) {

		String betreff = new String(
				"Stornierung für den " + buchung.getDatum());
		return betreff;
	}

	/* Betreff für die E-Mail bei Reservierungsbestätigung */
	public static String getBetreffBestaetigen(Buchung buchung) {

		String betreff = new String(
				"Buchung vom " + buchung.getDatum() + " wurde bestätigt");
		return betreff;
	}

	/* Betreff für die E-Mail bei Reservierungsablehnung */
	public static String getBetreffAbgelehnt(Buchung buchung) {

		String betreff = new String(
				"Buchung vom " + buchung.getDatum() + " wurde abgelehnt");
		return betreff;
	}

	/* Text für die E-Mail bei Reservierungsbestätigung */
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

	/* Text für die E-Mail bei Reservierungsstornierung */
	public static String getTextStornierung(Buchung buchung) {

		String antwort = new String(
				"Sehr geehrte Verwalter, \n\n " + "eine Buchung von "
						+ SQL_Schnittstelle.getBenutzerName(
								buchung.getBenutzerID())
				+ " wurde storniert. \n\n Datum:  \t " + buchung.getDatum()
				+ " \n Uhrzeit:\t " + buchung.getZeitVon() + " bis "
				+ buchung.getZeitBis() + " \n Raum:   \t"
				+ SQL_Schnittstelle.getRaumName(buchung.getRaumID()));

		return antwort;
	}

	/* Text für die E-Mail bei Reservierungsablehnung */
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

	/*
	 * Betreff für die E-Mail für Verwalter bei Eintreffen einer neuen
	 * Reservierung
	 */
	public static String getBetreffNeueReservierung() {

		String text = new String("Neue Raumreservierung eingetroffen");

		return text;
	}

	/*
	 * Text für die E-Mail für Verwalter bei Eintreffen einer neuen Reservierung
	 */
	public static String getTextNeueReservierung(Date datum) {

		String text = new String("Eine Neue Reservierung für den " + datum
				+ " ist eingetroffen");
		return text;
	}

}
