package it.cs.unicam.MunicipalDigitalization.model;

import java.util.List;

/**
 * Interface that represents an itinerary in the system.
 */
public interface IItinerary {
    
    /**
     * Method to get the list of POIs of the itinerary.
     *
     * @return the list of POIs of the itinerary as a List of IPOI objects.
     */
    List<IPOI> getListOfPOIs();
    
    /**
     * Method to get the name of the itinerary.
     *
     * @return the name of the itinerary as a String.
     */
    String getName();
    
    /**
     * Method to get the ID of the itinerary.
     *
     * @return the ID of the itinerary as a String.
     */
    String getID();
}
