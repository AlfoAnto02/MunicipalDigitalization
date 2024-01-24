package it.cs.unicam.MunicipalDigitalization.util;

import it.cs.unicam.MunicipalDigitalization.io.IContributorsView;
import it.cs.unicam.MunicipalDigitalization.model.AuthorizedContent;
import it.cs.unicam.MunicipalDigitalization.model.IContent;
import it.cs.unicam.MunicipalDigitalization.model.Municipality;
import it.cs.unicam.MunicipalDigitalization.model.PendingContent;

import java.util.List;

public class ContentController {

    private final IContributorsView contributorView;

    private final Municipality municipality;

    public ContentController(IContributorsView contributorView, Municipality municipality) {
        this.contributorView = contributorView;
        this.municipality = municipality;
    }

    private void selectType(ContentType type, IContent content) {
        content.setType(type);
    }

    private void setDescription(ContentType description, IContent content) {
        content.setDescription(String.valueOf(description));
    }

    private void setLink(ContentType link, IContent content) {
        content.setLink(String.valueOf(link));
    }

    private void setPhoto(ContentType photo, IContent content) {
        content.setPhoto(String.valueOf(photo));
    }

    public void upload(AuthorizedContent content) {
        this.municipality.uploadContent(content);
    }

    public void append(PendingContent content) {
        this.municipality.appendContent(content);
    }

    public List<PendingContent> getPendingContents() {
        return this.municipality.getPendingManager().getPendingContents();
    }

    private void selectContent(PendingContent content) {

    }

    private void validateContent(PendingContent content) {
        this.municipality.getPendingManager().removeContent(content);
        this.municipality.uploadContent(content);
    }

    private void invalidateContent(PendingContent content) {
        this.municipality.getPendingManager().removeContent(content);
    }
}
