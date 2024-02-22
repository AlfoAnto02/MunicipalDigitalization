package it.cs.unicam.MunicipalDigitalization.api.model.actors;

import it.cs.unicam.MunicipalDigitalization.api.model.Municipality;
import it.cs.unicam.MunicipalDigitalization.api.util.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This abstract class represents a general user.
 * It implements the IUser interface.
 * A user has an id, name, password, and a municipality.
 * It also provides the ViewPOI and viewItinerary method used to
 * see the specific details of a POI or Itinerary.
 */
@MappedSuperclass
@Getter
@Setter
public abstract class AbstractIUser implements IUser {

    /**
     * The unique identifier of the user.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The municipality of the user.
     * -- GETTER --
     *  Getter for the municipality of the user.
     */
    @ManyToOne
    @JoinColumn(name = "municipality", nullable = true)
    protected Municipality municipality;

    /**
     * Role of the Actor
     * -- GETTER --
     */
    @ElementCollection(targetClass = UserRole.class)
    private List<UserRole> role;

    /**
     * Constructor for the AbstractIUser class.
     *
     * @param municipality The municipality of the user.
     */
    public AbstractIUser(Municipality municipality) {
        this.municipality = municipality;
        this.role = new ArrayList<>();
    }

    public AbstractIUser() {
        this.role = new ArrayList<>();
    }

    /**
     * Method to remove a role from the user.
     *
     * @param role The role to remove.
     */
    public void removeRole(UserRole role) {
        if(this.role.contains(role))  this.role.remove(role);
        else throw new IllegalArgumentException("The user doesn't have the role to remove.");
    }

    /**
     * Method to add a role to the user.
     *
     * @param role The role to add.
     */

    public void addRole(UserRole role) {
        if(!this.role.contains(role))  this.role.add(role);
        else throw new IllegalArgumentException("The user already has the role.");
    }

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractIUser that = (AbstractIUser) o;
        return Objects.equals(id, that.id) && Objects.equals(municipality, that.municipality);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, municipality);
    }
}