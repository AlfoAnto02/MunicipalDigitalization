package it.cs.unicam.MunicipalDigitalization.model;

import it.cs.unicam.MunicipalDigitalization.util.ContentType;
import it.cs.unicam.MunicipalDigitalization.util.ID;

public class AuthorizedContent extends AbstractContent {
    public AuthorizedContent(AuthenticatedUser author, IMunicipalElements municipalElement) {
        super(author, municipalElement);
    }


    @Override
    public void setDescription(String description) {

    }

    @Override
    public void setLink(String link) {

    }

    @Override
    public void setPhoto(String photo) {

    }
}
