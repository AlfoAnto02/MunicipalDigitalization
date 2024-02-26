package it.cs.unicam.MunicipalDigitalization.api.model.users;

import it.cs.unicam.MunicipalDigitalization.api.model.Municipality;
import it.cs.unicam.MunicipalDigitalization.api.util.UserRole;

/**
 * This class represents a tourist user in the Municipal Digitalization system.
 * It extends the AbstractIUser class and automatically assigns the TOURIST role to the user upon creation.
 */
public class Tourist extends AbstractIUser {

    /**
     * Constructor for the Tourist class.
     * Initializes the municipality of the user and assigns the TOURIST role.
     *
     * @param municipality The municipality of the user.
     */
    public Tourist(Municipality municipality) {
        super(municipality);
        super.addRole(UserRole.TOURIST);
    }

    /**
     * Default constructor for the Tourist class.
     * Assigns the TOURIST role to the user.
     */
    public Tourist() {
        super.addRole(UserRole.TOURIST);
    }
}