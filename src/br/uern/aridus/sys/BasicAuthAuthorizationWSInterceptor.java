package br.uern.aridus.sys;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Response;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import org.apache.commons.codec.binary.Base64;
import org.apache.cxf.binding.soap.interceptor.SoapHeaderInterceptor;
import org.apache.cxf.configuration.security.AuthorizationPolicy;
import org.apache.cxf.endpoint.Endpoint;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.jaxrs.model.ClassResourceInfo;
import org.apache.cxf.message.Exchange;
import org.apache.cxf.message.Message;
import org.apache.cxf.transport.Conduit;
import org.apache.cxf.ws.addressing.EndpointReferenceType;

public class BasicAuthAuthorizationWSInterceptor extends SoapHeaderInterceptor {

	@Override
	public void handleMessage(Message message) throws Fault {

		AuthorizationPolicy policy = message.get(AuthorizationPolicy.class);

		// If the policy is not set, the user did not specify credentials.
		// 401 is sent to the client to indicate that authentication is
		// required.
		if (policy == null) {
			sendErrorResponse(message, HttpURLConnection.HTTP_UNAUTHORIZED);
			return;
		}

		String username = policy.getUserName();
		String password = policy.getPassword();

		// CHECK USERNAME AND PASSWORD
		if (!checkLogin(username, password)) {
			System.out
					.println("handleMessage: Invalid username or password for user: "
							+ policy.getUserName());
			sendErrorResponse(message, HttpURLConnection.HTTP_FORBIDDEN);
		}
	}
		
	public static boolean checkLogin(String username, String password) {
		long id = AUserImpl.checkCredentials(username, password); 
		return (id > 0 ? true : false);
	}
	
	public static String[] checkLogin(WebServiceContext wsctx) {
		MessageContext mctx = wsctx.getMessageContext();
		Map http_headers = (Map) mctx.get(MessageContext.HTTP_REQUEST_HEADERS);

		ArrayList list = (ArrayList) http_headers.get("Authorization");
		if (list == null || list.size() == 0) {
			throw new RuntimeException(
					"Authentication failed! This WS needs BASIC Authentication!");
		}

		String userpass = (String) list.get(0);
		userpass = userpass.substring(5);
		byte[] buf = Base64.decodeBase64(userpass.getBytes());
		String credentials = new String(buf);

		String username = null;
		String password = null;
		int p = credentials.indexOf(":");
		if (p > -1) {
			username = credentials.substring(0, p);
			password = credentials.substring(p + 1);
			if (checkLogin(username, password)) {
				return new String[] { username, password };
			}
		}
		return null;
	}

	private void sendErrorResponse(Message message, int responseCode) {
		Message outMessage = getOutMessage(message);
		outMessage.put(Message.RESPONSE_CODE, responseCode);

		// Set the response headers
		@SuppressWarnings("unchecked")
		Map<String, List<String>> responseHeaders = (Map<String, List<String>>) message
				.get(Message.PROTOCOL_HEADERS);

		if (responseHeaders != null) {
			responseHeaders.put("WWW-Authenticate",
					Arrays.asList(new String[] { "Basic realm=realm" }));
			responseHeaders.put("Content-Length",
					Arrays.asList(new String[] { "0" }));
		}
		message.getInterceptorChain().abort();
		try {
			getConduit(message).prepare(outMessage);
			close(outMessage);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Message getOutMessage(Message inMessage) {
		Exchange exchange = inMessage.getExchange();
		Message outMessage = exchange.getOutMessage();
		if (outMessage == null) {
			Endpoint endpoint = exchange.get(Endpoint.class);
			outMessage = endpoint.getBinding().createMessage();
			exchange.setOutMessage(outMessage);
		}
		outMessage.putAll(inMessage);
		return outMessage;
	}

	private Conduit getConduit(Message inMessage) throws IOException {
		Exchange exchange = inMessage.getExchange();
		EndpointReferenceType target = exchange
				.get(EndpointReferenceType.class);
		Conduit conduit = exchange.getDestination().getBackChannel(inMessage,
				null, target);
		exchange.setConduit(conduit);
		return conduit;
	}

	private void close(Message outMessage) throws IOException {
		OutputStream os = outMessage.getContent(OutputStream.class);
		os.flush();
		os.close();
	}

}
