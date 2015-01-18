<%@page contentType="text/html;charset=UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<HTML>
<HEAD>
<TITLE>Result</TITLE>
</HEAD>
<BODY>
<H1>Result</H1>

<jsp:useBean id="sampleQueryProcessid" scope="session" class="br.uern.aridus.ws.QueryProcess" />

<%
String method = request.getParameter("method");
int methodID = 0;
if (method == null) methodID = -1;

if(methodID != -1) methodID = Integer.parseInt(method);
boolean gotMethod = false;

try {
switch (methodID){ 
case 2:
        gotMethod = true;
        String queryLn_0id=  request.getParameter("queryLn45");
            java.lang.String queryLn_0idTemp = null;
        if(!queryLn_0id.equals("")){
         queryLn_0idTemp  = queryLn_0id;
        }
        String query_1id=  request.getParameter("query47");
            java.lang.String query_1idTemp = null;
        if(!query_1id.equals("")){
         query_1idTemp  = query_1id;
        }
        javax.ws.rs.core.Response query2mtemp = sampleQueryProcessid.query(queryLn_0idTemp,query_1idTemp);
if(query2mtemp == null){
%>
<%=query2mtemp %>
<%
}else{
%>
<TABLE>
<TR>
<TD COLSPAN="3" ALIGN="LEFT">returnp:</TD>
</TABLE>
<%
}
break;
case 49:
        gotMethod = true;
        String queryLn_2id=  request.getParameter("queryLn92");
            java.lang.String queryLn_2idTemp = null;
        if(!queryLn_2id.equals("")){
         queryLn_2idTemp  = queryLn_2id;
        }
        String query_3id=  request.getParameter("query94");
            java.lang.String query_3idTemp = null;
        if(!query_3id.equals("")){
         query_3idTemp  = query_3id;
        }
        javax.ws.rs.core.Response query149mtemp = sampleQueryProcessid.query1(queryLn_2idTemp,query_3idTemp);
if(query149mtemp == null){
%>
<%=query149mtemp %>
<%
}else{
%>
<TABLE>
<TR>
<TD COLSPAN="3" ALIGN="LEFT">returnp:</TD>
</TABLE>
<%
}
break;
}
} catch (Exception e) { 
%>
Exception: <%= org.eclipse.jst.ws.util.JspUtils.markup(e.toString()) %>
Message: <%= org.eclipse.jst.ws.util.JspUtils.markup(e.getMessage()) %>
<%
return;
}
if(!gotMethod){
%>
result: N/A
<%
}
%>
</BODY>
</HTML>