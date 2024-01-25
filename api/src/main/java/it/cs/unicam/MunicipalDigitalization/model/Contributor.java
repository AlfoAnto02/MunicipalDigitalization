package it.cs.unicam.MunicipalDigitalization.model;

import it.cs.unicam.MunicipalDigitalization.io.ContributorView;

/**
 * This class represents a contributor, which is a type of user.
 * It extends the AbstractIUser class.
 * A contributor has a view and can create pending points of interest (POIs) and itineraries.
 */
public class Contributor extends AbstractAuthenticatedUser {

    /**
     * The view of the contributor.
     */
    private final ContributorView view;

    /**
     * Constructor for the Contributor class.
     *
     * @param name         The name of the contributor.
     * @param password     The password of the contributor.
     * @param municipality The municipality of the contributor.
     */
    public Contributor(String name, String password, Municipality municipality) {
        super(name, password, municipality);
        this.view = new ContributorView(this);
    }

    /**
     * This method is used to create a pending point of interest (POI).
     * It delegates the creation to the view.
     */
    private void createPendingPOI() {
        view.createPOI();
    }

    /**
     * This method is used to create a pending itinerary.
     * It delegates the creation to the view.
     */
    private void createPendingItinerary() {
        view.createItinerary();
    }

    /**
     * This method is used to create a pending content.
     * It delegates the creation to the view.
     */
    private void createPendingContent() {
        view.createContent();
    }

}