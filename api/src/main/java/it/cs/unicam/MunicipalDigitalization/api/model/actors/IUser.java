package it.cs.unicam.MunicipalDigitalization.api.model.actors;

import it.cs.unicam.MunicipalDigitalization.api.model.Municipality;
import it.cs.unicam.MunicipalDigitalization.api.util.ID;

/**
 * This interface represents a user in the system.
 * It provides methods to get the id and the municipality associated with the IUser.
 * It also has the responsibility to permit the View of a POI or an Itinerary present
 * in the Municipality.
 */
public interface IUser {

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

    /**
     * This method is used to view the content of the Municipal Elements.
     */
    void viewContents(String id);

    /**
     * This method is used to get the map of the Municipality.
     */
    void getMap(String id);
}