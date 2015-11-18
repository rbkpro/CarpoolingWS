package org.taxi.algeria.service;


import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import org.taxi.algeria.database.FetchDatabase;
import org.taxi.algeria.model.Car;
import org.taxi.algeria.model.Trip;

public class TripService {
	private FetchDatabase fetchDatabase;
	
	
	public TripService()throws Exception{
		this.fetchDatabase= new FetchDatabase();
	}
	
	
	public int insertTrip(Trip trip)throws Exception{
		Car car=fetchDatabase.getCar(trip.getTripCar().getCarID());
		if(car.getCarID()>0)
			return fetchDatabase.insertTrip(trip);
		else{
			System.out.println("You can't add a trip right now !!");
			return 404;
		}
	}
	
	public void deleteTrip(int tripID)throws Exception{
		fetchDatabase.deleteTrip(tripID);
	}
	
	public Trip getTrip(int tripID)throws Exception{
		return fetchDatabase.getTrip(tripID);
	}
	
	public ArrayList<Trip> getAllTrips()throws Exception{
		return fetchDatabase.getAllTrips();
	}
	public ArrayList<Trip> getTrips(int departureLocation,int arrivalLocation, String dtDeparture)throws Exception{
		DateTimeFormatter df= DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		LocalDateTime ldt=LocalDateTime.from(df.parse(dtDeparture));
		Instant instant = ldt.atZone(ZoneId.systemDefault()).toInstant();
		Date date = Date.from(instant);
		return fetchDatabase.getTrips(departureLocation, arrivalLocation, date);
	}
	
	
}
