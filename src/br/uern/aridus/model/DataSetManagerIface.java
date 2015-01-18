package br.uern.aridus.model;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
public interface DataSetManagerIface {

	@RequestWrapper(className = "br.uern.aridus.model.jaxws.Create", localName = "create", targetNamespace = "http://model.aridus.uern.br/")
	@ResponseWrapper(className = "br.uern.aridus.model.jaxws.CreateResponse", localName = "createResponse", targetNamespace = "http://model.aridus.uern.br/")
	@WebResult(name = "return")
	@WebMethod
	@POST
	@Path("/create")
	public Boolean create(@WebParam(name="dtTitle") @FormParam("dtTitle") String dtTitle,
			@WebParam(name="dtDescription") @FormParam("dtDescription") String dtDescription,
			@WebParam(name="dtHomepage") @FormParam("dtHomepage") String dtHomepage,
			@WebParam(name="dtSparqlEndpoint") @FormParam("dtSparqlEndpoint") String dtSparqlEndpoint,
			@WebParam(name="triples") @FormParam("triples") long triples,
			@WebParam(name="v0") @FormParam("v0") @DefaultValue(" ") String v0,
			@WebParam(name="v1") @FormParam("v1") @DefaultValue(" ") String v1,
			@WebParam(name="v2") @FormParam("v2") @DefaultValue(" ") String v2,
			@WebParam(name="v3") @FormParam("v3") @DefaultValue(" ") String v3,
			@WebParam(name="v4") @FormParam("v4") @DefaultValue(" ") String v4,
			@WebParam(name="v5") @FormParam("v5") @DefaultValue(" ") String v5,
			@WebParam(name="v6") @FormParam("v6") @DefaultValue(" ") String v6,
			@WebParam(name="v7") @FormParam("v7") @DefaultValue(" ") String v7,
			@WebParam(name="v8") @FormParam("v8") @DefaultValue(" ") String v8,
			@WebParam(name="v9") @FormParam("v9") @DefaultValue(" ") String v9);

	@RequestWrapper(className = "br.uern.aridus.model.jaxws.Delete", localName = "delete", targetNamespace = "http://model.aridus.uern.br/")
	@ResponseWrapper(className = "br.uern.aridus.model.jaxws.DeleteResponse", localName = "deleteResponse", targetNamespace = "http://model.aridus.uern.br/")
	@WebResult(name = "return")
	@WebMethod
	@DELETE
	@Path("/delete")
	public Boolean delete(@WebParam(name="uri") @QueryParam("uri") String dtURI);

	@RequestWrapper(className = "br.uern.aridus.model.jaxws.ListDatasetsByUser", localName = "listDatasetsByUser", targetNamespace = "http://model.aridus.uern.br/")
	@ResponseWrapper(className = "br.uern.aridus.model.jaxws.ListDatasetsByUserResponse", localName = "listDatasetsByUserResponse", targetNamespace = "http://model.aridus.uern.br/")
	@WebResult(name = "return")
	@WebMethod
	@GET
	@Path("/{username}")
	public List<String> listDatasetsByUser(@WebParam(name="username") @PathParam("username") String username);

	@RequestWrapper(className = "br.uern.aridus.model.jaxws.ListDatasetsByVocab", localName = "listDatasetsByVocab", targetNamespace = "http://model.aridus.uern.br/")
	@ResponseWrapper(className = "br.uern.aridus.model.jaxws.ListDatasetsByVocabResponse", localName = "listDatasetsByVocabResponse", targetNamespace = "http://model.aridus.uern.br/")
	@WebResult(name = "return")
	@WebMethod
	@GET
	@Path("/vocabulary")
	public List<String> listDatasetsByVocab(@WebParam(name="vocab") @QueryParam("vocab") String vocab);

