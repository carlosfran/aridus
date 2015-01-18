package br.uern.aridus.sys;

import javax.jws.WebMethod;
import javax.jws.WebService;

import br.uern.aridus.model.UserProfile;

@WebService
public interface AUser {
	
	@WebMethod
	long create(UserProfile user);
	
	@WebMethod
	boolean delete(String username, String psswd);

	@WebMethod
	long check(String username, String psswd);

	@WebMethod
	String getProp(String username, String psswd, String prop);

	@WebMethod
	boolean updateProp(String username, String psswd, String prop, String value);
}
