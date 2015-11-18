package org.taxi.algeria.resources;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.taxi.algeria.model.Car;
import org.taxi.algeria.service.CarService;

@Path("/cars")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CarResource {
	
	CarService carService;
	
	public CarResource() throws Exception{
		carService = new CarService();
	}
	
	@GET
	public ArrayList<Car> getReservations(@QueryParam ("driver") String driverID) throws Exception {	
		
		return carService.getCars(driverID);
	}
	
	
	@POST
	public void insertCar(Car car)throws Exception{
		carService.insertCar(car);
	}
}
