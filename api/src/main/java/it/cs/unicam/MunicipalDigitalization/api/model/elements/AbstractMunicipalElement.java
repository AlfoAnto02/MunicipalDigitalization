package it.cs.unicam.MunicipalDigitalization.api.model.elements;

import it.cs.unicam.MunicipalDigitalization.api.model.Municipality;
import it.cs.unicam.MunicipalDigitalization.api.model.actors.AbstractAuthenticatedUser;
import it.cs.unicam.MunicipalDigitalization.api.model.actors.AbstractIUser;
import it.cs.unicam.MunicipalDigitalization.api.model.actors.IAuthenticatedUser;
import it.cs.unicam.MunicipalDigitalization.api.util.Coordinate;
import it.cs.unicam.MunicipalDigitalization.api.util.ElementStatus;
import it.cs.unicam.MunicipalDigitalization.api.util.ID;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * This abstract class represents a Municipal Element. A municipal Element is composed
 * by an ID, an AuthenticatedUser that create the Element, a creation Date of the Element
 * the coordinate of the Element and the name of the Element.
 */
@MappedSuperclass
@Getter
@Setter
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class AbstractMunicipalElement implements IMunicipalElement {
    /**
     * The unique identifier of the MunicipalElement.
     */
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Municipality of the Element
     */
    @ManyToOne
    @JoinColumn(name = "municipality",nullable = false)
    private Municipality municipality;
    /**
     * The date when the point MunicipalElement was created
     */
    @Getter
    @Column(name = "Creation Date")
    @CreatedDate
    private LocalDateTime creationDate;
    /**
     * The status of the Element, if it is Pending or Published.
     */
    @Getter
    @Column(name = "Status")
    private ElementStatus elementStatus;
    /**
     * The coordinate of the MunicipalElement
     */
    @Transient
    private Coordinate coordinate;

    /**
     * The name of the MunicipalElement
     */
    @Getter
    @Column(name = "Name", nullable = false)
    private String name;

    /**
     * The list of contents of the MunicipalElement
     */
    @OneToMany
    private List<AbstractContent> listOfContents;

    /**
     * Constructor for the AbstractMunicipalElement class.
     *
     */
    public AbstractMunicipalElement() {

    }

    /**
     * Getter for the coordinates of the MunicipalElement.
     *
     * @return The coordinate of the MunicipalElement
     */
    public Coordinate getCoordinates() {
        return this.coordinate;
    }

    /**
     * Setter for the coordinates of the MunicipalElement.
     *
     * @param coordinate The new coordinate of the MunicipalElement
     * @throws NullPointerException if the provided coordinate is null
     */
    public void setCoordinates(Coordinate coordinate) {
        if (this.coordinate == null) {
            throw new NullPointerException("Coordinate cannot be null");
        }
        this.coordinate.setX(coordinate.getX());
        this.coordinate.setY(coordinate.getY());
    }

    /**
     * Setter for the status of the MunicipalElement.
     *
     * @param elementStatus The new status of the MunicipalElement
     * @throws IllegalArgumentException if the provided status is null
     */
    public void setElementStatus(ElementStatus elementStatus) {
        if (elementStatus == null) throw new IllegalArgumentException("Element status invalid");
        this.elementStatus = elementStatus;
    }

    /**
     * Setter for the name of the MunicipalElement.
     *
     * @param name The new name of the MunicipalElement
     * @throws NullPointerException if the provided name is null or empty
     */
    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new NullPointerException("Name cannot be null");
        }
        this.name = name;
    }

    @Override
    public List<AbstractContent> listOfContents() {
        return this.listOfContents;
    }

    @Override
    public void uploadContent(AbstractContent content) {
        this.listOfContents.add(content);
    }

    /**
     * Method to get a specific content from the MunicipalElement by its ID.
     *
     * @param id The ID of the content to retrieve.
     * @return The content as a string.
     */
    public String getContent(String id) {
        return this.listOfContents.stream().filter(content -> content.getID().equals(id)).findFirst().get().getContent();
    }

    /**
     * Method to get full information about a specific content from the MunicipalElement by its ID.
     *
     * @param id The ID of the content to retrieve.
     */
    public void getContentFullInfo(String id) {
        // TODO - implement AbstractMunicipalElement.getContentFullInfo
        throw new UnsupportedOperationException();
    }
}