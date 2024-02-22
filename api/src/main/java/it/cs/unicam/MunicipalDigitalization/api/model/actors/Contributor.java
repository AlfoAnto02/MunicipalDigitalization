package it.cs.unicam.MunicipalDigitalization.api.model.actors;

import it.cs.unicam.MunicipalDigitalization.api.model.Municipality;
import it.cs.unicam.MunicipalDigitalization.api.util.UserRole;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;

/**
 * This class represents a contributor, which is a type of user.
 * It extends the AbstractIUser class.
 * A contributor has a view and can create pending points of interest (POIs) and itineraries.
 */
@Entity
public class Contributor extends AbstractAuthenticatedUser {

    /**
     * Constructor for the Contributor class.
     *
     * @param name         The name of the contributor.
     * @param password     The password of the contributor.
     * @param municipality The municipality of the contributor.
     */

    public Contributor(String name, String password, Municipality municipality) {
        super(name, password, municipality);
        super.addRole(UserRole.CONTRIBUTOR);
    }

    public Contributor() {
        super.addRole(UserRole.CONTRIBUTOR);
    }

}