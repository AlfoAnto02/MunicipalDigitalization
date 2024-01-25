package it.cs.unicam.MunicipalDigitalization.model;

import it.cs.unicam.MunicipalDigitalization.io.ITourist;
import it.cs.unicam.MunicipalDigitalization.util.ID;

import java.util.Objects;

/**
 * This abstract class represents a general user.
 * It implements the User interface.
 * A user has an id, name, password, and a municipality.
 * It also provides the ViewPOI and viewItinerary method used to
 * see the specific details of a POI or Itinerary.
 */
public abstract class AbstractUser implements User {

    /**
     * The municipality of the user.
     */
    protected final Municipality municipality;
    /**
     * The unique identifier of the user.
     */
    private final ID id;
    /**
     * The view of every User of the Platform
     */
    private final ITourist generalView;

    /**
     * Constructor for the AbstractUser class.
     *
     * @param municipality The municipality of the user.
     */
    public AbstractUser(Municipality municipality) {
        this.municipality = municipality;
        this.id = new ID();
        this.generalView = new ITourist(this.municipality, this);
    }

    @Override
    public ID getId() {
        return this.id;
    }

    /**
     * Getter for the municipality of the user.
     *
     * @return The municipality of the user.
     */
    public Municipality getMunicipality() {
        return this.municipality;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractUser that = (AbstractUser) o;
        return Objects.equals(id, that.id) && Objects.equals(municipality, that.municipality);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, municipality);
    }

    /**
     * This method allows the User to view a POI present in the Municipality.
     * It uses the ITourist General View.
     */
    public void viewPOI() {
        this.generalView.viewPOI();
    }

    /**
     * This method allows the User to view an Itinerary present in the Municipality.
     * It uses the ITourist General View.
     */
    public void viewItinerary() {
        this.generalView.viewItinerary();
    }
}