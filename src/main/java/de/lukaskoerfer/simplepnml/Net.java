package de.lukaskoerfer.simplepnml;

import lombok.*;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

/**
 * Represents a place/transition net
 */
@Builder
@EqualsAndHashCode
public class Net implements Identifiable, Named, Collectable {

    /**
     * Defines the identifier for Place-Transition (PT) nets
     */
    public static final String PLACE_TRANSITION_TYPE = "http://www.pnml.org/version-2009/grammar/ptnet";

    /**
     * -- GETTER --
     * Gets the identifier
     * @return The identifier
     */
    @Getter
    @XmlAttribute(name = "id", required = true)
    private String id;

    /**
     * -- GETTER --
     * Gets the net type
     * @return
     */
    @Getter
    @XmlAttribute(name = "type", required = true)
    private String type;

    /**
     * -- GETTER --
     * Gets the label containing the name
     * @return A label containing the name
     * -- SETTER --
     * Sets the label containing the name
     * @param name A label containing the name
     */
    @Getter @Setter
    @XmlElement(name = "name")
    private Label name;

    /**
     * -- GETTER --
     * Gets the pages of the net
     * @return A list of pages
     */
    @Getter @Setter
    @Singular
    @XmlElement(name = "page")
    private List<Page> pages;

    /**
     * -- GETTER --
     * Gets tool-specific data related to the net
     * @return A list of tool data definitions
     */
    @Getter @Setter
    @Singular("data")
    @XmlElement(name = "toolspecific")
    private List<ToolData> toolData;

    /**
     * Creates a new net using a random identifier
     */
    public Net() {
        this(null);
    }

    /**
     * Creates a new net
     * @param id An unique identifier, defaults to a random UUID if null, empty or whitespace
     */
    public Net(String id) {
        this(id, null);
    }

    /**
     * Creates a new net
     * @param id An unique identifier, defaults to a random UUID if null, empty or whitespace
     * @param type
     */
    public Net(String id, String type) {
        setId(id);
        setType(type);
        setPages(new ArrayList<>());
        setToolData(new ArrayList<>());
    }

    // Internal constructor for builder
    private Net(String id, String type, Label name, List<Page> pages, List<ToolData> toolData) {
        setId(id);
        setType(type);
        setName(name);
        setPages(new ArrayList<>(pages));
        setToolData(new ArrayList<>(toolData));
    }

    /**
     * Sets the identifier, defaults to a random UUID if null, empty or whitespace
     * @param id An unique identifier, defaults to a random UUID if null, empty or whitespace
     */
    public void setId(String id) {
        this.id = id != null ? id : UUID.randomUUID().toString();
    }

    /**
     * Sets the type of this net, defaults to a Place-Transition (PT) net if null, empty or whitespace
     * @param type A string containing a net type identifier
     */
    public void setType(String type) {
        this.type = type != null ? type : PLACE_TRANSITION_TYPE;
    }

    /**
     *
     * @return
     */
    @Override
    public Stream<Collectable> collect() {
        return Collector.create(this)
            .collect(getName())
            .collect(getPages())
            .collect(getToolData())
            .build();
    }
}
