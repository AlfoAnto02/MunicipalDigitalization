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
     * Setter for the photo of the content.
     *
     * @param photo The new photo of the content.
     */
    void setContent(String photo);

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
}