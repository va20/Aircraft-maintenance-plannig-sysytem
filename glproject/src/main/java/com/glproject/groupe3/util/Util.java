package com.glproject.groupe3.util;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.codec.digest.DigestUtils;

public class Util {

	public static String hashPass(String pass) {
		String passTmp = DigestUtils.sha512Hex(pass);
		for (int i = 0; i < 10000; i++) {
			passTmp = DigestUtils.sha512Hex(pass);
		}

		return passTmp;
	}

	public static String generateSalt() throws NoSuchAlgorithmException {
		SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
		byte[] saltByte = new byte[32];
		String salt = null;
		sr.nextBytes(saltByte);
		StringBuilder saltStringB = new StringBuilder();

		for (int i = 0; i < saltByte.length; i++)
			saltStringB.append(Integer.toString((saltByte[i] & 0xff) + 0x100, 16).substring(1));

		salt = saltStringB.toString();

		return salt;
	}

	public static void mail(String message, String subject) {
		Properties props = new Properties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		InternetAddress from = null;

		try {
			from = new InternetAddress("genielogiciel2017@gmail.com", "MCC");

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		try {

			Session sess = Session.getInstance(props, new javax.mail.Authenticator() {
				public PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("genielogiciel2017@gmail.com", "mccmrogenie");
				}
			});

			Message m = new MimeMessage(sess);
			m.setFrom(from);
			m.setRecipients(Message.RecipientType.TO, InternetAddress.parse("genielogiciel2017@gmail.com"));
			m.setSubject(subject);
			m.setText(message);

			Transport.send(m);

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}