package it.cs.unicam.MunicipalDigitalization.model;

import it.cs.unicam.MunicipalDigitalization.util.Coordinates;
import it.cs.unicam.MunicipalDigitalization.util.ID;
import it.cs.unicam.MunicipalDigitalization.util.Type;

import java.util.Date;
import java.util.Objects;

/**
 * This abstract class represents a general point of interest (POI).
 * It implements the IPOI interface.
 * A POI has an id, name, type, author, creation date, and coordinates.
 */
public abstract class AbstractPOI extends AbstractMunicipalElement implements IPOI {

    /**
     * The type of the point of interest (POI).
     */
    private Type type;

    public AbstractPOI(ID id, String name, Date creationDate, AuthenticatedUser author, Coordinates coordinates) {
        super(id, name, creationDate, author, coordinates);
    }


    @Override
    public Coordinates getCoordinates() {
        return super.getCoordinates();
    }

    @Override
    public void setCoordinates(Coordinates coordinates) {
        if (super.getCoordinates() == null) {
            throw new NullPointerException("Coordinates cannot be null");
        }
        super.getCoordinates().setX(coordinates.getX());
        super.getCoordinates().setY(coordinates.getY());
    }

    @Override
    public String getId() {
        return super.getID();
    }

    /**
     * Setter for the name of the point of interest (POI).
     *
     * @param name The new name of the point of interest (POI).
     */
    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new NullPointerException("Name cannot be null");
        }
        this.name = name;
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
     * Getter for the author of the point of interest (POI).
     *
     * @return The author of the point of interest (POI).
     */
    public AuthenticatedUser getAuthor() {
        return null;
    }

    /**
     * Getter for the creation date of the point of interest (POI).
     *
     * @return The creation date of the point of interest (POI).
     */
    public Date getCreationDate() {
        return null;
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
        return Objects.equals(coordinates, that.coordinates) && Objects.equals(name, that.name) && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinates, name, type);
    }
}