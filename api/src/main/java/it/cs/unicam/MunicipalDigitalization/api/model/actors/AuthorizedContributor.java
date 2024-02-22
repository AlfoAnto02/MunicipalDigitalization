package it.cs.unicam.MunicipalDigitalization.api.model.actors;

import it.cs.unicam.MunicipalDigitalization.api.model.Municipality;
import it.cs.unicam.MunicipalDigitalization.api.util.UserRole;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;

/**
 * This class represents an authorized contributor, which is a type of user.
 * It extends the AbstractIUser class.
 * An authorized contributor has a view and can create points of interest (POIs) and itineraries.
 */
@Entity
public class AuthorizedContributor extends AbstractAuthenticatedUser {

    /**
     * Constructor for the AuthorizedContributor class.
     *
     * @param name         The name of the authorized contributor.
     * @param password     The password of the authorized contributor.
     * @param municipality The municipality of the authorized contributor.
     */
    public AuthorizedContributor(String name, String password, Municipality municipality) {
        super(name, password, municipality);
        super.addRole(UserRole.AUTHORIZED_CONTRIBUTOR);
    }

    public AuthorizedContributor() {
        super.addRole(UserRole.AUTHORIZED_CONTRIBUTOR);
    }
}