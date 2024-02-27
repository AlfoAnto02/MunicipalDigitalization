package it.cs.unicam.MunicipalDigitalization.api.util.DesignPattern.Builder;

import it.cs.unicam.MunicipalDigitalization.api.model.elements.AbstractItinerary;
import it.cs.unicam.MunicipalDigitalization.api.model.elements.AbstractMunicipalElement;
import it.cs.unicam.MunicipalDigitalization.api.model.elements.AbstractPOI;
import it.cs.unicam.MunicipalDigitalization.api.model.elements.AuthorizedContent;
import it.cs.unicam.MunicipalDigitalization.api.model.users.AbstractAuthenticatedUser;
import it.cs.unicam.MunicipalDigitalization.api.util.ContentType;
import it.cs.unicam.MunicipalDigitalization.api.util.ElementStatus;
import org.springframework.stereotype.Component;

/**
 * This class represents a builder for an authorized content
 */
@Component
public class AuthorizedContentBuilder implements ContentBuilder {

    /**
     * The author of the content
     */
    private AbstractAuthenticatedUser author;

    /**
     * The name of the content
     */
    private String name;

    /**
     * The type of the content
     */
    private ContentType type;

    /**
     * The photo of the content
     */
    private String photo;

    /**
     * The link of the content
     */
    private String link;

    /**
     * The description of the content
     */
    private String description;

    /**
     * The referred POI
     */
    private AbstractPOI referredPOI;

    /**
     * The referred itinerary
     */
    private AbstractItinerary referredItinerary;

    /**
     * The status of the content
     */
    private ElementStatus contentStatus;

    @Override
    public void setContentAuthor(AbstractAuthenticatedUser author) {
        this.author = author;
    }

    @Override
    public void setContentName(String name) {
        this.name = name;
    }

    @Override
    public void setContentType(ContentType type) {
        this.type = type;
    }

    @Override
    public void setContentPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public void setContentLink(String link) {
        this.link = link;
    }

    @Override
    public void setContentDescription(String description) {
        this.description = description;
    }

    @Override
    public void setContentReferredMunicipalElement(AbstractMunicipalElement element) {
        if (element == null) throw new IllegalArgumentException("Element cannot be null");
        if (element instanceof AbstractPOI) this.referredPOI = (AbstractPOI) element;
        else if (element instanceof AbstractItinerary) this.referredItinerary = (AbstractItinerary) element;
        else throw new IllegalArgumentException("Element must be a POI or an Itinerary");
    }

    @Override
    public void setContentStatus() {
        this.contentStatus = ElementStatus.PUBLISHED;
    }

    /**
     * This method resets the builder
     */
    public void reset() {
        this.author = null;
        this.name = null;
        this.type = null;
        this.photo = null;
        this.link = null;
        this.description = null;
        this.contentStatus = null;
    }

    /**
     * This method builds the authorized content
     *
     * @return the authorized content
     */
    public AuthorizedContent build() {
        if (this.author == null || this.name == null || this.type == null || (this.photo == null && this.link == null && this.description == null) || this.contentStatus == null)
            throw new IllegalArgumentException("All fields must be set");
        else if (this.referredPOI == null && this.referredItinerary == null)
            throw new IllegalArgumentException("Referred element must be set");
        else if (this.referredPOI != null && this.referredItinerary != null)
            throw new IllegalArgumentException("Referred element must be unique");
        else if (this.referredPOI != null)
            return new AuthorizedContent(this.referredPOI, this.name, this.author, this.type, this.description, this.link, this.photo);
        else
            return new AuthorizedContent(this.name, this.referredItinerary, this.author, this.type, this.description, this.link, this.photo);
    }
}