	@RequestWrapper(className = "br.uern.aridus.model.jaxws.ListDatasetsByVocabs", localName = "listDatasetsByVocabs", targetNamespace = "http://model.aridus.uern.br/")
	@ResponseWrapper(className = "br.uern.aridus.model.jaxws.ListDatasetsByVocabsResponse", localName = "listDatasetsByVocabsResponse", targetNamespace = "http://model.aridus.uern.br/")
	@WebResult(name = "return")
	@WebMethod
	@GET
	@Path("/vocabularies")
	public List<String> listDatasetsByVocabs(
			@WebParam(name="v0") @QueryParam("v0") @DefaultValue(" ") String v0,
			@WebParam(name="v1") @QueryParam("v1") @DefaultValue(" ") String v1,
			@WebParam(name="v2") @QueryParam("v2") @DefaultValue(" ") String v2,
			@WebParam(name="v3") @QueryParam("v3") @DefaultValue(" ") String v3,
			@WebParam(name="v4") @QueryParam("v4") @DefaultValue(" ") String v4,
			@WebParam(name="v5") @QueryParam("v5") @DefaultValue(" ") String v5,
			@WebParam(name="v6") @QueryParam("v6") @DefaultValue(" ") String v6,
			@WebParam(name="v7") @QueryParam("v7") @DefaultValue(" ") String v7,
			@WebParam(name="v8") @QueryParam("v8") @DefaultValue(" ") String v8,
			@WebParam(name="v9") @QueryParam("v9") @DefaultValue(" ") String v9);

	@RequestWrapper(className = "br.uern.aridus.model.jaxws.ListEndpointsByUser", localName = "listEndpointsByUser", targetNamespace = "http://model.aridus.uern.br/")
	@ResponseWrapper(className = "br.uern.aridus.model.jaxws.ListEndpointsByUserResponse", localName = "listEndpointsByUserResponse", targetNamespace = "http://model.aridus.uern.br/")
	@WebResult(name = "return")
	@WebMethod
	@GET
	@Path("/endpoints/{username}")
	public List<String> listEndpointsByUser(@WebParam(name="username") @PathParam("username") String username);

	@RequestWrapper(className = "br.uern.aridus.model.jaxws.ListEndpointsByVocab", localName = "listEndpointsByVocab", targetNamespace = "http://model.aridus.uern.br/")
	@ResponseWrapper(className = "br.uern.aridus.model.jaxws.ListEndpointsByVocabResponse", localName = "listEndpointsByVocabResponse", targetNamespace = "http://model.aridus.uern.br/")
	@WebResult(name = "return")
	@WebMethod
	@GET
	@Path("/endpoints/vocabulary")
	public List<String> listEndpointsByVocab(@WebParam(name="vocab") @QueryParam("vocab") String vocab);

	@RequestWrapper(className = "br.uern.aridus.model.jaxws.ListEndpointsByVocabs", localName = "listEndpointsByVocabs", targetNamespace = "http://model.aridus.uern.br/")
	@ResponseWrapper(className = "br.uern.aridus.model.jaxws.ListEndpointsByVocabsResponse", localName = "listEndpointsByVocabsResponse", targetNamespace = "http://model.aridus.uern.br/")
	@WebResult(name = "return")
	@WebMethod
	@GET
	@Path("/endpoints/vocabularies")
	public List<String> listEndpointsByVocabs(
			@WebParam(name="v0") @QueryParam("v0") @DefaultValue(" ") String v0,
			@WebParam(name="v1") @QueryParam("v1") @DefaultValue(" ") String v1,
			@WebParam(name="v2") @QueryParam("v2") @DefaultValue(" ") String v2,
			@WebParam(name="v3") @QueryParam("v3") @DefaultValue(" ") String v3,
			@WebParam(name="v4") @QueryParam("v4") @DefaultValue(" ") String v4,
			@WebParam(name="v5") @QueryParam("v5") @DefaultValue(" ") String v5,
			@WebParam(name="v6") @QueryParam("v6") @DefaultValue(" ") String v6,
			@WebParam(name="v7") @QueryParam("v7") @DefaultValue(" ") String v7,
			@WebParam(name="v8") @QueryParam("v8") @DefaultValue(" ") String v8,
			@WebParam(name="v9") @QueryParam("v9") @DefaultValue(" ") String v9);

