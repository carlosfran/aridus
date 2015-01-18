<%@page contentType="text/html;charset=UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<HTML>
<HEAD>
<TITLE>Result</TITLE>
</HEAD>
<BODY>
<H1>Result</H1>

<jsp:useBean id="sampleDataSetManagerid" scope="session" class="br.uern.aridus.ws.DataSetManager" />

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
        String dtTitle_0id=  request.getParameter("dtTitle5");
            java.lang.String dtTitle_0idTemp = null;
        if(!dtTitle_0id.equals("")){
         dtTitle_0idTemp  = dtTitle_0id;
        }
        String dtDescription_1id=  request.getParameter("dtDescription7");
            java.lang.String dtDescription_1idTemp = null;
        if(!dtDescription_1id.equals("")){
         dtDescription_1idTemp  = dtDescription_1id;
        }
        String dtHomepage_2id=  request.getParameter("dtHomepage9");
            java.lang.String dtHomepage_2idTemp = null;
        if(!dtHomepage_2id.equals("")){
         dtHomepage_2idTemp  = dtHomepage_2id;
        }
        String dtSparqlEndpoint_3id=  request.getParameter("dtSparqlEndpoint11");
            java.lang.String dtSparqlEndpoint_3idTemp = null;
        if(!dtSparqlEndpoint_3id.equals("")){
         dtSparqlEndpoint_3idTemp  = dtSparqlEndpoint_3id;
        }
        String triples_4id=  request.getParameter("triples13");
        long triples_4idTemp  = Long.parseLong(triples_4id);
        String v0_5id=  request.getParameter("v015");
            java.lang.String v0_5idTemp = null;
        if(!v0_5id.equals("")){
         v0_5idTemp  = v0_5id;
        }
        String v1_6id=  request.getParameter("v117");
            java.lang.String v1_6idTemp = null;
        if(!v1_6id.equals("")){
         v1_6idTemp  = v1_6id;
        }
        String v2_7id=  request.getParameter("v219");
            java.lang.String v2_7idTemp = null;
        if(!v2_7id.equals("")){
         v2_7idTemp  = v2_7id;
        }
        String v3_8id=  request.getParameter("v321");
            java.lang.String v3_8idTemp = null;
        if(!v3_8id.equals("")){
         v3_8idTemp  = v3_8id;
        }
        String v4_9id=  request.getParameter("v423");
            java.lang.String v4_9idTemp = null;
        if(!v4_9id.equals("")){
         v4_9idTemp  = v4_9id;
        }
        String v5_10id=  request.getParameter("v525");
            java.lang.String v5_10idTemp = null;
        if(!v5_10id.equals("")){
         v5_10idTemp  = v5_10id;
        }
        String v6_11id=  request.getParameter("v627");
            java.lang.String v6_11idTemp = null;
        if(!v6_11id.equals("")){
         v6_11idTemp  = v6_11id;
        }
        String v7_12id=  request.getParameter("v729");
            java.lang.String v7_12idTemp = null;
        if(!v7_12id.equals("")){
         v7_12idTemp  = v7_12id;
        }
        String v8_13id=  request.getParameter("v831");
            java.lang.String v8_13idTemp = null;
        if(!v8_13id.equals("")){
         v8_13idTemp  = v8_13id;
        }
        String v9_14id=  request.getParameter("v933");
            java.lang.String v9_14idTemp = null;
        if(!v9_14id.equals("")){
         v9_14idTemp  = v9_14id;
        }
        java.lang.Boolean create2mtemp = sampleDataSetManagerid.create(dtTitle_0idTemp,dtDescription_1idTemp,dtHomepage_2idTemp,dtSparqlEndpoint_3idTemp,triples_4idTemp,v0_5idTemp,v1_6idTemp,v2_7idTemp,v3_8idTemp,v4_9idTemp,v5_10idTemp,v6_11idTemp,v7_12idTemp,v8_13idTemp,v9_14idTemp);
