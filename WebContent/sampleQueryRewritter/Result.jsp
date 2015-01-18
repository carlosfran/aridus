<%@page contentType="text/html;charset=UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<HTML>
<HEAD>
<TITLE>Result</TITLE>
</HEAD>
<BODY>
<H1>Result</H1>

<jsp:useBean id="sampleQueryRewritterid" scope="session" class="br.uern.aridus.query.QueryRewritter" />

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
        java.lang.String queryRewritter12mtemp = sampleQueryRewritterid.queryRewritter1(query_0idTemp);
if(queryRewritter12mtemp == null){
%>
<%=queryRewritter12mtemp %>
<%
}else{
        String tempResultreturnp3 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(queryRewritter12mtemp));
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
        String alignID_2id=  request.getParameter("alignID12");
            java.lang.String alignID_2idTemp = null;
        if(!alignID_2id.equals("")){
         alignID_2idTemp  = alignID_2id;
        }
        java.lang.String queryRewritter47mtemp = sampleQueryRewritterid.queryRewritter4(query_1idTemp,alignID_2idTemp);
if(queryRewritter47mtemp == null){
%>
<%=queryRewritter47mtemp %>
<%
}else{
        String tempResultreturnp8 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(queryRewritter47mtemp));
        %>
        <%= tempResultreturnp8 %>
        <%
}
break;
case 14:
        gotMethod = true;
        String squery_3id=  request.getParameter("squery19");
            java.lang.String squery_3idTemp = null;
        if(!squery_3id.equals("")){
         squery_3idTemp  = squery_3id;
        }
        java.util.List listVocabs14mtemp = sampleQueryRewritterid.listVocabs(squery_3idTemp);
if(listVocabs14mtemp == null){
%>
<%=listVocabs14mtemp %>
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