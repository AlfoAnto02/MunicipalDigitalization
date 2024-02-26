package it.cs.unicam.MunicipalDigitalization.api.util.DesignPattern.Builder;

import it.cs.unicam.MunicipalDigitalization.api.model.Municipality;
import it.cs.unicam.MunicipalDigitalization.api.model.users.AbstractAuthenticatedUser;
import it.cs.unicam.MunicipalDigitalization.api.model.elements.AbstractPOI;
import it.cs.unicam.MunicipalDigitalization.api.util.Coordinate;
import it.cs.unicam.MunicipalDigitalization.api.util.POIType;

/**
 * This is the Interface of the POI Builder.
 * It represents the construction of a POI step by step.
 */
public interface POIBuilder {

    /**
     * This method sets the author of the POI.
     *
     * @param author the author of the POI.
     */
    void setPOIAuthor(AbstractAuthenticatedUser author);

    /**
     * This method sets the coordinates of the POI.
     *
     * @param coordinates the coordinates of the POI.
     */
    void setPOICoordinates(Coordinate coordinates);

    /**
     * This method sets the name of the POI.
     *
     * @param name the name of the POI.
     */
    void setPOIName(String name);

    /**
     * This method sets the type of the POI.
     *
     * @param type the type of the POI.
     */
    void setPOIType(POIType type);

    /**
     * This method sets the municipality of the POI.
     *
     * @param municipality the municipality of the POI.
     */
    void setPOIMunicipality(Municipality municipality);

    /**
     * This method sets the status of the POI.
     */
    void setPOIStatus();

    /**
     * This method resets the builder.
     */
    void reset();

    /**
     * This method builds the POI.
     *
     * @return the POI.
     */
    AbstractPOI build();

}
