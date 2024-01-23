package it.cs.unicam.MunicipalDigitalization.model;

import it.cs.unicam.MunicipalDigitalization.io.IAuthorizedContributor;

/**
 * This class represents an authorized contributor, which is a type of user.
 * It extends the AbstractUser class.
 * An authorized contributor has a view and can create points of interest (POIs) and itineraries.
 */
public class AuthorizedContributor extends AbstractUser {

    /**
     * The view of the authorized contributor.
     * This is an instance of IAuthorizedContributor interface which provides the methods for creating POIs and itineraries.
     */
    private IAuthorizedContributor view;

    /**
     * Constructor for the AuthorizedContributor class.
     *
     * @param name     The name of the authorized contributor.
     * @param password The password of the authorized contributor.
     * @param municipality The municipality of the authorized contributor.
     */
    public AuthorizedContributor(String name, String password, Municipality municipality) {
        super(name, password, municipality);
    }

    /**
     * This method is used to create a point of interest (POI).
     * It delegates the creation of POI to the view.
     */
    private void createPOI() {
        view.createPendingPOI();
    }

    /**
     * This method is used to create an itinerary.
     * It delegates the creation of itinerary to the view.
     */
    private void createItinerary() {
        view.createPendingItinerary();
    }
}