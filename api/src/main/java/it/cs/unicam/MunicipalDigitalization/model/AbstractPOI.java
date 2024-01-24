package it.cs.unicam.MunicipalDigitalization.model;

import it.cs.unicam.MunicipalDigitalization.util.Coordinates;
import it.cs.unicam.MunicipalDigitalization.util.ID;
import it.cs.unicam.MunicipalDigitalization.util.Type;

import java.time.Instant;
import java.util.Date;
import java.util.Objects;

/**
 * This abstract class represents a general point of interest (POI).
 * It implements the IPOI interface.
 * A POI has an id, name, type, author, creation date, and coordinates.
 */
public abstract class AbstractPOI  extends MunicipalElements implements IPOI {

    /**
     * The type of the point of interest (POI).
     */
    private Type type;

    /**
     * The constructor of the Class
     *
     * @param author the AuthenticatedUser that create the POI
     */
    public AbstractPOI(AuthenticatedUser author) {
        super(author);
    }

    /**
     * Getter for the type of the point of interest (POI).
     *
     * @return The type of the point of interest (POI).
     */

    public Type getType() {
        return this.type;
    }

    /**
     * Setter for the type of the point of interest (POI).
     *
     * @param type The new type of the point of interest (POI).
     */

    public void setType(Type type) {
        if (type == null) {
            throw new NullPointerException("Type cannot be null");
        }
        this.type = type;
    }

    /**
     * Equals of the class based on the coordinates, name and type of the POI
     *
     * @param o The object to compare
     * @return true if the POIs are equals
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractPOI that = (AbstractPOI) o;
        return Objects.equals(super.getCoordinates(), that.getCoordinates()) &&
                Objects.equals(super.getName(), that.getName()) && type == that.type;
    }

    /**
     *
     * @return the Hashcode of the Object based on the Coordinates, name and type.
     */

    @Override
    public int hashCode() {
        return Objects.hash(super.getCoordinates(), super.getName(), type);
    }
}