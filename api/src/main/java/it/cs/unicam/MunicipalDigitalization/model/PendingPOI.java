package it.cs.unicam.MunicipalDigitalization.model;

/**
 * This class represents a POI that is not yet approved by the administrator.
 * It extends the AbstractPOI class.
 */
public class PendingPOI extends AbstractPOI {

    /**
     * The constructor of the AbstractPOI class.
     *
     * @param coordinates the coordinates of the POI as a Coordinate object.
     * @param name        the name of the POI as a String.
     * @param id          the ID of the POI as a String.
     */
    public PendingPOI(Coordinate coordinates, String name, String id) {
        super(coordinates, name, id);
    }
}
