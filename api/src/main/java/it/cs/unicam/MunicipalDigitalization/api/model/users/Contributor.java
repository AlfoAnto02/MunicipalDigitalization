package it.cs.unicam.MunicipalDigitalization.api.model.users;

import it.cs.unicam.MunicipalDigitalization.api.model.Municipality;
import it.cs.unicam.MunicipalDigitalization.api.util.UserRole;
import jakarta.persistence.Entity;

/**
 * This class represents a contributor in the Municipal Digitalization system.
 * A contributor is an authenticated user who can create pending points of interest (POIs) and itineraries.
 * This class extends the AbstractAuthenticatedUser class and is marked as an Entity for persistence.
 */
@Entity
public class Contributor extends AbstractAuthenticatedUser {

    /**
     * Constructor for the Contributor class.
     * Initializes the name, password, and municipality of the user and assigns the CONTRIBUTOR role.
     *
     * @param name         The name of the Contributor.
     * @param password     The password of the Contributor.
     * @param municipality The municipality of the Contributor.
     */
    public Contributor(String name, String password, Municipality municipality) {
        super(name, password, municipality);
        super.addRole(UserRole.CONTRIBUTOR);
    }

    /**
     * Default constructor for the Contributor class.
     * Assigns the CONTRIBUTOR role to the user.
     */
    public Contributor() {
        super();
        super.addRole(UserRole.CONTRIBUTOR);
    }
}