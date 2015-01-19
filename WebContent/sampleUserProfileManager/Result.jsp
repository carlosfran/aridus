<%@page contentType="text/html;charset=UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<HTML>
<HEAD>
<TITLE>Result</TITLE>
</HEAD>
<BODY>
	<H1>Result</H1>

	<jsp:useBean id="sampleUserProfileManagerid" scope="session"
		class="br.uern.aridus.ws.UserProfileManager" />

	<%
		String method = request.getParameter("method");
		int methodID = 0;
		if (method == null)
			methodID = -1;

		if (methodID != -1)
			methodID = Integer.parseInt(method);
		boolean gotMethod = false;

		try {
			switch (methodID) {
			case 2:
				gotMethod = true;
				String username_0id = request.getParameter("username5");
				java.lang.String username_0idTemp = null;
				if (!username_0id.equals("")) {
					username_0idTemp = username_0id;
				}
				String psswd_1id = request.getParameter("psswd7");
				java.lang.String psswd_1idTemp = null;
				if (!psswd_1id.equals("")) {
					psswd_1idTemp = psswd_1id;
				}
				String email_2id = request.getParameter("email9");
				java.lang.String email_2idTemp = null;
				if (!email_2id.equals("")) {
					email_2idTemp = email_2id;
				}
				String uriprofile_3id = request
						.getParameter("uriprofile11");
				java.lang.String uriprofile_3idTemp = null;
				if (!uriprofile_3id.equals("")) {
					uriprofile_3idTemp = uriprofile_3id;
				}
				java.lang.Long create2mtemp = sampleUserProfileManagerid
						.create(username_0idTemp, psswd_1idTemp,
								email_2idTemp, uriprofile_3idTemp);
				if (create2mtemp == null) {
	%>
	<%=create2mtemp%>
	<%
		} else {
					String tempResultreturnp3 = org.eclipse.jst.ws.util.JspUtils
							.markup(String.valueOf(create2mtemp));
	%>
	<%=tempResultreturnp3%>
	<%
		}
				break;
			case 13:
				gotMethod = true;
				java.lang.Boolean delete13mtemp = sampleUserProfileManagerid
						.delete();
				if (delete13mtemp == null) {
	%>
	<%=delete13mtemp%>
	<%
		} else {
					String tempResultreturnp14 = org.eclipse.jst.ws.util.JspUtils
							.markup(String.valueOf(delete13mtemp));
	%>
	<%=tempResultreturnp14%>
	<%
		}
				break;
			case 16:
				gotMethod = true;
				String username_4id = request.getParameter("username19");
				java.lang.String username_4idTemp = null;
				if (!username_4id.equals("")) {
					username_4idTemp = username_4id;
				}
				String psswd_5id = request.getParameter("psswd21");
				java.lang.String psswd_5idTemp = null;
				if (!psswd_5id.equals("")) {
					psswd_5idTemp = psswd_5id;
				}
				java.lang.Long check16mtemp = sampleUserProfileManagerid
						.check(username_4idTemp, psswd_5idTemp);
				if (check16mtemp == null) {
	%>
	<%=check16mtemp%>
	<%
		} else {
					String tempResultreturnp17 = org.eclipse.jst.ws.util.JspUtils
							.markup(String.valueOf(check16mtemp));
	%>
	<%=tempResultreturnp17%>
	<%
		}
				break;
			case 23:
				gotMethod = true;
				String username_6id = request.getParameter("username26");
				java.lang.String username_6idTemp = null;
				if (!username_6id.equals("")) {
					username_6idTemp = username_6id;
				}
				String prop_7id = request.getParameter("prop28");
				java.lang.String prop_7idTemp = null;
				if (!prop_7id.equals("")) {
					prop_7idTemp = prop_7id;
				}
				java.lang.String getProp23mtemp = sampleUserProfileManagerid
						.getProp(username_6idTemp, prop_7idTemp);
				if (getProp23mtemp == null) {
	%>
	<%=getProp23mtemp%>
	<%
		} else {
					String tempResultreturnp24 = org.eclipse.jst.ws.util.JspUtils
							.markup(String.valueOf(getProp23mtemp));
	%>
	<%=tempResultreturnp24%>
	<%
		}
				break;
			case 30:
				gotMethod = true;
				String username_8id = request.getParameter("username33");
				java.lang.String username_8idTemp = null;
				if (!username_8id.equals("")) {
					username_8idTemp = username_8id;
				}
				String prop_9id = request.getParameter("prop35");
				java.lang.String prop_9idTemp = null;
				if (!prop_9id.equals("")) {
					prop_9idTemp = prop_9id;
				}
				String value_10id = request.getParameter("value37");
				java.lang.String value_10idTemp = null;
				if (!value_10id.equals("")) {
					value_10idTemp = value_10id;
				}
				java.lang.Boolean updateProp30mtemp = sampleUserProfileManagerid
						.updateProp(username_8idTemp, prop_9idTemp,
								value_10idTemp);
				if (updateProp30mtemp == null) {
	%>
	<%=updateProp30mtemp%>
	<%
		} else {
					String tempResultreturnp31 = org.eclipse.jst.ws.util.JspUtils
							.markup(String.valueOf(updateProp30mtemp));
	%>
	<%=tempResultreturnp31%>
	<%
		}
				break;
			}
		} catch (Exception e) {
	%>
	Exception:
	<%=org.eclipse.jst.ws.util.JspUtils.markup(e.toString())%>
	Message:
	<%=org.eclipse.jst.ws.util.JspUtils.markup(e
						.getMessage())%>
	<%
		return;
		}
		if (!gotMethod) {
	%>
	result: N/A
	<%
		}
	%>
</BODY>
</HTML>