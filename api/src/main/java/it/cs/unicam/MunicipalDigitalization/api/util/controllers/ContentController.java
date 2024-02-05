package it.cs.unicam.MunicipalDigitalization.api.util.controllers;

import it.cs.unicam.MunicipalDigitalization.api.io.IContributorView;
import it.cs.unicam.MunicipalDigitalization.api.model.Municipality;
import it.cs.unicam.MunicipalDigitalization.api.model.elements.AuthorizedContent;
import it.cs.unicam.MunicipalDigitalization.api.model.elements.IContent;
import it.cs.unicam.MunicipalDigitalization.api.model.elements.IMunicipalElement;
import it.cs.unicam.MunicipalDigitalization.api.model.elements.PendingContent;
import it.cs.unicam.MunicipalDigitalization.api.util.ContentType;

import java.util.List;

/**
 * This class is used to manage the contents of the municipality.
 * It provides methods to upload, append, validate and invalidate contents.
 * It also provides methods to set the type, description, link, and photo of a content.
 */
public class ContentController {

    /**
     * The view for contributors.
     */
    private final IContributorView contributorView;


    /**
     * The municipality.
     */
    private final Municipality municipality;

    /**
     * Constructor for the ContentController class.
     *
     * @param contributorView The view for contributors.
     */
    public ContentController(IContributorView contributorView, Municipality municipality) {
        this.contributorView = contributorView;
        this.municipality = municipality;
    }

    /**
     * This method is used to set the type of content.
     *
     * @param type    The type to set.
     * @param content The content to set the type to.
     */
    public void selectType(ContentType type, IContent content) {
        content.setType(type);
    }

    /**
     * This method is used to set the contentType description of a content.
     *
     * @param description The description to set.
     * @param content     The content to set the description to.
     */
    public void setDescription(ContentType description, IContent content) {
        content.setDescription(String.valueOf(description));
    }

    /**
     * This method is used to set the contentType Link of a content.
     *
     * @param link    The link to set.
     * @param content The content to set the link to.
     */
    public void setLink(ContentType link, IContent content) {
        content.setLink(String.valueOf(link));
    }

    /**
     * This method is used to set the contentType Photo of a content.
     *
     * @param photo   The photo to set.
     * @param content The content to set the photo to.
     */
    public void setPhoto(ContentType photo, IContent content) {
        content.setPhoto(String.valueOf(photo));
    }

    /**
     * This method is used to upload a content.
     *
     * @param content The content to upload.
     */
    public void uploadContent(AuthorizedContent content, IMunicipalElement municipalElement) {
        municipalElement.uploadContent(content);
    }

    /**
     * This method is used to append a content to the list of pending contents.
     *
     * @param content The content to append.
     */
    public void appendContent(PendingContent content) {
        this.municipality.appendContent(content);
    }

    /**
     * This method is used to get the list of Pending contents that are stored
     * in the Municipality.
     *
     * @return The list of authorized contents.
     */
    public List<PendingContent> getPendingContents() {
        return this.municipality.getPendingManager().getPendingContents();
    }

    /**
     * This method is used to validate a content.
     *
     * @param content The content to validate.
     */
    public void validateContent(PendingContent content) {
        this.municipality.getPendingManager().removeContent(content);
        this.municipality.uploadContent(content);
    }

    /**
     * This method is used to invalidate a content.
     *
     * @param content The content to invalidate.
     */
    public void invalidateContent(PendingContent content) {
        this.municipality.getPendingManager().removeContent(content);
    }
}
