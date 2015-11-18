package org.taxi.algeria.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Trip {

	private int tripID;
	private L0cation departureLocation;
	private L0cation arrivalLocation;
	private Date departureDate;
	private Date arrivalDate;
	private int reservedSeats;
	private double unitPrice;
	private Car tripCar;

	public Trip() {
	}

	public Trip(int tripID, L0cation departureLocation,
			L0cation arrivalLocation, Date departureDate, Date arrivalDate,
			int reservedSeats, double unitPrice, Car tripCar) {
		this.tripID = tripID;
		this.departureLocation = departureLocation;
		this.arrivalLocation = arrivalLocation;
		this.departureDate = departureDate;
		this.arrivalDate = arrivalDate;
		this.reservedSeats = reservedSeats;
		this.unitPrice = unitPrice;
		this.tripCar = tripCar;
	}

	public int getTripID() {
		return tripID;
	}

	public void setTripID(int tripID) {
		this.tripID = tripID;
	}

	public L0cation getDepartureLocation() {
		return departureLocation;
	}

	public void setDepartureLocation(L0cation departureLocation) {
		this.departureLocation = departureLocation;
	}

	public L0cation getArrivalLocation() {
		return arrivalLocation;
	}

	public void setArrivalLocation(L0cation arrivalLocation) {
		this.arrivalLocation = arrivalLocation;
	}

	public Date getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}

	public Date getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public int getReservedSeats() {
		return reservedSeats;
	}

	public void setReservedSeats(int reservedSeats) {
		this.reservedSeats = reservedSeats;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Car getTripCar() {
		return tripCar;
	}

	public void setTripCar(Car tripCar) {
		this.tripCar = tripCar;
	}

}
