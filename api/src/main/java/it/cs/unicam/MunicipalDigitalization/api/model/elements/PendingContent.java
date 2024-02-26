package it.cs.unicam.MunicipalDigitalization.api.model.elements;

import it.cs.unicam.MunicipalDigitalization.api.model.users.AbstractAuthenticatedUser;
import it.cs.unicam.MunicipalDigitalization.api.util.ContentType;
import it.cs.unicam.MunicipalDigitalization.api.util.ElementStatus;
import jakarta.persistence.Entity;

/**
 * This class represents a pending content of a Municipal Element.
 * It extends the Abstract content class.
 * A pending content is a content that needs to be validated from a Curator.
 * A content has an id, type, author, and a municipal element referred by the content.
 */
@Entity
public class PendingContent extends AbstractContent {

    /**
     * Constructor for the PendingContent class.
     */
    public PendingContent() {
        super();
        this.setElementStatus(ElementStatus.PENDING);
    }

    public PendingContent(AbstractPOI referredPOI, String name, AbstractAuthenticatedUser author,
                          ContentType type, String description, String link, String photo) {
        super(referredPOI, name, author, type, description, link, photo);
        this.setElementStatus(ElementStatus.PENDING);
    }

    public PendingContent( AbstractItinerary referredItinerary,String name,AbstractAuthenticatedUser author,
                          ContentType type, String description, String link, String photo) {
        super(name, referredItinerary, author, type, description, link, photo);
        this.setElementStatus(ElementStatus.PENDING);
    }

}
