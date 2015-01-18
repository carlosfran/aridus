package br.uern.aridus.model;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;


@WebService
public interface DataSetInterface {

	@WebMethod
	boolean create(DataSet dataset);
	
	@WebMethod
	boolean delete(String dtURI);
	
	@WebMethod
	List<String> listDatasetsByUser(String username);
	
	@WebMethod
	List<String> listDatasetsByVocab(String vocab);
	
	@WebMethod
	List<String> listDatasetsByVocabs(List<String> vocabs);
	
	@WebMethod
	List<String> listEndpointsByUser(String username);
	
	@WebMethod
	List<String> listEndpointsByVocab(String vocab);
	
	@WebMethod
	List<String> listEndpointsByVocabs(List<String> vocabs);
	
	@WebMethod
	List<String> listDatasetVocabs(String dataset);
	
	@WebMethod
	List<String> listEndpointVocabs(String endpoint);
	
	@WebMethod
	String getProp(String dataset, String prop);
	
	@WebMethod
	boolean createProp(String dataset, String prop, String value);
	
	@WebMethod
	boolean updateProp(String dataset, String prop, String oldValue, String newValue);
	
	@WebMethod
	boolean deleteProp(String dataset, String prop, String value);
	
}
