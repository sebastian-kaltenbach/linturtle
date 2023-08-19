package de.dsheng.linturtle.domain.model.config;

import java.util.Collection;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "rule")
public class TXmlRule {

    private String id;
    private String name;
    private String description;
    private Collection<TXmlElementConvention> elementConventions;

    public TXmlRule() {
    }

    public TXmlRule(final String id, final String name, final String description,
                    final Collection<TXmlElementConvention> elementConventions) {
        super();
        this.id = id;
        this.name = name;
        this.description = description;
        this.elementConventions = elementConventions;
    }

    @XmlAttribute(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @XmlElement(name = "name", required = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlElementWrapper(name = "elementConventions")
    @XmlElement(name = "elementConvention", type = TXmlElementConvention.class)
    public Collection<TXmlElementConvention> getElementConventions() {
        return elementConventions;
    }

    public void setElementConventions(Collection<TXmlElementConvention> elementConventions) {
        this.elementConventions = elementConventions;
    }
}
