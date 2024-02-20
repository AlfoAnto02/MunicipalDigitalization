package it.cs.unicam.MunicipalDigitalization.api.model.elements;

import it.cs.unicam.MunicipalDigitalization.api.model.Municipality;
import it.cs.unicam.MunicipalDigitalization.api.model.actors.AbstractAuthenticatedUser;
import it.cs.unicam.MunicipalDigitalization.api.model.actors.AuthorizedContributor;
import it.cs.unicam.MunicipalDigitalization.api.util.Coordinate;
import it.cs.unicam.MunicipalDigitalization.api.util.ElementStatus;

import java.util.List;

/**
 * This class represents an authorized itinerary, which is a type of itinerary.
 * It extends the AbstractItinerary class.
 * An authorized itinerary has a list of points of interest (POIs), id, name, and description.
 * It also has a method to contains if a given POI is part of the itinerary.
 */
public class AuthorizedItinerary extends AbstractItinerary {

    /**
     * Constructor for the AuthorizedItinerary class.
     */
    public AuthorizedItinerary() {
        super();
    }

    /**
     * Constructor for the AuthorizedItinerary class used by the Builder
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
    public AuthorizedItinerary(Municipality municipality, ElementStatus elementStatus, Coordinate coordinate,
                               String name, List<AbstractPOI> pois, String types, String description,
                               AbstractAuthenticatedUser author) {
        super(municipality, elementStatus, coordinate, name, pois, types, description, author);
    }
}