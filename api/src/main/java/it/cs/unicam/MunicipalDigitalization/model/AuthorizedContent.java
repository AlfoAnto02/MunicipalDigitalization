package it.cs.unicam.MunicipalDigitalization.model;

import it.cs.unicam.MunicipalDigitalization.util.ContentType;

public class AuthorizedContent extends AbstractContent {
    public AuthorizedContent(String ID, ContentType type, IMunicipalElement municipalElement) {
        super(ID, type, municipalElement);
    }
}
