package it.cs.unicam.MunicipalDigitalization.model;

import it.cs.unicam.MunicipalDigitalization.io.ICurator;

/**
 * This class represents a curator, which is a type of authorized contributor.
 * It extends the AuthorizedContributor class.
 * A curator has a view and can validate points of interest (POIs).
 */
public class Curator extends AuthorizedContributor {

    /**
     * The view of the curator.
     */
    private ICurator view;

    /**
     * Constructor for the Curator class.
     *
     * @param name     The name of the curator.
     * @param password The password of the curator.
     * @param platform The platform of the curator.
     */
    public Curator(String name, String password, Platform platform) {
        super(name, password, platform);
    }

    /**
     * This method is used to validate a point of interest (POI) using the view.
     */
    private void validatePOI() {
        view.startValidatePOI();
    }

    /**
     * This method is used to validate an Itinerary using the view.
     */
    private void validateItinerary() {
        view.startValidateItinerary();
    }
}