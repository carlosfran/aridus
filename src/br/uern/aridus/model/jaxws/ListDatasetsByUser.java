
package br.uern.aridus.model.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * This class was generated by Apache CXF 2.7.7
 * Sat Jan 18 15:10:31 BRST 2014
 * Generated source version: 2.7.7
 */

@XmlRootElement(name = "listDatasetsByUser", namespace = "http://model.aridus.uern.br/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "listDatasetsByUser", namespace = "http://model.aridus.uern.br/")

public class ListDatasetsByUser {

    @XmlElement(name = "username")
    private java.lang.String username;

    public java.lang.String getUsername() {
        return this.username;
    }

    public void setUsername(java.lang.String newUsername)  {
        this.username = newUsername;
    }

}

