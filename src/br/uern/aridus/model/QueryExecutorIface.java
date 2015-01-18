package br.uern.aridus.model;

import java.io.IOException;
import java.util.List;

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

import org.openrdf.query.QueryEvaluationException;
import org.openrdf.query.TupleQueryResultHandlerException;
import org.openrdf.query.resultio.UnsupportedQueryResultFormatException;
import org.openrdf.repository.RepositoryException;

@WebService
@Consumes({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_XML,
	MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
@Produces({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_XML,
	MediaType.APPLICATION_JSON })
public interface QueryExecutorIface {

	@RequestWrapper(className = "br.uern.aridus.model.jaxws.Execute1", localName = "execute1", targetNamespace = "http://model.aridus.uern.br/")
	@ResponseWrapper(className = "br.uern.aridus.model.jaxws.Execute1Response", localName = "execute1Response", targetNamespace = "http://model.aridus.uern.br/")
	@WebResult(name = "return")
	@WebMethod
	@GET
	@Path("/execute1")
	public String execute1(@WebParam(name="query") @QueryParam("query") String query) throws RepositoryException,
			TupleQueryResultHandlerException,
			UnsupportedQueryResultFormatException, QueryEvaluationException,
			IOException;

	@RequestWrapper(className = "br.uern.aridus.model.jaxws.Execute2", localName = "execute2", targetNamespace = "http://model.aridus.uern.br/")
	@ResponseWrapper(className = "br.uern.aridus.model.jaxws.Execute2Response", localName = "execute2Response", targetNamespace = "http://model.aridus.uern.br/")
	@WebResult(name = "return")
	@WebMethod
	@GET
	@Path("/execute2")
	public String execute2(@WebParam(name="query") @QueryParam("query") String query, @WebParam(name="endpoints") @QueryParam("endpoints") List<String> targetEndpoints)
			throws TupleQueryResultHandlerException,
			UnsupportedQueryResultFormatException, QueryEvaluationException,
			IOException;

}