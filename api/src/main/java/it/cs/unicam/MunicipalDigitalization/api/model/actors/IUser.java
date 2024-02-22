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
    Long getId();

    /**
     * This method is used to get the municipality associated with the user.
     *
     * @return The municipality associated with the user.
     */
    Municipality getMunicipality();
}