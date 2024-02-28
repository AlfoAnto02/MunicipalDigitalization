package it.cs.unicam.MunicipalDigitalization.api.model.elements;

import com.fasterxml.jackson.annotation.JsonBackReference;
import it.cs.unicam.MunicipalDigitalization.api.model.users.AbstractAuthenticatedUser;
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
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ReferredPOI", nullable = true)
    @JsonBackReference
    private AbstractPOI referredPOI;

    /**
     * The name of the content.
     */
    @Column(name = "Name", nullable = false)
    private String name;

    /**
     * The municipal element referred by the content.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ReferredItinerary", nullable = true)
    @JsonBackReference
    private AbstractItinerary referredItinerary;

    /**
     * The author of the content.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Author", nullable = false)
    @JsonBackReference
    private AbstractAuthenticatedUser author;

    /**
     * The status of the Element, if it is Pending or Published.
     */
    @Getter
    @Column(name = "Status", nullable = false)
    private ElementStatus elementStatus;

    /**
     * The type of the content.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "Content Type", nullable = false)
    private ContentType type;

    /**
     * Type to fill
     */
    @Column(name = "Photo", nullable = true)
    private String content;

    public AbstractContent() {

    }

    /**
     * Constructor for the AbstractContent class used by the builder.
     * It has a referredPOI, name, author, elementStatus, type, description, link, photo.
     *
     * @param referredPOI the municipal element referred by the content.
     * @param name        the name of the content.
     * @param author      the author of the content.
     * @param type        the type of the content.
     * @param content     the content
     */
    public AbstractContent(AbstractPOI referredPOI, String name,
                           AbstractAuthenticatedUser author, ContentType type,
                           String content) {
        this.referredPOI = referredPOI;
        this.name = name;
        this.author = author;
        this.type = type;
        this.content = content;
    }

    /**
     * Constructor for the AbstractContent class used by the builder.
     * It has a referredItinerary, name, author, elementStatus, type, description, link, photo.
     *
     * @param referredItinerary the municipal element referred by the content.
     * @param name              the name of the content.
     * @param author            the author of the content.
     * @param type              the type of the content.
     * @param content           the content.
     */

    public AbstractContent(String name, AbstractItinerary referredItinerary, AbstractAuthenticatedUser author,
                           ContentType type, String content) {
        this.name = name;
        this.referredItinerary = referredItinerary;
        this.author = author;
        this.type = type;
        this.content = content;
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
    public void setContent(String content) {
        this.content = content;
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
}