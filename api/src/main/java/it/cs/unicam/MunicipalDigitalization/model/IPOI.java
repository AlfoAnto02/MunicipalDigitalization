package it.cs.unicam.MunicipalDigitalization.model;

/**
 * IPOI interface that defines the methods to be implemented by any class that represents a Point of Interest (POI).
 */
public interface IPOI {

    /**
     * Method to get the coordinates of the POI.
     *
     * @return the coordinates of the POI as a Coordinate object.
     */
    Coordinate getCoordinates();

    /**
     * Method to get the name of the POI.
     *
     * @return the name of the POI as a String.
     */
    String getName();

    /**
     * Method to get the ID of the POI.
     *
     * @return the ID of the POI as a String.
     */
    String getID();
}