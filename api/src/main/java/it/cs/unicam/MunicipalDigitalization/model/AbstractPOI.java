package it.cs.unicam.MunicipalDigitalization.model;

public abstract class AbstractPOI implements IPOI {

    /**
     * The ID of the POI.
     */
    protected final String id;
    /**
     * The coordinates of the POI.
     */
    protected Coordinate coordinates;
    /**
     * The name of the POI.
     */
    protected String name;

    /**
     * The constructor of the AbstractPOI class.
     *
     * @param coordinates the coordinates of the POI as a Coordinate object.
     * @param name        the name of the POI as a String.
     * @param id          the ID of the POI as a String.
     */
    public AbstractPOI(Coordinate coordinates, String name, String id) {
        this.coordinates = coordinates;
        this.name = name;
        this.id = id;
    }

    public Coordinate getCoordinates() {
        return this.coordinates;
    }

    public String getName() {
        return this.name;
    }

    public String getID() {
        return this.id;
    }
}
