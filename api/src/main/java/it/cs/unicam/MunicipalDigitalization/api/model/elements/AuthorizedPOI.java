package it.cs.unicam.MunicipalDigitalization.api.model.elements;

import it.cs.unicam.MunicipalDigitalization.api.model.Municipality;
import it.cs.unicam.MunicipalDigitalization.api.model.actors.AbstractAuthenticatedUser;
import it.cs.unicam.MunicipalDigitalization.api.model.actors.AuthorizedContributor;
import it.cs.unicam.MunicipalDigitalization.api.util.Coordinate;
import it.cs.unicam.MunicipalDigitalization.api.util.ElementStatus;
import it.cs.unicam.MunicipalDigitalization.api.util.POIType;
import jakarta.persistence.Entity;

/**
 * This class represents an authorized point of interest (POI), which is a type of POI.
 * It extends the AbstractPOI class.
 * An authorized POI has coordinates, id, and name.
 * It also has a method to set its coordinates.
 */
@Entity
public class AuthorizedPOI extends AbstractPOI {

    /**
     * Constructor for the AuthorizedPOI class.
     * It initializes the AuthorizedPOI with the provided user and the Municipality
     * where the POI should be located
     *
     */
    public AuthorizedPOI() {
        super();
    }

    /**
     * Constructor for the AuthorizedPOI class used by the Builder
     * @param municipality the municipality where the POI is located
     * @param elementStatus the status of the POI
     * @param coordinate the coordinates of the POI
     * @param name the name of the POI
     * @param POIType the type of the POI
     * @param author the author of the POI
     */
    public AuthorizedPOI(Municipality municipality, ElementStatus elementStatus,
                         Coordinate coordinate, String name,POIType POIType, AbstractAuthenticatedUser author) {
        super(municipality, elementStatus, coordinate, name, POIType, author);
    }
}