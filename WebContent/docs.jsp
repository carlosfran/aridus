<%@page import="java.sql.ResultSet"%>
<%@page import="br.uern.aridus.sys.ADocBaseImpl"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ARIDUS - DocBase</title>
</head>
<body>
<div id="content">
<div id="header"><h1>DocumentBase</h1></div>
<div id="search">
<form method="post">
Search by: <input type="text" size="20" name="termo" id="termo"/>
 in <select name="field" id="field">
	<option value="title">Title</option>
	<option value="keyword">Keyword</option>
	<option value="abstract">Abstract</option>
</select>
<input type="submit" value="Search">
</form>
</div>
<div id="docs">
<p/><table summary="Tabela contendo resultados da busca por documentos na base. A primeira coluna contém o identificador do documento.
 A segunda coluna traz o título com link para página com resumo do documento. A terceira coluna contém link para download do documento."
 	border="1" width="80%">
  <tr>
    <th scope="col">ID</th>
    <th scope="col">Title</th>
    <th scope="col">Link to download</th>
  </tr>
<%
	String httpMethod = request.getMethod();
	if (httpMethod.equalsIgnoreCase("POST")) {
		ADocBaseImpl dBase = new ADocBaseImpl();
		ResultSet docs = dBase.searchDoc(request.getParameter("termo"), request.getParameter("field"));
		while (docs.next()) {
			String id = docs.getString(1);
			String title = docs.getString(2);
			out.print("\n<tr><td>"+ id +"</td>");
			out.print("\n<td><a href=\"http://localhost:8080/aridus/ws/doc/"+id+"\" title=\""+ title +"\">"+ title+"</a></td>");
			out.print("\n<td><a href=\"http://localhost:8080/aridus/ws/doc/"+id+"/download\" title=\"Download do documento intitulado "+ title +"\">Download</a></td></tr>");
		}
	}
%>
</table>
</div>
<div id="search">
<p/>
<form method="post">
Search by: <input type="text" size="20" name="termo" id="termo"/>
 in <select name="field" id="field">
	<option value="title">Title</option>
	<option value="keyword">Keyword</option>
	<option value="abstract">Abstract</option>
</select>
<input type="submit" value="Search">
</form>
</div>
</div>
</body>
</html>