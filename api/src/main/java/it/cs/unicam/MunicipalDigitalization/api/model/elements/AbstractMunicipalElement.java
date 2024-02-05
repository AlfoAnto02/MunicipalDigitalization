package it.cs.unicam.MunicipalDigitalization.api.model.elements;

import it.cs.unicam.MunicipalDigitalization.api.model.Municipality;
import it.cs.unicam.MunicipalDigitalization.api.model.actors.IAuthenticatedUser;
import it.cs.unicam.MunicipalDigitalization.api.util.Coordinate;
import it.cs.unicam.MunicipalDigitalization.api.util.ID;

import java.time.Instant;
import java.util.Date;
import java.util.List;

/**
 * This class represents a Municipal Element. A municipal Element is composed
 * by an ID, an AutneticatedUser that create the Element, a creation Date of the Element
 * the coordinate of the Element and the name of the Element.
 */
public abstract class AbstractMunicipalElement implements IMunicipalElement {
    /**
     * The unique identifier of the MunicipalElement.
     */
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
    private final Date creationDate;

    /**
     * The coordinate of the MunicipalElement
     */
    private Coordinate coordinate;

    /**
     * The name of the MunicipalElement
     */
    private String name;

    /**
     * The list of contents of the MunicipalElement
     */
    private List<IContent> listOfContents;

    /**
     * The constructor of the class
     *
     * @param user the Authenticated IUser that create this Element
     */
    public AbstractMunicipalElement(IAuthenticatedUser user, Municipality municipality) {
        this.author = user;
        this.municipality = municipality;
        this.id = new ID();
        this.creationDate = Date.from(Instant.now());
    }

    /**
     * This method is used to get the coordinate of the MunicipalElement
     *
     * @return The coordinate of the MunicipalElement
     */
    public Coordinate getCoordinates() {
        return this.coordinate;
    }

    /**
     * This method is used to set the coordinate of the MunicipalElement
     * The implementation should handle the setting of the coordinate.
     *
     * @param coordinate The new coordinate of the MunicipalElement
     */
    public void setCoordinates(Coordinate coordinate) {
        if (this.coordinate == null) {
            throw new NullPointerException("Coordinate cannot be null");
        }
        this.coordinate.setX(coordinate.getX());
        this.coordinate.setY(coordinate.getY());
    }

    /**
     * This method is used to get the id of the MunicipalElement
     *
     * @return The id of the MunicipalElement
     */
    public ID getId() {
        return this.id;
    }

    /**
     * This method is used to get the name of the MunicipalElement
     *
     * @return The name of the MunicipalElement
     */
    public String getName() {
        return this.name;
    }

    /**
     * Setter for the name of the MunicipalElement
     *
     * @param name The new name of the MunicipalElement
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

    /**
     * Getter for the creation date of the MunicipalElement
     *
     * @return The creation date of the MunicipalElement
     */
    public Date getCreationDate() {
        return this.creationDate;
    }

    /**
     * This method is used to get the list of contents of the Municipal Element.
     *
     * @return The list of contents of the Municipal Element.
     */
    @Override
    public List<IContent> listOfContents() {
        return this.listOfContents;
    }

    /**
     * This method is used to upload an authorized content.
     *
     * @param content The content to upload.
     */
    @Override
    public void uploadContent(IContent content) {
        this.listOfContents.add(content);
    }

    public String getContent(String id) {
        return this.listOfContents.stream().filter(content -> content.getID().equals(id)).findFirst().get().getContent();
    }
    
    public String getContentFullInfo(String id) {
        return "ciao";
    }
}
