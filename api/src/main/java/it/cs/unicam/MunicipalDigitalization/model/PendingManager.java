package it.cs.unicam.MunicipalDigitalization.model;

import java.util.ArrayList;
import java.util.List;

/**
 * This class manages pending points of interest (POIs) and itineraries.
 * It provides methods to get and set lists of pending POIs and itineraries, get a specific POI or itinerary, and remove a POI or itinerary from the pending lists.
 */
public class PendingManager {

    /**
     * List of pending POIs.
     */
    private ArrayList<PendingPOI> listOfPendingPOI;

    /**
     * List of pending itineraries.
     */
    private ArrayList<PendingItinerary> listOfPendingItinerary;

    private Platform platformRef;

    public PendingManager(Platform platform){
        this.listOfPendingPOI = new ArrayList<>();
        this.listOfPendingItinerary = new ArrayList<>();
        this.platformRef=platform;
    }

    /**
     * This method is used to remove a specific POI from the list of pending POIs.
     *
     * @param poi The POI to remove.
     */
    public void removePOI(PendingPOI poi) {
        this.listOfPendingPOI.remove(poi);
    }


    /**
     * This method is used to remove a specific itinerary from the list of pending itineraries.
     *
     * @param itinerary The itinerary to remove.
     */
    public void removeItinerary(PendingItinerary itinerary) {
        this.listOfPendingItinerary.remove(itinerary);
    }

    /**
     * This method is used to add a specific POI to the list of pending POIs.
     *
     * @param poi The POI to add.
     */
    public void addPOI(PendingPOI poi) {
        this.listOfPendingPOI.add(poi);
    }

    /**
     * This method is used to add a specific itinerary to the list of pending itineraries.
     *
     * @param pendingItinerary The itinerary to add.
     */
    public void addPendingItinerary(PendingItinerary pendingItinerary) {
        this.listOfPendingItinerary.add(pendingItinerary);
    }

    /**
     * This method is used to get the list of pending POIs.
     *
     * @return The list of pending POIs.
     */
    public ArrayList<PendingPOI> getListOfPendingPOI() {
        return this.listOfPendingPOI;
    }

    /**
     * This method is used to set the list of pending POIs.
     *
     * @param listOfPendingPOI The list of pending POIs to be set.
     */
    public void setListOfPendingPOI(ArrayList<PendingPOI> listOfPendingPOI) {
        this.listOfPendingPOI = listOfPendingPOI;
    }

    /**
     * This method is used to get the list of pending itineraries.
     *
     * @return The list of pending itineraries.
     */
    public List<PendingItinerary> getListOfPendingItinerary() {
        return this.listOfPendingItinerary;
    }

    /**
     * This method is used to set the list of pending itineraries.
     *
     * @param listOfPendingItinerary The list of pending itineraries to be set.
     */
    public void setListOfPendingItinerary(ArrayList<PendingItinerary> listOfPendingItinerary) {
        this.listOfPendingItinerary = listOfPendingItinerary;
    }

    /**
     * This method is used to get a specific POI from the list of pending POIs.
     *
     * @param poi The POI to get.
     * @return The requested POI.
     */
    public PendingPOI getPOI(PendingPOI poi) {
        if (this.listOfPendingPOI.contains(poi)) return poi;
        else throw new IllegalArgumentException("Not present");
    }


    /**
     * This method is used to get a specific itinerary from the list of pending itineraries.
     *
     * @param itinerary The itinerary to get.
     * @return The requested itinerary.
     */
    public PendingItinerary getItinerary(PendingItinerary itinerary) {
        if (this.listOfPendingItinerary.contains(itinerary)) return itinerary;
        else throw new IllegalArgumentException("Not present");
    }

}