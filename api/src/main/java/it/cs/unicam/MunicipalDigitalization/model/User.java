package it.cs.unicam.MunicipalDigitalization.model;

import it.cs.unicam.MunicipalDigitalization.util.ID;

/**
 * This interface represents a user in the system.
 * It provides methods to get the id and the municipality associated with the User.
 * It also has the responsibility to permit the View of a POI or an Itinerary present
 * in the Municipality.
 */
public interface User {

    /**
     * This method is used to get the id of the user.
     *
     * @return The id of the user.
     */
    ID getId();

    /**
     * This method is used to get the municipality associated with the user.
     *
     * @return The municipality associated with the user.
     */
    Municipality getMunicipality();

    /**
     * This method is used to view the details of a POI present in the Platform.
     */
    void viewPOI();

    /**
     * This method is used to view the details of an Itinerary present in the Platform.
     */
    void viewItinerary();


}