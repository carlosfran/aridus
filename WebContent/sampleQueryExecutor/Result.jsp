<%@page contentType="text/html;charset=UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<HTML>
<HEAD>
<TITLE>Result</TITLE>
</HEAD>
<BODY>
<H1>Result</H1>

<jsp:useBean id="sampleQueryExecutorid" scope="session" class="br.uern.aridus.query.QueryExecutor" />

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
        String query_0id=  request.getParameter("query5");
            java.lang.String query_0idTemp = null;
        if(!query_0id.equals("")){
         query_0idTemp  = query_0id;
        }
        java.lang.String execute12mtemp = sampleQueryExecutorid.execute1(query_0idTemp);
if(execute12mtemp == null){
%>
<%=execute12mtemp %>
<%
}else{
        String tempResultreturnp3 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(execute12mtemp));
        %>
        <%= tempResultreturnp3 %>
        <%
}
break;
case 7:
        gotMethod = true;
        String query_1id=  request.getParameter("query10");
            java.lang.String query_1idTemp = null;
        if(!query_1id.equals("")){
         query_1idTemp  = query_1id;
        }
        java.util.List java1util1List_2id = null;
        java.lang.String execute27mtemp = sampleQueryExecutorid.execute2(query_1idTemp,java1util1List_2id);
if(execute27mtemp == null){
%>
<%=execute27mtemp %>
<%
}else{
        String tempResultreturnp8 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(execute27mtemp));
        %>
        <%= tempResultreturnp8 %>
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