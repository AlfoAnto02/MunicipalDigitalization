package it.cs.unicam.MunicipalDigitalization.api.model.actors;

import it.cs.unicam.MunicipalDigitalization.api.model.Municipality;
import it.cs.unicam.MunicipalDigitalization.api.util.UserRole;
import jakarta.persistence.Entity;

/**
 * This class represents an authenticated tourist, which is a type of user that Registered On the Platform
 * The authenticatedTourist is the lowest level of user.
 * When a Tourist decide to Register the first Role that he will have is the AuthenticatedTourist.
 */
@Entity
public class AuthenticatedTourist  extends AbstractAuthenticatedUser{


    /**
     * Constructor for the AuthenticatedTourist class.
     *
     * @param name name of the AuthenticatedTourist
     * @param password password of the AuthenticatedTourist
     * @param municipality municipality of the AuthenticatedTourist
     */
        public AuthenticatedTourist(String name, String password, Municipality municipality) {
            super(name, password, municipality);
            super.addRole(UserRole.AUTHENTICATED_TOURIST);
        }

        public AuthenticatedTourist() {
            super();
            super.addRole(UserRole.AUTHENTICATED_TOURIST);
        }
}
