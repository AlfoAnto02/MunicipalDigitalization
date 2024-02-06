package it.cs.unicam.MunicipalDigitalization.api.model;

import it.cs.unicam.MunicipalDigitalization.api.model.actors.IUser;
import it.cs.unicam.MunicipalDigitalization.api.model.elements.*;
import it.cs.unicam.MunicipalDigitalization.api.util.Coordinate;
import it.cs.unicam.MunicipalDigitalization.api.util.ID;
import it.cs.unicam.MunicipalDigitalization.api.util.PendingManager;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a Municipality in the system.
 * It contains information about the municipality and methods to manage its points of interest,
 * itineraries, and contents.
 */
public class Municipality {

    /**
     * The unique ID of the municipality.
     */
    private final ID id;

    /**
     * The geographical coordinates of the municipality.
     */
    private final Coordinate territory;

    /**
     * The list of points of interest in the municipality.
     */
    private final List<IPOI> listOfPOIs;

    /**
     * The list of itineraries in the municipality.
     */
    private final List<IItinerary> listOfItineraries;

    /**
     * The name of the municipality.
     */
    private final String name;

    /**
     * The manager for pending operations in the municipality.
     */
    @Getter
    private final PendingManager pendingManager;

    /**
     * The list of users in the municipality.
     */
    private List<IUser> listOfIUsers;

    /**
     * Constructor for the Municipality class.
     *
     * @param territory The geographical coordinates of the municipality.
     * @param name      The name of the municipality.
     */
    public Municipality(Coordinate territory, String name) {
        this.name = name;
        this.listOfPOIs = new ArrayList<>();
        this.listOfItineraries = new ArrayList<>();
        this.territory = territory;
        this.id = new ID();
        this.pendingManager = new PendingManager(this);
    }

    /**
     * This method is used to check if a coordinate is within the territory of the municipality.
     *
     * @param coordinate The coordinate to check.
     * @return True if the coordinate is within the territory, false otherwise.
     */
    public boolean checkCoordinates(Coordinate coordinate) {
        // TODO - implement Municipality.checkCoordinates
        throw new UnsupportedOperationException();
    }

    /**
     * This method is used to check if a name is valid for the municipality.
     *
     * @param name The name to check.
     * @return True if the name is valid, false otherwise.
     */
    private boolean checkName(String name) {
        // TODO - implement Municipality.checkName
        throw new UnsupportedOperationException();
    }

    /**
     * This method is used to add a pending point of interest to the pending manager.
     *
     * @param pendingPOI The pending point of interest to add.
     */
    public void appendPOI(PendingPOI pendingPOI) {
        this.pendingManager.addPendingPOI(pendingPOI);
    }

    /**
     * This method is used to upload a point of interest to the municipality.
     *
     * @param poi The point of interest to upload.
     */
    public void uploadPOI(IPOI poi) {
        if (!this.listOfPOIs.contains(poi)) this.listOfPOIs.add(poi);
    }

    /**
     * This method is used to add a pending itinerary to the pending manager.
     *
     * @param pendingItinerary The pending itinerary to add.
     */
    public void appendItinerary(PendingItinerary pendingItinerary) {
        this.pendingManager.addPendingItinerary(pendingItinerary);
    }

    /**
     * This method is used to upload an itinerary to the municipality.
     *
     * @param itinerary The itinerary to upload.
     */
    public void uploadItinerary(IItinerary itinerary) {
        if (!listOfItineraries.contains(itinerary)) listOfItineraries.add(itinerary);
    }

    /**
     * This method is used to add a pending content to the pending manager.
     *
     * @param pendingContent The pending content to add.
     */
    public void appendContent(PendingContent pendingContent) {
        this.pendingManager.addPendingContent(pendingContent);
    }

    /**
     * This method is used to upload a content to the municipality.
     *
     * @param content The content to upload.
     */
    public void uploadContent(IContent content) {
        if (!content.getReferredMunicipalElement().listOfContents().contains(content)) {
            content.getReferredMunicipalElement().listOfContents().add(content);
        }
    }

    /**
     * This method is used to get the list of points of interest in the municipality.
     *
     * @return The list of points of interest in the municipality.
     */
    public List<IPOI> getPOIList() {
        return this.listOfPOIs;
    }

    /**
     * This method is used to get the list of itineraries in the municipality.
     *
     * @return The list of itineraries in the municipality.
     */
    public List<IItinerary> getItineraryList() {
        return this.listOfItineraries;
    }

    /**
     * This method is used to get full information about a point of interest in the municipality.
     *
     * @param id The id of the point of interest.
     * @return Full information about the point of interest.
     */
    public String getPOIFullInfo(String id) {
        // TODO - implement Municipality.getPOIFullInfo
        throw new UnsupportedOperationException();
    }

    /**
     * This method is used to get full information about an itinerary in the municipality.
     *
     * @param id The id of the itinerary.
     * @return Full information about the itinerary.
     */
    public String getItineraryFullInfo(String id) {
        // TODO - implement Municipality.getItineraryFullInfo
        throw new UnsupportedOperationException();
    }

    /**
     * This method is used to get full information about a content in the municipality.
     *
     * @param id The id of the content.
     * @return Full information about the content.
     */
    public String getContentFullInfo(String id) {
        // TODO - implement Municipality.getContentFullInfo
        throw new UnsupportedOperationException();
    }

    /**
     * This method is used to get a string representation of the points of interest in the municipality.
     *
     * @return A string representation of the points of interest in the municipality.
     */
    public String getPOIs() {
        StringBuilder element = new StringBuilder();
        for (IPOI p : this.listOfPOIs) {
            element.append("Name: ").append(p.getName()).append("\nID: ").append(p.getId()).append("\n\n");
        }

        return element.toString();
    }

    /**
     * This method is used to get a string representation of the itineraries in the municipality.
     *
     * @return A string representation of the itineraries in the municipality.
     */
    public String getItineraries() {
        StringBuilder element = new StringBuilder();
        for (IItinerary i : this.listOfItineraries) {
            element.append("Name: ").append(i.getName()).append("\nID: ").append(i.getId()).append("\n\n");
        }
        return element.toString();
    }
}