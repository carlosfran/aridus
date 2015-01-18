package br.uern.aridus.sys;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.codec.binary.Base64;
import org.apache.cxf.jaxrs.ext.MessageContext;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.PhaseInterceptorChain;

public class AAuthentication {

	public static Long checkLogin(String username, String password) {
		Long id = AUserImpl.checkCredentials(username, password);
		return id;
	}

	public static String[] getCredentials() {
		Message msg = PhaseInterceptorChain.getCurrentMessage();
		Map<String, List<String>> map = (Map<String, List<String>>) msg.get(Message.PROTOCOL_HEADERS);
		Set<String> k = map.keySet();
		Iterator<String> i = k.iterator();
		while(i.hasNext()){
			String s = (String) i.next();
			System.out.println(s + " = " + map.get(s).toString());
		}
		String userpass = map.get("Authorization").get(0);
		userpass = userpass.substring(5);
		byte[] buf = Base64.decodeBase64(userpass.getBytes());
		String credentials = new String(buf);
		System.out.println(credentials);
		int p = credentials.indexOf(":");
		if (p > -1) {
			String[] creds = new String[2];
			creds[0] = credentials.substring(0, p);
			creds[1] = credentials.substring(p + 1);
			if (checkLogin(creds[0], creds[1]) > 0) {
				return creds;
			}
		}
		return null;
	}
}