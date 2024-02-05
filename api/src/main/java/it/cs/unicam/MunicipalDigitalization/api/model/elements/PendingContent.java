package it.cs.unicam.MunicipalDigitalization.api.model.elements;

import it.cs.unicam.MunicipalDigitalization.api.model.actors.Contributor;

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
     * @param author           The authorized contributor who creates the content.
     * @param municipalElement The municipal element to which the content refers.
     */
    public PendingContent(Contributor author, IMunicipalElement municipalElement) {
        super(author, municipalElement);
    }
}
