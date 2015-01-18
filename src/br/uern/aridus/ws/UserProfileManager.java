package br.uern.aridus.ws;

import java.sql.SQLException;

import javax.jws.WebService;

import br.uern.aridus.model.UserProfile;
import br.uern.aridus.model.UserProfileManagerIface;
import br.uern.aridus.sys.AAuthentication;
import br.uern.aridus.sys.AUser;
import br.uern.aridus.sys.AUserImpl;

@WebService(targetNamespace = "http://ws.aridus.uern.br/", portName = "UserProfileManagerPort", serviceName = "UserProfileManagerService", endpointInterface = "br.uern.aridus.model.UserProfileManagerIface")
public class UserProfileManager implements UserProfileManagerIface {
	
	private AUser userProfile;

	public UserProfileManager() throws InstantiationException,
			IllegalAccessException, ClassNotFoundException, SQLException {
		userProfile = new AUserImpl();
	}

	@Override
	public Long create(String username, String psswd, String email, String uriprofile) {
		UserProfile user = new UserProfile();
		user.setUsername(username);
		user.setPsswd(psswd);
		user.setEmail(email);
		user.setUriprofile(uriprofile);
		Long id = userProfile.create(user);
		return id;
	}

	@Override
	public Boolean delete() {
		Boolean ret = false;
		String[] creds = AAuthentication.getCredentials();
		if(creds != null)
			ret = userProfile.delete(creds[0], creds[1]); 
        return ret;
	}

	@Override
	public Long check(String username, String psswd) {
		Long ret = 0L;
		ret = userProfile.check(username, psswd);
		return ret;
	}

	@Override
	public String getProp(String username, String prop) {
		String ret = "";
		String[] creds = AAuthentication.getCredentials();
		if(creds != null){
			ret = userProfile.getProp(username, creds[1], prop);
		}
		return ret;
	}

	@Override
	public Boolean updateProp(String username, String prop, String value) {
		
		Boolean ret = false;
		String[] creds = AAuthentication.getCredentials();
		if(creds != null){
			ret = userProfile.updateProp(username, creds[1], prop, value);
		}
		return ret;
	}
}
