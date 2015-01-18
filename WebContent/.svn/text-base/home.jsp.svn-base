<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ARIDUS</title>
</head>
<body>
	<%
		HttpSession usession = request.getSession(true);
		String check = (String) usession.getAttribute("username");
		String path = request.getContextPath();
		String getProtocol = request.getScheme();
		String getDomain = request.getServerName();
		String getPort = Integer.toString(request.getServerPort());
		String getPath = getProtocol + "://" + getDomain + ":" + getPort
				+ path;
		if (check != null) {
			out.println("<h2>Hi, " + check + "!</h2>");
			out.println("<p/><a href=\"" + getPath
					+ "/addDtset.jsp\" title=\"Dataset register\" >Dataset register</a>\t|\t"
					+ "<a href=\"" + getPath
					+ "/addDoc.jsp\" title=\"Project register\" >Document register</a>\t|\t"+
					"<a href=\"" + getPath
					+ "/logout.jsp\" title=\"logout\" >Logout</a>");
			out.println("\n\n\n");
			// Mostrar opcoes para:
				// cadastrar dataset
				// upload content
				// cadastrar ontologia
				// importar alignment
				// ...
		} else {
			response.sendRedirect(getPath + "/login.jsp");
		}
	%>
</body>
</html>