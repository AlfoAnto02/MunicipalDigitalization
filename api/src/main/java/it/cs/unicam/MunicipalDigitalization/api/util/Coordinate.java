package it.cs.unicam.MunicipalDigitalization.api.util;

import jakarta.persistence.Embeddable;

import java.util.Objects;

/**
 * This class represents a set of coordinates.
 * A coordinate has an x and y value.
 */
@Embeddable
public class Coordinate {

    /**
     * The x value of the coordinate.
     */
    private double x;

    /**
     * The y value of the coordinate.
     */
    private double y;

    /**
     * Constructor for the Coordinate class.
     *
     * @param x The x value of the coordinate.
     * @param y The y value of the coordinate.
     */
    public Coordinate(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Coordinate() {

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

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Coordinate coordinate = (Coordinate) other;
        return Double.compare(coordinate.x, x) == 0 && Double.compare(coordinate.y, y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Coordinate{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}