package de.dsheng.linturtle.domain.model.config;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "operation")
public class TXmlOperation {
    private String type;
    private String value;

    public TXmlOperation() {

    }

    public TXmlOperation(final String type, final String value) {
        this.type = type;
        this.value = value;
    }

    @XmlAttribute(name = "type", required = true)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @XmlAttribute(name = "value", required = true)
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
