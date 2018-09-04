package br.com.fiap.geradorCupons.utils;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GeraHash 
{
	public static String gera(String s) 
	{
		try {
		    MessageDigest m = MessageDigest.getInstance("MD5");
		 
		    m.update( s.getBytes(), 0 , s.length() );
		             
		    byte[] digest = m.digest();
		         
		    String hexa = new BigInteger(1,digest).toString(16);
		             
		    return formatHash(hexa);
		} 
		catch ( NoSuchAlgorithmException e ) 
		{
		    return e.getMessage();
		}
	}

	private static String formatHash(String h) {
		Pattern pattern = Pattern.compile("(.{5})(.{5})(.{5})(.{5})(.{5})(.{5})(.{2})");
		Matcher matcher = pattern.matcher(h);
		if (matcher.matches()){
			h = matcher.replaceAll("$1		$2		$3		$4		$5		$6		$7");
		}
		return h;
	}

}
