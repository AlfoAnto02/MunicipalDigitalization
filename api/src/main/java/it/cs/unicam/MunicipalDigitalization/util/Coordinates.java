package it.cs.unicam.MunicipalDigitalization.util;

import java.util.Objects;

/**
 * This class represents a set of coordinates.
 * A coordinate has an x and y value.
 */
public class Coordinates {

    /**
     * The x value of the coordinate.
     */
    private double x;

    /**
     * The y value of the coordinate.
     */
    private double y;

    /**
     * Constructor for the Coordinates class.
     *
     * @param x The x value of the coordinate.
     * @param y The y value of the coordinate.
     */
    public Coordinates(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Getter for the x value of the coordinate.
     *
     * @return The x value of the coordinate.
     */
    public double getX() {
        return this.x;
    }

    /**
     * Setter for the x value of the coordinate.
     *
     * @param x The new x value of the coordinate.
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Getter for the y value of the coordinate.
     *
     * @return The y value of the coordinate.
     */
    public double getY() {
        return this.y;
    }

    /**
     * Setter for the y value of the coordinate.
     *
     * @param y The new y value of the coordinate.
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Equals method of the class using the x and y of a coordinate.
     * @param o
     * @return true if the coordinates are equals, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return Double.compare(that.x, x) == 0 && Double.compare(that.y, y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}