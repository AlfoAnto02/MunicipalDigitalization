package it.cs.unicam.MunicipalDigitalization.model;

import it.cs.unicam.MunicipalDigitalization.util.ContentType;
import it.cs.unicam.MunicipalDigitalization.util.ID;

public class PendingContent extends AbstractContent {

    public PendingContent(ID id, ContentType type, IMunicipalElement municipalElement) {
        super(id, type, municipalElement);
    }
}
