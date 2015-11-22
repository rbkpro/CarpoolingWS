package org.taxi.algeria.service;

import java.security.MessageDigest;
import java.util.*;

import javax.mail.*;
import javax.mail.internet.*;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

import org.taxi.algeria.database.FetchDatabase;


public class ResetPasswordService {

	static Properties mailServerProperties;
	static Session getMailSession;
	static MimeMessage generateMailMessage;
	
	private final static String FROM = "azize.real@gmail.com";
	private final static String PASSWORD = "azizereal";
	private final static String SUBJECT = "Reset PassWord Code !";
	private int token ;
	private FetchDatabase fetchDatabase;
	
	public ResetPasswordService(){
		fetchDatabase= new FetchDatabase();
	}
	
	public void checkEmail(String email) throws Exception{
		if(fetchDatabase.getEmail(email)){
			System.out.println("Sending tokenMail");
			sendToken(email);
			saveToken(email, token);
		}
		else throw new Exception ();
	}
	
	public void resetPassword(String email,String pw,int token)throws Exception{
		if(fetchDatabase.getEmail(email,token)){
			System.out.println("the token were correct ");
			fetchDatabase.resetPasswordInDriver(email, encryptPassWord(pw));
			fetchDatabase.resetPasswordInCustomer(email, encryptPassWord(pw));
			fetchDatabase.deleteToken(email);
		}else 
			System.out.println("the token were false "+email+" "+token);
	}
	
	
	public void sendToken(String email)throws AddressException, MessagingException {
		// Step1
		mailServerProperties = System.getProperties();
		mailServerProperties.put("mail.smtp.port", "587");
		mailServerProperties.put("mail.smtp.auth", "true");
		mailServerProperties.put("mail.smtp.starttls.enable", "true");
		// Step2
		getMailSession = Session.getDefaultInstance(mailServerProperties, null);
		generateMailMessage = new MimeMessage(getMailSession);
		generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
		generateMailMessage.setSubject(SUBJECT);
		token = generateToken();
		String emailBody = "Token : "+token;
		generateMailMessage.setContent(emailBody, "text/html");
		// Step3
		Transport transport = getMailSession.getTransport("smtp");
		transport.connect("smtp.gmail.com", FROM, PASSWORD);
		transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
		transport.close();
		System.out.println("TokenMail  has been sent successfully..");
		
	}	

	public int generateToken() {
		Random r = new Random(System.currentTimeMillis());
		int token =10000 + r.nextInt(20000);
		return token;
	}

	
	private void saveToken(String email,int token) throws Exception {
		fetchDatabase.saveToken(email,token);
	}
	
	
	public String encryptPassWord(String pw) throws Exception{
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		String cryptedPW=(new HexBinaryAdapter()).marshal(md5.digest(pw.getBytes()));
		return cryptedPW;
	}
	


}