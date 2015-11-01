/* Programmiert von: Melanie Müller
 * Programmiert für: EncryptPassword
 * Beschreibung: Dient als Steuerprogramm zur Entschlüsselung des Passworts
 */

package de.dhbw.java;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class SHA512_Encrypt {

	public static String encrypt(String _passwort) {
		String antwort = "";
		try {
			/* Passwort wird entschlüsselt */
			antwort = EncryptPassword.SHA512(_passwort);
		} catch (NoSuchAlgorithmException ex) {
			System.out.println(ex.getMessage());
		} catch (UnsupportedEncodingException ex) {
			System.out.println(ex.getMessage());
		}
		return antwort;
	}

}
