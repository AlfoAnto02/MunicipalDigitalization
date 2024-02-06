package it.cs.unicam.MunicipalDigitalization.api.io;

import it.cs.unicam.MunicipalDigitalization.api.model.Municipality;
import it.cs.unicam.MunicipalDigitalization.api.model.OSMSystem;
import it.cs.unicam.MunicipalDigitalization.api.model.actors.IUser;
import it.cs.unicam.MunicipalDigitalization.api.util.MunicipalRepository;
import it.cs.unicam.MunicipalDigitalization.api.util.controllers.MapController;
import it.cs.unicam.MunicipalDigitalization.api.util.controllers.ViewController;

import java.util.Scanner;

/**
 * This class represents the view of Every IUser that can interact with the Map and the Platform
 * Every user can see the Map, the POIs stored in the map, The Itineraries and the Contribution Contests.
 */
public class TouristView {

    /**
     * The view Controller of the class
     */
    private final ViewController viewController;

    /**
     * The user that will use this view
     */
    private final IUser tourist;

    /**
     * The municipality of the Controller. Each Municipality has a different View.
     */
    private final Municipality municipality;

    /**
     * Scanner used for input.
     */
    private final Scanner inputScanner;

    private final MapController mapController;

    /**
     * Constructor for the TouristView Class
     *
     * @param municipality municipality of the View
     * @param tourist      IUser that will use this view
     */
    public TouristView(Municipality municipality, IUser tourist) {
        this.municipality = municipality;
        this.tourist = tourist;
        this.viewController = new ViewController(this, this.municipality);
        this.inputScanner = new Scanner(System.in);
        this.mapController = new MapController(this, new OSMSystem(new MunicipalRepository()));
    }

    /**
     * This method start the View of a selected POI. Before showing the specific Details of the POI
     * It will show the general Info of the POIs that are Stored in the Municipality
     */
    public void viewPOI() {
        System.out.println("This are the POIs Present in the Municipality");
        System.out.println(this.viewController.getPOIs());
        this.viewController.getPOIFullInfo(this.getStringInput("Please Select a POI using an ID"));
    }

    /**
     * This method start the View of a selected Itinerary. Before showing the specific Details of the Itinerary
     * It will show the general Info of the Itineraries that are Stored in the Municipality
     */
    public void viewItinerary() {
        System.out.println("This are the Itineraries Present in the Municipality");
        System.out.println(this.viewController.getItineraries());
        this.viewController.getItineraryFullInfo(this.getStringInput("Please Select a POI using an ID"));
    }

    /**
     * This method is used to get input from the user
     *
     * @param message The message to be displayed to the user.
     * @return the input from the user, a string.
     */
    private String getStringInput(String message) {
        System.out.println(message);
        return inputScanner.nextLine();
    }

    public void viewContents(String id) {
        System.out.println("This are the Contents Present in the Municipal Elements of the Municipality");
        System.out.println(this.viewController.getContents(id));
        this.viewController.getContentFullInfo(this.getStringInput("Please Select a Content using an ID"));
    }

    public void viewMunicipalities() {
        System.out.println("This are the Municipalities Present in the Platform");
        System.out.println(this.mapController.getMunicipalities());
    }

    public void viewMap(String id) {
        // TODO - implement TouristView.viewMap
        throw new UnsupportedOperationException();
    }
}
