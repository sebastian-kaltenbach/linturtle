//
// This file was generated by the Eclipse Implementation of JAXB, v3.0.0 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2023.07.19 at 08:42:29 PM CEST 
//


package org.omg.spec.bpmn._20100524.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for tRootElement complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tRootElement"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.omg.org/spec/BPMN/20100524/MODEL}tBaseElement"&gt;
 *       &lt;anyAttribute processContents='lax' namespace='##other'/&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tRootElement")
@XmlSeeAlso({
    TCategory.class,
    TCorrelationProperty.class,
    TDataStore.class,
    TEndPoint.class,
    TError.class,
    TEscalation.class,
    TCollaboration.class,
    TInterface.class,
    TItemDefinition.class,
    TMessage.class,
    TPartnerEntity.class,
    TPartnerRole.class,
    TCallableElement.class,
    TResource.class,
    TSignal.class,
    TEventDefinition.class
})
public abstract class TRootElement
    extends TBaseElement
{


}
