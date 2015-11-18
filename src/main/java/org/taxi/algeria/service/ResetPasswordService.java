package org.taxi.algeria.service;

import java.util.*;

import javax.mail.*;
import javax.mail.internet.*;

import org.taxi.algeria.database.FetchDatabase;


public class ResetPasswordService {

	private final static String FROM = "me@me.com";
	private final static String SUBJECT = "Reset PassWord Code !";
	private FetchDatabase fetchDatabase;
	public ResetPasswordService(){
		fetchDatabase= new FetchDatabase();
	}
	
	public void checkEmail(String email) throws Exception{
		if(fetchDatabase.getEmail())
			sendToken(email);
		
	}

	public void sendToken(String email) {
		java.util.Properties props = new java.util.Properties();
		props.put("mail.smtp.host", "smtp.myisp.com");
		Session session = Session.getDefaultInstance(props, null);

		Message msg = new MimeMessage(session);
		try {
			msg.setFrom(new InternetAddress(FROM));
			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(
					email));
			msg.setSubject(SUBJECT);
			msg.setText("Hi,\n\n Use this code to reset your password? "
					+ " : " + generateToken());
			Transport.send(msg);
		} catch (MessagingException e) {

		}
	}

	public int generateToken() {
		Random r = new Random(System.currentTimeMillis());
		return 10000 + r.nextInt(20000);
	}
}