package it.cs.unicam.MunicipalDigitalization.api.io;

import it.cs.unicam.MunicipalDigitalization.api.model.elements.IContent;
import it.cs.unicam.MunicipalDigitalization.api.model.elements.IItinerary;
import it.cs.unicam.MunicipalDigitalization.api.model.elements.IPOI;
import it.cs.unicam.MunicipalDigitalization.api.util.controllers.ContentController;
import it.cs.unicam.MunicipalDigitalization.api.util.controllers.ItineraryController;
import it.cs.unicam.MunicipalDigitalization.api.util.controllers.POIController;

/**
 * This interface represents the view of a contributor.
 * It provides methods to create points of interest (POIs), itineraries and content\
 * it also provides to get the POI, itinerary and content controllers.
 */
public interface IContributorView {

    /**
     * This method is used to create a point of interest (POI).
     * The implementation should handle the creation of a new POI.
     *
     * @param poi The POI to be created.
     */
    void createPOI(IPOI poi);

    /**
     * This method is used to create an itinerary.
     * The implementation should handle the creation of a new itinerary.
     *
     * @param itinerary The itinerary to be created.
     */
    void createItinerary(IItinerary itinerary);

    /**
     * This method is used to create a content.
     * The implementation should handle the creation of a new content.
     *
     * @param content The content to be created.
     */
    void createContent(IContent content);

    /**
     * This method is used to get the POI controller.
     * The implementation should return the POI controller.
     *
     * @return The POI controller.
     */
    POIController getPOIController();

    /**
     * This method is used to get the itinerary controller.
     * The implementation should return the itinerary controller.
     *
     * @return The itinerary controller.
     */
    ItineraryController getItineraryController();

    /**
     * This method is used to get the content controller.
     * The implementation should return the content controller.
     *
     * @return The content controller.
     */
    ContentController getContentController();

    /**
     * This method is used to get string input from the user.
     *
     * @param message The message to be displayed to the user.
     * @return The string input from the user.
     */
    String getStringInput(String message);
}