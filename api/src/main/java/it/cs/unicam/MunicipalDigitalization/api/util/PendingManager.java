package it.cs.unicam.MunicipalDigitalization.api.util;

import it.cs.unicam.MunicipalDigitalization.api.model.Municipality;
import it.cs.unicam.MunicipalDigitalization.api.model.elements.PendingContent;
import it.cs.unicam.MunicipalDigitalization.api.model.elements.PendingItinerary;
import it.cs.unicam.MunicipalDigitalization.api.model.elements.PendingPOI;

import java.util.ArrayList;
import java.util.List;

/**
 * This class manages pending points of interest (POIs) and itineraries.
 * It provides methods to get and set lists of pending POIs and itineraries, get a specific POI or itinerary, and remove a POI or itinerary from the pending lists.
 */
public class PendingManager {

    /**
     * The municipality of the pending manager.
     */
    private final Municipality municipalityRef;
    /**
     * List of pending contents.
     */
    private final ArrayList<PendingContent> pendingContents;
    /**
     * List of pending POIs.
     */
    private ArrayList<PendingPOI> pendingPOIs;
    /**
     * List of pending itineraries.
     */
    private ArrayList<PendingItinerary> pendingItineraries;

    /**
     * Constructor for the PendingManager class.
     * It initializes the PendingManager with the provided municipality.
     *
     * @param municipality The municipality of the pending manager.
     */
    public PendingManager(Municipality municipality) {
        this.pendingPOIs = new ArrayList<>();
        this.pendingItineraries = new ArrayList<>();
        this.pendingContents = new ArrayList<>();
        this.municipalityRef = municipality;
    }

    /**
     * This method is used to remove a specific POI from the list of pending POIs.
     *
     * @param poi The POI to remove.
     */
    public void removePOI(PendingPOI poi) {
        this.pendingPOIs.remove(poi);
    }

    /**
     * This method is used to remove a specific itinerary from the list of pending itineraries.
     *
     * @param itinerary The itinerary to remove.
     */
    public void removeItinerary(PendingItinerary itinerary) {
        this.pendingItineraries.remove(itinerary);
    }

    /**
     * This method is used to remove a specific content from the list of pending contents.
     *
     * @param content The content to remove.
     */
    public void removeContent(PendingContent content) {
        this.pendingContents.remove(content);
    }

    /**
     * This method is used to add a specific POI to the list of pending POIs.
     *
     * @param poi The POI to add.
     */
    public void addPendingPOI(PendingPOI poi) {
        this.pendingPOIs.add(poi);
    }

    /**
     * This method is used to add a specific itinerary to the list of pending itineraries.
     *
     * @param pendingItinerary The itinerary to add.
     */
    public void addPendingItinerary(PendingItinerary pendingItinerary) {
        this.pendingItineraries.add(pendingItinerary);
    }

    /**
     * This method is used to add a specific content to the list of pending contents.
     *
     * @param pendingContent The content to add.
     */
    public void addPendingContent(PendingContent pendingContent) {
        this.pendingContents.add(pendingContent);
    }

    /**
     * This method is used to get the list of pending POIs.
     *
     * @return The list of pending POIs.
     */
    public ArrayList<PendingPOI> getPendingPOIs() {
        return this.pendingPOIs;
    }

    /**
     * This method is used to set the list of pending POIs.
     *
     * @param pendingPOIs The list of pending POIs to be set.
     */
    public void setPendingPOIs(ArrayList<PendingPOI> pendingPOIs) {
        this.pendingPOIs = pendingPOIs;
    }

    /**
     * This method is used to get the list of pending itineraries.
     *
     * @return The list of pending itineraries.
     */
    public List<PendingItinerary> getPendingItineraries() {
        return this.pendingItineraries;
    }

    /**
     * This method is used to set the list of pending itineraries.
     *
     * @param pendingItineraries The list of pending itineraries to be set.
     */
    private void setPendingItineraries(ArrayList<PendingItinerary> pendingItineraries) {
        this.pendingItineraries = pendingItineraries;
    }

    /**
     * This method is used to get the list of pending contents.
     *
     * @return The list of pending contents.
     */
    public List<PendingContent> getPendingContents() {
        return this.pendingContents;
    }

    /**
     * This method is used to get a specific POI from the list of pending POIs.
     *
     * @param poi The POI to get.
     * @return The requested POI.
     */
    public PendingPOI getPOI(PendingPOI poi) {
        if (this.pendingPOIs.contains(poi)) return poi;
        else throw new IllegalArgumentException("Not present");
    }

    /**
     * This method is used to get a specific itinerary from the list of pending itineraries.
     *
     * @param itinerary The itinerary to get.
     * @return The requested itinerary.
     */
    public PendingItinerary getItinerary(PendingItinerary itinerary) {
        if (this.pendingItineraries.contains(itinerary)) return itinerary;
        else throw new IllegalArgumentException("Not present");
    }
}