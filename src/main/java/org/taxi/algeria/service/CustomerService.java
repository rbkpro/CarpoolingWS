package org.taxi.algeria.service;

import java.security.MessageDigest;
import java.util.ArrayList;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

import org.taxi.algeria.database.FetchDatabase;
import org.taxi.algeria.model.Customer;
import org.taxi.algeria.model.Message;


public class CustomerService {

	private FetchDatabase fetchDatabase;

	public CustomerService(){
		this.fetchDatabase= new FetchDatabase();
	}

	public Customer getCustomer(String customerID,String passWord,String regID) throws Exception{
		Customer customer = fetchDatabase.getCustomer(customerID);
		if(customer.getPassword().equals(encryptPassWord(passWord))){
			customer.setRegID(regID);
			fetchDatabase.updateCustomer(customer);
			for(Message message:getMessages(customer.getCustomerID())){
				sendNotification(message);
			}
			customer.setPassword("Yes");
			
		}
		else customer.setPassword("No");
		return customer;
	}
	
	public int insertCustomer(Customer customer) throws Exception{
		customer.setPassword(encryptPassWord(customer.getPassword()));
		return fetchDatabase.insertCustomer(customer);
	}
	

	public void deleteCustomer(String customerID) throws Exception{
		fetchDatabase.deleteUser(customerID,2);	
	}
	
	public Customer updateCustomer(Customer customer)throws Exception {
		if(customer.getCustomerID().isEmpty()){
			return null;
		}
		customer.setPassword(encryptPassWord(customer.getPassword()));
		fetchDatabase.updateCustomer(customer);
		return customer;
	}
	
	
	public String encryptPassWord(String pw) throws Exception{
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		String cryptedPW=(new HexBinaryAdapter()).marshal(md5.digest(pw.getBytes()));
		return cryptedPW;
	}
	
	public void customerLogout(Customer customer)throws Exception{
		fetchDatabase.setRegIdNull(customer.getCustomerID(), 2);
	}

	private ArrayList<Message> getMessages(String userID) {
		return fetchDatabase.getMessages(userID,2);
		
	}
	
	public void sendNotification(Message message){
		System.out.println( "Sending POST to GCM" );	
        NotificationService.post(message);
	}
}
