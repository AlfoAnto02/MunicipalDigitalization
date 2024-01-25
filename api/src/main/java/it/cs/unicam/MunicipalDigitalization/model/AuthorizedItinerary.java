package it.cs.unicam.MunicipalDigitalization.model;

/**
 * This class represents an authorized itinerary, which is a type of itinerary.
 * It extends the AbstractItinerary class.
 * An authorized itinerary has a list of points of interest (POIs), id, name, and description.
 * It also has a method to contains if a given POI is part of the itinerary.
 */
public class AuthorizedItinerary extends AbstractItinerary {

    /**
     * Constructor for the AuthorizedItinerary class.
     * It initializes the AuthorizedItinerary with the provided user and the Municipality
     * where the Itinerary should be located
     *
     * @param user         that creates the itinerary
     * @param municipality where is located
     */
    public <T extends AuthorizedContributor> AuthorizedItinerary(T user, Municipality municipality) {
        super(user, municipality);
    }
}