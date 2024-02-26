package it.cs.unicam.MunicipalDigitalization.api.model.users;

import it.cs.unicam.MunicipalDigitalization.api.model.Municipality;
import it.cs.unicam.MunicipalDigitalization.api.util.UserRole;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * This abstract class represents a general user in the Municipal Digitalization system.
 * It implements the IUser interface and provides methods to get and set the user's id, municipality, and roles.
 * It also provides methods to add and remove roles from the user.
 */
@MappedSuperclass
@Getter
@Setter
@EqualsAndHashCode
public abstract class AbstractIUser implements IUser {

    /**
     * The municipality of the user.
     */
    @ManyToOne
    @JoinColumn(name = "municipality", nullable = true)
    protected Municipality municipality;

    /**
     * The unique identifier of the user.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The roles of the user.
     */
    @ElementCollection(targetClass = UserRole.class)
    private List<UserRole> role;

    /**
     * Constructor for the AbstractIUser class.
     * Initializes the municipality of the user and creates an empty list of roles.
     *
     * @param municipality The municipality of the user.
     */
    public AbstractIUser(Municipality municipality) {
        this.municipality = municipality;
        this.role = new ArrayList<>();
    }

    /**
     * Default constructor for the AbstractIUser class.
     * Creates an empty list of roles.
     */
    public AbstractIUser() {
        this.role = new ArrayList<>();
    }

    /**
     * Method to remove a role from the user.
     * Throws an IllegalArgumentException if the user does not have the role.
     *
     * @param role The role to remove.
     */
    public void removeRole(UserRole role) {
        if (this.role.contains(role)) this.role.remove(role);
        else throw new IllegalArgumentException("The user doesn't have the role to remove.");
    }

    /**
     * Method to add a role to the user.
     * Throws an IllegalArgumentException if the user already has the role.
     *
     * @param role The role to add.
     */
    public void addRole(UserRole role) {
        if (!this.role.contains(role)) this.role.add(role);
        else throw new IllegalArgumentException("The user already has the role.");
    }
}