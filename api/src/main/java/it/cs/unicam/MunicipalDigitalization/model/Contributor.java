package it.cs.unicam.MunicipalDigitalization.model;

import it.cs.unicam.MunicipalDigitalization.io.IContributor;

/**
 * This class represents a contributor, which is a type of user.
 * It extends the AbstractUser class.
 * A contributor has a view and can create pending points of interest (POIs) and itineraries.
 */
public class Contributor extends AbstractUser {

    /**
     * The view of the contributor.
     */
    private final IContributor view;

    /**
     * Constructor for the Contributor class.
     *
     * @param name     The name of the contributor.
     * @param password The password of the contributor.
     * @param platform The platform of the contributor.
     */
    public Contributor(String name, String password, Platform platform) {
        super(name, password, platform);
        this.view = new IContributor(platform, this);
    }

    /**
     * This method is used to create a pending point of interest (POI).
     * It delegates the creation to the view.
     */
    public void createPendingPOI() {
        view.createPendingPOI();
    }

    /**
     * This method is used to create a pending itinerary.
     * It delegates the creation to the view.
     */
    public void createPendingItinerary() {
        view.createPendingItinerary();
    }

}