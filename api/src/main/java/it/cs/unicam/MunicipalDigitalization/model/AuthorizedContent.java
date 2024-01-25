package it.cs.unicam.MunicipalDigitalization.model;

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
     * @param author           of the Content
     * @param municipalElement referred by the Content
     */
    public AuthorizedContent(AuthenticatedUser author, IMunicipalElements municipalElement) {
        super(author, municipalElement);
    }
}
