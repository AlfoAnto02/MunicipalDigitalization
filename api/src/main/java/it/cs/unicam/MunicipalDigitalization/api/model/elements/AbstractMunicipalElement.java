package it.cs.unicam.MunicipalDigitalization.api.model.elements;

import it.cs.unicam.MunicipalDigitalization.api.model.Municipality;
import it.cs.unicam.MunicipalDigitalization.api.model.actors.IAuthenticatedUser;
import it.cs.unicam.MunicipalDigitalization.api.util.Coordinate;
import it.cs.unicam.MunicipalDigitalization.api.util.ElementStatus;
import it.cs.unicam.MunicipalDigitalization.api.util.ID;
import lombok.Getter;

import java.time.Instant;
import java.util.Date;
import java.util.List;

/**
 * This abstract class represents a Municipal Element. A municipal Element is composed
 * by an ID, an AuthenticatedUser that create the Element, a creation Date of the Element
 * the coordinate of the Element and the name of the Element.
 */
public abstract class AbstractMunicipalElement implements IMunicipalElement {
    /**
     * The unique identifier of the MunicipalElement.
     */
    @Getter
    private final ID id;

    /**
     * Municipality of the Element
     */
    private final Municipality municipality;
    /**
     * The author of the point of the MunicipalElement
     */
    private final IAuthenticatedUser author;
    /**
     * The date when the point MunicipalElement was created
     */
    @Getter
    private final Date creationDate;
    /**
     * The status of the Element, if it is Pending or Published.
     */
    @Getter
    private ElementStatus elementStatus;
    /**
     * The coordinate of the MunicipalElement
     */
    private Coordinate coordinate;

    /**
     * The name of the MunicipalElement
     */
    @Getter
    private String name;

    /**
     * The list of contents of the MunicipalElement
     */
    private List<IContent> listOfContents;

    /**
     * Constructor for the AbstractMunicipalElement class.
     *
     * @param user         the Authenticated IUser that create this Element
     * @param municipality the Municipality where this Element is located
     */
    public AbstractMunicipalElement(IAuthenticatedUser user, Municipality municipality) {
        this.author = user;
        this.municipality = municipality;
        this.id = new ID();
        this.creationDate = Date.from(Instant.now());
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

    /**
     * Getter for the author of the MunicipalElement.
     *
     * @return The author of the MunicipalElement
     */
    public IAuthenticatedUser getUser() {
        return this.author;
    }

    @Override
    public List<IContent> listOfContents() {
        return this.listOfContents;
    }

    @Override
    public void uploadContent(IContent content) {
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