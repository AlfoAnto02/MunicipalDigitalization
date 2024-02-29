package it.cs.unicam.MunicipalDigitalization.api.util.DesignPattern.Builder;

import it.cs.unicam.MunicipalDigitalization.api.model.elements.AbstractItinerary;
import it.cs.unicam.MunicipalDigitalization.api.model.elements.AbstractMunicipalElement;
import it.cs.unicam.MunicipalDigitalization.api.model.elements.AbstractPOI;
import it.cs.unicam.MunicipalDigitalization.api.model.elements.PendingContent;
import it.cs.unicam.MunicipalDigitalization.api.model.users.AbstractAuthenticatedUser;
import it.cs.unicam.MunicipalDigitalization.api.util.ContentType;
import it.cs.unicam.MunicipalDigitalization.api.util.ElementStatus;
import org.springframework.stereotype.Component;

/**
 * This class represents a builder for a pending content
 */
@Component
public class PendingContentBuilder implements ContentBuilder {

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
     * The content field
     */
    private String content;

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
    public void setContentField(String content) {
        this.content = content;
    }

    @Override
    public void setContentReferredMunicipalElement(AbstractMunicipalElement element) {
        if (element instanceof AbstractPOI) this.referredPOI = (AbstractPOI) element;
        else if (element instanceof AbstractItinerary) this.referredItinerary = (AbstractItinerary) element;
        else throw new IllegalArgumentException("Element must be a POI or an Itinerary");
    }

    @Override
    public void setContentStatus() {
        this.contentStatus = ElementStatus.PENDING;
    }

    /**
     * This method builds the pending content
     *
     * @return the pending content
     */
    public PendingContent build() {
        if (this.author == null || this.name == null || this.type == null || this.content == null || this.contentStatus == null)
            throw new IllegalArgumentException("All fields must be set");
        else if (this.referredPOI == null && this.referredItinerary == null)
            throw new IllegalArgumentException("Referred element must be set");
        else if (this.referredPOI != null && this.referredItinerary != null)
            throw new IllegalArgumentException("Referred element must be unique");
        else if (this.referredPOI != null)
            return new PendingContent(this.referredPOI, this.name, this.author, this.type, this.content);
        else
            return new PendingContent(this.referredItinerary, this.name, this.author, this.type, this.content);
    }
}