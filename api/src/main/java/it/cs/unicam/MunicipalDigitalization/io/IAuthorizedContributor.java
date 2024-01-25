package it.cs.unicam.MunicipalDigitalization.io;

import it.cs.unicam.MunicipalDigitalization.model.AuthorizedContributor;
import it.cs.unicam.MunicipalDigitalization.model.AuthorizedItinerary;
import it.cs.unicam.MunicipalDigitalization.model.AuthorizedPOI;
import it.cs.unicam.MunicipalDigitalization.model.Municipality;
import it.cs.unicam.MunicipalDigitalization.util.ContentController;
import it.cs.unicam.MunicipalDigitalization.util.ItineraryController;
import it.cs.unicam.MunicipalDigitalization.util.POIController;

/**
 * This class represents an authorized contributor's view.
 * It implements the IContributorsView interface.
 * An authorized contributor can create points of interest (POIs) and itineraries.
 * It also has methods to set POI coordinates, show a list of types, set POI name, set type, show POI list, select POI, confirm itinerary, and set itinerary name.
 */
public class IAuthorizedContributor extends AbstractIContributorsView {

    /**
     * The POI controller of the authorized contributor.
     */
    private final POIController poiController;

    /**
     * The itinerary controller of the authorized contributor.
     */
    private final ItineraryController itineraryController;

    /**
     * The content controller of the authorized contributor.
     */
    private final ContentController contentController;

    /**
     * The municipality of the authorized contributor.
     */
    private final Municipality municipality;

    /**
     * The authorized contributor.
     */
    private final AuthorizedContributor contributor;

    /**
     * Constructor for the IAuthorizedContributor class.
     *
     * @param municipality The municipality of the authorized contributor.
     * @param contributor  The authorized contributor.
     */
    public IAuthorizedContributor(Municipality municipality, AuthorizedContributor contributor) {
        super(municipality);
        this.contributor = contributor;
        this.municipality = municipality;
        this.poiController = new POIController(this, municipality);
        this.itineraryController = new ItineraryController(this, municipality);
        this.contentController = new ContentController(this, municipality);
    }

    @Override
    public void createPOI() {
        AuthorizedPOI poi = new AuthorizedPOI(this.contributor);
        this.setPOICoordinates(poi);
        this.showListOfTypes();
        this.setType(poi);
        this.setPOIName(poi);
        System.out.println("Your Poi has been created !!");
    }

    @Override
    public void createItinerary() {
        AuthorizedItinerary itinerary = new AuthorizedItinerary(this.contributor);
        this.selectPOI(itinerary);
        this.setItineraryName(itinerary);
    }

    @Override
    public void createContent() {
        //TODO - implement IAuthorizedContributor.createContent
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