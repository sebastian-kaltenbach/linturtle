package de.dsheng.linturtle.domain.model.config;

import jakarta.xml.bind.annotation.*;

import java.util.Collection;

import java.util.Collections;

@XmlRootElement(name = "elementConvention")
@XmlType(propOrder = { "name", "description", "operations" })
public class TXmlElementConvention {

    private String name;
    private String description;
    private Collection<TXmlOperation> operations;

    public TXmlElementConvention() {
    }

    public TXmlElementConvention(final String name,
                                 final String description,
                                 final Collection<TXmlOperation> operations) {
        super();
        this.name = name;
        this.description = description;
        this.operations = operations;
    }

    @XmlElement(name = "name", required = true)
    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @XmlElement(name = "description", required = true)
    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    @XmlElementWrapper(name = "operations")
    @XmlElement(name = "operation")
    public Collection<TXmlOperation> getOperations() {
        return operations;
    }

    public void setOperations(final Collection<TXmlOperation> operations) {
        this.operations = operations;
    }
}
