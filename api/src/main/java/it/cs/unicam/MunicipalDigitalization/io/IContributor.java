package it.cs.unicam.MunicipalDigitalization.io;

import it.cs.unicam.MunicipalDigitalization.model.*;
import it.cs.unicam.MunicipalDigitalization.util.*;

import java.util.List;
import java.util.Scanner;

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
     * Constructor for the IContributor class.
     *
     * @param municipality The municipality of the contributor.
     * @param contributor  The contributor.
     */
    public IContributor(Municipality municipality, Contributor contributor) {
        super(municipality);
        this.contributor = contributor;
        this.municipality = municipality;
        this.poiController = new POIController(this, municipality);
        this.itineraryController = new ItineraryController(this, municipality);
        this.contentController = new ContentController(this, municipality);
    }

    @Override
    public void createPOI() {
        PendingPOI poi = new PendingPOI(this.contributor);
        this.setPOICoordinates(poi);
        this.showListOfTypes();
        this.setType(poi);
        this.setPOIName(poi);
        this.appendPOI(poi);
        System.out.println("Your Poi has been created !!");
    }

    /**
     * This method is used to append a POI.
     *
     * @param pendingPOI The POI to be appended.
     */
    public void appendPOI(PendingPOI pendingPOI) {
        this.poiController.append(pendingPOI);
    }

    @Override
    public void createItinerary() {
        PendingItinerary itinerary = new PendingItinerary(this.contributor);
        this.selectPOI(itinerary);
        this.setItineraryName(itinerary);
        this.appendItinerary(itinerary);
    }

    /**
     * This method is used to append an itinerary.
     *
     * @param itinerary The itinerary to be appended.
     */
    private void appendItinerary(PendingItinerary itinerary) {
        this.itineraryController.append(itinerary);
    }

    @Override
    public void createContent() {
        //TODO - implement IContributor.createContent
    }

    /**
     * This method is used to append a content.
     *
     * @param pendingContent The content to be appended.
     */
    public void appendContent(PendingContent pendingContent) {
        this.contentController.append(pendingContent);
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