package it.cs.unicam.MunicipalDigitalization.io;

import it.cs.unicam.MunicipalDigitalization.model.*;
import it.cs.unicam.MunicipalDigitalization.util.ContentController;
import it.cs.unicam.MunicipalDigitalization.util.ItineraryController;
import it.cs.unicam.MunicipalDigitalization.util.POIController;

/**
 * This class represents a contributor's view.
 * It implements the IContributorsView interface.
 * A contributor can create points of interest (POIs) and itineraries.
 */
public class IContributor extends AbstractIContributorsView {

    /**
     * The POI controller of the contributor.
     */
    private final POIController poiController;

    /**
     * The itinerary controller of the contributor.
     */
    private final ItineraryController itineraryController;

    /**
     * The content controller of the contributor.
     */
    private final ContentController contentController;

    /**
     * The municipality of the contributor.
     */
    private final Municipality municipality;

    /**
     * The contributor.
     */
    private final Contributor contributor;

    /**
     * This is the Constructor of che class. It has to initialize all the
     * Controller of the class.
     *
     * @param contributor actor
     */

    public IContributor(Contributor contributor) {
        super(contributor);
        this.contributor = contributor;
        this.municipality = contributor.getMunicipality();
        this.poiController = new POIController(this, municipality);
        this.itineraryController = new ItineraryController(this, municipality);
        this.contentController = new ContentController(this, municipality);
    }

    /**
     * This method is used to create a point of interest (POI).
     * It creates a POI, uploads it, and prints a message to the user.
     */
    public void createPOI() {
        PendingPOI pendingPOI = new PendingPOI(this.contributor, this.municipality);
        super.createPOI(pendingPOI);
        this.poiController.append(pendingPOI);
        System.out.println("Your Poi has been created !!");
    }

    /**
     * This method is used to create an itinerary.
     * It creates an itinerary, uploads it, and prints a message to the user.
     */
    public void createItinerary() {
        PendingItinerary itinerary = new PendingItinerary(this.contributor, this.municipality);
        super.createItinerary(itinerary);
        this.itineraryController.append(itinerary);
        System.out.println("Your Itinerary has been created !!");
    }

    /**
     * This method is used to create a content.
     * It creates a content, uploads it, and prints a message to the user.
     */
    public void createContent() {
        IMunicipalElements municipalElements = super.selectMunicipalElement();
        PendingContent pendingContent = new PendingContent(this.contributor, municipalElements);
        super.createContent(pendingContent);
        this.contentController.appendContent(pendingContent);
    }

    @Override
    public POIController getPOIController() {
        return this.poiController;
    }

    @Override
    public ItineraryController getItineraryController() {
        return this.itineraryController;
    }

    @Override
    public ContentController getContentController() {
        return this.contentController;
    }
}