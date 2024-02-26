package it.cs.unicam.MunicipalDigitalization.api.util.DesignPattern.Builder;

import it.cs.unicam.MunicipalDigitalization.api.model.actors.AbstractAuthenticatedUser;
import it.cs.unicam.MunicipalDigitalization.api.model.elements.*;
import it.cs.unicam.MunicipalDigitalization.api.util.ContentType;
import it.cs.unicam.MunicipalDigitalization.api.util.ElementStatus;
import it.cs.unicam.MunicipalDigitalization.api.util.UserRole;
import org.springframework.stereotype.Component;

import static it.cs.unicam.MunicipalDigitalization.api.util.MatchingAlgorithms.containsSpecialCharacters;
import static it.cs.unicam.MunicipalDigitalization.api.util.MatchingAlgorithms.isLink;

/**
 * This class represents a builder for a pending content
 */
@Component
public class PendingContentBuilder implements ContentBuilder{

    private AbstractAuthenticatedUser author;
    private String name;
    private ContentType type;
    private String photo;
    private String link;
    private String description;
    private AbstractPOI referredPOI;
    private AbstractItinerary referredItinerary;
    private ElementStatus contentStatus;

    @Override
    public void setContentAuthor(AbstractAuthenticatedUser author) {
        if(author == null) throw new IllegalArgumentException("Author cannot be null");
        if(author.getRole().contains(UserRole.CONTRIBUTOR)) this.author = author;
        else throw new IllegalArgumentException("Author must be a contributor");
    }

    @Override
    public void setContentName(String name) {
        if(name == null) throw new IllegalArgumentException("Name cannot be null");
        if(name.isBlank()) throw new IllegalArgumentException("Name cannot be blank");
        if(name.length() > 20) throw new IllegalArgumentException("Name cannot be longer than 20 characters");
        if(name.length() < 3) throw new IllegalArgumentException("Name cannot be shorter than 3 characters");
        if(containsSpecialCharacters(name)) throw new IllegalArgumentException("Name cannot contain special characters");
        this.name = name;
    }

    @Override
    public void setContentType(ContentType type) {
        if(type == null) throw new IllegalArgumentException("Type cannot be null");
        this.type = type;
    }

    @Override
    public void setContentPhoto(String photo) {
        if(photo == null) throw new IllegalArgumentException("Photo cannot be null");
        if(!(this.type == ContentType.PHOTO)) throw new IllegalArgumentException("Type must be photo");
        if(!(photo.endsWith(".jpg") || photo.endsWith(".png") || photo.endsWith(".jpeg"))) throw new IllegalArgumentException("Photo must be a jpg, jpeg or png file");
        this.photo = photo;
    }

    @Override
    public void setContentLink(String link) {
        if(link == null) throw new IllegalArgumentException("Link cannot be null");
        if(!isLink(link)) throw new IllegalArgumentException("Link must be a valid link");
        this.link = link;
    }

    @Override
    public void setContentDescription(String description) {
        if(description == null) throw new IllegalArgumentException("Description cannot be null");
        if(description.isBlank()) throw new IllegalArgumentException("Description cannot be blank");
        if(description.length() > 200) throw new IllegalArgumentException("Description cannot be longer than 200 characters");
        if(description.length() < 10) throw new IllegalArgumentException("Description cannot be shorter than 10 characters");
        this.description = description;
    }

    @Override
    public void setContentReferredMunicipalElement(AbstractMunicipalElement element) {
        if(element == null) throw new IllegalArgumentException("Element cannot be null");
        if(element instanceof AbstractPOI) this.referredPOI = (AbstractPOI) element;
        else if(element instanceof AbstractItinerary) this.referredItinerary = (AbstractItinerary) element;
        else throw new IllegalArgumentException("Element must be a POI or an Itinerary");
    }

    @Override
    public void setContentStatus() {
        this.contentStatus = ElementStatus.PENDING;
    }

    public void reset(){
        this.author = null;
        this.name = null;
        this.type = null;
        this.photo = null;
        this.link = null;
        this.description = null;
        this.contentStatus = null;
    }

    /**
     * This method builds a pending content
     *
     * @return a pending content;
     */
    public PendingContent build(){
        if(this.author == null || this.name == null || this.type == null || (this.photo == null && this.link == null &&
                this.description == null) || this.contentStatus == null) throw new IllegalArgumentException("All fields must be set");
        else if (this.referredPOI == null && this.referredItinerary == null) throw new IllegalArgumentException("Referred element must be set");
        else if (this.referredPOI != null && this.referredItinerary != null) throw new IllegalArgumentException("Referred element must be unique");
        else if (this.referredPOI != null) return new PendingContent(this.referredPOI, this.name, this.author, this.type, this.description, this.link, this.photo);
        else return new PendingContent(this.name, this.referredItinerary, this.author, this.type, this.description, this.link, this.photo);
    }
}