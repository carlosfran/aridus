package br.uern.aridus.ws;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.jws.WebService;

import org.openrdf.repository.RepositoryException;

import br.uern.aridus.model.DataSet;
import br.uern.aridus.model.DataSetManagerIface;
import br.uern.aridus.query.DataSetImpl;
import br.uern.aridus.sys.AAuthentication;

@WebService(targetNamespace = "http://ws.aridus.uern.br/", portName = "DataSetManagerPort", serviceName = "DataSetManagerService", endpointInterface = "br.uern.aridus.model.DataSetManagerIface")
public class DataSetManager implements DataSetManagerIface{

	private DataSetImpl datasetApi;
	
	public DataSetManager() throws RepositoryException{
		datasetApi = new DataSetImpl();
	}

	@Override
	public Boolean create(String dtTitle, String dtDescription,
			 String dtHomepage, String dtSparqlEndpoint, long triples,
			 String v0, String v1,
			 String v2, String v3,
			 String v4, String v5,
			 String v6, String v7,
			 String v8, String v9) {

		Boolean ret = false;
		String[] creds = AAuthentication.getCredentials();
		if(creds != null){
			String dtURI = DataSetImpl.datasetURIGenerator(creds[0], dtTitle);
			
			List<String> vocabs = new ArrayList<String>(10);
			String[] vs = new String[] { v0, v1, v2, v3, v4, v5, v6, v7, v8, v9 };
			for (int i = 0; i < 10; i++) {
				if (vs[i] != null && !vs[i].isEmpty() && vs[i].startsWith("http")) {
					vocabs.add(vs[i]);
					// System.out.println(">> "+vs[i]);
				}
			}
			DataSet dataset = new DataSet();
			dataset.setDescription(dtDescription);
			dataset.setUri(dtURI);
			dataset.setTitle(dtTitle);
			dataset.setHomepage(dtHomepage);
			dataset.setSparqlEndpoint(dtSparqlEndpoint);
			dataset.setTriples(triples);
			dataset.setVocabulary(vocabs);
			
			ret = datasetApi.create(dataset);		
		}
		return ret;
	}

	@Override
	public Boolean delete(String dtURI) {
		Boolean ret = false;
		String[] creds = AAuthentication.getCredentials();
		if(creds != null){
			if(DataSetImpl.checkDataSetOfUser(dtURI, creds[0]));
				ret = datasetApi.delete(dtURI);
		}
		return ret;
	}

	@Override
	public List<String> listDatasetsByUser(String username) {
		List<String> list = datasetApi.listDatasetsByUser(username);
		// String ret = new String("");
		// Iterator<String> it = list.iterator();
		// while(it.hasNext()){
			// ret = ret.concat(it.next()+"\n");
		// }
		return list; // Response.status(200).entity(ret).build();
	}

	@Override
	public List<String> listDatasetsByVocab(String vocab) {
//		String ret = new String("");
		List<String> list = datasetApi.listDatasetsByVocab(vocab);
//		Iterator<String> it = list.iterator();
//		while(it.hasNext()){
//			ret = ret.concat(it.next()+"\n");
//		}
		return list; // Response.status(200).entity(ret).build();
	}

	@Override
	public List<String> listDatasetsByVocabs(String v0, String v1,
			String v2, String v3,
			String v4, String v5,
			String v6, String v7,
			String v8, String v9) {


		List<String> vocabs = new ArrayList<String>(10);
		String[] vs = new String[] { v0, v1, v2, v3, v4, v5, v6, v7, v8, v9 };
		
		for (int i = 0; i < 10; i++) {
			if (vs[i] != null && !vs[i].isEmpty() && vs[i].startsWith("http")) {
				vocabs.add(vs[i]);
				// System.out.println(">> "+vs[i]);
			}
		}
		
		String ret = new String("");
		List<String> list = datasetApi.listDatasetsByVocabs(vocabs);
		Iterator<String> it = list.iterator();
		while(it.hasNext()){
			ret = ret.concat(it.next()+"\n");
		}
		return vocabs;
	}

