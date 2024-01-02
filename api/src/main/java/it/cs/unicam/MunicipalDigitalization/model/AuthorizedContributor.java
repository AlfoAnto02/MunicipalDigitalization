package it.cs.unicam.MunicipalDigitalization.model;

import java.util.List;

/**
 * AuthorizedContributor class that extends the AbstractUser class.
 * This class represents an authorized contributor in the system.
 */
public class AuthorizedContributor extends AbstractUser {

    /**
     * The constructor of the AuthorizedContributor class.
     *
     * @param id       the ID of the authorized contributor as a String.
     * @param name     the name of the authorized contributor as a String.
     * @param password the password of the authorized contributor as a String.
     */
    public AuthorizedContributor(String id, String name, String password) {
        super(id, name, password);
    }

    /**
     * Method to create an authorized Point of Interest (POI).
     *
     * @param id          the ID of the POI as a String.
     * @param name        the name of the POI as a String.
     * @param coordinates the coordinates of the POI as a Coordinate object.
     */
    public void createAuthorizedPOI(String id, String name, Coordinate coordinates) {
        // TODO - implement createAuthorizedPOI
        throw new UnsupportedOperationException();
    }

    /**
     * Method to create an authorized itinerary.
     *
     * @param id         the ID of the itinerary as a String.
     * @param name       the name of the itinerary as a String.
     * @param listOfPOIs a list of POIs as a List of IPOI objects.
     */
    public void createAuthorizedItinerary(String id, String name, List<IPOI> listOfPOIs) {
        // TODO - implement createAuthorizedItinerary
        throw new UnsupportedOperationException();
    }

    /**
     * Method to notify the system updater.
     */
    private void notifySystemUpdater() {
        // TODO - implement notifySystemUpdater
        throw new UnsupportedOperationException();
    }
}
