package it.cs.unicam.MunicipalDigitalization.api.model.elements;

import it.cs.unicam.MunicipalDigitalization.api.util.ContentType;
import it.cs.unicam.MunicipalDigitalization.api.util.ElementStatus;

/**
 * This interface represents a general content.
 * A content has an id, type, author, and a municipal element referred by the content.
 */
public interface IContent {

    /**
     * Getter for the type of the content.
     *
     * @return The type of the content.
     */
    ContentType getType();

    /**
     * Setter for the type of the content.
     *
     * @param type The new type of the content.
     */
    void setType(ContentType type);

    /**
     * Getter for the ID of the content.
     *
     * @return The ID of the content.
     */
    String getID();

    /**
     * Setter for the description of the content.
     *
     * @param description The new description of the content.
     */
    void setDescription(String description);

    /**
     * Setter for the link of the content.
     *
     * @param link The new link of the content.
     */
    void setLink(String link);

    /**
     * Setter for the photo of the content.
     *
     * @param photo The new photo of the content.
     */
    void setPhoto(String photo);

    /**
     * Getter for the Referred Municipal Element of the content.
     *
     * @return The Referred Municipal Element of the content.
     */
    AbstractPOI getReferredPOI();

    /**
     * Getter for the Referred POI of the content.
     *
     * @return The Referred Itinerary of the content.
     */
    AbstractItinerary getReferredItinerary();

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
     * This method is used to get the content description and type.
     *
     * @return A string representation of the content description and type.
     */
    String getContent();
}