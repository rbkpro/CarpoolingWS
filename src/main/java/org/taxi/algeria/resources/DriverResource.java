package org.taxi.algeria.resources;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.taxi.algeria.model.Driver;
import org.taxi.algeria.service.DriverService;

@Path("/drivers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DriverResource {

	DriverService driverService;

	public DriverResource() throws Exception {
		driverService = new DriverService();
	}

	@GET
	public ArrayList<Driver> getAllDrivers() throws Exception {
		return driverService.getAlldrivers();
	}

	@GET
	@Path("/{driverID}")
	public Driver getDriver(@PathParam("driverID") String driverID,
			@QueryParam("passWord") String passWord,
			@QueryParam("regID") String regID) throws Exception {
		return driverService.getDriver(driverID, passWord, regID);
	}

	@POST
	public void insertDriver(Driver driver) throws Exception {
		driverService.insertDriver(driver);
	}

	@POST
	@Path("logout")
	public void driverLogout(Driver driver) throws Exception {
		driverService.driverLogout(driver);
	}

	@PUT
	public Driver updateDriver(Driver Driver)throws Exception {
		return driverService.updateDriver(Driver);
	}
		
	@DELETE
	@Path("/{driverID}")
	public void deleteDriver(@PathParam("driverID") String driverID)
			throws Exception {
		driverService.deleteDriver(driverID);
	}

}
