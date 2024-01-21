package it.cs.unicam.MunicipalDigitalization.model;

import it.cs.unicam.MunicipalDigitalization.util.Coordinates;
import it.cs.unicam.MunicipalDigitalization.util.ID;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This class represents a platform for managing points of interest (POIs) and itineraries.
 * It provides methods to check coordinates and names, append and upload POIs and itineraries, and get lists of POIs.
 */
public class Platform {

    /**
     * The id of the platform.
     */
    private final ID id;

    /**
     * The territory of the platform.
     */
    private final Coordinates territory;

    /**
     * List of POIs.
     */
    private final List<IPOI> listOfPOIs;

    /**
     * List of itineraries.
     */
    private final List<IItinerary> listOfItineraries;

    /**
     * The name of the platform.
     */
    private final String name;

    /**
     * List of users.
     */
    private List<User> listOfUsers;

    /**
     * The manager for pending POIs and itineraries.
     */
    private PendingManager pendingManager;

    /**
     * Constructor for the Platform class.
     * It initializes the Platform with the provided territory.
     *
     * @param territory The territory of the platform.
     */
    public Platform(Coordinates territory, String name) {
        this.name = name;
        this.listOfPOIs = new ArrayList<>();
        this.listOfItineraries = new ArrayList<>();
        this.territory = territory;
        this.id = new ID();
        this.pendingManager = new PendingManager(this);
    }

    /**
     * This method is used to check if the coordinates are valid.
     *
     * @param coordinates The coordinates to check.
     * @return True if the coordinates are valid, false otherwise.
     */
    public boolean checkCoordinates(Coordinates coordinates) {
        // TODO - implement Platform.checkCoordinates
        throw new UnsupportedOperationException();
    }

    /**
     * This method is used to check if the name is valid.
     *
     * @param name The name to check.
     * @return True if the name is valid, false otherwise.
     */
    private boolean checkName(String name) {
        // TODO - implement Platform.checkName
        throw new UnsupportedOperationException();
    }

    /**
     * This method is used to append a pending POI to the list of POIs.
     *
     * @param pendingPOI The pending POI to append.
     */
    public void appendPOI(PendingPOI pendingPOI) {
        this.pendingManager.addPOI(pendingPOI);
    }

    /**
     * This method is used to upload a POI to the platform.
     *
     * @param poi The POI to upload.
     */
    private void uploadPOI(IPOI poi) {
        if (!this.listOfPOIs.contains(poi)) this.listOfPOIs.add(poi);
    }

    /**
     * This method is used to upload an itinerary to the platform.
     *
     * @param itinerary The itinerary to upload.
     */
    private void uploadItinerary(IItinerary itinerary) {
        if (!listOfItineraries.contains(itinerary)) listOfItineraries.add(itinerary);
    }

    /**
     * This method is used to append a pending itinerary to the list of itineraries.
     *
     * @param pendingItinerary The pending itinerary to append.
     */
    public void appendItinerary(PendingItinerary pendingItinerary) {
        this.pendingManager.addPendingItinerary(pendingItinerary);
    }

    /**
     * This method is used to get the list of POIs.
     *
     * @return The list of POIs.
     */
    public List<IPOI> getPOIList() {
        return this.listOfPOIs;
    }


    /**
     * This method is used to get the list of pending POIs.
     *
     * @return The list of pending POIs.
     */
    public List<PendingPOI> getPendingPoiList() {
        return this.pendingManager.getListOfPendingPOI();
    }

    /**
     * This method is used to get the list of pending itineraries.
     *
     * @return The list of pending itineraries.
     */
    public List<PendingItinerary> getPendingItineraryList() {
        return this.pendingManager.getListOfPendingItinerary();
    }

    /**
     * This method is used to get the list of itineraries.
     *
     * @return The list of itineraries.
     */
    public List<IItinerary> getItineraryList() {
        return this.listOfItineraries;
    }

    /**
     * equals of the class based on the ID of the Platform and the territory where is situated
     * @param o
     * @return true if they are equals.
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Platform platform = (Platform) o;
        return Objects.equals(id, platform.id) && Objects.equals(territory, platform.territory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, territory);
    }
}