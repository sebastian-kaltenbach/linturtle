//
// This file was generated by the Eclipse Implementation of JAXB, v3.0.0 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2023.07.19 at 08:42:29 PM CEST 
//


package org.omg.spec.bpmn._20100524.model;

import javax.xml.namespace.QName;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for tEscalationEventDefinition complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tEscalationEventDefinition"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.omg.org/spec/BPMN/20100524/MODEL}tEventDefinition"&gt;
 *       &lt;attribute name="escalationRef" type="{http://www.w3.org/2001/XMLSchema}QName" /&gt;
 *       &lt;anyAttribute processContents='lax' namespace='##other'/&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tEscalationEventDefinition")
public class TEscalationEventDefinition
    extends TEventDefinition
{

    @XmlAttribute(name = "escalationRef")
    protected QName escalationRef;

    /**
     * Gets the value of the escalationRef property.
     * 
     * @return
     *     possible object is
     *     {@link QName }
     *     
     */
    public QName getEscalationRef() {
        return escalationRef;
    }

    /**
     * Sets the value of the escalationRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link QName }
     *     
     */
    public void setEscalationRef(QName value) {
        this.escalationRef = value;
    }

}
