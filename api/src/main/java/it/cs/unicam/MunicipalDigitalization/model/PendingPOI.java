package it.cs.unicam.MunicipalDigitalization.model;

/**
 * This class represents a pending point of interest (POI).
 * It extends the AbstractPOI class.
 * A pending POI is a POI that has not yet been validated.
 * It provides methods to get the coordinates, id, and name of the POI, and to set the coordinates of the POI.
 * It also provides a constructor to initialize the PendingPOI with the provided user.
 */
public class PendingPOI extends AbstractPOI {

    /**
     * Constructor for the PendingPOI class.
     * It initializes the PendingPOI with the provided user.
     *
     * @param user The user who created the POI.
     */
    public PendingPOI(Contributor user) {
        super(user);
    }

}