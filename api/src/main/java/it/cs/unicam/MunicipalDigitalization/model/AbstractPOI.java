package it.cs.unicam.MunicipalDigitalization.model;

import it.cs.unicam.MunicipalDigitalization.util.POIType;

import java.util.Objects;

/**
 * This abstract class represents a general point of interest (POI).
 * It implements the IPOI interface.
 * A POI has an id, name, POIType, author, creation date, coordinates and a list of contents.
 */
public abstract class AbstractPOI extends MunicipalElements implements IPOI {

    /**
     * The POIType of the point of interest (POI).
     */
    private POIType POIType;

    /**
     * The constructor of the Class
     *
     * @param author the AuthenticatedUser that create the POI
     */
    public AbstractPOI(AuthenticatedUser author, Municipality municipality) {
        super(author, municipality);
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
}