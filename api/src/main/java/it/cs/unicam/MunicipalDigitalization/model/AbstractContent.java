package it.cs.unicam.MunicipalDigitalization.model;

import it.cs.unicam.MunicipalDigitalization.util.ContentType;

public abstract class AbstractContent implements IContent {

    private final String ID;

    private final ContentType type;

    private final IMunicipalElement municipalElement;

    public AbstractContent(String ID, ContentType type, IMunicipalElement municipalElement) {
        this.ID = ID;
        this.type = type;
        this.municipalElement = municipalElement;
    }

    @Override
    public String getID() {
        return this.ID;
    }

    @Override
    public ContentType getType() {
        return this.type;
    }
}
