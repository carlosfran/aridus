/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uern.aridus.query;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.jws.WebService;

import org.openrdf.query.QueryEvaluationException;
import org.openrdf.query.QueryLanguage;
import org.openrdf.query.TupleQuery;
import org.openrdf.query.TupleQueryResult;
import org.openrdf.query.TupleQueryResultHandlerException;
import org.openrdf.query.resultio.QueryResultIO;
import org.openrdf.query.resultio.TupleQueryResultFormat;
import org.openrdf.query.resultio.UnsupportedQueryResultFormatException;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.sail.SailRepository;

import br.uern.aridus.model.QueryExecutorIface;

import com.fluidops.fedx.Config;
import com.fluidops.fedx.FedXFactory;
import com.fluidops.fedx.FederationManager;
import com.fluidops.fedx.exception.FedXException;
import com.fluidops.fedx.structures.Endpoint;
import com.fluidops.fedx.util.EndpointFactory;

/**
 * 
 * @author carlosfran
 */
@WebService(targetNamespace = "http://ws.aridus.uern.br/", 
portName = "QueryExecutorPort", serviceName = "QueryExecutorService",
endpointInterface = "br.uern.aridus.model.QueryExecutorIface")
public class QueryExecutor implements QueryExecutorIface {
	FederationManager fmg = null;
	SailRepository virtualRepository = null;
	boolean multiDatasetKB = false;
	List<String> datasetKB;
	DataSetImpl searchEndpoints;

	public QueryExecutor() throws FedXException, FileNotFoundException,
			IOException, RepositoryException {
		if (!FederationManager.isInitialized()) {
			// Config.initialize();
			Config.initialize("/dados/workspace/ARIDUS/aridus-config/fedx.config");
			virtualRepository = FedXFactory
					.initializeFederation(new ArrayList<Endpoint>());
			fmg = FederationManager.getInstance();
			datasetKB = new ArrayList<String>();
			searchEndpoints = new DataSetImpl();

			// Configuration Void Dataset Endpoint
			Properties prop = new Properties();
			String locationProp = "/dados/workspace/ARIDUS/aridus-config/VoIDEndpoints.properties";
			FileInputStream fis = new FileInputStream(locationProp);
			prop.load(fis);
			Iterator<Object> ivoid = prop.values().iterator();
			if (!ivoid.hasNext())
				System.out.println("Sem VoID Endpoints configurados!\n");
			while (ivoid.hasNext()) {
				String s = (String) ivoid.next();
				System.out.println("Void Endpoint: " + s);
				this.datasetKB.add(s);
			}
		}
	}

	public boolean isMultiDatasetKB() {
		return multiDatasetKB;
	}

	public void setMultiDatasetKB(boolean multiDatasetKB) {
		this.multiDatasetKB = multiDatasetKB;
	}

	@Override
	public String execute1(String query) throws RepositoryException,
			TupleQueryResultHandlerException,
			UnsupportedQueryResultFormatException, QueryEvaluationException,
			IOException {
		QueryRewritter qRewritter = new QueryRewritter();
		List<String> endpoints = searchEndpoints
				.listEndpointsByVocabs(qRewritter.listVocabs(query));
		System.out.println("Executor - Endpoints = " + endpoints.size());
		TupleQueryResult rs = federatedQueryExecutor(endpoints, query);
		return convertTupleResult(rs);
	}

	@Override
	public String execute2(String query, List<String> targetEndpoints)
			throws TupleQueryResultHandlerException,
			UnsupportedQueryResultFormatException, QueryEvaluationException,
			IOException {
		TupleQueryResult rs = federatedQueryExecutor(targetEndpoints, query);
		return convertTupleResult(rs);
	}

	public String convertTupleResult(TupleQueryResult result)
			throws IOException, TupleQueryResultHandlerException,
			UnsupportedQueryResultFormatException, QueryEvaluationException {
		StringBuffer bout = new StringBuffer();
		File f = File.createTempFile("tempfile-" + System.currentTimeMillis(),
				null);
		FileOutputStream out = new FileOutputStream(f);
		// QueryResultIO.write(result, TupleQueryResultFormat.SPARQL,
		// System.out);
		QueryResultIO.write(result, TupleQueryResultFormat.SPARQL, out);
		BufferedReader in = new BufferedReader(new FileReader(f));
		while (in.ready()) {
			bout.append(in.readLine());
		}
		return bout.toString();
	}

	public TupleQueryResult federatedQueryExecutor(List<String> endpoints,
			String squery) {

		TupleQueryResult sw = null; // new
									// TupleQueryResultBuilder().getQueryResult();
		try {
			fmg.removeAll();
			ArrayList<Endpoint> endpointList = new ArrayList<Endpoint>();
			Iterator<String> i = endpoints.iterator();
			while (i.hasNext()) {
				String s = i.next();
				endpointList.add(EndpointFactory.loadSPARQLEndpoint(s));
			}
			fmg.addAll(endpointList);
			TupleQuery query = virtualRepository.getConnection()
					.prepareTupleQuery(QueryLanguage.SPARQL, squery);
			sw = query.evaluate();
		} catch (Exception e) {
			java.awt.Toolkit.getDefaultToolkit().beep();
			e.printStackTrace();
		}
		return sw;
	}

}
