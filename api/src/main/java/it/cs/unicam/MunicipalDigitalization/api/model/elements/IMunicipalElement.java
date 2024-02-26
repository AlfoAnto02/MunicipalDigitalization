package it.cs.unicam.MunicipalDigitalization.api.model.elements;

import it.cs.unicam.MunicipalDigitalization.api.model.actors.IAuthenticatedUser;
import it.cs.unicam.MunicipalDigitalization.api.util.Coordinate;
import it.cs.unicam.MunicipalDigitalization.api.util.ElementStatus;

import java.time.LocalDateTime;
import java.util.List;

/**
 * This interface represents the contract of a Municipal Elements.
 * A class needs to implement this interface to be a Municipal Element such as
 * a POI or an Itinerary.
 */
public interface IMunicipalElement {

    /**
     * Getter for the creation date of the Municipal Element.
     *
     * @return The creation date of the Municipal Element.
     */
    LocalDateTime getCreationDate();

    /**
     * Getter for the author of the Municipal Element.
     *
     * @return The author of the Municipal Element.
     */
    IAuthenticatedUser getUser();

    /**
     * This method is used to get the coordinates of the Municipal Element.
     *
     * @return The coordinates of the Municipal Element.
     */
    Coordinate getCoordinates();

    /**
     * This method is used to set the coordinates of the Municipal Element.
     * The implementation should handle the setting of the coordinates.
     *
     * @param cc The new coordinates of the Municipal Element.
     */
    void setCoordinates(Coordinate cc);

    /**
     * This method is used to get the id of the Municipal Element.
     *
     * @return The id of the Municipal Element.
     */
    Long getId();

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
    List<AbstractContent> listOfContents();

    /**
     * This method is used to get the status of the Element
     *
     * @return the status of the Element
     */
    ElementStatus getElementStatus();

    /**
     * This method is used to set the status of the Element, Pending or Published.
     *
     * @param status status of the Element
     */
    void setElementStatus(ElementStatus status);

    /**
     * This method is used to upload an authorized content.
     *
     * @param content The content to upload.
     */
    void uploadContent(AbstractContent content);


    /**
     * Get the content full info of the municipal element using its id.
     *
     * @param id of the content
     */
    void getContentFullInfo(String id);
}
