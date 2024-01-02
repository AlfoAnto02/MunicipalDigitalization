package it.cs.unicam.MunicipalDigitalization.model;

import java.util.List;

/**
 * PendingGestor class that manages the pending Points of Interest (POIs) and Itineraries in the system.
 */
public class PendingGestor {

    /**
     * List of pending POIs in the system.
     */
    private List<PendingPOI> listOfPOIs;

    /**
     * List of pending itineraries in the system.
     */
    private List<PendingItinerary> listOfItineraries;

    /**
     * Method to append a pending POI to the list of POIs.
     *
     * @param pendingPOI the pending POI to be appended as a PendingPOI object.
     */
    public void append(PendingPOI pendingPOI) {
        // TODO - implement append
        throw new UnsupportedOperationException();
    }

    /**
     * Method to append a pending itinerary to the list of itineraries.
     *
     * @param pendingItinerary the pending itinerary to be appended as a PendingItinerary object.
     */
    public void append(PendingItinerary pendingItinerary) {
        // TODO - implement append
        throw new UnsupportedOperationException();
    }

    /**
     * Method to check if a pending POI already exist in the list.
     *
     * @param pendingPOI the pending POI to be checked as a PendingPOI object.
     * @return a boolean indicating the result of the check.
     */
    private boolean check(PendingPOI pendingPOI) {
        // TODO - implement check
        throw new UnsupportedOperationException();
    }

    /**
     * Method to check if a pending itinerary already exist in the list.
     *
     * @param pendingItinerary the pending itinerary to be checked as a PendingItinerary object.
     * @return a boolean indicating the result of the check.
     */
    private boolean check(PendingItinerary pendingItinerary) {
        // TODO - implement check
        throw new UnsupportedOperationException();
    }
}