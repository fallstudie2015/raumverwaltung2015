package de.dhbw.java;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class SHA512_Encrypt {

	
	public static String encrypt(String _passwort)
	{String antwort ="";
		try
	    {
	        antwort = EncryptPassword.SHA512(_passwort);
	    }
	    catch (NoSuchAlgorithmException ex)
	    {
	        System.out.println(ex.getMessage());
	    }
	    catch (UnsupportedEncodingException ex)
	    {
	        System.out.println(ex.getMessage());
	    }
		return antwort;
	}
	
}
