package mail;

public class MailConnectionTest {

	public static void main(String[] args) {

		MailConnection mail = new MailConnection();

		mail.sendMail("fallstudie2015@gmx.de", "fallstudie2015",
				"fallstudie2015@gmx.de", "fallstudie2015@gmx.de",
				"Erste Nachricht", "Das ist der Text");
	}

}
