package it.cs.unicam.MunicipalDigitalization.model;

import java.util.List;

/**
 * Curator class that extends the AuthorizedContributor class.
 * This class represents a curator in the system who validates Points of Interest (POIs) and Itineraries.
 */
public class Curator extends AuthorizedContributor {

    /**
     * List of pending POIs for the curator to validate.
     */
    private List<PendingPOI> pendingPOIsList;

    /**
     * List of pending itineraries for the curator to validate.
     */
    private List<PendingItinerary> pendingItineraryList;

    /**
     * The constructor of the Curator class.
     *
     * @param id       the ID of the curator as a String.
     * @param name     the name of the curator as a String.
     * @param password the password of the curator as a String.
     */
    public Curator(String id, String name, String password) {
        super(id, name, password);
    }

    /**
     * Private method to validate a POI.
     *
     * @return a boolean indicating the result of the validation.
     */
    private boolean validatePOI() {
        // TODO - implement validatePOI
        throw new UnsupportedOperationException();
    }

    /**
     * Private method to validate an itinerary.
     *
     * @return a boolean indicating the result of the validation.
     */
    private boolean validateItinerary() {
        // TODO - implement validateItinerary
        throw new UnsupportedOperationException();
    }

    /**
     * Method to pull the latest pending POIs and itineraries for validation.
     */
    public void pull() {
        // TODO - implement pull
        throw new UnsupportedOperationException();
    }

    /**
     * Private method to delete an invalid POI.
     */
    private void deleteInvalidPOI() {
        // TODO - implement deleteInvalidPOI
        throw new UnsupportedOperationException();
    }

    /**
     * Private method to delete an invalid itinerary.
     */
    private void deleteInvalidItinerary() {
        // TODO - implement deleteInvalidItinerary
        throw new UnsupportedOperationException();
    }
}