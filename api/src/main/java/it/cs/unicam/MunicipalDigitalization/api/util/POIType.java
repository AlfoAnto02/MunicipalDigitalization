package it.cs.unicam.MunicipalDigitalization.api.util;
/**
 * This enum represents the types of points of interest (POIs) that can be created.
 * The types include BookShop, TownHall, Church, Cinema, and School.
 * Each type is associated with a code.
 * It provides methods to select a type based on a string, get a string representation of all types, and contains if a string starts with the code of a type.
 */
public enum POIType {

    /**
     * Represents a bookshop POI.
     */
    BookShop,

    /**
     * Represents a town hall POI.
     */
    TownHall,

    /**
     * Represents a church POI.
     */
    Church,

    /**
     * Represents a cinema POI.
     */
    Cinema,

    /**
     * Represents a school POI.
     */
    School
}