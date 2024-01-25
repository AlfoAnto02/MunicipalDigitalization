package it.cs.unicam.MunicipalDigitalization.model;

import it.cs.unicam.MunicipalDigitalization.util.ContentType;

/**
 * This abstract class represents a general content.
 * It implements the IContent interface.
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
    IMunicipalElements getReferredMunicipalElement();
}
