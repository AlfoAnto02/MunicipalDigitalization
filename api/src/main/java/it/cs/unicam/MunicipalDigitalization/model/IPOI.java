package it.cs.unicam.MunicipalDigitalization.model;

import it.cs.unicam.MunicipalDigitalization.util.Coordinates;
import it.cs.unicam.MunicipalDigitalization.util.Type;

/**
 * This interface represents a point of interest (POI).
 * It provides methods to get and set the coordinates, id, name, and type of the POI.
 */
public interface IPOI {

    /**
     * This method is used to get the coordinates of the POI.
     *
     * @return The coordinates of the POI.
     */
    Coordinates getCoordinates();

    /**
     * This method is used to set the coordinates of the POI.
     * The implementation should handle the setting of the coordinates.
     *
     * @param cc The new coordinates of the POI.
     */
    void setCoordinates(Coordinates cc);

    /**
     * This method is used to get the id of the POI.
     *
     * @return The id of the POI.
     */
    String getId();

    /**
     * This method is used to get the type of the POI.
     *
     * @return The type of the POI.
     */
    Type getType();

    /**
     * This method is used to set the type of the POI.
     *
     * @param t The new type of the POI.
     */
    void setType(Type t);

    /**
     * This method is used to get the name of the POI.
     *
     * @return The name of the POI.
     */
    String getName();

    /**
     * This method is used to set the name of the POI.
     *
     * @param name The new name of the POI.
     */
    void setName(String name);

}