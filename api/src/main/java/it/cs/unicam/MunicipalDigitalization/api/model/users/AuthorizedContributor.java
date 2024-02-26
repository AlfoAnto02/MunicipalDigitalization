package it.cs.unicam.MunicipalDigitalization.api.model.users;

import it.cs.unicam.MunicipalDigitalization.api.model.Municipality;
import it.cs.unicam.MunicipalDigitalization.api.util.UserRole;
import jakarta.persistence.Entity;

/**
 * This class represents an authorized contributor in the Municipal Digitalization system.
 * An authorized contributor is a user who has been granted additional permissions to create points of interest (POIs) and itineraries.
 * This class extends the AbstractAuthenticatedUser class and is marked as an Entity for persistence.
 */
@Entity
public class AuthorizedContributor extends AbstractAuthenticatedUser {

    /**
     * Constructor for the AuthorizedContributor class.
     * Initializes the name, password, and municipality of the user and assigns the AUTHORIZED_CONTRIBUTOR role.
     *
     * @param name         The name of the AuthorizedContributor.
     * @param password     The password of the AuthorizedContributor.
     * @param municipality The municipality of the AuthorizedContributor.
     */
    public AuthorizedContributor(String name, String password, Municipality municipality) {
        super(name, password, municipality);
        super.addRole(UserRole.AUTHORIZED_CONTRIBUTOR);
    }

    /**
     * Default constructor for the AuthorizedContributor class.
     * Assigns the AUTHORIZED_CONTRIBUTOR role to the user.
     */
    public AuthorizedContributor() {
        super();
        super.addRole(UserRole.AUTHORIZED_CONTRIBUTOR);
    }
}