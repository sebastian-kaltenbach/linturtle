//
// This file was generated by the Eclipse Implementation of JAXB, v3.0.0 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2023.07.19 at 08:42:29 PM CEST 
//


package org.omg.spec.bpmn._20100524.di;

import javax.xml.namespace.QName;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.omg.spec.dd._20100524.di.LabeledShape;


/**
 * <p>Java class for BPMNShape complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BPMNShape"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.omg.org/spec/DD/20100524/DI}LabeledShape"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{http://www.omg.org/spec/BPMN/20100524/DI}BPMNLabel" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="bpmnElement" type="{http://www.w3.org/2001/XMLSchema}QName" /&gt;
 *       &lt;attribute name="isHorizontal" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *       &lt;attribute name="isExpanded" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *       &lt;attribute name="isMarkerVisible" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *       &lt;attribute name="isMessageVisible" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *       &lt;attribute name="participantBandKind" type="{http://www.omg.org/spec/BPMN/20100524/DI}ParticipantBandKind" /&gt;
 *       &lt;attribute name="choreographyActivityShape" type="{http://www.w3.org/2001/XMLSchema}QName" /&gt;
 *       &lt;anyAttribute processContents='lax' namespace='##other'/&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BPMNShape", propOrder = {
    "bpmnLabel"
})
public class BPMNShape
    extends LabeledShape
{

    @XmlElement(name = "BPMNLabel")
    protected BPMNLabel bpmnLabel;
    @XmlAttribute(name = "bpmnElement")
    protected QName bpmnElement;
    @XmlAttribute(name = "isHorizontal")
    protected Boolean isHorizontal;
    @XmlAttribute(name = "isExpanded")
    protected Boolean isExpanded;
    @XmlAttribute(name = "isMarkerVisible")
    protected Boolean isMarkerVisible;
    @XmlAttribute(name = "isMessageVisible")
    protected Boolean isMessageVisible;
    @XmlAttribute(name = "participantBandKind")
    protected ParticipantBandKind participantBandKind;
    @XmlAttribute(name = "choreographyActivityShape")
    protected QName choreographyActivityShape;

    /**
     * Gets the value of the bpmnLabel property.
     * 
     * @return
     *     possible object is
     *     {@link BPMNLabel }
     *     
     */
    public BPMNLabel getBPMNLabel() {
        return bpmnLabel;
    }

    /**
     * Sets the value of the bpmnLabel property.
     * 
     * @param value
     *     allowed object is
     *     {@link BPMNLabel }
     *     
     */
    public void setBPMNLabel(BPMNLabel value) {
        this.bpmnLabel = value;
    }

    /**
     * Gets the value of the bpmnElement property.
     * 
     * @return
     *     possible object is
     *     {@link QName }
     *     
     */
    public QName getBpmnElement() {
        return bpmnElement;
    }

    /**
     * Sets the value of the bpmnElement property.
     * 
     * @param value
     *     allowed object is
     *     {@link QName }
     *     
     */
    public void setBpmnElement(QName value) {
        this.bpmnElement = value;
    }

    /**
     * Gets the value of the isHorizontal property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsHorizontal() {
        return isHorizontal;
    }

    /**
     * Sets the value of the isHorizontal property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsHorizontal(Boolean value) {
        this.isHorizontal = value;
    }

    /**
     * Gets the value of the isExpanded property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsExpanded() {
        return isExpanded;
    }

    /**
     * Sets the value of the isExpanded property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsExpanded(Boolean value) {
        this.isExpanded = value;
    }

    /**
     * Gets the value of the isMarkerVisible property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsMarkerVisible() {
        return isMarkerVisible;
    }

    /**
     * Sets the value of the isMarkerVisible property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsMarkerVisible(Boolean value) {
        this.isMarkerVisible = value;
    }

    /**
     * Gets the value of the isMessageVisible property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsMessageVisible() {
        return isMessageVisible;
    }

    /**
     * Sets the value of the isMessageVisible property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsMessageVisible(Boolean value) {
        this.isMessageVisible = value;
    }

    /**
     * Gets the value of the participantBandKind property.
     * 
     * @return
     *     possible object is
     *     {@link ParticipantBandKind }
     *     
     */
    public ParticipantBandKind getParticipantBandKind() {
        return participantBandKind;
    }

    /**
     * Sets the value of the participantBandKind property.
     * 
     * @param value
     *     allowed object is
     *     {@link ParticipantBandKind }
     *     
     */
    public void setParticipantBandKind(ParticipantBandKind value) {
        this.participantBandKind = value;
    }

    /**
     * Gets the value of the choreographyActivityShape property.
     * 
     * @return
     *     possible object is
     *     {@link QName }
     *     
     */
    public QName getChoreographyActivityShape() {
        return choreographyActivityShape;
    }

    /**
     * Sets the value of the choreographyActivityShape property.
     * 
     * @param value
     *     allowed object is
     *     {@link QName }
     *     
     */
    public void setChoreographyActivityShape(QName value) {
        this.choreographyActivityShape = value;
    }

}