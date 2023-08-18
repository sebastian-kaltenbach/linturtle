//
// This file was generated by the Eclipse Implementation of JAXB, v3.0.0 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2023.07.20 at 09:28:47 PM CEST 
//


package de.dsheng.linturtle.domain.model.omg.spec.bpmn._20100524.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for tParticipantMultiplicity complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tParticipantMultiplicity"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.omg.org/spec/BPMN/20100524/MODEL}tBaseElement"&gt;
 *       &lt;attribute name="minimum" type="{http://www.w3.org/2001/XMLSchema}int" default="0" /&gt;
 *       &lt;attribute name="maximum" type="{http://www.w3.org/2001/XMLSchema}int" default="1" /&gt;
 *       &lt;anyAttribute processContents='lax' namespace='##other'/&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tParticipantMultiplicity")
public class TParticipantMultiplicity
    extends TBaseElement
{

    @XmlAttribute(name = "minimum")
    protected Integer minimum;
    @XmlAttribute(name = "maximum")
    protected Integer maximum;

    /**
     * Gets the value of the minimum property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public int getMinimum() {
        if (minimum == null) {
            return  0;
        } else {
            return minimum;
        }
    }

    /**
     * Sets the value of the minimum property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMinimum(Integer value) {
        this.minimum = value;
    }

    /**
     * Gets the value of the maximum property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public int getMaximum() {
        if (maximum == null) {
            return  1;
        } else {
            return maximum;
        }
    }

    /**
     * Sets the value of the maximum property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMaximum(Integer value) {
        this.maximum = value;
    }

}