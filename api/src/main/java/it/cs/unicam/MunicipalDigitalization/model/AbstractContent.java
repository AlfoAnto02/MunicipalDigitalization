package it.cs.unicam.MunicipalDigitalization.model;

import it.cs.unicam.MunicipalDigitalization.util.ContentType;
import it.cs.unicam.MunicipalDigitalization.util.ID;

public abstract class AbstractContent implements IContent {

    private final ID id;

    private final ContentType type;

    private final IMunicipalElement municipalElement;

    public AbstractContent(ID id, ContentType type, IMunicipalElement municipalElement) {
        this.id = id;
        this.type = type;
        this.municipalElement = municipalElement;
    }

    @Override
    public String getID() {
        return this.id.toString();
    }

    @Override
    public ContentType getType() {
        return this.type;
    }
}
