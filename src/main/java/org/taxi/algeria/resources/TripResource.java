package org.taxi.algeria.resources;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;




import org.taxi.algeria.model.Trip;
import org.taxi.algeria.service.TripService;

@Path("/trips")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TripResource {

	TripService tripService; 
	
	public TripResource() throws Exception{
		tripService = new TripService();
	}
	
	@GET
	public ArrayList<Trip> getAllTrips(@QueryParam ("departure") int departureLocation,
										@QueryParam ("arrival") int arrivalLocation,
										@QueryParam ("date") String departureDate) throws Exception {
		
		return tripService.getTrips(departureLocation,arrivalLocation,departureDate);
	}
	@GET
	
	@Path("/{driverID}")
	public ArrayList<Trip> getTrips(@PathParam("driverID") String driverID)throws Exception{
		return tripService.getTrips(driverID);
	}
	
	@POST
	public void insertTrip(Trip trip) throws Exception{
		 tripService.insertTrip(trip);
	}
	
	
	
	
}
