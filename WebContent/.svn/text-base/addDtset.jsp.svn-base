<%@page import="br.uern.aridus.query.DataSetImpl"%>
<%@page import="javax.swing.JOptionPane"%>
<%@page import="javax.ws.rs.core.MediaType"%>
<%@page import="com.sun.jersey.api.client.ClientResponse"%>
<%@page import="javax.ws.rs.core.Response"%>
<%@page import="com.sun.jersey.api.representation.Form"%>
<%@page import="com.sun.jersey.api.client.WebResource"%>
<%@page import="com.sun.jersey.api.client.config.DefaultClientConfig"%>
<%@page import="com.sun.jersey.api.client.filter.HTTPBasicAuthFilter"%>
<%@page import="com.sun.jersey.api.client.config.ClientConfig"%>
<%@page import="com.sun.jersey.api.client.Client"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ARIDUS - Dataset register</title>
</head>
<body>
	<%
		HttpSession usession = request.getSession(true);
		String scheck = (String) usession.getAttribute("username");
		String path = request.getContextPath();
		String getProtocol = request.getScheme();
		String getDomain = request.getServerName();
		String getPort = Integer.toString(request.getServerPort());
		String getPath = getProtocol + "://" + getDomain + ":" + getPort
				+ path;
		if (scheck != null) {
			String httpMethod = request.getMethod();
			String username = scheck;
			if (httpMethod.equalsIgnoreCase("get")) {

				// out.println("http://aridus.uern.br/" + username	+ "/dataset/%datasetName%");
				out.println("<h1>Dataset register</h1>"
						+"<form action=\"\" method=\"post\"><p>"
						+ "Title : <input type=\"text\" name=\"dtTitle\" /></p>"
						+ "Description : <input type=\"text\" name=\"dtDescription\" /></p>"
						+ "Homepage : <input type=\"text\" name=\"dtHomepage\" /></p>"
						+ "SPARQL Endpoint : <input type=\"text\" name=\"dtSparqlEndpoint\" /></p>"
						+ "Triples: <input type=\"text\" name=\"triples\" /></p>"
						+ "Vocabulary 0 : <input type=\"text\" name=\"v0\" /></p>"
						+ "Vocabulary 1 : <input type=\"text\" name=\"v1\" /></p>"
						+ "Vocabulary 2 : <input type=\"text\" name=\"v2\" /></p>"
						+ "Vocabulary 3 : <input type=\"text\" name=\"v3\" /></p>"
						+ "Vocabulary 4 : <input type=\"text\" name=\"v4\" /></p>"
						+ "Vocabulary 5 : <input type=\"text\" name=\"v5\" /></p>"
						+ "Vocabulary 6 : <input type=\"text\" name=\"v6\" /></p>"
						+ "Vocabulary 7 : <input type=\"text\" name=\"v7\" /></p>"
						+ "Vocabulary 8 : <input type=\"text\" name=\"v8\" /></p>"
						+ "Vocabulary 9 : <input type=\"text\" name=\"v9\" /></p>"
						+ "<input type=\"submit\" value=\"Register dataset\" />"
						+ "</form>");
			} else if (httpMethod.equalsIgnoreCase("post")) {
				// Chamando o serviço
				ClientConfig cconfig = new DefaultClientConfig();
				Client client = Client.create(cconfig);
				client.addFilter(new HTTPBasicAuthFilter(
						(String) usession.getAttribute("username"), (String) usession.getAttribute("password")));
				ClientResponse resp;

				Form form = new Form();
				String dtURI = DataSetImpl.datasetURIGenerator(username, request.getParameter("dtTitle").toLowerCase().trim());
				form.add("dtURI", dtURI);
				form.add("dtTitle", request.getParameter("dtTitle"));
				form.add("dtDescription",
						request.getParameter("dtDescription"));
				form.add("dtHomepage", request.getParameter("dtHomepage"));
				form.add("triples", request.getParameter("triples"));
				form.add("dtSparqlEndpoint",
						request.getParameter("dtSparqlEndpoint"));
				form.add("v0", request.getParameter("v0"));
				form.add("v1", request.getParameter("v1"));
				form.add("v2", request.getParameter("v2"));
				form.add("v3", request.getParameter("v3"));
				form.add("v4", request.getParameter("v4"));
				form.add("v5", request.getParameter("v5"));
				form.add("v6", request.getParameter("v6"));
				form.add("v7", request.getParameter("v7"));
				form.add("v8", request.getParameter("v8"));
				form.add("v9", request.getParameter("v9"));
				WebResource service = client.resource(getPath).path(
						"/ws/dataset/create");
				service.accept(MediaType.TEXT_PLAIN);
				resp = service.post(ClientResponse.class, form);
				
				Boolean callReturn = new Boolean(resp.getEntity(String.class));
				if (callReturn) {
					out.println("<p>Dataset successfully registered!!</br>URI: "+ dtURI +"</p>");
					out.println("<p/><a href=\"" + getPath
							+ "/home.jsp\" title=\"home\" >Home</a>");
					out.println("<p/><a href=\""
							+ getPath
							+ "/addDataset.jsp\" title=\"Dataset register\" >Dataset register</a>");
				} else {
					out.println("Error while registering dataset!");
					out.println("<p/><a href=\""
							+ getPath
							+ "/addDataset.jsp\" title=\"Dataset register\" >Try again!</a>");
				}
			}
		} else {
			response.sendRedirect(getPath + "/login.jsp");
		}
	%>
</body>
</html>