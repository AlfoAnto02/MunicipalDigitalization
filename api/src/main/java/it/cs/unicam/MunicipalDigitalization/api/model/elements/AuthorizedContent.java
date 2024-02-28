package it.cs.unicam.MunicipalDigitalization.api.model.elements;

import it.cs.unicam.MunicipalDigitalization.api.model.users.AbstractAuthenticatedUser;
import it.cs.unicam.MunicipalDigitalization.api.util.ContentType;
import it.cs.unicam.MunicipalDigitalization.api.util.ElementStatus;
import jakarta.persistence.Entity;

/**
 * This class represent an AuthorizedContent.
 * An authorized content is a content that can be uploaded immediately on the Municipal
 * Element without being validated by a curator
 * It implements extends the AbstractContent class.
 * A content has an id, type, author, and a municipal element referred by the content.
 */
@Entity
public class AuthorizedContent extends AbstractContent {

    /**
     * The constructor of the class
     */
    public AuthorizedContent() {
        super();
        this.setElementStatus(ElementStatus.PUBLISHED);
    }

    /**
     * The constructor of the class for a referredPOI Content
     *
     * @param referredPOI referredPOI
     * @param name        name of the content
     * @param author      author of the content
     * @param type        type of the content
     * @param content     content of the content
     */
    public AuthorizedContent(AbstractPOI referredPOI, String name, AbstractAuthenticatedUser author,
                             ContentType type, String content) {
        super(referredPOI, name, author, type, content);
        this.setElementStatus(ElementStatus.PUBLISHED);
    }

    public AuthorizedContent(String name, AbstractItinerary referredItinerary, AbstractAuthenticatedUser author,
                             ContentType type, String content) {
        super(name, referredItinerary, author, type, content);
        this.setElementStatus(ElementStatus.PUBLISHED);
    }

}
