package it.cs.unicam.MunicipalDigitalization.api.util.controllers;

import it.cs.unicam.MunicipalDigitalization.api.io.TouristView;
import it.cs.unicam.MunicipalDigitalization.api.model.Municipality;
import it.cs.unicam.MunicipalDigitalization.api.model.elements.AbstractMunicipalElement;

/**
 * This class is responsible for controlling the view of the tourist.
 * It provides methods to get points of interest (POIs), itineraries, and contents in a municipality.
 */
public class ViewController {

    /**
     * The TouristView instance used to control the view.
     */
    private final TouristView touristView;

    /**
     * The Municipality instance representing the municipality being viewed.
     */
    private final Municipality municipality;

    /**
     * The AbstractMunicipalElement instance representing the municipal element being viewed.
     */
    private AbstractMunicipalElement municipalElement;

    /**
     * Constructor for the ViewController class.
     *
     * @param touristView  The TouristView instance used to control the view.
     * @param municipality The Municipality instance representing the municipality being viewed.
     */
    public ViewController(TouristView touristView, Municipality municipality) {
        this.touristView = touristView;
        this.municipality = municipality;
    }

    /**
     * This method is used to get the POIs in the municipality.
     *
     * @return The POIs in the municipality as a string.
     */
    public String getPOIs() {
        return this.municipality.getPOIs();
    }

    /**
     * This method is used to get full information about a POI in the municipality.
     *
     * @param id The id of the POI.
     */
    public void getPOIFullInfo(String id) {
        this.municipality.getPOIFullInfo(id);
    }

    /**
     * This method is used to get the itineraries in the municipality.
     *
     * @return The itineraries in the municipality as a string.
     */
    public String getItineraries() {
        return this.municipality.getItineraries();
    }

    /**
     * This method is used to get full information about an itinerary in the municipality.
     *
     * @param id The id of the itinerary.
     */
    public void getItineraryFullInfo(String id) {
        this.municipality.getItineraryFullInfo(id);
    }

    /**
     * This method is used to get the contents in the municipal element.
     *
     * @param id The id of the content.
     * @return The contents in the municipal element as a string.
     */
    public String getContents(String id) {
        return this.municipalElement.getContent(id);
    }

    /**
     * This method is used to get full information about a content in the municipal element.
     *
     * @param id The id of the content.
     */
    public void getContentFullInfo(String id) {
        this.municipalElement.getContentFullInfo(id);
    }
}