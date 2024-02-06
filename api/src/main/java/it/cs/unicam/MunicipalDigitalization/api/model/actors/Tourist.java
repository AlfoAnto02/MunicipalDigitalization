package it.cs.unicam.MunicipalDigitalization.api.model.actors;

import it.cs.unicam.MunicipalDigitalization.api.model.Municipality;
import it.cs.unicam.MunicipalDigitalization.api.util.UserRole;

/**
 * This class represents a general IUser of the Platform.
 * Every IUser of the Platform/Municipality have the possibility to View the POIs
 * presents in the Platform, the Itineraries and the Map.
 * Every other type of IUser have to extends this behavior.
 */
public class Tourist extends AbstractIUser {

    /**
     * Role of the actor -> Tourist
     */
    private final UserRole role = UserRole.TOURIST;


    /**
     * Constructor for the AbstractIUser class.
     *
     * @param municipality The municipality of the user.
     */
    public Tourist(Municipality municipality) {
        super(municipality);
    }

    /**
     *
     * @return the role of the Actor.
     */
    public UserRole getRole() {
        return role;
    }
}
