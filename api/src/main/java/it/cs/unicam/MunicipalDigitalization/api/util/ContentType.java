package it.cs.unicam.MunicipalDigitalization.api.util;
/**
 * This enum represents the types of contents that can be created.
 * The types include Description, Link, and Image.
 * Each type is associated with a code.
 * It provides methods to select a type based on a string, get a string representation of all types, and contains if a string starts with the code of a type.
 */
public enum ContentType {

    /**
     * Represents a description content.
     */
    DESCRIPTION,

    /**
     * Represents a link content.
     */
    LINK,

    /**
     * Represents an image content.
     */
    PHOTO
}