	@Override
	public List<String> listEndpointsByUser(String username) {
		List<String> list = datasetApi.listEndpointsByUser(username);
//		String ret = new String("");
//		Iterator<String> it = list.iterator();
//		while(it.hasNext()){
//			ret = ret.concat(it.next()+"\n");
//		}
		return list; // Response.status(200).entity(ret).build();
	}

	@Override
	public List<String> listEndpointsByVocab(String vocab) {
//		String ret = new String("");
		List<String> list = datasetApi.listEndpointsByVocab(vocab);
//		Iterator<String> it = list.iterator();
//		while(it.hasNext()){
//			ret = ret.concat(it.next()+"\n");
//		}
		return list; // Response.status(200).entity(ret).build();
	}

	@Override
	public List<String> listEndpointsByVocabs(
		String v0, String v1,
		String v2, String v3,
		String v4, String v5,
		String v6, String v7,
		String v8, String v9) {
	
		List<String> vocabs = new ArrayList<String>(10);
		String[] vs = new String[] { v0, v1, v2, v3, v4, v5, v6, v7, v8, v9 };
		
		for (int i = 0; i < 10; i++) {
			if (vs[i] != null && !vs[i].isEmpty() && vs[i].startsWith("http")) {
				vocabs.add(vs[i]);
				// System.out.println(">> "+vs[i]);
			}
		}
	
//		String ret = new String("");
		List<String> list = datasetApi.listEndpointsByVocabs(vocabs);
//		Iterator<String> it = list.iterator();
//		while(it.hasNext()){
//			ret = ret.concat(it.next()+"\n");
//		}
		return list; // Response.status(200).entity(ret).build();
	}

	@Override
	public List<String> listDatasetVocabs(String dataset) {
		// String ret = new String("");
		List<String> list = datasetApi.listDatasetVocabs(dataset);
//		Iterator<String> it = list.iterator();
//		while(it.hasNext()){
//			ret = ret.concat(it.next()+"\n");
//		}
		return list; // Response.status(200).entity(ret).build();
	}

	@Override
	public List<String> listEndpointVocabs(String endpoint) {
//		String ret = new String("");
		List<String> list = datasetApi.listEndpointVocabs(endpoint);
//		Iterator<String> it = list.iterator();
//		while(it.hasNext()){
//			ret = ret.concat(it.next()+"\n");
//		}
		return list; // Response.status(200).entity(ret).build();
	}

	@Override
	public String getProp(String dataset, String prop) {
		String ret = datasetApi.getProp(dataset, prop);
		return ret; // Response.status(200).entity(ret).build();
	}

	@Override
	public Boolean createProp(String dataset, String prop, String value) {
		Boolean ret = false;
		String[] creds = AAuthentication.getCredentials();
		if(creds != null){
			if(DataSetImpl.checkDataSetOfUser(dataset, creds[0]))
				ret = datasetApi.createProp(dataset, prop, value);			 
		}
		return ret; // Response.status(200).entity(ret.toString()).build();
	}

	@Override
	public Boolean updateProp(String dataset, String prop, String oldValue, String newValue) {
		Boolean ret = false;
		String[] creds = AAuthentication.getCredentials();
		if(creds != null){
			if(DataSetImpl.checkDataSetOfUser(dataset, creds[0]))
				ret = datasetApi.updateProp(dataset, prop, oldValue, newValue);
		}
		return ret; // Response.status(200).entity(ret.toString()).build();
	}
	
	@Override
	public Boolean deleteProp(String dataset, String prop, String value) {
		Boolean ret = false;
		String[] creds = AAuthentication.getCredentials();
		if(creds != null){
			if(DataSetImpl.checkDataSetOfUser(dataset, creds[0]))
				ret = datasetApi.deleteProp(dataset, prop, value);
		}
		return ret; // Response.status(200).entity(ret.toString()).build();
	}	
}
