package org.taxi.algeria.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.taxi.algeria.service.ResetPasswordService;

@Path("/reset-password")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ResetPasswordResource {
	private ResetPasswordService resPwService;
	
	
	public ResetPasswordResource() {
		resPwService = new ResetPasswordService();
	}
	
	@GET
	public void resetPassword(@QueryParam("email") String email) throws Exception {
		 resPwService.checkEmail(email);
	}
	
	@GET
	@Path("/reset")
	public void resetPassword(@QueryParam("email") String email,
								@QueryParam("password") String pw,
								@QueryParam("token")int token)throws Exception{
		
		resPwService.resetPassword(email, pw,token);
		
	}
	

}
