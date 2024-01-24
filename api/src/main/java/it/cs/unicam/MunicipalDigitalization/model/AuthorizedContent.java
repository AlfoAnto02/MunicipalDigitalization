package it.cs.unicam.MunicipalDigitalization.model;

import it.cs.unicam.MunicipalDigitalization.util.ContentType;
import it.cs.unicam.MunicipalDigitalization.util.ID;

public class AuthorizedContent extends AbstractContent {
    public AuthorizedContent(ID id, ContentType type, IMunicipalElement municipalElement) {
        super(id, type, municipalElement);
    }
}
