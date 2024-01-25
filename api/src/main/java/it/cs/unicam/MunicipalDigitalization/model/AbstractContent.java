package it.cs.unicam.MunicipalDigitalization.model;

import it.cs.unicam.MunicipalDigitalization.util.ContentType;
import it.cs.unicam.MunicipalDigitalization.util.ID;

public abstract class AbstractContent implements IContent {

    private final ID id;

    private ContentType type;

    private final IMunicipalElements referredMunicipalElement;

    private final AuthenticatedUser author;

    public AbstractContent(AuthenticatedUser author, IMunicipalElements municipalElement) {
        this.author = author;
        this.referredMunicipalElement = municipalElement;
        this.id = new ID();
    }

    @Override
    public void setType(ContentType type) {
        if(type==null) throw new IllegalArgumentException("Content type is null");
        this.type=type;
    }

    @Override
    public String getID() {
        return this.id.toString();
    }

    @Override
    public ContentType getType() {
        return this.type;
    }

    @Override
    public IMunicipalElements getReferredMunicipalElement() {
        return this.referredMunicipalElement;
    }
}
