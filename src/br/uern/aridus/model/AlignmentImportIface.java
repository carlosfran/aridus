package br.uern.aridus.model;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

@WebService
@Consumes({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_XML,
		MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
@Produces({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_XML,
		MediaType.APPLICATION_JSON })
public interface AlignmentImportIface {

	@RequestWrapper(className = "br.uern.aridus.model.jaxws.AlignImport", localName = "alignImport", targetNamespace = "http://model.aridus.uern.br/")
	@ResponseWrapper(className = "br.uern.aridus.model.jaxws.AlignImportResponse", localName = "alignImportResponse", targetNamespace = "http://model.aridus.uern.br/")
	@WebResult(name = "return")
	@WebMethod
	@GET
	@Path("/import")
	@Produces(MediaType.TEXT_XML)
	public String alignImport(@WebParam(name="urlid") @QueryParam("urlid") String urlIDAlign);

}