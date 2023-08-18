//
// This file was generated by the Eclipse Implementation of JAXB, v3.0.0 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2023.07.20 at 09:28:47 PM CEST 
//


package de.dsheng.linturtle.domain.model.omg.spec.bpmn._20100524.di;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ParticipantBandKind.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <pre>
 * &lt;simpleType name="ParticipantBandKind"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="top_initiating"/&gt;
 *     &lt;enumeration value="middle_initiating"/&gt;
 *     &lt;enumeration value="bottom_initiating"/&gt;
 *     &lt;enumeration value="top_non_initiating"/&gt;
 *     &lt;enumeration value="middle_non_initiating"/&gt;
 *     &lt;enumeration value="bottom_non_initiating"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "ParticipantBandKind")
@XmlEnum
public enum ParticipantBandKind {

    @XmlEnumValue("top_initiating")
    TOP_INITIATING("top_initiating"),
    @XmlEnumValue("middle_initiating")
    MIDDLE_INITIATING("middle_initiating"),
    @XmlEnumValue("bottom_initiating")
    BOTTOM_INITIATING("bottom_initiating"),
    @XmlEnumValue("top_non_initiating")
    TOP_NON_INITIATING("top_non_initiating"),
    @XmlEnumValue("middle_non_initiating")
    MIDDLE_NON_INITIATING("middle_non_initiating"),
    @XmlEnumValue("bottom_non_initiating")
    BOTTOM_NON_INITIATING("bottom_non_initiating");
    private final String value;

    ParticipantBandKind(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ParticipantBandKind fromValue(String v) {
        for (ParticipantBandKind c: ParticipantBandKind.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}