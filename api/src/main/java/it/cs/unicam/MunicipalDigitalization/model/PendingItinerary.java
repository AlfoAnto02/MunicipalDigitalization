package it.cs.unicam.MunicipalDigitalization.model;

import java.util.List;

/**
 * Class that represents a pending itinerary in the system.
 */
public class PendingItinerary extends AbstractItinerary {

    /**
     * The constructor of the AbstractItinerary class.
     *
     * @param listOfPOIs the list of POIs of the itinerary as a List of IPOI objects.
     * @param name       the name of the itinerary as a String.
     * @param id         the ID of the itinerary as a String.
     */
    public PendingItinerary(List<IPOI> listOfPOIs, String name, String id) {
        super(listOfPOIs, name, id);
    }
}
