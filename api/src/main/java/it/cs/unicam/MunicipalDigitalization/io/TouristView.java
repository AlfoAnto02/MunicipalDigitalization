package it.cs.unicam.MunicipalDigitalization.io;

import it.cs.unicam.MunicipalDigitalization.model.Municipality;
import it.cs.unicam.MunicipalDigitalization.model.actors.IUser;
import it.cs.unicam.MunicipalDigitalization.util.controllers.ViewController;

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
    }

    /**
     * This method start the View of a selected POI. Before showing the specific Details of the POI
     * It will show the general Info of the POIs that are Stored in the Municipality
     */
    public void viewPOI() {
        System.out.println("This are the POIs Present in the Municipality");
        System.out.println(this.viewController.getPOIsInformation());
        this.viewController.getPOIDetails(this.getStringInput("Please Select a POI using an ID"));
    }

    /**
     * This method start the View of a selected Itinerary. Before showing the specific Details of the Itinerary
     * It will show the general Info of the Itineraries that are Stored in the Municipality
     */
    public void viewItinerary() {
        System.out.println("This are the Itineraries Present in the Municipality");
        System.out.println(this.viewController.getItinerariesInformation());
        this.viewController.getItineraryDetails(this.getStringInput("Please Select a POI using an ID"));
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
}
