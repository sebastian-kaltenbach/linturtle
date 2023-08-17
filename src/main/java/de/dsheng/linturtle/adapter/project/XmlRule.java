package de.dsheng.linturtle.adapter.project;

import java.util.Collection;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "rule")
public class XmlRule {

    private String id;
    private String name;
    private String description;
    private Collection<XmlElementConvention> elementConventions;

    public XmlRule() {
    }

    public XmlRule(String id, String name, String description,
            final Collection<XmlElementConvention> elementConventions) {
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
    @XmlElement(name = "elementConvention")
    public Collection<XmlElementConvention> getElementConventions() {
        return elementConventions;
    }

    public void setElementConventions(Collection<XmlElementConvention> elementConventions) {
        this.elementConventions = elementConventions;
    }
}
