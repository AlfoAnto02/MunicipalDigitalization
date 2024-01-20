package it.cs.unicam.MunicipalDigitalization.model;

import it.cs.unicam.MunicipalDigitalization.util.Coordinates;
import it.cs.unicam.MunicipalDigitalization.util.ID;
import it.cs.unicam.MunicipalDigitalization.util.Type;

import java.time.Instant;
import java.util.Date;

/**
 * This abstract class represents a general point of interest (POI).
 * It implements the IPOI interface.
 * A POI has an id, name, type, author, creation date, and coordinates.
 */
public abstract class AbstractPOI implements IPOI {

    /**
     * The unique identifier of the point of interest (POI).
     */
    private final ID id;

    /**
     * The author of the point of interest (POI).
     */
    private final User author;

    /**
     * The date when the point of interest (POI) was created.
     */
    private final Date creationDate;

    /**
     * The coordinates of the point of interest (POI).
     */
    private Coordinates coordinates;

    /**
     * The name of the point of interest (POI).
     */
    private String name;

    /**
     * The type of the point of interest (POI).
     */
    private Type type;

    /**
     * Constructor for the AbstractPOI class.
     *
     * @param user The author of the point of interest (POI).
     */
    public AbstractPOI(User user) {
        this.author = user;
        this.id = new ID();
        this.creationDate = Date.from(Instant.now());
    }

    @Override
    public Coordinates getCoordinates() {
        return this.coordinates;
    }

    @Override
    public void setCoordinates(Coordinates coordinates) {
        if (this.coordinates == null) {
            throw new NullPointerException("Coordinates cannot be null");
        }
        this.coordinates.setX(coordinates.getX());
        this.coordinates.setY(coordinates.getY());
    }

    @Override
    public ID getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
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
    public User getAuthor() {
        return this.author;
    }

    /**
     * Getter for the creation date of the point of interest (POI).
     *
     * @return The creation date of the point of interest (POI).
     */
    public Date getCreationDate() {
        return this.creationDate;
    }
}