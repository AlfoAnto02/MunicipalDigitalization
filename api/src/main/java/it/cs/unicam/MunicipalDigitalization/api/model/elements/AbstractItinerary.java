package it.cs.unicam.MunicipalDigitalization.api.model.elements;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import it.cs.unicam.MunicipalDigitalization.api.model.Municipality;
import it.cs.unicam.MunicipalDigitalization.api.model.actors.AbstractAuthenticatedUser;
import it.cs.unicam.MunicipalDigitalization.api.util.Coordinate;
import it.cs.unicam.MunicipalDigitalization.api.util.ElementStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This abstract class represents a general itinerary.
 * It implements the IItinerary interface.
 * An itinerary has an id, name, description, author, creation date, the types of the points of interest
 * the  list of points of interest (POIs) and a list of Contents.
 */
@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(
        name = "Itineraries",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "Identification",
                        columnNames = "id"
                )
        }
)
public abstract class AbstractItinerary extends AbstractMunicipalElement implements IItinerary {

    /**
     * List of POIs that composed the Itinerary
     */
    @ManyToMany
    @JoinTable(
            name = "Itinerary_POIs",
            joinColumns = @JoinColumn(name = "Itinerary_ID"),
            inverseJoinColumns = @JoinColumn(name = "POI_ID")
    )
    private final List<AbstractPOI> pois;

    /**
     * The types of the Itinerary. The types depend on the Types of the POIs
     * that composed the Itinerary
     * -- GETTER --
     *  Getter for the types of the itinerary.
     *
     */
    @Getter
    @Column(name = "Type",nullable = false)
    private String types;

    /**
     * A general Description of the Itinerary
     */
    @Column(name = "Description",nullable = false)
    private String description;

    /**
     * The author of the point of the MunicipalElement
     */
    @ManyToOne
    @JoinColumn(name = "Author",nullable = false)
    @JsonManagedReference
    private AbstractAuthenticatedUser author;

    /**
     * The constructor of the class
     *
     */
    public AbstractItinerary() {
        super();
        this.pois = new ArrayList<>();
    }

    /**
     * The constructor of the class used by the Builder
     *
     * @param municipality The municipality of the Itinerary
     * @param elementStatus The status of the Itinerary
     * @param coordinate The coordinate of the Itinerary
     * @param name The name of the Itinerary
     * @param pois The list of POIs that composed the Itinerary
     * @param types The types of the Itinerary
     * @param description The description of the Itinerary
     * @param author The author of the Itinerary
     */
    public AbstractItinerary(Municipality municipality, ElementStatus elementStatus, Coordinate coordinate,
                             String name, List<AbstractPOI> pois, String types, String description,
                             AbstractAuthenticatedUser author) {
        super(municipality, elementStatus, coordinate, name);
        this.pois = new ArrayList<>(pois);
        this.types = types;
        this.description = description;
        this.author = author;
    }

    /**
     * Setter for the types of the itinerary.
     *
     * @param types The types of the itinerary.
     */

    public void setTypes(String types) {
        if (types == null) {
            throw new NullPointerException("The types cannot be null.");
        }

        this.types = types;
    }

    /**
     * get the description of the Itinerary
     *
     * @return a string that contains the description of the itinerary.
     */

    @Override
    public String getDescription() {
        return this.description;
    }

    /**
     * Setter for the description of the itinerary.
     *
     * @param description The description of the itinerary.
     */

    public void setDescription(String description) {
        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("The description cannot be null or empty.");
        }
        this.description = description;
    }

    /**
     * Getter for the author of the Itinerary.
     *
     * @return The author of the Itinerary
     */
    public AbstractAuthenticatedUser getUser() {
        return this.author;
    }

    /**
     * get the List of the POIs that composes the itinerary
     *
     * @return a list of POIs
     */

    @Override
    public List<AbstractPOI> getPOIs() {
        return this.pois;
    }

    /**
     * Method to contains if a POI is in the itinerary.
     *
     * @param poi The POI to contains.
     * @return True if the POI is in the itinerary, false otherwise.
     */

    public boolean contains(IPOI poi) {
        return this.getPOIs().contains(poi);
    }

    /**
     * Method to add a POI to the itinerary.
     *
     * @param poi The POI to add.
     */

    public void addPOI(AbstractPOI poi) {
        this.pois.add(poi);
    }

    /**
     * Equals of the class based on the name and ListOfPOIs of the itinerary
     *
     * @param other object to compare
     * @return true if the itineraries are equals.
     */

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        AbstractItinerary abstractItinerary = (AbstractItinerary) other;
        return Objects.equals(super.getName(), abstractItinerary.getName()) && Objects.equals(pois, abstractItinerary.pois);
    }

    /**
     * @return the Hashcode of the Object based on the Name and on the List of POIs.
     */

    @Override
    public int hashCode() {
        return Objects.hash(super.getName(), pois);
    }
}