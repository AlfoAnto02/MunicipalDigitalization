package it.cs.unicam.MunicipalDigitalization.util;

import java.util.Optional;
import java.util.stream.Stream;

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
    BookShop("Bookshop"),

    /**
     * Represents a town hall POI.
     */
    TownHall("TownHall"),

    /**
     * Represents a church POI.
     */
    Church("Church"),

    /**
     * Represents a cinema POI.
     */
    Cinema("Cinema"),

    /**
     * Represents a school POI.
     */
    School("Cinema");

    /**
     * The code associated with the type.
     */
    private final String code;

    /**
     * Constructor for the POIType enum.
     * It initializes the type with the provided code.
     *
     * @param code The code associated with the type.
     */
    POIType(String code) {
        this.code = code;
    }

    /**
     * This method is used to select a type based on a string.
     *
     * @param line The string to be used to select a type.
     * @return An Optional containing the selected type if it exists, or an empty Optional if it does not.
     */
    public static Optional<POIType> selectType(String line) {
        return Stream.of(POIType.values()).filter(c -> c.isTypeOfLine(line)).findFirst();
    }

    /**
     * This method is used to get a string representation of all types.
     *
     * @return A string representation of all types.
     */
    public static String getTypes() {
        return "{BookShop,TownHall,Church,Cinema,School}";
    }

    /**
     * This method is used to contains if a string starts with the code of a type.
     *
     * @param line The string to be checked.
     * @return True if the string starts with the code of a type, false otherwise.
     */
    private boolean isTypeOfLine(String line) {
        return line.startsWith(this.code);
    }
}