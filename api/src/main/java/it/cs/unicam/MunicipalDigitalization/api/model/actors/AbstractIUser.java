package it.cs.unicam.MunicipalDigitalization.api.model.actors;

import it.cs.unicam.MunicipalDigitalization.api.io.TouristView;
import it.cs.unicam.MunicipalDigitalization.api.model.Municipality;
import it.cs.unicam.MunicipalDigitalization.api.util.ID;
import lombok.Getter;

import java.util.Objects;

/**
 * This abstract class represents a general user.
 * It implements the IUser interface.
 * A user has an id, name, password, and a municipality.
 * It also provides the ViewPOI and viewItinerary method used to
 * see the specific details of a POI or Itinerary.
 */
public abstract class AbstractIUser implements IUser {

    /**
     * The municipality of the user.
     * -- GETTER --
     *  Getter for the municipality of the user.
     */
    @Getter
    protected Municipality municipality;

    /**
     * The unique identifier of the user.
     */
    private final ID id;

    /**
     * The view of every IUser of the Platform
     */
    private final TouristView generalView;

    /**
     * Constructor for the AbstractIUser class.
     *
     * @param municipality The municipality of the user.
     */
    public AbstractIUser(Municipality municipality) {
        this.municipality = municipality;
        this.id = new ID();
        this.generalView = new TouristView(this.municipality, this);
    }

    @Override
    public ID getId() {
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
    public void getMap(String id) { this.generalView.viewMap(id); }
}