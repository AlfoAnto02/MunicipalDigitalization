package it.cs.unicam.MunicipalDigitalization.model;

import it.cs.unicam.MunicipalDigitalization.util.ContentType;

public class PendingContent extends AbstractContent {

    public PendingContent(String ID, ContentType type, IMunicipalElement municipalElement) {
        super(ID, type, municipalElement);
    }
}
