package org.taxi.algeria.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Reservation {

	private Customer customer;
	private Trip trip;
	private int nbrSeats;
	private String status;
	private float lat;
	private float lng;
	

	public Reservation() {
	}

	public Reservation(Customer customer, Trip trip, int nbrSeats, String status, float lat, float lng) {
		this.customer = customer;
		this.trip = trip;
		this.nbrSeats = nbrSeats;
		this.status = status;
		this.lat=lat;
		this.lng=lng;
		
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Trip getTrip() {
		return trip;
	}

	public void setTrip(Trip trip) {
		this.trip = trip;
	}

	public int getNbrSeats() {
		return nbrSeats;
	}

	public void setNbrSeats(int nbrSeats) {
		this.nbrSeats = nbrSeats;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public float getLat() {
		return lat;
	}

	public void setLat(float lat) {
		this.lat = lat;
	}

	public float getLng() {
		return lng;
	}

	public void setLng(float lng) {
		this.lng = lng;
	}

	

}
