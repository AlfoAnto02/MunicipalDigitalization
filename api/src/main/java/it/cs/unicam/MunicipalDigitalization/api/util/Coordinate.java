package it.cs.unicam.MunicipalDigitalization.api.util;

import jakarta.persistence.Embeddable;
import lombok.*;

/**
 * This class represents a set of coordinates.
 * A coordinate has an x and y value.
 */
@Setter
@Getter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Coordinate {

    /**
     * The x value of the coordinate.
     * -- GETTER --
     *  Getter for the x value of the coordinate.
     * -- SETTER --
     *  Setter for the x value of the coordinate.
     *

     */
    private double x;

    /**
     * The y value of the coordinate.
     * -- GETTER --
     *  Getter for the y value of the coordinate.
     * -- SETTER --
     *  Setter for the y value of the coordinate.

     */
    private double y;
}