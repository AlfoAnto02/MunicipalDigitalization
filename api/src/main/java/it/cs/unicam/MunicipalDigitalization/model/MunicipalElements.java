package it.cs.unicam.MunicipalDigitalization.model;

import it.cs.unicam.MunicipalDigitalization.util.Coordinates;
import it.cs.unicam.MunicipalDigitalization.util.ID;

import java.time.Instant;
import java.util.Date;
import java.util.List;

/**
 * This class represents a Municipal Element. A municipal Element is composed
 * by an ID, an AutneticatedUser that create the Element, a creation Date of the Element
 * the coordinates of the Element and the name of the Element.
 *
 */
public abstract class MunicipalElements implements IMunicipalElements {

    /**
     * Municipality of the Element
     */
    private final Municipality municipality;
    /**
     * The unique identifier of the MunicipalElement.
     */
    private final ID id;

    /**
     * The author of the point of the MunicipalElement
     */
    private final AuthenticatedUser author;

    /**
     * The date when the point MunicipalElement was created
     */
    private final Date creationDate;

    /**
     * The coordinates of the MunicipalElement
     */
    private Coordinates coordinates;

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
     * @param user the Authenticated User that create this Element
     */
    public MunicipalElements(AuthenticatedUser user, Municipality municipality){
        this.author=user;
        this.municipality=municipality;
        this.id = new ID();
        this.creationDate = Date.from(Instant.now());
    }

    /**
     * This method is used to get the coordinates of the MunicipalElement
     *
     * @return The coordinates of the MunicipalElement
     */
    public Coordinates getCoordinates() {
        return this.coordinates;
    }

    /**
     * This method is used to set the coordinates of the MunicipalElement
     * The implementation should handle the setting of the coordinates.
     *
     * @param coordinates The new coordinates of the MunicipalElement
     */
    public void setCoordinates(Coordinates coordinates) {
        if (this.coordinates == null) {
            throw new NullPointerException("Coordinates cannot be null");
        }
        this.coordinates.setX(coordinates.getX());
        this.coordinates.setY(coordinates.getY());
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
     * This method is used to get the name of the MunicipalElement
     *
     * @return The name of the MunicipalElement
     */
    public String getName(){
        return this.name;
    }

    /**
     * Getter for the author of the MunicipalElement.
     *
     * @return The author of the MunicipalElement
     */
    public AuthenticatedUser getUser(){
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
     * @param content The content to upload.
     */
    @Override
    public void uploadContent(IContent content){
        this.listOfContents.add(content);
    }

}
