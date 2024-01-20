package it.cs.unicam.MunicipalDigitalization.io;

import it.cs.unicam.MunicipalDigitalization.util.ID;

import java.util.List;

/**
 * This interface represents an itinerary.
 * It provides methods to get a list of points of interest (POIs), get the id, name, and description of the itinerary, and to check if a POI is in the itinerary.
 * It also provides methods to set the name, description, and types of the itinerary, and to add a POI to the itinerary.
 */
public interface IItinerary {

    /**
     * This method is used to get a list of POIs in the itinerary.
     *
     * @return A list of POIs in the itinerary.
     */
    List<IPOI> getListOfPOIs();

    /**
     * This method is used to get the id of the itinerary.
     *
     * @return The id of the itinerary.
     */
    ID getId();

    /**
     * This method is used to get the name of the itinerary.
     *
     * @return The name of the itinerary.
     */
    String getName();

    /**
     * This method is used to set the name of the itinerary.
     *
     * @param name The new name of the itinerary.
     */
    void setName(String name);

    /**
     * This method is used to get the description of the itinerary.
     *
     * @return The description of the itinerary.
     */
    String getDescription();

    /**
     * This method is used to set the description of the itinerary.
     *
     * @param s The new description of the itinerary.
     */
    void setDescription(String s);

    /**
     * This method is used to check if a POI is in the itinerary.
     *
     * @param poi The POI to check.
     * @return True if the POI is in the itinerary, false otherwise.
     */
    boolean check(IPOI poi);

    /**
     * This method is used to add a POI to the itinerary.
     *
     * @param poi The POI to add.
     */
    void addPOI(IPOI poi);

    /**
     * This method is used to set the types of the itinerary.
     *
     * @param s The new types of the itinerary.
     */
    void setTypes(String s);
}