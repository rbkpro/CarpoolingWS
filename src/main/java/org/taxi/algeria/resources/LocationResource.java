package org.taxi.algeria.resources;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


import org.taxi.algeria.model.L0cation;
import org.taxi.algeria.service.LocationService;


@Path("/locations")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LocationResource {
	
LocationService locationService; 
	
	public LocationResource() throws Exception{
		locationService = new LocationService();
	}
	
	@GET
	public ArrayList<L0cation> getLocations() throws Exception {
		return locationService.getLocations();
	}
	
	@GET
	@Path("/{locationID}")
	public L0cation getLocation(@PathParam("locationID") int locationID) throws Exception {
		return locationService.getLocation(locationID);
	}
	
	@POST 
	@Produces(MediaType.TEXT_PLAIN)
	public String insertLocation(L0cation location) throws Exception{
		return String.valueOf(locationService.insertLocation(location));
	}
	

	
	
	

}
