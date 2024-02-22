package it.cs.unicam.MunicipalDigitalization.api.model.actors;

import it.cs.unicam.MunicipalDigitalization.api.model.Municipality;
import it.cs.unicam.MunicipalDigitalization.api.util.UserRole;
import jakarta.persistence.Entity;

/**
 * This class represents a platform gestor, which is a type of authenticated user.
 * It extends the AbstractAuthenticatedUser class.
 * A platform gestor has a name and a password and can manage the platform.
 */
@Entity
public class PlatformGestor extends AbstractAuthenticatedUser{

    /**
     * Constructor for the PlatformGestor class.
     *
     * @param name         The name of the platform gestor.
     * @param password     The password of the platform gestor.
     */
    public PlatformGestor(String name, String password) {
        super(name, password);
        super.addRole(UserRole.PLATFORM_GESTOR);
    }

    public PlatformGestor() {
        super.addRole(UserRole.PLATFORM_GESTOR);
    }

}
