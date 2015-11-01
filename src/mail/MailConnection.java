/* Programmiert von: Florian Fay
 * Programmiert für: E-Mail-Versand
 * Beschreibung: Dient zum Versenden von E-Mails über einen gmx-Account
 */

package mail;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailConnection {

	public void sendMail(String empfaengerAdresse, String betreff,
			String text) {

		/*
		 * Anlegen der Daten für den Mail-Account über den die E-Mails gesendet
		 * werden
		 */

		String username = new String("fallstudie2015@gmx.de");
		String password = new String("fallstudie2015");
		String senderAddress = new String("fallstudie2015@gmx.de");

		MailAuthenticator auth = new MailAuthenticator(username, password);

		Properties properties = new Properties();

		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmx.de");
		properties.put("mail.smtp.user", username);
		properties.put("mail.smtp.password", password);
		// password, wird bereits in MailAuthenticator gespeichert
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.auth", "true");

		// !!Wichtig!! Falls der SMTP-Server eine Authentifizierung
		// verlangt muss an dieser Stelle die Property auf "true" gesetzt werden

		// Hier wird mit den Properties und dem im Constructor
		// erzeugten MailAuthenticator eine Session erzeugt
		Session session = Session.getDefaultInstance(properties, auth);

		try {
			// Eine neue Message erzeugen
			Message msg = new MimeMessage(session);

			// Hier werden die Absender- und Empfängeradressen gesetzt
			msg.setFrom(new InternetAddress(senderAddress));
			msg.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(empfaengerAdresse, false));

			// Der Betreff und Body der Message werden gesetzt
			msg.setSubject(betreff);
			msg.setText(text);

			// Hier lassen sich HEADER-Informationen hinzufügen
			msg.setHeader("Test", "Test");
			msg.setSentDate(new Date());

			// Zum Schluss wird die Mail natürlich noch verschickt
			Transport.send(msg);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static class MailAuthenticator extends Authenticator {

		/*
		 * Ein String, der den Usernamen nach der Erzeugung eines Objektes
		 * dieser Klasse enthalten wird.
		 */
		private final String user;

		/*
		 * Ein String, der das Passwort nach der Erzeugung eines Objektes dieser
		 * Klasse enthalten wird.
		 */
		private final String password;

		/*
		 * Der Konstruktor erzeugt ein MailAuthenticator Objekt aus den beiden
		 * Parametern user und passwort.
		 */
		public MailAuthenticator(String user, String password) {
			this.user = user;
			this.password = password;
		}

		/*
		 * Diese Methode gibt ein neues PasswortAuthentication Objekt zurück.
		 *
		 * 
		 */
		protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(this.user, this.password);
		}
	}
}
