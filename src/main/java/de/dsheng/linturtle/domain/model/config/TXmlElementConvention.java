package de.dsheng.linturtle.domain.model.config;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement(name = "elementConvention")
@XmlType(propOrder = { "name", "description", "pattern" })
public class TXmlElementConvention {

    private String name;
    private String description;
    private TXmlOperation operation;

    public TXmlElementConvention() {
    }

    public TXmlElementConvention(final String name,
                                 final String description,
                                 final TXmlOperation operation) {
        super();
        this.name = name;
        this.description = description;
        this.operation = operation;
    }

    @XmlElement(name = "name", required = true)
    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @XmlElement(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    @XmlElement(name = "operation", required = true)
    public TXmlOperation getPattern() {
        return operation;
    }

    public void setPattern(final TXmlOperation operation) {
        this.operation = operation;
    }
}
