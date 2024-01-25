package it.cs.unicam.MunicipalDigitalization.model;

import it.cs.unicam.MunicipalDigitalization.util.ContentType;
import it.cs.unicam.MunicipalDigitalization.util.ID;

/**
 * This abstract class represents a general content.
 * It implements the IContent interface.
 * A content has an id, type, author, and a municipal element referred by the content.
 */
public class AuthorizedContent extends AbstractContent {

    /**
     * The constructor of the class
     *
     * @param author of the Content
     * @param municipalElement referred by the Content
     */
    public AuthorizedContent(AuthenticatedUser author, IMunicipalElements municipalElement) {
        super(author, municipalElement);
    }

    @Override
    public void setDescription(String description) {
        //TODO  implement
    }

    @Override
    public void setLink(String link) {
        //TODO  implement
    }

    @Override
    public void setPhoto(String photo) {
        //TODO  implement
    }
}
