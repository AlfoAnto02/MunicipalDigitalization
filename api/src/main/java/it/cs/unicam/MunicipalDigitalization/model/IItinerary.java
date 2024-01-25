package it.cs.unicam.MunicipalDigitalization.model;


import java.util.List;

/**
 * This interface represents an itinerary.
 * It provides methods to get a list of points of interest (POIs), get the id, name, and description of the itinerary, and to contains if a POI is in the itinerary.
 * It also provides methods to set the name, description, and types of the itinerary, and to add a POI to the itinerary.
 */
public interface IItinerary extends IMunicipalElement {

    /**
     * This method is used to get a list of POIs in the itinerary.
     *
     * @return A list of POIs in the itinerary.
     */
    List<IPOI> getPOIs();

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
     * This method is used to contains if a POI is in the itinerary.
     *
     * @param poi The POI to contains.
     * @return True if the POI is in the itinerary, false otherwise.
     */
    boolean contains(IPOI poi);

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