if(create2mtemp == null){
%>
<%=create2mtemp %>
<%
}else{
        String tempResultreturnp3 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(create2mtemp));
        %>
        <%= tempResultreturnp3 %>
        <%
}
break;
case 35:
        gotMethod = true;
        String dtURI_15id=  request.getParameter("dtURI38");
            java.lang.String dtURI_15idTemp = null;
        if(!dtURI_15id.equals("")){
         dtURI_15idTemp  = dtURI_15id;
        }
        java.lang.Boolean delete35mtemp = sampleDataSetManagerid.delete(dtURI_15idTemp);
if(delete35mtemp == null){
%>
<%=delete35mtemp %>
<%
}else{
        String tempResultreturnp36 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(delete35mtemp));
        %>
        <%= tempResultreturnp36 %>
        <%
}
break;
case 40:
        gotMethod = true;
        String username_16id=  request.getParameter("username45");
            java.lang.String username_16idTemp = null;
        if(!username_16id.equals("")){
         username_16idTemp  = username_16id;
        }
        java.util.List listDatasetsByUser40mtemp = sampleDataSetManagerid.listDatasetsByUser(username_16idTemp);
if(listDatasetsByUser40mtemp == null){
%>
<%=listDatasetsByUser40mtemp %>
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
case 47:
        gotMethod = true;
        String vocab_17id=  request.getParameter("vocab52");
            java.lang.String vocab_17idTemp = null;
        if(!vocab_17id.equals("")){
         vocab_17idTemp  = vocab_17id;
        }
        java.util.List listDatasetsByVocab47mtemp = sampleDataSetManagerid.listDatasetsByVocab(vocab_17idTemp);
if(listDatasetsByVocab47mtemp == null){
%>
<%=listDatasetsByVocab47mtemp %>
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
case 54:
        gotMethod = true;
        String v0_18id=  request.getParameter("v059");
            java.lang.String v0_18idTemp = null;
        if(!v0_18id.equals("")){
         v0_18idTemp  = v0_18id;
        }
        String v1_19id=  request.getParameter("v161");
            java.lang.String v1_19idTemp = null;
        if(!v1_19id.equals("")){
         v1_19idTemp  = v1_19id;
        }
        String v2_20id=  request.getParameter("v263");
            java.lang.String v2_20idTemp = null;
        if(!v2_20id.equals("")){
         v2_20idTemp  = v2_20id;
        }
        String v3_21id=  request.getParameter("v365");
            java.lang.String v3_21idTemp = null;
        if(!v3_21id.equals("")){
         v3_21idTemp  = v3_21id;
        }
        String v4_22id=  request.getParameter("v467");
            java.lang.String v4_22idTemp = null;
        if(!v4_22id.equals("")){
         v4_22idTemp  = v4_22id;
        }
        String v5_23id=  request.getParameter("v569");
            java.lang.String v5_23idTemp = null;
        if(!v5_23id.equals("")){
         v5_23idTemp  = v5_23id;
        }
        String v6_24id=  request.getParameter("v671");
            java.lang.String v6_24idTemp = null;
        if(!v6_24id.equals("")){
         v6_24idTemp  = v6_24id;
        }
        String v7_25id=  request.getParameter("v773");
            java.lang.String v7_25idTemp = null;
        if(!v7_25id.equals("")){
         v7_25idTemp  = v7_25id;
        }
        String v8_26id=  request.getParameter("v875");
            java.lang.String v8_26idTemp = null;
        if(!v8_26id.equals("")){
         v8_26idTemp  = v8_26id;
        }
        String v9_27id=  request.getParameter("v977");
            java.lang.String v9_27idTemp = null;
        if(!v9_27id.equals("")){
         v9_27idTemp  = v9_27id;
        }
        java.util.List listDatasetsByVocabs54mtemp = sampleDataSetManagerid.listDatasetsByVocabs(v0_18idTemp,v1_19idTemp,v2_20idTemp,v3_21idTemp,v4_22idTemp,v5_23idTemp,v6_24idTemp,v7_25idTemp,v8_26idTemp,v9_27idTemp);
if(listDatasetsByVocabs54mtemp == null){
%>
<%=listDatasetsByVocabs54mtemp %>
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
case 79:
        gotMethod = true;
        String username_28id=  request.getParameter("username84");
            java.lang.String username_28idTemp = null;
        if(!username_28id.equals("")){
         username_28idTemp  = username_28id;
        }
        java.util.List listEndpointsByUser79mtemp = sampleDataSetManagerid.listEndpointsByUser(username_28idTemp);
if(listEndpointsByUser79mtemp == null){
%>
<%=listEndpointsByUser79mtemp %>
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
case 86:
        gotMethod = true;
        String vocab_29id=  request.getParameter("vocab91");
            java.lang.String vocab_29idTemp = null;
        if(!vocab_29id.equals("")){
         vocab_29idTemp  = vocab_29id;
        }
        java.util.List listEndpointsByVocab86mtemp = sampleDataSetManagerid.listEndpointsByVocab(vocab_29idTemp);
if(listEndpointsByVocab86mtemp == null){
%>
<%=listEndpointsByVocab86mtemp %>
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
case 93:
        gotMethod = true;
        String v0_30id=  request.getParameter("v098");
            java.lang.String v0_30idTemp = null;
        if(!v0_30id.equals("")){
         v0_30idTemp  = v0_30id;
        }
        String v1_31id=  request.getParameter("v1100");
            java.lang.String v1_31idTemp = null;
        if(!v1_31id.equals("")){
         v1_31idTemp  = v1_31id;
        }
        String v2_32id=  request.getParameter("v2102");
            java.lang.String v2_32idTemp = null;
        if(!v2_32id.equals("")){
         v2_32idTemp  = v2_32id;
        }
        String v3_33id=  request.getParameter("v3104");
            java.lang.String v3_33idTemp = null;
        if(!v3_33id.equals("")){
         v3_33idTemp  = v3_33id;
        }
        String v4_34id=  request.getParameter("v4106");
            java.lang.String v4_34idTemp = null;
        if(!v4_34id.equals("")){
         v4_34idTemp  = v4_34id;
        }
        String v5_35id=  request.getParameter("v5108");
            java.lang.String v5_35idTemp = null;
        if(!v5_35id.equals("")){
         v5_35idTemp  = v5_35id;
        }
        String v6_36id=  request.getParameter("v6110");
            java.lang.String v6_36idTemp = null;
        if(!v6_36id.equals("")){
         v6_36idTemp  = v6_36id;
        }
        String v7_37id=  request.getParameter("v7112");
            java.lang.String v7_37idTemp = null;
        if(!v7_37id.equals("")){
         v7_37idTemp  = v7_37id;
        }
        String v8_38id=  request.getParameter("v8114");
            java.lang.String v8_38idTemp = null;
        if(!v8_38id.equals("")){
         v8_38idTemp  = v8_38id;
        }
        String v9_39id=  request.getParameter("v9116");
            java.lang.String v9_39idTemp = null;
        if(!v9_39id.equals("")){
         v9_39idTemp  = v9_39id;
        }
        java.util.List listEndpointsByVocabs93mtemp = sampleDataSetManagerid.listEndpointsByVocabs(v0_30idTemp,v1_31idTemp,v2_32idTemp,v3_33idTemp,v4_34idTemp,v5_35idTemp,v6_36idTemp,v7_37idTemp,v8_38idTemp,v9_39idTemp);
if(listEndpointsByVocabs93mtemp == null){
%>
<%=listEndpointsByVocabs93mtemp %>
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
case 118:
        gotMethod = true;
        String dataset_40id=  request.getParameter("dataset123");
            java.lang.String dataset_40idTemp = null;
        if(!dataset_40id.equals("")){
         dataset_40idTemp  = dataset_40id;
        }
        java.util.List listDatasetVocabs118mtemp = sampleDataSetManagerid.listDatasetVocabs(dataset_40idTemp);
if(listDatasetVocabs118mtemp == null){
%>
<%=listDatasetVocabs118mtemp %>
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
case 125:
        gotMethod = true;
        String endpoint_41id=  request.getParameter("endpoint130");
            java.lang.String endpoint_41idTemp = null;
        if(!endpoint_41id.equals("")){
         endpoint_41idTemp  = endpoint_41id;
        }
        java.util.List listEndpointVocabs125mtemp = sampleDataSetManagerid.listEndpointVocabs(endpoint_41idTemp);
if(listEndpointVocabs125mtemp == null){
%>
<%=listEndpointVocabs125mtemp %>
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
case 132:
        gotMethod = true;
        String dataset_42id=  request.getParameter("dataset135");
            java.lang.String dataset_42idTemp = null;
        if(!dataset_42id.equals("")){
         dataset_42idTemp  = dataset_42id;
        }
        String prop_43id=  request.getParameter("prop137");
            java.lang.String prop_43idTemp = null;
        if(!prop_43id.equals("")){
         prop_43idTemp  = prop_43id;
        }
        java.lang.String getProp132mtemp = sampleDataSetManagerid.getProp(dataset_42idTemp,prop_43idTemp);
if(getProp132mtemp == null){
%>
<%=getProp132mtemp %>
<%
}else{
        String tempResultreturnp133 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(getProp132mtemp));
        %>
        <%= tempResultreturnp133 %>
        <%
}
break;
case 139:
        gotMethod = true;
        String dataset_44id=  request.getParameter("dataset142");
            java.lang.String dataset_44idTemp = null;
        if(!dataset_44id.equals("")){
         dataset_44idTemp  = dataset_44id;
        }
        String prop_45id=  request.getParameter("prop144");
            java.lang.String prop_45idTemp = null;
        if(!prop_45id.equals("")){
         prop_45idTemp  = prop_45id;
        }
        String value_46id=  request.getParameter("value146");
            java.lang.String value_46idTemp = null;
        if(!value_46id.equals("")){
         value_46idTemp  = value_46id;
        }
        java.lang.Boolean createProp139mtemp = sampleDataSetManagerid.createProp(dataset_44idTemp,prop_45idTemp,value_46idTemp);
if(createProp139mtemp == null){
%>
<%=createProp139mtemp %>
<%
}else{
        String tempResultreturnp140 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(createProp139mtemp));
        %>
        <%= tempResultreturnp140 %>
        <%
}
break;
case 148:
        gotMethod = true;
        String dataset_47id=  request.getParameter("dataset151");
            java.lang.String dataset_47idTemp = null;
        if(!dataset_47id.equals("")){
         dataset_47idTemp  = dataset_47id;
        }
        String prop_48id=  request.getParameter("prop153");
            java.lang.String prop_48idTemp = null;
        if(!prop_48id.equals("")){
         prop_48idTemp  = prop_48id;
        }
        String oldValue_49id=  request.getParameter("oldValue155");
            java.lang.String oldValue_49idTemp = null;
        if(!oldValue_49id.equals("")){
         oldValue_49idTemp  = oldValue_49id;
        }
        String newValue_50id=  request.getParameter("newValue157");
            java.lang.String newValue_50idTemp = null;
        if(!newValue_50id.equals("")){
         newValue_50idTemp  = newValue_50id;
        }
        java.lang.Boolean updateProp148mtemp = sampleDataSetManagerid.updateProp(dataset_47idTemp,prop_48idTemp,oldValue_49idTemp,newValue_50idTemp);
if(updateProp148mtemp == null){
%>
<%=updateProp148mtemp %>
<%
}else{
        String tempResultreturnp149 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(updateProp148mtemp));
        %>
        <%= tempResultreturnp149 %>
        <%
}
break;
case 159:
        gotMethod = true;
        String dataset_51id=  request.getParameter("dataset162");
            java.lang.String dataset_51idTemp = null;
        if(!dataset_51id.equals("")){
         dataset_51idTemp  = dataset_51id;
        }
        String prop_52id=  request.getParameter("prop164");
            java.lang.String prop_52idTemp = null;
        if(!prop_52id.equals("")){
         prop_52idTemp  = prop_52id;
        }
        String value_53id=  request.getParameter("value166");
            java.lang.String value_53idTemp = null;
        if(!value_53id.equals("")){
         value_53idTemp  = value_53id;
        }
        java.lang.Boolean deleteProp159mtemp = sampleDataSetManagerid.deleteProp(dataset_51idTemp,prop_52idTemp,value_53idTemp);
if(deleteProp159mtemp == null){
%>
<%=deleteProp159mtemp %>
<%
}else{
        String tempResultreturnp160 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(deleteProp159mtemp));
        %>
        <%= tempResultreturnp160 %>
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