	@RequestWrapper(className = "br.uern.aridus.model.jaxws.ListDatasetVocabs", localName = "listDatasetVocabs", targetNamespace = "http://model.aridus.uern.br/")
	@ResponseWrapper(className = "br.uern.aridus.model.jaxws.ListDatasetVocabsResponse", localName = "listDatasetVocabsResponse", targetNamespace = "http://model.aridus.uern.br/")
	@WebResult(name = "return")
	@WebMethod
	@GET
	@Path("/listvocabularies")
	public List<String> listDatasetVocabs(@WebParam(name="datset") @QueryParam("dataset") String dataset);

	@RequestWrapper(className = "br.uern.aridus.model.jaxws.ListEndpointVocabs", localName = "listEndpointVocabs", targetNamespace = "http://model.aridus.uern.br/")
	@ResponseWrapper(className = "br.uern.aridus.model.jaxws.ListEndpointVocabsResponse", localName = "listEndpointVocabsResponse", targetNamespace = "http://model.aridus.uern.br/")
	@WebResult(name = "return")
	@WebMethod
	@GET
	@Path("/endpoint/listvocabularies")
	public List<String> listEndpointVocabs(@WebParam(name="endpoint") @QueryParam("endpoint") String endpoint);

	@RequestWrapper(className = "br.uern.aridus.model.jaxws.GetProp", localName = "getProp", targetNamespace = "http://model.aridus.uern.br/")
	@ResponseWrapper(className = "br.uern.aridus.model.jaxws.GetPropResponse", localName = "getPropResponse", targetNamespace = "http://model.aridus.uern.br/")
	@WebResult(name = "return")
	@WebMethod
	@GET
	@Path("/property/get")
	public String getProp(@WebParam(name="databset") @QueryParam("dataset") String dataset,
			@WebParam(name="prop") @QueryParam("prop") String prop);

	@RequestWrapper(className = "br.uern.aridus.model.jaxws.CreateProp", localName = "createProp", targetNamespace = "http://model.aridus.uern.br/")
	@ResponseWrapper(className = "br.uern.aridus.model.jaxws.CreatePropResponse", localName = "createPropResponse", targetNamespace = "http://model.aridus.uern.br/")
	@WebResult(name = "return")
	@WebMethod
	@POST
	@Path("/property/create")
	public Boolean createProp(@WebParam(name="dataset") @QueryParam("dataset") String dataset,
			@WebParam(name="prop") @QueryParam("prop") String prop, @WebParam(name="value") @QueryParam("value") String value);

	@RequestWrapper(className = "br.uern.aridus.model.jaxws.UpdateProp", localName = "updateProp", targetNamespace = "http://model.aridus.uern.br/")
	@ResponseWrapper(className = "br.uern.aridus.model.jaxws.UpdatePropResponse", localName = "updatePropResponse", targetNamespace = "http://model.aridus.uern.br/")
	@WebResult(name = "return")
	@WebMethod
	@PUT
	@Path("/property/update")
	public Boolean updateProp(@WebParam(name="dataset") @QueryParam("dataset") String dataset,
			@WebParam(name="prop") @QueryParam("prop") String prop,
			@WebParam(name="oldValue") @QueryParam("oldValue") String oldValue,
			@WebParam(name="newValue") @QueryParam("newValue") String newValue);

	@RequestWrapper(className = "br.uern.aridus.model.jaxws.DeleteProp", localName = "deleteProp", targetNamespace = "http://model.aridus.uern.br/")
	@ResponseWrapper(className = "br.uern.aridus.model.jaxws.DeletePropResponse", localName = "deletePropResponse", targetNamespace = "http://model.aridus.uern.br/")
	@WebResult(name = "return")
	@WebMethod
	@DELETE
	@Path("/property/delete")
	public Boolean deleteProp(@WebParam(name="dataset") @QueryParam("dataset") String dataset,
			@WebParam(name="prop") @QueryParam("prop") String prop, @WebParam(name="value") @QueryParam("value") String value);

}