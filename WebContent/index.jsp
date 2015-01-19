<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	String path = request.getContextPath();
	String getProtocol = request.getScheme();
	String getDomain = request.getServerName();
	String getPort = Integer.toString(request.getServerPort());
	String getPath = getProtocol + "://" + getDomain + ":" + getPort
			+ path;
	response.sendRedirect("services");
%>