package it.cs.unicam.MunicipalDigitalization.api.model.elements;
import it.cs.unicam.MunicipalDigitalization.api.model.Municipality;
import it.cs.unicam.MunicipalDigitalization.api.model.users.AbstractAuthenticatedUser;
import it.cs.unicam.MunicipalDigitalization.api.util.Coordinate;
import it.cs.unicam.MunicipalDigitalization.api.util.ElementStatus;
import it.cs.unicam.MunicipalDigitalization.api.util.POIType;
import jakarta.persistence.Entity;

/**
 * This class represents a pending point of interest (POI).
 * It extends the AbstractPOI class.
 * A pending POI is a POI that has not yet been validated.
 * It provides methods to get the coordinates, id, and name of the POI, and to set the coordinates of the POI.
 * It also provides a constructor to initialize the PendingPOI with the provided user and Municipality
 */
@Entity
public class PendingPOI extends AbstractPOI {

    /**
     * Constructor for the PendingPOI class.
     * It initializes the PendingPOI with the provided user and the Municipality
     * where the Poi should be located
     *
     */
    public PendingPOI() {
        super();
    }

    /**
     * Constructor for the PendingPOI class used by the Builder
     *
     * @param municipality the municipality where the POI is located
     * @param elementStatus the status of the POI
     * @param coordinate the coordinates of the POI
     * @param name the name of the POI
     * @param POIType the type of the POI
     * @param author the author of the POI
     */

    public PendingPOI(Municipality municipality, ElementStatus elementStatus,
                      Coordinate coordinate, String name, POIType POIType, AbstractAuthenticatedUser author) {
        super(municipality, elementStatus, coordinate, name, POIType, author);
    }
}