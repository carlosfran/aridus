<%@page import="javax.swing.JOptionPane"%>
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
<title>ARIDUS - User register</title>
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
			out.println("<h1>User register</h1>"
					+"<form action=\"\" method=\"post\"><p>"
					+ "Username : <input type=\"text\" name=\"username\" /></p>"
					+ "Password : <input type=\"password\" name=\"psswd\" /></p>"
					+ "E-mail : <input type=\"text\" name=\"email\" /></p>"
					+ "URI-Profile : <input type=\"text\" name=\"uriprofile\" /></p>"
					+ "<input type=\"submit\" value=\"Create User\" />"
					+ "</form>");
		} else if (httpMethod.equalsIgnoreCase("post")) {
			// Chamando o serviço
			ClientConfig cconfig = new DefaultClientConfig();
			Client client = Client.create(cconfig);
			ClientResponse resp;

			Form form = new Form();
			form.add("username", request.getParameter("username"));
			form.add("uriprofile", request.getParameter("uriprofile"));
			form.add("email", request.getParameter("email"));
			form.add("psswd", request.getParameter("psswd"));
			WebResource service = client.resource(getPath).path(
					"/ws/user/create");
			service.accept(MediaType.TEXT_PLAIN);
			resp = service.post(ClientResponse.class, form);
			String id = "0";
			id = resp.getEntity(String.class);
//			JOptionPane.showMessageDialog(null, "O valor é: "+id);
			if (Long.valueOf(id) > 0L) {
				out.println("User registered successfully!");
				out.println("<p/><a href=\"" + getPath
						+ "/login.jsp\" title=\"login\" >Login</a>");
			} else {
				out.println("Error while registering user");
				out.println("<p/><a href=\""
						+ getPath
						+ "/register.jsp\" title=\"User register\" >Try again!</a>");
			}
		}
	%>
</body>
</html>