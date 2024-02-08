package it.cs.unicam.MunicipalDigitalization.api.model.elements;

import it.cs.unicam.MunicipalDigitalization.api.model.Municipality;
import it.cs.unicam.MunicipalDigitalization.api.model.actors.AuthorizedContributor;
import it.cs.unicam.MunicipalDigitalization.api.util.ElementStatus;

/**
 * This class represents an authorized point of interest (POI), which is a type of POI.
 * It extends the AbstractPOI class.
 * An authorized POI has coordinates, id, and name.
 * It also has a method to set its coordinates.
 */
public class AuthorizedPOI extends AbstractPOI {

    /**
     * Constructor for the AuthorizedPOI class.
     * It initializes the AuthorizedPOI with the provided user and the Municipality
     * where the POI should be located
     *
     */
    public AuthorizedPOI() {
        super();
        this.setElementStatus(ElementStatus.PUBLISHED);
    }
}