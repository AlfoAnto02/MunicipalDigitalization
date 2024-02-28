package it.cs.unicam.MunicipalDigitalization.api.model.users;

import it.cs.unicam.MunicipalDigitalization.api.model.Municipality;
import it.cs.unicam.MunicipalDigitalization.api.util.UserRole;
import jakarta.persistence.Entity;

/**
 * This class represents an animator.
 * The animator can create ContributionContest and manage them.
 * He is a type of AuthenticatedUser
 */
@Entity
public class Animator extends AbstractAuthenticatedUser {

    /**
     * Constructor for the Animator class.
     * Initializes the name, password, and municipality of the user and assigns the ANIMATOR role.
     *
     * @param name         The name of the Animator.
     * @param password     The password of the Animator.
     * @param municipality The municipality of the Animator.
     */
    public Animator(String name, String password, Municipality municipality) {
        super(name, password, municipality);
        super.addRole(UserRole.ANIMATOR);
    }

    /**
     * Default constructor for the Animator class.
     * Assigns the ANIMATOR role to the user.
     */
    public Animator() {
        super();
        super.addRole(UserRole.ANIMATOR);
    }
}
