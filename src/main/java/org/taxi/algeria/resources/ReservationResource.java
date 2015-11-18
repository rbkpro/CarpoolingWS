package org.taxi.algeria.resources;

import java.util.ArrayList;


import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.taxi.algeria.model.Reservation;
import org.taxi.algeria.service.ReservationService;

@Path("/reservations")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ReservationResource {

	ReservationService reservationService; 
	
	public ReservationResource() throws Exception{
		reservationService = new ReservationService();
	}
	
	@GET
	public ArrayList<Reservation> getReservations(@QueryParam ("driver") String driverID) throws Exception {	
		return reservationService.getReservations(driverID);
	}
	
	@GET
	@Path("/{trip}")
	public Reservation getReservation(@PathParam("trip") int tripID,@QueryParam("customer") String customer) throws Exception{
		return reservationService.getReservations(tripID,customer);
	}
	
	@POST
	public void insertReservation(Reservation reservation)throws Exception{
		reservationService.insertReservation(reservation);	
	}
	
	@PUT
	public void updateReservation(Reservation reservation)throws Exception{
		reservationService.updateReservation(reservation);	
	}
}
