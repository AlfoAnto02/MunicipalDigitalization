package it.cs.unicam.MunicipalDigitalization.api.model.elements;

import it.cs.unicam.MunicipalDigitalization.api.model.actors.AbstractAuthenticatedUser;
import it.cs.unicam.MunicipalDigitalization.api.util.ContentType;
import it.cs.unicam.MunicipalDigitalization.api.util.ElementStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * This abstract class represents a general content.
 * It implements the IContent interface.
 * A content has an id, type, author, and a municipal element referred by the content.
 */
@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(
        name = "Contents",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "Identification",
                        columnNames = "id"
                )
        }
)
public abstract class AbstractContent implements IContent {

    /**
     * The id of the content.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The municipal element referred by the content.
     */
    @ManyToOne
    @JoinColumn(name = "ReferredPOI",nullable = true)
    private AbstractPOI referredPOI;

    /**
     * The name of the content.
     */
    @Column(name = "Name", nullable = false)
    private String name;

    /**
     * The municipal element referred by the content.
     */
    @ManyToOne
    @JoinColumn(name = "ReferredItinerary",nullable = true)
    private AbstractItinerary referredItinerary;

    /**
     * The author of the content.
     */
    @ManyToOne
    @JoinColumn(name = "Author",nullable = false)
    private AbstractAuthenticatedUser author;

    /**
     * The status of the Element, if it is Pending or Published.
     */
    @Getter
    @Column(name = "Status",nullable = false)
    private ElementStatus elementStatus;

    /**
     * The type of the content.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "Content Type", nullable = false)
    private ContentType type;

    /**
     * Description type to fill
     */
    @Column(name = "Description", nullable = true)
    private String description;

    /**
     * Link POIType to fill
     */
    @Column(name = "Link", nullable = true)
    private String link;

    /**
     * Photo type to fill
     */
    @Column(name = "Photo", nullable = true)
    private String photo;

    public AbstractContent() {

    }

    /**
     * Constructor for the AbstractContent class used by the builder.
     * It has a referredPOI, name, author, elementStatus, type, description, link, photo.
     *
     * @param referredPOI the municipal element referred by the content.
     * @param name the name of the content.
     * @param author the author of the content.
     * @param type the type of the content.
     * @param description the description of the content.
     * @param link the link of the content.
     * @param photo the photo of the content.
     */
    public AbstractContent(AbstractPOI referredPOI, String name,
                           AbstractAuthenticatedUser author, ContentType type,
                           String description, String link, String photo) {
        this.referredPOI = referredPOI;
        this.name = name;
        this.author = author;
        this.type = type;
        this.description = description;
        this.link = link;
        this.photo = photo;
    }

    /**
     * Constructor for the AbstractContent class used by the builder.
     * It has a referredItinerary, name, author, elementStatus, type, description, link, photo.
     *
     * @param referredItinerary the municipal element referred by the content.
     * @param name the name of the content.
     * @param author the author of the content.
     * @param type the type of the content.
     * @param description the description of the content.
     * @param link the link of the content.
     * @param photo the photo of the content.
     */

    public AbstractContent(String name, AbstractItinerary referredItinerary, AbstractAuthenticatedUser author,
                           ContentType type, String description, String link, String photo) {
        this.name = name;
        this.referredItinerary = referredItinerary;
        this.author = author;
        this.type = type;
        this.description = description;
        this.link = link;
        this.photo = photo;
    }

    @Override
    public ContentType getType() {
        return this.type;
    }

    @Override
    public void setType(ContentType type) {
        if (type == null) throw new IllegalArgumentException("Content type is null");
        this.type = type;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public void setPhoto(String photo) {
        this.photo = photo;
    }

    /**
     * set the status of the Element
     *
     * @param elementStatus status to set
     */
    public void setElementStatus(ElementStatus elementStatus) {
        if (elementStatus == null) throw new IllegalArgumentException("Element status invalid");
        this.elementStatus = elementStatus;
    }

    /**
     * Method to get the content description and type.
     *
     * @return A string representation of the content description and type.
     */
    public String getContent() {
        return "Content: " + this.description + " " + this.getType();
    }
}