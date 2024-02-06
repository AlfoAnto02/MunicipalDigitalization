package it.cs.unicam.MunicipalDigitalization.api.io;

import it.cs.unicam.MunicipalDigitalization.api.model.Municipality;
import it.cs.unicam.MunicipalDigitalization.api.model.actors.IUser;
import it.cs.unicam.MunicipalDigitalization.api.util.controllers.ViewController;

import java.util.Scanner;

/**
 * This class represents the view for a tourist user in the system.
 * It provides methods to view points of interest (POIs), itineraries, and contents in a municipality.
 */
public class TouristView {

    /**
     * The ViewController instance used to control the view.
     */
    private final ViewController viewController;

    /**
     * The IUser instance representing the tourist using the view.
     */
    private final IUser tourist;

    /**
     * The Municipality instance representing the municipality being viewed.
     */
    private final Municipality municipality;

    /**
     * The Scanner instance used to get user input.
     */
    private final Scanner inputScanner;

    /**
     * Constructor for the TouristView class.
     *
     * @param municipality The Municipality instance representing the municipality being viewed.
     * @param tourist      The IUser instance representing the tourist using the view.
     */
    public TouristView(Municipality municipality, IUser tourist) {
        this.municipality = municipality;
        this.tourist = tourist;
        this.viewController = new ViewController(this, this.municipality);
        this.inputScanner = new Scanner(System.in);
    }

    /**
     * This method is used to view the POIs in the municipality.
     */
    public void viewPOI() {
        System.out.println("This are the POIs Present in the Municipality");
        System.out.println(this.viewController.getPOIs());
        this.viewController.getPOIFullInfo(this.getStringInput("Please Select a POI using an ID"));
    }

    /**
     * This method is used to view the itineraries in the municipality.
     */
    public void viewItinerary() {
        System.out.println("This are the Itineraries Present in the Municipality");
        System.out.println(this.viewController.getItineraries());
        this.viewController.getItineraryFullInfo(this.getStringInput("Please Select a POI using an ID"));
    }

    /**
     * This method is used to get a string input from the user.
     *
     * @param message The message to be displayed to the user.
     * @return The input from the user as a string.
     */
    private String getStringInput(String message) {
        System.out.println(message);
        return inputScanner.nextLine();
    }

    /**
     * This method is used to view the contents in the municipality.
     *
     * @param id The id of the content to be viewed.
     */
    public void viewContents(String id) {
        System.out.println("This are the Contents Present in the Municipal Elements of the Municipality");
        System.out.println(this.viewController.getContents(id));
        this.viewController.getContentFullInfo(this.getStringInput("Please Select a Content using an ID"));
    }
}