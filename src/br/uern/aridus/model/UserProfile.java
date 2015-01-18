package br.uern.aridus.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
public class UserProfile {
	@Id
	@GeneratedValue
	private long userid;
	
	@Column(nullable=false, unique=true)
	private String username;
	@Column(nullable=false, unique=true)
	private String uriprofile;
	@Column(nullable=false)
	private String psswd;
	@Column(nullable=false)
	private String email;
	
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUriprofile() {
		return uriprofile;
	}
	public void setUriprofile(String uriprofile) {
		this.uriprofile = uriprofile;
	}
	public String getPsswd() {
		return psswd;
	}
	public void setPsswd(String psswd) {
		this.psswd = psswd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
