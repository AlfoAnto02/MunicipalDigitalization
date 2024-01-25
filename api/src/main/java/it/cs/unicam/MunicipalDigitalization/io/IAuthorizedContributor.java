package it.cs.unicam.MunicipalDigitalization.io;

import it.cs.unicam.MunicipalDigitalization.model.*;
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
    private final AuthorizedContributor authorizedContributor;


    /**
     * This is the Constructor of the Class. It has to initialize all the Controllers
     * of the class.
     *
     * @param authorizedContributor Actor
     */
    public IAuthorizedContributor(AuthorizedContributor authorizedContributor) {
        super(authorizedContributor);
        this.authorizedContributor = authorizedContributor;
        this.municipality = authorizedContributor.getMunicipality();
        this.poiController = new POIController(this, municipality);
        this.itineraryController = new ItineraryController(this, municipality);
        this.contentController = new ContentController(this, municipality);
    }

    /**
     * This method is used to create a POI.
     * It creates a POI, uploads it, and prints a message to the user.
     */
    public void createPOI() {
        AuthorizedPOI poi = new AuthorizedPOI(this.authorizedContributor, authorizedContributor.getMunicipality());
        super.createPOI(poi);
        this.poiController.upload(poi);
        System.out.println("Your Poi has been created !!");
    }

    /**
     * This method is used to create an itinerary.
     * It creates an itinerary, uploads it, and prints a message to the user.
     */
    public void createItinerary() {
        AuthorizedItinerary itinerary = new AuthorizedItinerary(this.authorizedContributor, authorizedContributor.getMunicipality());
        super.createItinerary(itinerary);
        this.itineraryController.upload(itinerary);
        System.out.println("Your Itinerary has been created !!");
    }

    /**
     * This method is used to create content.
     * It creates content, uploads it, and prints a message to the user.
     */
    public void createContent() {
        IMunicipalElements municipalElement = super.selectMunicipalElement();
        AuthorizedContent content = new AuthorizedContent(this.authorizedContributor, municipalElement);
        super.createContent(content);
        this.contentController.uploadContent(content, municipalElement);
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