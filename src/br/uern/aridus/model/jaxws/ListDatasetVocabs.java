
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

@XmlRootElement(name = "listDatasetVocabs", namespace = "http://model.aridus.uern.br/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "listDatasetVocabs", namespace = "http://model.aridus.uern.br/")

public class ListDatasetVocabs {

    @XmlElement(name = "datset")
    private java.lang.String datset;

    public java.lang.String getDatset() {
        return this.datset;
    }

    public void setDatset(java.lang.String newDatset)  {
        this.datset = newDatset;
    }

}

