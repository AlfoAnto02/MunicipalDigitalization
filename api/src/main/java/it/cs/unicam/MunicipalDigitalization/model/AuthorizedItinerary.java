package it.cs.unicam.MunicipalDigitalization.model;

/**
 * This class represents an authorized itinerary, which is a type of itinerary.
 * It extends the AbstractItinerary class.
 * An authorized itinerary has a list of points of interest (POIs), id, name, and description.
 * It also has a method to check if a given POI is part of the itinerary.
 */
public class AuthorizedItinerary extends AbstractItinerary {

    /**
     * Constructor for the AuthorizedItinerary class.
     *
     * @param user The authorized contributor who creates the itinerary.
     */
    public <T extends AuthorizedContributor> AuthorizedItinerary(T user) {
        super(user);
    }
}