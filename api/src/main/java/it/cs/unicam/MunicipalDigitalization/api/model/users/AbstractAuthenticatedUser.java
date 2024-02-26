package it.cs.unicam.MunicipalDigitalization.api.model.users;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import it.cs.unicam.MunicipalDigitalization.api.model.Municipality;
import it.cs.unicam.MunicipalDigitalization.api.model.elements.AbstractContent;
import it.cs.unicam.MunicipalDigitalization.api.model.elements.AbstractItinerary;
import it.cs.unicam.MunicipalDigitalization.api.model.elements.AbstractPOI;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * This abstract class represents an authenticated user in the Municipal Digitalization API.
 * An authenticated user has a name, password, and can author POIs, Itineraries, and Contents.
 * This class extends the AbstractIUser class and implements the IAuthenticatedUser interface.
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "User", uniqueConstraints = {@UniqueConstraint(name = "Identification", columnNames = "id")})
@Getter
@Setter
public abstract class AbstractAuthenticatedUser extends AbstractIUser implements IAuthenticatedUser {

    /**
     * The name of the authenticated user.
     */
    @Column(name = "Name")
    private String name;

    /**
     * The password of the authenticated user.
     */
    @Column(name = "Password", length = 16)
    private String password;

    /**
     * The list of POIs authored by the authenticated user.
     */
    @OneToMany(mappedBy = "author")
    @JsonBackReference
    private List<AbstractPOI> authoredPOIs;

    /**
     * The list of Itineraries authored by the authenticated user.
     */
    @OneToMany(mappedBy = "author")
    @JsonBackReference
    private List<AbstractItinerary> authoredItineraries;

    /**
     * The list of Contents authored by the authenticated user.
     */
    @OneToMany(mappedBy = "author")
    @JsonManagedReference
    private List<AbstractContent> authoredContents;

    /**
     * Constructs a new authenticated user with the given name, password, and municipality.
     *
     * @param name         the name of the authenticated user
     * @param password     the password of the authenticated user
     * @param municipality the municipality where the authenticated user will operate
     */
    public AbstractAuthenticatedUser(String name, String password, Municipality municipality) {
        super(municipality);
        this.name = name;
        this.password = password;
        this.authoredPOIs = new ArrayList<>();
        this.authoredItineraries = new ArrayList<>();
        this.authoredContents = new ArrayList<>();
    }

    /**
     * Constructs a new authenticated user with the given name and password.
     *
     * @param name     the name of the authenticated user
     * @param password the password of the authenticated user
     */
    public AbstractAuthenticatedUser(String name, String password) {
        this.name = name;
        this.password = password;
        this.authoredPOIs = new ArrayList<>();
        this.authoredItineraries = new ArrayList<>();
        this.authoredContents = new ArrayList<>();
    }

    /**
     * Default constructor for the AbstractAuthenticatedUser class.
     */
    public AbstractAuthenticatedUser() {
        this.authoredPOIs = new ArrayList<>();
        this.authoredItineraries = new ArrayList<>();
        this.authoredContents = new ArrayList<>();
    }

    /**
     * Adds a POI to the list of POIs authored by the authenticated user.
     *
     * @param poi the POI to add
     */
    public void addPOI(AbstractPOI poi) {
        this.authoredPOIs.add(poi);
    }

    /**
     * Adds an Itinerary to the list of Itineraries authored by the authenticated user.
     *
     * @param itinerary the Itinerary to add
     */
    public void addItinerary(AbstractItinerary itinerary) {
        this.authoredItineraries.add(itinerary);
    }

    /**
     * Adds a Content to the list of Contents authored by the authenticated user.
     *
     * @param content the Content to add
     */
    public void addContent(AbstractContent content) {
        this.authoredContents.add(content);
    }

    /**
     * Sets the name of the authenticated user.
     *
     * @param name the new name of the authenticated user
     * @throws IllegalArgumentException if the name is null or empty
     */
    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.name = name;
    }

    /**
     * Sets the password of the authenticated user.
     *
     * @param password the new password of the authenticated user
     * @throws IllegalArgumentException if the password is null or empty
     */
    @Override
    public void setPassword(String password) {
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        this.password = password;
    }
}