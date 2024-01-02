package it.cs.unicam.MunicipalDigitalization.model;

import java.util.List;

/**
 * Contributor class that extends the AbstractUser class.
 * This class represents a contributor in the system.
 */
public class Contributor extends AbstractUser {

    /**
     * The constructor of the Contributor class.
     *
     * @param id       the ID of the contributor as a String.
     * @param name     the name of the contributor as a String.
     * @param password the password of the contributor as a String.
     */
    public Contributor(String id, String name, String password) {
        super(id, name, password);
    }

    /**
     * Method to create a pending Point of Interest (POI).
     *
     * @param id          the ID of the POI as a String.
     * @param name        the name of the POI as a String.
     * @param coordinates the coordinates of the POI as a Coordinate object.
     */
    public void createPendingPOI(String id, String name, Coordinate coordinates) {
        // TODO - implement createPendingPOI
        throw new UnsupportedOperationException();
    }

    /**
     * Method to create a pending itinerary.
     *
     * @param id         the ID of the itinerary as a String.
     * @param name       the name of the itinerary as a String.
     * @param listOfPOIs a list of POIs as a List of IPOI objects.
     */
    public void createPendingItinerary(String id, String name, List<IPOI> listOfPOIs) {
        // TODO - implement createPendingItinerary
        throw new UnsupportedOperationException();
    }

    /**
     * Private method to append a pending POI to the list of POIs or a pending itinerary to the list of itineraries.
     */
    private void append() {
        // TODO - implement append
        throw new UnsupportedOperationException();
    }
}