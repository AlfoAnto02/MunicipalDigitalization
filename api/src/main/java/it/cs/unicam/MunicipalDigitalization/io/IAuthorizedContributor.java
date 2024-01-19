package it.cs.unicam.MunicipalDigitalization.io;

import it.cs.unicam.MunicipalDigitalization.util.ItineraryController;
import it.cs.unicam.MunicipalDigitalization.util.POIController;

import java.util.List;
import java.util.Scanner;

/**
 * This class represents an authorized contributor's view.
 * It implements the IContributorsView interface.
 * An authorized contributor can create points of interest (POIs) and itineraries.
 * It also has methods to set POI coordinates, show a list of types, set POI name, set type, show POI list, select POI, confirm itinerary, and set itinerary name.
 */
public class IAuthorizedContributor implements IContributorsView {

    /**
     * The scanner to get input from the user.
     */
    protected final Scanner inputScanner;
    /**
     * The POI controller of the authorized contributor.
     */
    private final POIController poiController;
    /**
     * The itinerary controller of the authorized contributor.
     */
    private final ItineraryController itineraryController;
    /**
     * The platform of the authorized contributor.
     */
    private final Platform platform;
    /**
     * The authorized contributor.
     */
    private final AuthorizedContributor contributor;

    /**
     * Constructor for the IAuthorizedContributor class.
     *
     * @param platform    The platform of the authorized contributor.
     * @param contributor The authorized contributor.
     */
    public IAuthorizedContributor(Platform platform, AuthorizedContributor contributor) {
        this.contributor = contributor;
        this.platform = platform;
        this.inputScanner = new Scanner(System.in);
        this.poiController = new POIController(this, platform);
        this.itineraryController = new ItineraryController(this, platform);
    }

    @Override
    public void createPendingPOI() {
        AuthorizedPOI poi = new AuthorizedPOI(this.contributor);
        this.setPOICoordinates(poi);
        this.showListOfTypes();
        this.setType(poi);
        this.setPOIName(poi);
        System.out.println("Your Poi has been created !!");
    }

    /**
     * This method is used to set the coordinates of a POI.
     *
     * @param poi The POI whose coordinates are to be set.
     */
    private void setPOICoordinates(AuthorizedPOI poi) {
        double x = getInput("Please Insert the x coordinate");
        double y = getInput("Please Insert the y coordinate");
        poiController.setPOICoordinates(new Coordinates(x, y), poi);
    }

    /**
     * This method is used to show a list of types.
     */
    private void showListOfTypes() {
        System.out.println("Select one of the following types");
        System.out.println(Type.getTypes());
    }

    /**
     * This method is used to set the type of POI.
     *
     * @param poi The POI whose type is to be set.
     */
    private void setType(AuthorizedPOI poi) {
        this.showListOfTypes();
        String typeString = getStringInput("Please Select a Type");
        if (!Type.getTypes().contains(typeString)) {
            typeString = getStringInput("Please Select a Type from the list");
            showListOfTypes();
        }
        poiController.setPOIType(typeString, poi);
    }

    /**
     * This method is used to set the name of a POI.
     *
     * @param poi The POI whose name is to be set.
     */
    private void setPOIName(AuthorizedPOI poi) {
        poiController.setPOIName(getStringInput("Please Insert a Name for your POI"), poi);
    }

    @Override
    public void createPendingItinerary() {
        AuthorizedItinerary itinerary = new AuthorizedItinerary(this.contributor);
        this.selectPOI(itinerary);
        this.setItineraryName(itinerary);
        this.setItineraryDescription(itinerary);
    }

    /**
     * This method is used to select a POI for an itinerary.
     *
     * @param itinerary The itinerary for which a POI is to be selected.
     */
    private void selectPOI(AuthorizedItinerary itinerary) {
        this.showListOfPOIs();
        do {
            this.itineraryController.selectPOIToAdd(itinerary, this.itineraryController.getPOIList().get(inputScanner.nextInt()));
        } while (itinerary.getListOfPOIs().isEmpty() || !confirmItinerary());
    }

    /**
     * This method is used to set the name of a POI.
     *
     * @param itinerary The POI whose name is to be set.
     */
    private void setItineraryName(AuthorizedItinerary itinerary) {
        this.itineraryController.setItineraryName(itinerary, this.getStringInput("Please Enter a Name for your Itinerary!!! "));
    }

    /**
     * This method is used to set the description of an itinerary.
     *
     * @param itinerary The itinerary whose description is to be set.
     */
    private void setItineraryDescription(AuthorizedItinerary itinerary) {
        this.itineraryController.setItineraryDescription(itinerary, this.getStringInput("Please Enter a Description for your Itinerary !!! "));
    }

    /**
     * This method is used to show a list of POIs.
     */
    private void showListOfPOIs() {
        List<IPOI> poiList = this.itineraryController.getPOIList();
        System.out.println("Please, Select the number of which Poi do you wanna add to your itinerary !!! ");
        for (int i = 0; i < poiList.size(); i++) {
            System.out.println(i + " " + poiList.get(i).getName());
        }
    }

    /**
     * This method is used to confirm an itinerary.
     *
     * @return A boolean value indicating whether the itinerary is confirmed.
     */
    private boolean confirmItinerary() {
        return getStringInput("Do you want to continue Adding POIs?  Y/N").equalsIgnoreCase("Y");
    }

    @Override
    public POIController getPOIController() {
        return this.poiController;
    }

    @Override
    public ItineraryController getItineraryController() {
        return this.itineraryController;
    }

    /**
     * This method is used to get input from the user.
     *
     * @param message The message to be displayed to the user.
     * @return The input from the user.
     */
    private double getInput(String message) {
        System.out.println(message);
        return inputScanner.nextDouble();
    }

    @Override
    public String getStringInput(String message) {
        System.out.println(message);
        return inputScanner.nextLine();
    }
}