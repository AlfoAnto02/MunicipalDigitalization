package it.cs.unicam.MunicipalDigitalization.util;

import it.cs.unicam.MunicipalDigitalization.io.IContributorsView;
import it.cs.unicam.MunicipalDigitalization.model.*;

import java.util.List;

/**
 * This class is used to manage the contents of the municipality.
 * It provides methods to upload, append, validate and invalidate contents.
 */
public class ContentController {

    /**
     * The view for contributors.
     */
    private final IContributorsView contributorView;

    /**
     * Constructor for the ContentController class.
     *
     * @param contributorView The view for contributors.
     */
    public ContentController(IContributorsView contributorView) {
        this.contributorView = contributorView;
    }

    /**
     * This method is used to set the name of a content.
     *
     * @param type    The type to set.
     * @param content The content to set the name to.
     */
    public void selectType(ContentType type, IContent content) {
        content.setType(type);
    }

    /**
     * This method is used to set the description of a content.
     *
     * @param description The description to set.
     * @param content     The content to set the description to.
     */
    public void setDescription(ContentType description, IContent content) {
        content.setDescription(String.valueOf(description));
    }

    /**
     * This method is used to set the link of a content.
     *
     * @param link    The link to set.
     * @param content The content to set the link to.
     */
    public void setLink(ContentType link, IContent content) {
        content.setLink(String.valueOf(link));
    }

    /**
     * This method is used to set the photo of a content.
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
    public void upload(AuthorizedContent content, IMunicipalElements municipalElement) {

        municipalElement.uploadContent(content);
    }

    /**
     * This method is used to append a content to the list of authorized contents.
     *
     * @param content The content to append.
     */
    public void append(PendingContent content, IMunicipalElements municipalElement) {
        municipalElement.appendContent(content);
    }

    /**
     * This method is used to get the list of authorized contents.
     *
     * @return The list of authorized contents.
     */
    public List<PendingContent> getPendingContents(IMunicipalElements municipalElement) {
        return municipalElement.getPendingManager().getListOfPendingContent();
    }

    /**
     * This method is used to validate a content.
     *
     * @param content The content to validate.
     */
    private void validateContent(IContent content, IMunicipalElements municipalElement) {
        municipalElement.getPendingManager().removeContent(content);
        municipalElement.uploadContent(content);
    }

    /**
     * This method is used to invalidate a content.
     *
     * @param content The content to invalidate.
     */
    private void invalidateContent(PendingContent content, IMunicipalElements municipalElement) {
        municipalElement.getPendingManager().removeContent(content);
    }
}
