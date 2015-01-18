package br.uern.aridus.ws;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.openrdf.repository.RepositoryException;

import br.uern.aridus.query.QueryExecutor;
import br.uern.aridus.query.QueryRewritter;

import com.fluidops.fedx.exception.FedXException;

@Path("/query")
public class QueryProcess {
	private static QueryExecutor executor = null;
	private static QueryRewritter queryRewritter = null;

	public QueryProcess() throws FileNotFoundException, RepositoryException,
			FedXException, IOException {
		if (executor == null || queryRewritter == null) {
			executor = new QueryExecutor();
			queryRewritter = new QueryRewritter();
		}
	}

	@POST
	@Path("/sparql")
	@Produces("application/sparql-results+xml")
	public Response query(@FormParam("queryLn") String queryLn,
			@FormParam("query") String query) {

		String output = null;
		try {
			System.out
					.println("\nQueryProcess query inputed:\n" + query + "\n");
			String q = queryRewritter.queryRewritter1(query);
			System.out.println("\nQueryProcess query after the rewrite:\n" + q
					+ "\n");
			output = executor.execute1(q.toString());
		} catch (Exception e) {
			e.printStackTrace();
			output = "<?xml version='1.0' encoding='UTF-8'?>"
					+ "\n\t<sparql xmlns='http://www.w3.org/2005/sparql-results#'>"
					+ "\n\t\t<head/>" + "\n\t\t<results/>" + "\n\t</sparql>";
		}
		return Response.status(200).entity(output.toString()).build();
	}

	@GET
	@Path("/sparql")
	// @Produces("application/sparql-results+xml")
	public Response query1(@QueryParam("queryLn") String queryLn,
			@QueryParam("query") String query) {
		return query(queryLn, query);
	}
}
