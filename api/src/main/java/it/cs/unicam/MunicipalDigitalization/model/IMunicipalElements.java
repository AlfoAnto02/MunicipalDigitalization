package it.cs.unicam.MunicipalDigitalization.model;

import it.cs.unicam.MunicipalDigitalization.util.Coordinates;
import it.cs.unicam.MunicipalDigitalization.util.ID;

import java.util.Date;
import java.util.List;

/**
 * This interface represents the contract of a Municipal Elements.
 * A class needs to implement this interface to be a Municipal Element such as
 * a POI or an Itinerary.
 */
public interface IMunicipalElements {

    /**
     * Getter for the creation date of the Municipal Element.
     *
     * @return The creation date of the Municipal Element.
     */
    Date getCreationDate();

    /**
     * Getter for the author of the Municipal Element.
     *
     * @return The author of the Municipal Element.
     */
    AuthenticatedUser getUser();

    /**
     * This method is used to get the coordinates of the Municipal Element.
     *
     * @return The coordinates of the Municipal Element.
     */
    Coordinates getCoordinates();

    /**
     * This method is used to set the coordinates of the Municipal Element.
     * The implementation should handle the setting of the coordinates.
     *
     * @param cc The new coordinates of the Municipal Element.
     */
    void setCoordinates(Coordinates cc);

    /**
     * This method is used to get the id of the Municipal Element.
     *
     * @return The id of the Municipal Element.
     */
    ID getId();

    /**
     * This method is used to get the name of the Municipal Element.
     *
     * @return The name of the Municipal Element.
     */
    String getName();

    /**
     * This method is used to set the name of the Municipal Element.
     *
     * @param name The new name of the Municipal Element.
     */
    void setName(String name);

    /**
     * This method is used to get the list of contents of the Municipal Element.
     *
     * @return The list of contents of the Municipal Element.
     */
    List<IContent> listOfContents();

    /**
     * This method is used to upload an authorized content.
     *
     * @param content The content to upload.
     */
    void uploadContent(IContent content);
}
