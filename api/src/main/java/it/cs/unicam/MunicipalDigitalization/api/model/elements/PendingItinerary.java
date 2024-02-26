package it.cs.unicam.MunicipalDigitalization.api.model.elements;

import it.cs.unicam.MunicipalDigitalization.api.model.Municipality;
import it.cs.unicam.MunicipalDigitalization.api.model.users.AbstractAuthenticatedUser;
import it.cs.unicam.MunicipalDigitalization.api.util.Coordinate;
import it.cs.unicam.MunicipalDigitalization.api.util.ElementStatus;
import jakarta.persistence.Entity;

import java.util.List;

/**
 * This class represents a pending itinerary.
 * It extends the AbstractItinerary class.
 * A pending itinerary is an itinerary that has not yet been validated.
 * It provides methods to get a list of points of interest (POIs), get the id, name, and description of the itinerary, and to contains if a POI is in the itinerary.
 * It also provides methods to set the name, description, and types of the itinerary, and to add a POI to the itinerary.
 */
@Entity
public class PendingItinerary extends AbstractItinerary {


    /**
     * Constructor for the PendingItinerary class.
     */
    public PendingItinerary() {
        super();
    }

    /**
     * Constructor for the PendingItinerary class used by the Builder
     *
     * @param municipality the municipality where the itinerary should be located
     * @param elementStatus the status of the itinerary
     * @param coordinate the coordinate of the itinerary
     * @param name the name of the itinerary
     * @param pois the list of points of interest (POIs) that compose the itinerary
     * @param types the types of the itinerary
     * @param description the description of the itinerary
     * @param author the author of the itinerary
     */
    public PendingItinerary(Municipality municipality, ElementStatus elementStatus,
                            Coordinate coordinate, String name, List<AbstractPOI> pois, String types,
                            String description, AbstractAuthenticatedUser author) {
        super(municipality, elementStatus, coordinate, name, pois, types, description, author);
    }
}