package it.cs.unicam.MunicipalDigitalization.model;

/**
 * This class represents a pending itinerary.
 * It extends the AbstractItinerary class.
 * A pending itinerary is an itinerary that has not yet been validated.
 * It provides methods to get a list of points of interest (POIs), get the id, name, and description of the itinerary, and to contains if a POI is in the itinerary.
 * It also provides methods to set the name, description, and types of the itinerary, and to add a POI to the itinerary.
 */
public class PendingItinerary extends AbstractItinerary {


    /**
     * Constructor for the PendingItinerary class.
     * It initializes the PendingItinerary with the provided user and the Municipality
     * where the Itinerary should be located
     *
     * @param user         that creates the itinerary
     * @param municipality where is located
     */
    public PendingItinerary(Contributor user, Municipality municipality) {
        super(user, municipality);
    }
}