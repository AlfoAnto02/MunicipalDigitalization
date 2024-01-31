package it.cs.unicam.MunicipalDigitalization.util.controllers;

import it.cs.unicam.MunicipalDigitalization.io.TouristView;
import it.cs.unicam.MunicipalDigitalization.model.Municipality;

/**
 * This class represents the view Controller of the Municipality. It contains the method for get the Information about
 * all the POIs and Itineraries stored in the Municipality
 */
public class ViewController {

    /**
     * The tourist View
     */
    private final TouristView touristView;

    /**
     * The municipality associated with the Controller
     */
    private final Municipality municipality;

    /**
     * Constructor for the ViewController class.
     * It initializes the ViewController with the provided view and municipality.
     *
     * @param touristView  The tourist view.
     * @param municipality The municipality.
     */
    public ViewController(TouristView touristView, Municipality municipality) {
        this.touristView = touristView;
        this.municipality = municipality;
    }

    /**
     * This method returns the general Information of the POIs that are presents in the
     * Municipality
     *
     * @return a string that contains all the general info of the POIs
     */
    public String getPOIGeneralInfo() {
        return this.municipality.getPOIGeneralInfo();
    }

    /**
     * This method returns all the specific details of a POI selected by using the ID that is
     * stored in the Municipality.
     *
     * @param id of the POI
     */
    public void getPOIFullInfo(String id) {
        this.municipality.getPOIFullInfo(id);
    }

    /**
     * This method returns the general Information of the Itineraries that are presents in the
     * Municipality
     *
     * @return a string that contains all the general info of the Itineraries
     */
    public String getItinerariesGeneralInfo() {
        return this.municipality.getItinerariesGeneralInfo();
    }

    /**
     * This method returns all the specific details of an Itinerary selected by using the ID that is
     * stored in the Municipality.
     *
     * @param id of the Itinerary
     */
    public void getItineraryFullInfo(String id) {
        this.municipality.getItineraryFullInfo(id);
    }
}
