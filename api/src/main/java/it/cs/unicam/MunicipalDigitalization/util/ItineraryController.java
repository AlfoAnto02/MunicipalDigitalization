package it.cs.unicam.MunicipalDigitalization.util;

import it.cs.unicam.MunicipalDigitalization.io.IContributorsView;
import it.cs.unicam.MunicipalDigitalization.model.IItinerary;
import it.cs.unicam.MunicipalDigitalization.model.IPOI;
import it.cs.unicam.MunicipalDigitalization.model.PendingItinerary;
import it.cs.unicam.MunicipalDigitalization.model.Platform;

import java.util.List;

/**
 * This class represents the controller for itineraries.
 * It provides methods to get a list of POIs, select a POI to add to an itinerary, get the type of POIs in an itinerary, set the type and name of an itinerary, get a list of pending itineraries, select an itinerary, and validate or invalidate an itinerary.
 */
public class ItineraryController {

    /**
     * The contributor's view.
     */
    private final IContributorsView contributorView;
    /**
     * The platform.
     */
    private final Platform platform;

    /**
     * Constructor for the ItineraryController class.
     * It initializes the ItineraryController with the provided view and platform.
     * @param contributorView The contributor's view.
     * @param platform        The platform.
     */
    public ItineraryController(IContributorsView contributorView, Platform platform) {
        this.contributorView = contributorView;
        this.platform = platform;
    }

    /**
     * This method is used to append an itinerary to the Pending manager
     * using the appendItinerary Method of the Platform.
     *
     * @param itinerary The itinerary to be appended.
     */
    public void append(PendingItinerary itinerary) {
        this.platform.appendItinerary(itinerary);
    }


    /**
     * This method is used to validate an itinerary.
     *
     * @param itinerary The itinerary to be validated.
     */
    public void validateItinerary(PendingItinerary itinerary) {
        this.platform.getPendingItineraryList().remove(itinerary);
        this.platform.getItineraryList().add(itinerary);
    }

    /**
     * This method is used to invalidate an itinerary.
     *
     * @param itinerary The itinerary to be invalidated.
     */
    public void invalidateItinerary(PendingItinerary itinerary) {
        this.platform.getPendingItineraryList().remove(itinerary);
    }

    /**
     * This method is used to get the list of POIs published on the Platform.
     *
     * @return A list of POIs.
     */
    public List<IPOI> getPOIList() {
        return this.platform.getPOIList();
    }

    /**
     * This method is used to add a poi to the Itinerary. This method also
     * checks if the POI is already present in the itinerary.
     *
     * @param itinerary The itinerary to which the POI is to be added.
     * @param poi       The POI to be added.
     */
    public void selectPOIToAdd(IItinerary itinerary, IPOI poi) {
        if (itinerary.check(poi)) itinerary.addPOI(poi);
        else throw new IllegalArgumentException("You already added this POI to your Itinerary !!!");
    }

    /**
     * This method is used to get the type of POIs in an itinerary.
     *
     * @param itinerary The itinerary from which the type of POIs is to be obtained.
     * @return The type of POIs in the itinerary.
     */
    private String getPOIsType(IItinerary itinerary) {
        return itinerary.getListOfPOIs().toString();
    }

    /**
     * This method is used to set the type of itinerary.
     *
     * @param itinerary The itinerary whose type is to be set.
     */
    public void setItineraryType(IItinerary itinerary) {
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
        return this.platform.getPendingItineraryList();
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