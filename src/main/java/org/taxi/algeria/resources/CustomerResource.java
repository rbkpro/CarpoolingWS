package org.taxi.algeria.resources;

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

import org.taxi.algeria.model.Customer;
import org.taxi.algeria.service.CustomerService;

@Path("/customers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CustomerResource {
	
	private CustomerService customerService;
	
	public CustomerResource()throws Exception {
		customerService=new CustomerService();
	}
	
	@GET
	@Path("/{customerID}")
	public Customer getCustomer(@PathParam("customerID") String customerID,
			@QueryParam ("passWord") String passWord,@QueryParam("regID") String regID) throws Exception{
		return customerService.getCustomer(customerID,passWord,regID);
	}
	
	@POST
	public void insertCustomer(Customer customer) throws Exception {	
		customerService.insertCustomer(customer);
	}
	
	@POST
	@Path("logout")
	public void customerLogout(Customer customer) throws Exception {
		customerService.customerLogout(customer);
	}
	
	@PUT
	public Customer updateCustomer(Customer customer)throws Exception{
		return customerService.updateCustomer(customer);
	}
	
	 
	@DELETE
	@Path("/{customerID}")
	public void deleteCustomer(@PathParam("customerID") String customerID) throws Exception{
		customerService.deleteCustomer(customerID);
	}

}
