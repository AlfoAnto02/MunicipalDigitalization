package it.cs.unicam.MunicipalDigitalization.api.model.users;

import it.cs.unicam.MunicipalDigitalization.api.model.Municipality;
import it.cs.unicam.MunicipalDigitalization.api.util.UserRole;
import jakarta.persistence.Entity;

/**
 * This class represents an authenticated tourist in the Municipal Digitalization system.
 * An authenticated tourist is a user that has registered on the platform.
 * When a tourist decides to register, the first role that they will have is the AuthenticatedTourist.
 */
@Entity
public class AuthenticatedTourist extends AbstractAuthenticatedUser {

    /**
     * Constructor for the AuthenticatedTourist class.
     * Initializes the name, password, and municipality of the user and assigns the AUTHENTICATED_TOURIST role.
     *
     * @param name         The name of the AuthenticatedTourist.
     * @param password     The password of the AuthenticatedTourist.
     * @param municipality The municipality of the AuthenticatedTourist.
     */
    public AuthenticatedTourist(String name, String password, Municipality municipality) {
        super(name, password, municipality);
        super.addRole(UserRole.AUTHENTICATED_TOURIST);
    }

    /**
     * Default constructor for the AuthenticatedTourist class.
     * Assigns the AUTHENTICATED_TOURIST role to the user.
     */
    public AuthenticatedTourist() {
        super();
        super.addRole(UserRole.AUTHENTICATED_TOURIST);
    }
}