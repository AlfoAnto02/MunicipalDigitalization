package it.cs.unicam.MunicipalDigitalization.api.model.actors;


import it.cs.unicam.MunicipalDigitalization.api.model.Municipality;
import it.cs.unicam.MunicipalDigitalization.api.model.elements.AbstractContent;
import it.cs.unicam.MunicipalDigitalization.api.model.elements.AbstractItinerary;
import it.cs.unicam.MunicipalDigitalization.api.model.elements.AbstractPOI;
import it.cs.unicam.MunicipalDigitalization.api.model.elements.AuthorizedPOI;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * This class Represent an Authenticated IUser.
 * Differently from a IUser, the Authenticated IUser has a name and a Password.
 */
@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(
        name = "User",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "Identification",
                        columnNames = "id"
                )
        }
)
public abstract class AbstractAuthenticatedUser extends AbstractIUser implements IAuthenticatedUser {

    /**
     * Name of the IUser
     */
    @Column(name = "Name")
    private String name;

    /**
     * Password of the IUser
     */
    @Column(name = "Password", length = 16)
    private String password;

    /**
     * This is the list of Abstract POIs that the author created
     */
    @OneToMany(mappedBy = "author")
    private List<AbstractPOI> authoredPOIs;

    /**
     * This is the List of Abstract Itineraries that the author created
     */
    @OneToMany(mappedBy = "author")
    private List<AbstractItinerary> authoredItineraries;

    /**
     * This is the List of Abstract Contents that the author created
     */
    @OneToMany(mappedBy = "author")
    private List<AbstractContent> authoredContents;

    /**
     * The Constructor for a general Authenticated IUser.
     *
     * @param name         name of the IUser
     * @param password     password of the IUser
     * @param municipality Municipality where the IUser will Operate
     */
    public AbstractAuthenticatedUser(String name, String password, Municipality municipality) {
        super(municipality);
        this.name = name;
        this.password = password;
    }

    public AbstractAuthenticatedUser() {
        this.authoredPOIs = new ArrayList<>();
        this.authoredItineraries = new ArrayList<>();
        this.authoredContents = new ArrayList<>();
    }

    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Setter for the name of the user.
     *
     * @param name The new name of the user.
     */
    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.name = name;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public void setPassword(String password) {
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        this.password = password;
    }

    public void addPOI(AbstractPOI poi) {
        this.authoredPOIs.add(poi);
    }

    public void addItinerary(AbstractItinerary itinerary) {
        this.authoredItineraries.add(itinerary);
    }
}
