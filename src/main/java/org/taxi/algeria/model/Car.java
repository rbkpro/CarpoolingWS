package org.taxi.algeria.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Car {

	private int carID;
	private String carType;
	private String carColor;
	private int carSeats;
	private Driver carDriver;

	public Car() {
	}

	public Car(int carID, String carType, String carColor, int carSeats,
			Driver carDriver) {
		this.carID = carID;
		this.carType = carType;
		this.carColor = carColor;
		this.carSeats = carSeats;
		this.carDriver = carDriver;
	}

	public int getCarID() {
		return carID;
	}

	public void setCarID(int carID) {
		this.carID = carID;
	}

	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

	public String getCarColor() {
		return carColor;
	}

	public void setCarColor(String carColor) {
		this.carColor = carColor;
	}

	public int getCarSeats() {
		return carSeats;
	}

	public void setCarSeats(int carSeats) {
		this.carSeats = carSeats;
	}

	public Driver getCarDriver() {
		return carDriver;
	}

	public void setCarDriver(Driver carDriver) {
		this.carDriver = carDriver;
	}

}
