package it.cs.unicam.MunicipalDigitalization.api.model.elements;

import it.cs.unicam.MunicipalDigitalization.api.model.actors.AbstractAuthenticatedUser;
import it.cs.unicam.MunicipalDigitalization.api.model.actors.IAuthenticatedUser;
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
     *
     */
    public AuthorizedContent() {
        super();
    }

    public AuthorizedContent(AbstractPOI referredPOI, String name, AbstractAuthenticatedUser author,
                             ElementStatus elementStatus, ContentType type, String description, String link, String photo) {
        super(referredPOI, name, author, elementStatus, type, description, link, photo);
    }

    public AuthorizedContent(String name, AbstractItinerary referredItinerary, AbstractAuthenticatedUser author,
                             ElementStatus elementStatus, ContentType type, String description, String link, String photo) {
        super(name, referredItinerary, author, elementStatus, type, description, link, photo);
    }
}
