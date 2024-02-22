package it.cs.unicam.MunicipalDigitalization.api.model.actors;

import it.cs.unicam.MunicipalDigitalization.api.model.Municipality;
import it.cs.unicam.MunicipalDigitalization.api.util.UserRole;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;

/**
 * This class represents a curator, which is a type of authorized contributor.
 * It extends the AuthorizedContributor class.
 * A curator has a view and can validate points of interest (POIs).
 */
@Entity
public class Curator extends AuthorizedContributor {

    /**
     * Constructor for the Curator class.
     *
     * @param name         The name of the curator.
     * @param password     The password of the curator.
     * @param municipality The municipality of the curator.
     */
    public Curator(String name, String password, Municipality municipality) {
        super(name, password, municipality);
        super.addRole(UserRole.CURATOR);
    }

    public Curator() {
        super.addRole(UserRole.CURATOR);
    }

}