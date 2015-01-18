package br.uern.aridus.model;

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

@WebService
@Consumes({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_XML,
		MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
@Produces({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_XML,
		MediaType.APPLICATION_JSON })
public interface QueryRewritterIface {

	// 1
	@RequestWrapper(className = "br.uern.aridus.model.jaxws.QueryRewritter1", localName = "queryRewritter1", targetNamespace = "http://model.aridus.uern.br/")
	@ResponseWrapper(className = "br.uern.aridus.model.jaxws.QueryRewritter1Response", localName = "queryRewritter1Response", targetNamespace = "http://model.aridus.uern.br/")
	@WebResult(name = "return")
	@WebMethod
	@GET
	@Path("/rewritter1")
	public String queryRewritter1(
			@WebParam(name = "query") @QueryParam("query") String query);

	@RequestWrapper(className = "br.uern.aridus.model.jaxws.QueryRewritter2", localName = "queryRewritter2", targetNamespace = "http://model.aridus.uern.br/")
	@ResponseWrapper(className = "br.uern.aridus.model.jaxws.QueryRewritter2Response", localName = "queryRewritter2Response", targetNamespace = "http://model.aridus.uern.br/")
	@WebResult(name = "return")
	@WebMethod
	@GET
	@Path("/rewritter2")
	public String queryRewritter2(
			@WebParam(name = "query") @QueryParam("query") String query,
			@WebParam(name = "alignentities") @QueryParam("alignentities") String[][] alignEntities);

	@RequestWrapper(className = "br.uern.aridus.model.jaxws.QueryRewritter3", localName = "queryRewritter3", targetNamespace = "http://model.aridus.uern.br/")
	@ResponseWrapper(className = "br.uern.aridus.model.jaxws.QueryRewritter3Response", localName = "queryRewritter3Response", targetNamespace = "http://model.aridus.uern.br/")
	@WebResult(name = "return")
	@WebMethod
	@GET
	@Path("/rewritter3")
	public String queryRewritter3(
			@WebParam(name = "query") @QueryParam("query") String query,
			@WebParam(name = "alignidlist") @QueryParam("alignidlist") String[] alignIDList);

	@RequestWrapper(className = "br.uern.aridus.model.jaxws.QueryRewritter4", localName = "queryRewritter4", targetNamespace = "http://model.aridus.uern.br/")
	@ResponseWrapper(className = "br.uern.aridus.model.jaxws.QueryRewritter4Response", localName = "queryRewritter4Response", targetNamespace = "http://model.aridus.uern.br/")
	@WebResult(name = "return")
	@WebMethod
	@GET
	@Path("/rewritter4")
	public String queryRewritter4(
			@WebParam(name = "query") @QueryParam("query") String query,
			@WebParam(name = "alignid") @QueryParam("alignid") String alignID);

	@RequestWrapper(className = "br.uern.aridus.model.jaxws.ListVocabs", localName = "listVocabs", targetNamespace = "http://model.aridus.uern.br/")
	@ResponseWrapper(className = "br.uern.aridus.model.jaxws.ListVocabsResponse", localName = "listVocabsResponse", targetNamespace = "http://model.aridus.uern.br/")
	@WebResult(name = "return")
	@WebMethod
	@GET
	@Path("/listvocabs")
	public List<String> listVocabs(
			@WebParam(name = "query") @QueryParam("query") String squery);

}