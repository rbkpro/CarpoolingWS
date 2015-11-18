package org.taxi.algeria.service;


import java.io.IOException;

import org.taxi.algeria.model.Message;


import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;

public class NotificationService {

	private static final String apiKey = "AIzaSyA3RY87DAQong4g4r6a7Mxp9dDg2gQfxJs";

	public static void post(Message message) {
		
		Sender sender = new Sender(apiKey);
		com.google.android.gcm.server.Message gcmessage = new com.google.android.gcm.server.Message.Builder()
			    .addData("message", message.getContent())
			    .addData("To", message.getUserID())
			    .build();
			Result result;
			try {
				result = sender.send(gcmessage, message.registration_ids.get(0), 1);
				System.out.println("GCM Result : "+result);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}
}