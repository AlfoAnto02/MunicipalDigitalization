package it.cs.unicam.MunicipalDigitalization.api.util.controllers;

import it.cs.unicam.MunicipalDigitalization.api.io.TouristView;
import it.cs.unicam.MunicipalDigitalization.api.model.Municipality;
import it.cs.unicam.MunicipalDigitalization.api.model.elements.AbstractMunicipalElement;
import it.cs.unicam.MunicipalDigitalization.api.model.elements.IItinerary;
import it.cs.unicam.MunicipalDigitalization.api.model.elements.IMunicipalElement;
import it.cs.unicam.MunicipalDigitalization.api.model.elements.IPOI;

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
    
    private AbstractMunicipalElement municipalElement;

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
    public String getPOIs() {
        return this.municipality.getPOIs();
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
    public String getItineraries() {
        return this.municipality.getItineraries();
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
    
    public String getContents(String id) {
        return this.municipalElement.getContent(id);
    }

    public void getMunicipalities() {
        //TODO Implement this method
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
