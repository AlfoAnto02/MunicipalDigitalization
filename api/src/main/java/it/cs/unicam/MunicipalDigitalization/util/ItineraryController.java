package it.cs.unicam.MunicipalDigitalization.util;

import it.cs.unicam.MunicipalDigitalization.io.IContributorView;
import it.cs.unicam.MunicipalDigitalization.model.IItinerary;
import it.cs.unicam.MunicipalDigitalization.model.IPOI;
import it.cs.unicam.MunicipalDigitalization.model.Municipality;
import it.cs.unicam.MunicipalDigitalization.model.PendingItinerary;

import java.util.List;

/**
 * This class represents the controller for itineraries.
 * It provides methods to get a list of POIs, select a POI to add to an itinerary, get the type of POIs in an itinerary, set the type and name of an itinerary, get a list of pending itineraries, select an itinerary, and validate or invalidate an itinerary.
 */
public class ItineraryController {

    /**
     * The contributor's view.
     */
    private final IContributorView contributorView;

    /**
     * The municipality.
     */
    private final Municipality municipality;

    /**
     * Constructor for the ItineraryController class.
     * It initializes the ItineraryController with the provided view and municipality.
     *
     * @param contributorView The contributor's view.
     * @param municipality    The municipality.
     */
    public ItineraryController(IContributorView contributorView, Municipality municipality) {
        this.contributorView = contributorView;
        this.municipality = municipality;
    }

    /**
     * This method is used to append an itinerary to the Pending manager
     * using the appendItinerary Method of the Municipality.
     *
     * @param itinerary The itinerary to be appended.
     */
    public void append(PendingItinerary itinerary) {
        this.municipality.appendItinerary(itinerary);
    }

    /**
     * This method is used to upload an itinerary to the Municipality.
     *
     * @param itinerary The itinerary to be uploaded.
     */
    public void upload(IItinerary itinerary) {
        this.municipality.getItineraryList().add(itinerary);
    }

    /**
     * This method is used to validate an itinerary.
     *
     * @param itinerary The itinerary to be validated.
     */
    public void validateItinerary(PendingItinerary itinerary) {
        this.municipality.getPendingManager().removeItinerary(itinerary);
        this.municipality.getItineraryList().add(itinerary);
    }

    /**
     * This method is used to invalidate an itinerary.
     *
     * @param itinerary The itinerary to be invalidated.
     */
    public void invalidateItinerary(PendingItinerary itinerary) {
        this.municipality.getPendingManager().removeItinerary(itinerary);
    }

    /**
     * This method is used to get the list of POIs published on the Municipality.
     *
     * @return A list of POIs.
     */
    public List<IPOI> getPOIList() {
        return this.municipality.getPOIList();
    }

    /**
     * This method is used to add a poi to the Itinerary. This method also
     * checks if the POI is already present in the itinerary.
     *
     * @param itinerary The itinerary to which the POI is to be added.
     * @param poi       The POI to be added.
     */
    public void selectPOIToAdd(IItinerary itinerary, IPOI poi) {
        if (itinerary.contains(poi)) itinerary.addPOI(poi);
        else throw new IllegalArgumentException("You already added this POI to your Itinerary !!!");
    }

    /**
     * This method is used to get the type of POIs in an itinerary.
     *
     * @param itinerary The itinerary from which the type of POIs is to be obtained.
     * @return The type of POIs in the itinerary.
     */
    private String getPOIsType(IItinerary itinerary) {
        return itinerary.getPOIs().toString();
    }

    /**
     * This method is used to set the type of itinerary.
     *
     * @param itinerary The itinerary whose type is to be set.
     */
    private void setItineraryType(IItinerary itinerary) {
        itinerary.setTypes(this.getPOIsType(itinerary));
    }

    /**
     * This method is used to set the name of an itinerary.
     *
     * @param itinerary The itinerary whose name is to be set.
     * @param name      The name to be set.
     */
    public void setItineraryName(IItinerary itinerary, String name) {
        itinerary.setName(name);
    }

    /**
     * This method is used to get a list of pending itineraries present
     * in the Pending Manager of the Platfrom.
     *
     * @return A list of pending itineraries.
     */
    public List<PendingItinerary> getPendingItineraryList() {
        return this.municipality.getPendingManager().getPendingItineraries();
    }

    /**
     * This method is used to set the description of an itinerary and its type.
     *
     * @param itinerary The itinerary whose description and type are to be set.
     * @param s         The description to be set.
     */
    public void setItineraryDescription(IItinerary itinerary, String s) {
        itinerary.setDescription(s);
        this.setItineraryType(itinerary);
    }
}