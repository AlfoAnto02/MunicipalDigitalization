package it.cs.unicam.MunicipalDigitalization.api.model.elements;

import it.cs.unicam.MunicipalDigitalization.api.model.actors.IAuthenticatedUser;
import it.cs.unicam.MunicipalDigitalization.api.util.ContentType;
import it.cs.unicam.MunicipalDigitalization.api.util.ElementStatus;
import it.cs.unicam.MunicipalDigitalization.api.util.ID;
import lombok.Getter;

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
    private final IMunicipalElement referredMunicipalElement;
    
    /**
     * The author of the content.
     */
    private final IAuthenticatedUser author;
    
    /**
     * The status of the Element, if it is Pending or Published.
     */
    @Getter
    private ElementStatus elementStatus;
    
    /**
     * The type of the content.
     */
    private ContentType type;

    /**
     * Description type to fill
     */
    private String description;

    /**
     * Link POIType to fill
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
    public AbstractContent(IAuthenticatedUser author, IMunicipalElement municipalElement) {
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
    public IMunicipalElement getReferredMunicipalElement() {
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

    /**
     * set the status of the Element
     *
     * @param elementStatus status to set
     */
    public void setElementStatus(ElementStatus elementStatus) {
        if (elementStatus == null) throw new IllegalArgumentException("Element status invalid");
        this.elementStatus = elementStatus;
    }

    /**
     * Method to get the content description and type.
     *
     * @return A string representation of the content description and type.
     */
    public String getContent() {
        return "Content: " + this.description + " " + this.getType();
    }
}