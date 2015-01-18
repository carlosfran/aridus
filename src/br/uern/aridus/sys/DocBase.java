package br.uern.aridus.sys;

import java.util.Collection;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

import br.uern.aridus.model.UserProfile;

@XmlRootElement
@Entity
public class DocBase {
	@Id
	@GeneratedValue
	private long iddoc;
	@Column(nullable=false)
	private String title;
	@Column(nullable=false)
	private String abstractText;
	
	@OneToMany
	private Collection<KeywordDoc> keywords;
	
	@ManyToOne
	private UserProfile user;

	public long getIdDoc() {
		return iddoc;
	}

	public void setIdDocument(long idProject) {
		this.iddoc = idProject;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAbstractText() {
		return abstractText;
	}

	public void setAbstractText(String abstractText) {
		this.abstractText = abstractText;
	}

	public Collection<KeywordDoc> getKeywords() {
		return keywords;
	}

	public void setKeywords(Collection<KeywordDoc> keywords) {
		this.keywords = keywords;
	}

	public long getIddoc() {
		return iddoc;
	}

	public void setIddoc(long iddoc) {
		this.iddoc = iddoc;
	}

	public UserProfile getUser() {
		return user;
	}

	public void setUser(UserProfile user) {
		this.user = user;
	}
}
