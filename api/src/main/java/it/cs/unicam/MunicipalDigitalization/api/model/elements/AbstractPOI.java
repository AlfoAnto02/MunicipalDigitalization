package it.cs.unicam.MunicipalDigitalization.api.model.elements;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import it.cs.unicam.MunicipalDigitalization.api.model.Municipality;
import it.cs.unicam.MunicipalDigitalization.api.model.actors.AbstractAuthenticatedUser;
import it.cs.unicam.MunicipalDigitalization.api.util.Coordinate;
import it.cs.unicam.MunicipalDigitalization.api.util.ElementStatus;
import it.cs.unicam.MunicipalDigitalization.api.util.POIType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This abstract class represents a general point of interest (POI).
 * It implements the IPOI interface.
 * A POI has an id, name, POIType, author, creation date, coordinates and a list of contents.
 */
@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(
        name = "Pois",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "Identification",
                        columnNames = "id"
                )
        }
)
public abstract class AbstractPOI extends AbstractMunicipalElement implements IPOI {

    /**
     * The POIType of the point of interest (POI).
     */
    @Column(name = "Type", nullable = false)
    private POIType POIType;

    /**
     * The author of the point of the MunicipalElement
     */
    @ManyToOne
    @JoinColumn(name = "Author",nullable = false)
    @JsonManagedReference
    private AbstractAuthenticatedUser author;

    /**
     * The itinerary of the POI
     */
    @ManyToMany(mappedBy = "pois")
    private List<AbstractItinerary> itineraryOfPOI;

    /**
     * The constructor of the Class
     *
     */
    public AbstractPOI() {
        super();
    }

    /**
     * The constructor of the Class used by the Builder
     *
     * @param municipality The municipality of the POI
     * @param elementStatus The status of the POI
     * @param coordinate The coordinate of the POI
     * @param name The name of the POI
     * @param POIType The type of the POI
     * @param author The author of the POI
     */
    public AbstractPOI(Municipality municipality, ElementStatus elementStatus, Coordinate coordinate,
                       String name, POIType POIType, AbstractAuthenticatedUser author) {
        super(municipality, elementStatus, coordinate, name);
        this.POIType = POIType;
        this.author = author;
        this.itineraryOfPOI = new ArrayList<>();
    }

    /**
     * Getter for the POIType of the point of interest (POI).
     *
     * @return The POIType of the point of interest (POI).
     */
    public POIType getType() {
        return this.POIType;
    }

    /**
     * Setter for the POIType of the point of interest (POI).
     *
     * @param POIType The new POIType of the point of interest (POI).
     */
    public void setType(POIType POIType) {
        if (POIType == null) {
            throw new NullPointerException("POIType cannot be null");
        }
        this.POIType = POIType;
    }

    /**
     * Getter for the author of the POI.
     *
     * @return The author of the POI
     */
    public AbstractAuthenticatedUser getUser() {
        return this.author;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        AbstractPOI abstractPOI = (AbstractPOI) other;
        return Objects.equals(super.getCoordinates(), abstractPOI.getCoordinates()) && Objects.equals(super.getName(), abstractPOI.getName()) && POIType == abstractPOI.POIType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.getCoordinates(), super.getName(), POIType);
    }

    public void addItinerary(AbstractItinerary id) {
        this.itineraryOfPOI.add(id);
    }
}