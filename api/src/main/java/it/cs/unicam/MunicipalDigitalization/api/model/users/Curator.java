package it.cs.unicam.MunicipalDigitalization.api.model.users;

import it.cs.unicam.MunicipalDigitalization.api.model.Municipality;
import it.cs.unicam.MunicipalDigitalization.api.util.UserRole;
import jakarta.persistence.Entity;

/**
 * This class represents a curator in the Municipal Digitalization system.
 * A curator is an authorized contributor who has the ability to validate points of interest (POIs).
 * This class extends the AuthorizedContributor class and is marked as an Entity for persistence.
 */
@Entity
public class Curator extends AuthorizedContributor {

    /**
     * Constructor for the Curator class.
     * Initializes the name, password, and municipality of the user and assigns the CURATOR role.
     *
     * @param name         The name of the Curator.
     * @param password     The password of the Curator.
     * @param municipality The municipality of the Curator.
     */
    public Curator(String name, String password, Municipality municipality) {
        super(name, password, municipality);
        super.addRole(UserRole.CURATOR);
    }

    /**
     * Default constructor for the Curator class.
     * Assigns the CURATOR role to the user.
     */
    public Curator() {
        super();
        super.addRole(UserRole.CURATOR);
    }
}