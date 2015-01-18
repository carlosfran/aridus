<%@page contentType="text/html;charset=UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<HTML>
<HEAD>
<TITLE>Result</TITLE>
</HEAD>
<BODY>
<H1>Result</H1>

<jsp:useBean id="sampleAlignmentImportid" scope="session" class="br.uern.aridus.ws.AlignmentImport" />

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
        String urlIDAlign_0id=  request.getParameter("urlIDAlign5");
            java.lang.String urlIDAlign_0idTemp = null;
        if(!urlIDAlign_0id.equals("")){
         urlIDAlign_0idTemp  = urlIDAlign_0id;
        }
        java.lang.String alignImport2mtemp = sampleAlignmentImportid.alignImport(urlIDAlign_0idTemp);
if(alignImport2mtemp == null){
%>
<%=alignImport2mtemp %>
<%
}else{
        String tempResultreturnp3 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(alignImport2mtemp));
        %>
        <%= tempResultreturnp3 %>
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