package org.taxi.algeria.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Message implements Serializable {

    public List<String> registration_ids;
    public Map<String,String> content;
    private int messageID;
    private String userID;
    
    
    
    public Message (){
    	
    }
    public Message (int messageID,String data,String userID){
    	this.messageID=messageID;
    	this.userID=userID;
    	this.registration_ids =new LinkedList<String>();
    	this.content = new HashMap<String,String>();
    }

    
    public void setRegID(String regId){
        if(registration_ids == null)
            registration_ids = new LinkedList<String>();
        registration_ids.add(regId);
    }

    public void setContent(String title, String message){
        if(content == null)
            content = new HashMap<String,String>();

        content.put("title", title);
        content.put("message", message);
    }
    
    public String getContent(){
    	return content.get("message");
    }

	public int getMessageID() {
		return messageID;
	}

	public void setMessageID(int messageID) {
		this.messageID = messageID;
	}


	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}
	
}