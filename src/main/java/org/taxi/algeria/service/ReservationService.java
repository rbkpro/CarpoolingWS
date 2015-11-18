
package org.taxi.algeria.service;


import java.util.ArrayList;

import org.taxi.algeria.database.FetchDatabase;
import org.taxi.algeria.model.Customer;
import org.taxi.algeria.model.Driver;
import org.taxi.algeria.model.Message;
import org.taxi.algeria.model.Reservation;




public class ReservationService {

	private FetchDatabase fetchDatabase;
	
	public ReservationService()throws Exception{
		this.fetchDatabase= new FetchDatabase();
	}
	
	public void updateReservation(Reservation reservation)throws Exception{
		Customer customer =fetchDatabase.getCustomer(reservation.getCustomer().getCustomerID());
		String content ="Driver "+reservation.getTrip().getTripCar().getCarDriver().getDriverID()
						+" "+reservation.getStatus()
						+" your reservation with trip "+reservation.getTrip().getDepartureLocation().getName()
						+" To "+reservation.getTrip().getArrivalLocation().getName();
		
		if (customer.getRegID().equals("null")) 
			insertMessage(customer.getCustomerID(),content);
		else{ 
			Message message= new Message();
			message.setUserID(customer.getCustomerID());
			message.setRegID(customer.getRegID());
			message.setContent(reservation.getCustomer().getCustomerID(), content);
			sendNotification(message);
			
			
		}
		
		if(reservation.getStatus().equals("validate"))
			fetchDatabase.updateTrip(reservation.getTrip().getTripID(), reservation.getNbrSeats());
		fetchDatabase.updateReservation(reservation);
	}
	
	public void deleteReservation(int tripID,int customerID)throws Exception{
		
	}
	
	public Reservation getReservation(int tripID,int customerID)throws Exception{
		return null;
	}
	
	

	public ArrayList<Reservation> getReservations(String driverID)throws Exception{
		return fetchDatabase.getReservations(driverID);
	}
	
	public int insertReservation(Reservation reservation)throws Exception{
		Driver driver =fetchDatabase.getDriver(reservation.getTrip().getTripCar().getCarDriver().getDriverID());
		String content=""+reservation.getCustomer().getCustomerID()+" book "+reservation.getNbrSeats()
		+" with the trip "+reservation.getTrip().getDepartureLocation().getName()+" To "
				+reservation.getTrip().getArrivalLocation().getName();
		if (driver.getRegID().equals("null")) 
			insertMessage(driver.getDriverID(),content);
		else{ 
			Message message= new Message();
			message.setUserID(driver.getDriverID());
			message.setRegID(driver.getRegID());
			message.setContent(reservation.getCustomer().getCustomerID(), content);
			sendNotification(message);
			
		}
		return fetchDatabase.insertReservation(reservation);
	}

	private void insertMessage(String userID, String content) throws Exception {
		Message message=new Message();
		message.setUserID(userID);
		message.setContent("", content);
		fetchDatabase.insertMessage(message);
		
	}

	public Reservation getReservations(int tripID, String customer) throws Exception {
		return fetchDatabase.getReservations(tripID,customer);
	}
	
	public void sendNotification(Message message){
		System.out.println( "Sending POST to GCM" );	
        NotificationService.post(message);
	}
}
