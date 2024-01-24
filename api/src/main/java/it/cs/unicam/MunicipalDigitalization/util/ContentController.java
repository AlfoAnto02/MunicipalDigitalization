package it.cs.unicam.MunicipalDigitalization.util;

import it.cs.unicam.MunicipalDigitalization.io.IContributorsView;
import it.cs.unicam.MunicipalDigitalization.model.AuthorizedContent;
import it.cs.unicam.MunicipalDigitalization.model.IContent;
import it.cs.unicam.MunicipalDigitalization.model.Municipality;
import it.cs.unicam.MunicipalDigitalization.model.PendingContent;

import java.util.List;

public class ContentController {

    private final IContributorsView contributorsView;

    private final Municipality municipality;

    public ContentController(IContributorsView contributorsView, Municipality municipality) {
        this.contributorsView = contributorsView;
        this.municipality = municipality;
    }

    private void selectType(ContentType type, IContent content) {
        // TODO implement here
        throw new UnsupportedOperationException("The method is not implemented yet.");
    }

    private void setDescription(String description, IContent content) {
        // TODO implement here
        throw new UnsupportedOperationException("The method is not implemented yet.");
    }

    private void setLink(Link link, IContent content) {
        // TODO implement here
        throw new UnsupportedOperationException("The method is not implemented yet.");
    }

    private void setPhoto(Photo photo, IContent content) {
        // TODO implement here
        throw new UnsupportedOperationException("The method is not implemented yet.");
    }

    private void upload(AuthorizedContent content) {
        // TODO implement here
        throw new UnsupportedOperationException("The method is not implemented yet.");
    }

    private void append(PendingContent content) {
        // TODO implement here
        throw new UnsupportedOperationException("The method is not implemented yet.");
    }

    public List<PendingContent> getPendingContents() {
        // TODO implement here
        throw new UnsupportedOperationException("The method is not implemented yet.");
    }

    private void selectContent(PendingContent content) {
        // TODO implement here
        throw new UnsupportedOperationException("The method is not implemented yet.");
    }

    private void validateContent(PendingContent content) {
        // TODO implement here
        throw new UnsupportedOperationException("The method is not implemented yet.");
    }

    private void invalidateContent(PendingContent content) {
        // TODO implement here
        throw new UnsupportedOperationException("The method is not implemented yet.");
    }
}
