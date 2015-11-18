package org.taxi.algeria.service;

import java.util.ArrayList;

import org.taxi.algeria.database.FetchDatabase;
import org.taxi.algeria.model.Car;


public class CarService {
	
	private FetchDatabase fetchDatabase;
	
	public CarService()throws Exception{
		this.fetchDatabase= new FetchDatabase();
	}
	
	public ArrayList<Car> getCars(String driverID) throws Exception {
		return fetchDatabase.getCars(driverID);
	}
	
	public int insertCar(Car car)throws Exception{
		return fetchDatabase.insertCar(car);
	}

}
