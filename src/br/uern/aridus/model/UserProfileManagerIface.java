package br.uern.aridus.model;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@WebService
@Consumes({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_XML,
		MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
@Produces({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_XML,
		MediaType.APPLICATION_JSON })
public interface UserProfileManagerIface {

	@WebMethod
	@POST
	@Path("/user/create")
	public Long create(
			@FormParam("username") @WebParam(name="username") String username,
			@FormParam("psswd") @WebParam(name="psswd") String psswd,
			@FormParam("email") @WebParam(name="email") String email,
			@FormParam("uriprofile") @WebParam(name="uriprofile") String uriprofile);

	@WebMethod
	@DELETE
	@Path("/user/delete")
	public Boolean delete();

	@WebMethod
	@POST
	@Path("/user/check")
	public Long check(
			@FormParam("username") @WebParam(name="username") String username,
			@FormParam("psswd") @WebParam(name="psswd") String psswd);

	@WebMethod
	@GET
	@Path("/user/{username}/{prop}")
	public String getProp(@PathParam("username") @WebParam(name="username") String username,
			@PathParam("prop") @WebParam(name="prop") String prop);

	@WebMethod
	@PUT
	@Path("/user/{username}/{prop}")
	public Boolean updateProp(
			@PathParam("username") @WebParam(name="username") String username,
			@PathParam("prop") @WebParam(name="prop") String prop,
			@QueryParam("value") @WebParam(name="value") String value);

}