package it.cs.unicam.MunicipalDigitalization.model;

import it.cs.unicam.MunicipalDigitalization.util.ContentType;
import it.cs.unicam.MunicipalDigitalization.util.ID;

/**
 * This abstract class represents a general content.
 * It implements the IContent interface.
 * A content has an id, type, author, and a municipal element referred by the content.
 */
public abstract class AbstractContent implements IContent {

    /**
     * The id of the content.
     */
    private final ID id;

    /**
     * The municipal element referred by the content.
     */
    private final IMunicipalElements referredMunicipalElement;

    /**
     * The author of the content.
     */
    private final AuthenticatedUser author;

    /**
     * The type of the content.
     */
    private ContentType type;

    /**
     * Description type to fill
     */
    private String description;

    /**
     * Link Type to fill
     */
    private String link;

    /**
     * Photo type to fill
     */
    private String photo;

    /**
     * Constructor for the AbstractContent class.
     *
     * @param author           The author of the content.
     * @param municipalElement The municipal element referred by the content.
     */
    public AbstractContent(AuthenticatedUser author, IMunicipalElements municipalElement) {
        this.author = author;
        this.referredMunicipalElement = municipalElement;
        this.id = new ID();
    }

    /**
     *
     * @return the ID of the content
     */
    @Override
    public String getID() {
        return this.id.toString();
    }

    /**
     *
     * @return the Type of the Content
     */
    @Override
    public ContentType getType() {
        return this.type;
    }

    /**
     *
     * @param type The new type of the content.
     */
    @Override
    public void setType(ContentType type) {
        if (type == null) throw new IllegalArgumentException("Content type is null");
        this.type = type;
    }

    /**
     *
     * @return the referred Municipal Element of this Content
     */
    @Override
    public IMunicipalElements getReferredMunicipalElement() {
        return this.referredMunicipalElement;
    }

    /**
     *
     * @param description The new description of the content.
     */
    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @param link The new link of the content.
     */
    @Override
    public void setLink(String link) {
        this.link = link;
    }

    /**
     *
     * @param photo The new photo of the content.
     */
    @Override
    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
