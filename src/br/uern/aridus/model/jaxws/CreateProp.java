
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

@XmlRootElement(name = "createProp", namespace = "http://model.aridus.uern.br/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "createProp", namespace = "http://model.aridus.uern.br/", propOrder = {"dataset", "prop", "value"})

public class CreateProp {

    @XmlElement(name = "dataset")
    private java.lang.String dataset;
    @XmlElement(name = "prop")
    private java.lang.String prop;
    @XmlElement(name = "value")
    private java.lang.String value;

    public java.lang.String getDataset() {
        return this.dataset;
    }

    public void setDataset(java.lang.String newDataset)  {
        this.dataset = newDataset;
    }

    public java.lang.String getProp() {
        return this.prop;
    }

    public void setProp(java.lang.String newProp)  {
        this.prop = newProp;
    }

    public java.lang.String getValue() {
        return this.value;
    }

    public void setValue(java.lang.String newValue)  {
        this.value = newValue;
    }

}

