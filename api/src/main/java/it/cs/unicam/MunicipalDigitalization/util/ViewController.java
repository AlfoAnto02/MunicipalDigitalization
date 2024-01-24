package it.cs.unicam.MunicipalDigitalization.util;

import it.cs.unicam.MunicipalDigitalization.io.ITourist;
import it.cs.unicam.MunicipalDigitalization.model.Municipality;

/**
 * This class represents the view Controller of the Municipality. It contains the method for get the Information about
 * all the POIs and Itineraries stored in the Municipality
 */
public class ViewController {

    /**
     * The tourist View
     */

    private ITourist touristView;

    /**
     * The municipality associated with the Controller
     */

    private Municipality municipality;

    /**
     * Constructor for the ViewController class.
     * It initializes the ViewController with the provided view and municipality.
     *
     * @param touristView  The tourist view.
     * @param municipality The municipality.
     */

    public ViewController(ITourist touristView, Municipality municipality){
        this.touristView=touristView;
        this.municipality=municipality;
    }

    /**
     * This method returns the general Information of the POIs that are presents in the
     * Municipality
     * @return a string that contains all the general info of the POIs
     */

    public String getPOIsInformation(){
        return this.municipality.getPOIsInformations();
    }

    /**
     * This method returns all the specific details of a POI selected by using the ID that is
     * stored in the Municipality.
     * @param id of the POI
     * @return a string that contains all the specific details of the POI
     */

    public String getPOIDetails(String id){
        return this.municipality.getPOIInformation(id);
    }

    /**
     * This method returns the general Information of the Itineraries that are presents in the
     * Municipality
     * @return a string that contains all the general info of the Itineraries
     */

    public String getItinerariesInformation() {
        return this.municipality.getItinerariesInformations();
    }

    /**
     * This method returns all the specific details of an Itinerary selected by using the ID that is
     * stored in the Municipality.
     * @param id of the Itinerary
     * @return a string that contains all the specific details of the Itinerary
     */

    public String getItineraryDetails(String id) {
        return this.municipality.getItineraryInformation(id);
    }
}
