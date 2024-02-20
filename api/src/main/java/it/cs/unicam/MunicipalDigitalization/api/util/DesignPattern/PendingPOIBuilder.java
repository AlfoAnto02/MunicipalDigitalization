package it.cs.unicam.MunicipalDigitalization.api.util.DesignPattern;

import it.cs.unicam.MunicipalDigitalization.api.model.Municipality;
import it.cs.unicam.MunicipalDigitalization.api.model.actors.AbstractAuthenticatedUser;
import it.cs.unicam.MunicipalDigitalization.api.model.elements.PendingPOI;
import it.cs.unicam.MunicipalDigitalization.api.util.Coordinate;
import it.cs.unicam.MunicipalDigitalization.api.util.ElementStatus;
import it.cs.unicam.MunicipalDigitalization.api.util.POIType;
import it.cs.unicam.MunicipalDigitalization.api.util.UserRole;

import static it.cs.unicam.MunicipalDigitalization.api.util.MatchingAlgorithms.containsSpecialCharacters;

/**
 * This class represents the building of a Pending POI Object.
 */
public class PendingPOIBuilder implements POIBuilder{

    private AbstractAuthenticatedUser author;

    private Coordinate coordinates;

    private String name;

    private POIType type;

    private Municipality municipality;

    private ElementStatus status;




    @Override
    public void setPOIAuthor(AbstractAuthenticatedUser author) {
        if(author.getRole() == UserRole.CONTRIBUTOR) {
            this.author = author;
        }
        else throw new IllegalArgumentException("The author must be a Contributor");
    }

    @Override
    public void setPOICoordinates(Coordinate coordinates) {
        if(coordinates != null) this.coordinates = coordinates;
        else throw new IllegalArgumentException("The coordinates must not be null");
    }

    @Override
    public void setPOIName(String name) {
        if(name.length() > 25) throw new IllegalArgumentException("The name must not be longer than 25 characters");
        if(name.length()< 3) throw new IllegalArgumentException("The name must not be shorter than 3 characters");
        if(!containsSpecialCharacters(name)) throw new IllegalArgumentException("The name must not contain special characters");
        if(!name.isBlank()) this.name = name;
        else throw new IllegalArgumentException("The name must not be null or blank");
    }

    @Override
    public void setPOIType(POIType type) {
        if (type != null)  this.type = type;
        else throw new IllegalArgumentException("The type must not be null");
    }

    @Override
    public void setPOIMunicipality(Municipality municipality) {
        if(municipality != null && municipality.checkCoordinates(this.coordinates))  this.municipality = municipality;
        else throw new IllegalArgumentException("The municipality must not be null and the coordinates must be inside the municipality");
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
    public PendingPOI buildPendingPOI(){
        return new PendingPOI(this.municipality, this.status, this.coordinates, this.name, this.type, this.author);
    }
}
