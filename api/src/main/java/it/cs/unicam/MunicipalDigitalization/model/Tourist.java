package it.cs.unicam.MunicipalDigitalization.model;

/**
 * This class represents a general User of the Platform.
 * Every User of the Platform/Municipality have the possibility to View the POIs
 * presents in the Platform, the Itineraries and the Map.
 * Every other type of User have to extends this behavior.
 */
public class Tourist extends AbstractUser {

    /**
     * Constructor for the AbstractUser class.
     *
     * @param municipality The municipality of the user.
     */
    public Tourist(Municipality municipality) {
        super(municipality);
    }
}
