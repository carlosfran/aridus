
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

@XmlRootElement(name = "updateProp", namespace = "http://model.aridus.uern.br/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "updateProp", namespace = "http://model.aridus.uern.br/", propOrder = {"dataset", "prop", "oldValue", "newValue"})

public class UpdateProp {

    @XmlElement(name = "dataset")
    private java.lang.String dataset;
    @XmlElement(name = "prop")
    private java.lang.String prop;
    @XmlElement(name = "oldValue")
    private java.lang.String oldValue;
    @XmlElement(name = "newValue")
    private java.lang.String newValue;

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

    public java.lang.String getOldValue() {
        return this.oldValue;
    }

    public void setOldValue(java.lang.String newOldValue)  {
        this.oldValue = newOldValue;
    }

    public java.lang.String getNewValue() {
        return this.newValue;
    }

    public void setNewValue(java.lang.String newNewValue)  {
        this.newValue = newNewValue;
    }

}

