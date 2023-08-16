package de.dsheng.linturtle.config;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement(name = "elementConvention")
@XmlType(propOrder = { "name", "description", "pattern" })
public class XmlElementConvention {

    private String name;
    private String description;
    private String pattern;

    public XmlElementConvention() {
    }

    public XmlElementConvention(final String name,
            final String description,
            final String pattern) {
        super();
        this.name = name;
        this.description = description;
        this.pattern = pattern;
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

    @XmlElement(name = "pattern", required = true)
    public String getPattern() {
        return pattern;
    }

    public void setPattern(final String pattern) {
        this.pattern = pattern;
    }
}
