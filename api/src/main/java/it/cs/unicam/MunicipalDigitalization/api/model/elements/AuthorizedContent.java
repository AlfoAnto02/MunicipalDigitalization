package it.cs.unicam.MunicipalDigitalization.api.model.elements;

import it.cs.unicam.MunicipalDigitalization.api.model.actors.IAuthenticatedUser;
import it.cs.unicam.MunicipalDigitalization.api.util.ElementStatus;

/**
 * This class represent an AuthorizedContent.
 * An authorized content is a content that can be uploaded immediately on the Municipal
 * Element without being validated by a curator
 * It implements extends the AbstractContent class.
 * A content has an id, type, author, and a municipal element referred by the content.
 */
public class AuthorizedContent extends AbstractContent {

    /**
     * The constructor of the class
     *
     */
    public AuthorizedContent() {
        super();
        this.setElementStatus(ElementStatus.PUBLISHED);
    }
}
