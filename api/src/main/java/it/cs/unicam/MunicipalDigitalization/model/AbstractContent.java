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

    private String description;

    private String link;

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

    @Override
    public String getID() {
        return this.id.toString();
    }

    @Override
    public ContentType getType() {
        return this.type;
    }

    @Override
    public void setType(ContentType type) {
        if (type == null) throw new IllegalArgumentException("Content type is null");
        this.type = type;
    }

    @Override
    public IMunicipalElements getReferredMunicipalElement() {
        return this.referredMunicipalElement;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
