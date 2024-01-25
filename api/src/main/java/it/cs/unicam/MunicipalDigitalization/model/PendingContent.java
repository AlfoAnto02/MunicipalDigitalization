package it.cs.unicam.MunicipalDigitalization.model;

public class PendingContent extends AbstractContent {

    /**
     * Constructor for the PendingContent class.
     *
     * @param author           The authorized contributor who creates the content.
     * @param municipalElement The municipal element to which the content refers.
     */
    public PendingContent(Contributor author, IMunicipalElements municipalElement) {
        super(author, municipalElement);
    }
}
