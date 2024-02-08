package it.cs.unicam.MunicipalDigitalization.api.model.actors;

import it.cs.unicam.MunicipalDigitalization.api.io.TouristView;
import it.cs.unicam.MunicipalDigitalization.api.model.Municipality;
import it.cs.unicam.MunicipalDigitalization.api.model.elements.AbstractItinerary;
import it.cs.unicam.MunicipalDigitalization.api.model.elements.AbstractMunicipalElement;
import it.cs.unicam.MunicipalDigitalization.api.model.elements.AbstractPOI;
import it.cs.unicam.MunicipalDigitalization.api.util.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
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
    @JoinColumn(name = "municipality")
    protected Municipality municipality;

    /**
     * The view of every IUser of the Platform
     */
    @Transient
    private final TouristView generalView;

    /**
     * Role of the Actor
     * -- GETTER --
     */
    @Column(name = "Role")
    private UserRole role;

    /**
     * Constructor for the AbstractIUser class.
     *
     * @param municipality The municipality of the user.
     */
    public AbstractIUser(Municipality municipality) {
        this.municipality = municipality;
        this.generalView = new TouristView(this.municipality, this);
    }

    public AbstractIUser() {
        this.generalView = new TouristView(this.municipality, this);
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

    /**
     * This method allows the IUser to view a POI present in the Municipality.
     * It uses the TouristView General View.
     */
    public void viewPOI() {
        this.generalView.viewPOI();
    }

    /**
     * This method allows the IUser to view an Itinerary present in the Municipality.
     * It uses the TouristView General View.
     */
    public void viewItinerary() {
        this.generalView.viewItinerary();
    }

    /**
     * This method allows the IUser to view the contents present in the Municipality Elements.
     * It uses the TouristView General View.
     */
    public void viewContents(String id) { this.generalView.viewContents(id); }

    /**
     * This method allows the IUser to view the map of the Municipality.
     * It uses the TouristView General View.
     */
    //public void getMap(String id) { this.generalView.viewMap(id); }

}