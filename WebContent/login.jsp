<%@page import="javax.ws.rs.core.Response"%>
<%@page import="javax.ws.rs.core.MediaType"%>
<%@page import="com.sun.jersey.api.client.ClientResponse"%>
<%@page import="javax.ws.rs.core.Response"%>
<%@page import="com.sun.jersey.api.representation.Form"%>
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
<title>ARIDUS - Login</title>
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
			response.sendRedirect(getPath + "/home.jsp");
		}

		String httpMethod = request.getMethod();
		if (httpMethod.equalsIgnoreCase("get")) {
			out.println("<h1>Login</h1>"
					+ "<form action=\"\" method=\"post\" >"
					+ "<p>Username : <input type=\"text\" name=\"username\" /></p>"
					+ "<p>Password : <input type=\"password\" name=\"psswd\" /></p>"
					+ "<input type=\"submit\" value=\"Login\" />"
					+ "</form>");

		} else if (httpMethod.equalsIgnoreCase("post")) {
			// Chamando o servi�o
			ClientConfig cconfig = new DefaultClientConfig();
			Client client = Client.create(cconfig);
			ClientResponse resp;
			
			Form form = new Form();
			form.add("username", request.getParameter("username"));
			form.add("psswd", request.getParameter("psswd"));
			WebResource service = client.resource(getPath).path(
					"services/rs/userprofile/user/check");
			service.accept(MediaType.TEXT_PLAIN);
			resp = service.post(ClientResponse.class, form);
			Long check = new Long(resp.getEntity(String.class));
			if (check > 0) {
				// redicionar... para home
				usession.setAttribute("username", request.getParameter("username"));
				usession.setAttribute("psswd", request.getParameter("psswd"));
				usession.setAttribute("userid", check);
				response.sendRedirect(getPath + "/home.jsp");
			} else {
				out.println("Username and/or password invalid!");
				out.println("<p/><a href=\""
						+ getPath
						+ "/login.jsp\" title=\"login\" >Try again!</a>");
			}
		}
	%>
</body>
</html>