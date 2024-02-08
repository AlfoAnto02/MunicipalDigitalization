package it.cs.unicam.MunicipalDigitalization.api.model.elements;

import it.cs.unicam.MunicipalDigitalization.api.model.actors.Contributor;
import it.cs.unicam.MunicipalDigitalization.api.util.ElementStatus;

/**
 * This class represents a pending content of a Municipal Element.
 * It extends the Abstract content class.
 * A pending content is a content that needs to be validated from a Curator.
 * A content has an id, type, author, and a municipal element referred by the content.
 */
public class PendingContent extends AbstractContent {

    /**
     * Constructor for the PendingContent class.
     *
     */
    public PendingContent() {
        super();
        this.setElementStatus(ElementStatus.PENDING);
    }
}
