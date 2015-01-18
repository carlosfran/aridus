<%@page import="com.sun.jersey.core.header.reader.HttpHeaderReader"%>
<%@page import="com.sun.jersey.core.header.FormDataContentDisposition"%>
<%@page import="com.sun.jersey.core.header.ContentDisposition"%>
<%@page import="java.io.InputStream"%>
<%@page import="com.sun.jersey.api.client.filter.LoggingFilter"%>
<%@page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@page import="org.apache.commons.fileupload.FileItemFactory"%>
<%@page import="org.apache.commons.fileupload.FileItem"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@page import="java.io.File"%>
<%@page import="java.util.Enumeration"%>
<%@page import="com.sun.jersey.multipart.*"%>
<%@page import="com.sun.jersey.api.client.filter.HTTPBasicAuthFilter"%>
<%@page import="br.uern.aridus.query.DataSetImpl"%>
<%@page import="javax.ws.rs.core.MediaType"%>
<%@page import="com.sun.jersey.api.client.ClientResponse"%>
<%@page import="javax.ws.rs.core.Response"%>
<%@page import="com.sun.jersey.api.client.WebResource"%>
<%@page import="com.sun.jersey.api.client.config.DefaultClientConfig"%>
<%@page import="com.sun.jersey.api.client.config.ClientConfig"%>
<%@page import="com.sun.jersey.api.client.Client"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ARIDUS - Doc register</title>
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

				out.println("<h1>Document register</h1>"
						+
						// action=\"addProject.jsp\" 
						"<form method=\"post\" enctype=\"multipart/form-data\"> <p>"
						+ "title : <input type=\"text\" id=\"title\" name=\"title\" size=\"45\" /> </p><p> "
						+ "abstract :<br/>"
						+ "<textarea id=\"abstractText\" name=\"abstractText\" rows=\"5\" cols=\"50\"></textarea></p><p> "
						+ "keywords : <input type=\"text\" id=\"keywords\" name=\"keywords\" size=\"45\" /></p><p>"
						+ "Select file : <input type=\"file\" id=\"file\" name=\"file\" size=\"45\" /></p>"
						+ "<input type=\"submit\" value=\"Submit\" /></form>");

			} else if (httpMethod.equalsIgnoreCase("post")) {

				FormDataMultiPart form = new FormDataMultiPart();

				if (ServletFileUpload.isMultipartContent(request)) {
					DiskFileItemFactory factory = new DiskFileItemFactory(
							20000, new File(System.getProperty("java.io.tmpdir")));
					System.out.println("TempDir: "+System.getProperty("java.io.tmpdir"));
					ServletFileUpload fileUpload = new ServletFileUpload(
							factory);
					List<FileItem> itens = fileUpload.parseRequest(request);
					Iterator<FileItem> it = itens.iterator();
					while (it.hasNext()) {
						FileItem item = it.next();
						if (item.isFormField()) {
							form.field(item.getFieldName(),
									item.getString());
						} else {
							FormDataBodyPart bodyPart = new FormDataBodyPart("file", item.getInputStream(), MediaType.APPLICATION_OCTET_STREAM_TYPE);
							bodyPart.setFormDataContentDisposition(
									new FormDataContentDisposition("form-data; name=\"file\" ; fileName=\""+item.getName()+"\""));
							form.bodyPart(bodyPart);
						}
					}
					// factory.setFileCleaningTracker(new FileCleaningTracker());
				}
				
				// Chamando o servi�o
				ClientConfig cconfig = new DefaultClientConfig();
				//cconfig.getClasses().add(MultiPartWriter.class);
				
				Client client = Client.create(cconfig);
				//client.addFilter(new LoggingFilter());
				client.addFilter(new HTTPBasicAuthFilter(
						(String) usession.getAttribute("username"),
						(String) usession.getAttribute("password")));
				
				WebResource service = client.resource(getPath + "/ws/doc/add");
				
				ClientResponse resp = service
						.type(MediaType.MULTIPART_FORM_DATA)
						.accept(MediaType.TEXT_PLAIN)
						.post(ClientResponse.class, form);
				String s = resp.getEntity(String.class);
				Integer callReturn = 0;
				if(s != null)
					callReturn = Integer.valueOf(s);
				
				if (callReturn > 0) {
					out.println("<p>Document successfully registered!</br>ID : "
							+ callReturn + "</p>");
					out.println("<p/><a href=\"" + getPath
							+ "/home.jsp\" title=\"home\" >Home</a>");
					out.println("<p/><a href=\""
							+ getPath
							+ "/addDoc.jsp\" title=\"Document register\" >Document register</a>");
				} else {
					out.println("Error while registering document!");
					out.println("<p/><a href=\""
							+ getPath
							+ "/addDoc.jsp\" title=\"Document register\" >Try again!</a>");
				}
			}
		} else {
			response.sendRedirect(getPath + "/login.jsp");
		}
	%>
</body>
</html>