package it.cs.unicam.MunicipalDigitalization.model;

import it.cs.unicam.MunicipalDigitalization.util.ContentType;
import it.cs.unicam.MunicipalDigitalization.util.ID;

public class PendingContent extends AbstractContent {

    /**
     * Constructor for the PendingContent class.
     *
     * @param author The authorized contributor who creates the content.
     * @param municipalElement The municipal element to which the content refers.
     */
    public PendingContent(Contributor author, IMunicipalElements municipalElement) {
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
