package br.uern.aridus.sys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class KeywordDoc{
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(unique=true,nullable=false)
	private String keyword;

}
