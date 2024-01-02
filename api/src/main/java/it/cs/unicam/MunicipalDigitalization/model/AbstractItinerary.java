package it.cs.unicam.MunicipalDigitalization.model;

import java.util.List;

/**
 * Abstract class that represents an itinerary in the system.
 */
public abstract class AbstractItinerary implements IItinerary {

    /**
     * The ID of the itinerary.
     */
    protected final String id;
    /**
     * The list of POIs of the itinerary.
     */
    protected List<IPOI> listOfPOIs;
    /**
     * The name of the itinerary.
     */
    protected String name;

    /**
     * The constructor of the AbstractItinerary class.
     *
     * @param listOfPOIs the list of POIs of the itinerary as a List of IPOI objects.
     * @param name       the name of the itinerary as a String.
     * @param id         the ID of the itinerary as a String.
     */
    public AbstractItinerary(List<IPOI> listOfPOIs, String name, String id) {
        this.listOfPOIs = listOfPOIs;
        this.name = name;
        this.id = id;
    }

    public List<IPOI> getListOfPOIs() {
        return this.listOfPOIs;
    }

    public String getName() {
        return this.name;
    }

    public String getID() {
        return this.id;
    }
}
