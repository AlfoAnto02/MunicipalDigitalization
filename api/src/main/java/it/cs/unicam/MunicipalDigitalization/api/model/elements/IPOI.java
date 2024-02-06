package it.cs.unicam.MunicipalDigitalization.api.model.elements;

import it.cs.unicam.MunicipalDigitalization.api.util.ElementStatus;
import it.cs.unicam.MunicipalDigitalization.api.util.POIType;

/**
 * This interface represents a point of interest (POI).
 * It provides methods to get and set the type of the POI.
 */
public interface IPOI extends IMunicipalElement {

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