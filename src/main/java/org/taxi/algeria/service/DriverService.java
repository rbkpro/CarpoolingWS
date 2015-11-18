package org.taxi.algeria.service;


import java.security.MessageDigest;
import java.util.ArrayList;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

import org.taxi.algeria.database.FetchDatabase;
import org.taxi.algeria.model.Driver;
import org.taxi.algeria.model.Message;



public class DriverService  {
	
	//private Map<String, Driver> drivers ;
	private FetchDatabase fetchDatabase;
	
	public DriverService () throws Exception {
		this.fetchDatabase= new FetchDatabase();
		//drivers=fetchDatabase.getDrivers();
	}
	
	public ArrayList<Driver> getAlldrivers() throws Exception{
		return new ArrayList<Driver>(fetchDatabase.getDrivers().values());
 	}
	
	public Driver getDriver(String driverID,String passWord,String regID)throws Exception{
		
		Driver driver = fetchDatabase.getDriver(driverID);
		if(driver.getPassword().equals(encryptPassWord(passWord))){
			driver.setRegID(regID);
			System.out.println(regID);
			updateDriver(driver);
			for(Message message:getMessages(driver.getDriverID())){
				sendNotification(message);
			}
			driver.setPassword("Yes");
			
		}
		else driver.setPassword("No");
		System.out.println("getDriver Result : "+driver.getFirstName());
		
		return driver ;
	}
		

	public Driver updateDriver(Driver driver)throws Exception {
		if(driver.getDriverID().isEmpty()){
			return null;
		}
		driver.setPassword(encryptPassWord(driver.getPassword()));
		fetchDatabase.updateDriver(driver);
		return driver;
	}
	
	public void deleteDriver(String driverID) throws Exception{
		fetchDatabase.deleteUser(driverID,1);	
	}
	
	public int insertDriver(Driver driver) throws Exception{
		driver.setPassword(encryptPassWord(driver.getPassword()));
		System.out.println(driver.getBirthDate());
		return fetchDatabase.insertDriver(driver);
	}
	
	public String encryptPassWord(String pw) throws Exception{
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		String cryptedPW=(new HexBinaryAdapter()).marshal(md5.digest(pw.getBytes()));
		return cryptedPW;
	}
	
	public void driverLogout(Driver driver)throws Exception{
		fetchDatabase.setRegIdNull(driver.getDriverID(), 1);
	}

	private ArrayList<Message> getMessages(String userID) {
		return fetchDatabase.getMessages(userID,1);
		
	}
	
	public void sendNotification(Message message){
		System.out.println( "Sending POST to GCM" );	
        NotificationService.post(message);
	}
	
	

}
