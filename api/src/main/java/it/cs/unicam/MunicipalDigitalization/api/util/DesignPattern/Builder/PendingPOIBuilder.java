package it.cs.unicam.MunicipalDigitalization.api.util.DesignPattern.Builder;

import it.cs.unicam.MunicipalDigitalization.api.model.Municipality;
import it.cs.unicam.MunicipalDigitalization.api.model.elements.PendingPOI;
import it.cs.unicam.MunicipalDigitalization.api.model.users.AbstractAuthenticatedUser;
import it.cs.unicam.MunicipalDigitalization.api.util.Coordinate;
import it.cs.unicam.MunicipalDigitalization.api.util.ElementStatus;
import it.cs.unicam.MunicipalDigitalization.api.util.POIType;
import org.springframework.stereotype.Component;

/**
 * This class represents the building of a Pending POI Object.
 */
@Component
public class PendingPOIBuilder implements POIBuilder {

    private AbstractAuthenticatedUser author;

    private Coordinate coordinates;

    private String name;

    private POIType type;

    private Municipality municipality;

    private ElementStatus status;


    @Override
    public void setPOIAuthor(AbstractAuthenticatedUser author) {
        this.author = author;
    }

    @Override
    public void setPOICoordinates(Coordinate coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public void setPOIName(String name) {
        this.name = name;
    }

    @Override
    public void setPOIType(POIType type) {
        this.type = type;
    }

    @Override
    public void setPOIMunicipality(Municipality municipality) {
        // TODO check if the poi is within the municipality coordinates

        this.municipality = municipality;
    }

    @Override
    public void setPOIStatus() {
        this.status = ElementStatus.PENDING;
    }

    @Override
    public void reset() {
        this.author = null;
        this.coordinates = null;
        this.name = null;
        this.type = null;
        this.municipality = null;
        this.status = null;
    }

    /**
     * This method is used to build the Pending POI.
     *
     * @return The Pending POI.
     */
    public PendingPOI build() {
        return new PendingPOI(this.municipality, this.status, this.coordinates, this.name, this.type, this.author);
    }
}
