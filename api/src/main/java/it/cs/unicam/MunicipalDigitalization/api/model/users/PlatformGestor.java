package it.cs.unicam.MunicipalDigitalization.api.model.users;

import it.cs.unicam.MunicipalDigitalization.api.util.UserRole;
import jakarta.persistence.Entity;

/**
 * This class represents a platform gestor in the Municipal Digitalization system.
 * A platform gestor is a type of authenticated user who has the ability to manage the platform.
 * This class extends the AbstractAuthenticatedUser class and is marked as an Entity for persistence.
 */
@Entity
public class PlatformGestor extends AbstractAuthenticatedUser {

    /**
     * Constructor for the PlatformGestor class.
     * Initializes the name and password of the user and assigns the PLATFORM_GESTOR role.
     *
     * @param name     The name of the PlatformGestor.
     * @param password The password of the PlatformGestor.
     */
    public PlatformGestor(String name, String password) {
        super(name, password);
        super.addRole(UserRole.PLATFORM_GESTOR);
    }

    /**
     * Default constructor for the PlatformGestor class.
     * Assigns the PLATFORM_GESTOR role to the user.
     */
    public PlatformGestor() {
        super();
        super.addRole(UserRole.PLATFORM_GESTOR);
    }
}