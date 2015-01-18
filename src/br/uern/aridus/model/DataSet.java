package br.uern.aridus.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DataSet {
	private String uri;
	private String title;
	private String description;
	private String homepage;
	private String sparqlEndpoint;
	private long triples;
	
	@XmlElement
	private List<String> vocabulary;
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getHomepage() {
		return homepage;
	}
	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}
	public String getSparqlEndpoint() {
		return sparqlEndpoint;
	}
	public void setSparqlEndpoint(String sparqlEndpoint) {
		this.sparqlEndpoint = sparqlEndpoint;
	}
	public long getTriples() {
		return triples;
	}
	public void setTriples(long triples) {
		this.triples = triples;
	}
	public List<String> getVocabulary() {
		return vocabulary;
	}
	public void setVocabulary(List<String> vocabulary) {
		this.vocabulary = vocabulary;
	}
}
