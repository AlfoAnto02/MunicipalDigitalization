package it.cs.unicam.MunicipalDigitalization.model;

import it.cs.unicam.MunicipalDigitalization.util.POIType;

/**
 * This interface represents a point of interest (POI).
 * It provides methods to get and set the coordinates, id, name, and type of the POI.
 */
public interface IPOI extends IMunicipalElements {

    /**
     * This method is used to get the type of the POI.
     *
     * @return The type of the POI.
     */
    POIType getType();

    /**
     * This method is used to set the type of the POI.
     *
     * @param t The new type of the POI.
     */
    void setType(POIType t);


}