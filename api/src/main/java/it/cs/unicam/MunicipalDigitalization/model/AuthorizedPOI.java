package it.cs.unicam.MunicipalDigitalization.model;

/**
 * This class represents an authorized point of interest (POI), which is a type of POI.
 * It extends the AbstractPOI class.
 * An authorized POI has coordinates, id, and name.
 * It also has a method to set its coordinates.
 */
public class AuthorizedPOI extends AbstractPOI {

    /**
     * Constructor for the AuthorizedPOI class.
     *
     * @param user The authorized contributor who creates the point of interest (POI).
     */
    public <T extends AuthorizedContributor> AuthorizedPOI(T user) {
        super(user);
    }
}