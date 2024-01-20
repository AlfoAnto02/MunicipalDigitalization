package it.cs.unicam.MunicipalDigitalization.io;

import it.cs.unicam.MunicipalDigitalization.model.PendingItinerary;
import it.cs.unicam.MunicipalDigitalization.model.PendingPOI;
import it.cs.unicam.MunicipalDigitalization.model.Platform;
import it.cs.unicam.MunicipalDigitalization.util.Coordinates;
import it.cs.unicam.MunicipalDigitalization.util.ItineraryController;
import it.cs.unicam.MunicipalDigitalization.util.POIController;
import it.cs.unicam.MunicipalDigitalization.util.Type;

import java.util.List;
import java.util.Scanner;

/**
 * This class represents a contributor's view.
 * It implements the IContributorsView interface.
 * A contributor can create points of interest (POIs) and itineraries.
 */
public class IContributor implements IContributorsView {

    /**
     * The POI controller of the contributor.
     */
    private final POIController poiController;

    /**
     * The itinerary controller of the contributor.
     */
    private final ItineraryController itineraryController;

    /**
     * The platform of the contributor.
     */
    private final Platform platform;

    /**
     * The contributor.
     */
    private final Contributor contributor;

    /**
     * The scanner to get input from the user.
     */
    private final Scanner inputScanner;

    /**
     * Constructor for the IContributor class.
     *
     * @param platform    The platform of the contributor.
     * @param contributor The contributor.
     */
    public IContributor(Platform platform, Contributor contributor) {
        this.contributor = contributor;
        this.platform = platform;
        this.inputScanner = new Scanner(System.in);
        this.poiController = new POIController(this, platform);
        this.itineraryController = new ItineraryController(this, platform);
    }

    @Override
    public void createPendingPOI() {
        PendingPOI poi = new PendingPOI(this.contributor);
        this.setPOICoordinates(poi);
        this.showListOfTypes();
        this.setType(poi);
        this.setPendingPOIName(poi);
        this.appendPOI(poi);
        System.out.println("Your Poi has been created !!");
    }

    /**
     * This method is used to set the coordinates of a pending POI.
     *
     * @param poi The pending POI whose coordinates are to be set.
     */
    private void setPOICoordinates(PendingPOI poi) {
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
     * This method is used to set the type of pending POI.
     *
     * @param poi The pending POI whose type is to be set.
     */
    private void setType(PendingPOI poi) {
        showListOfTypes();
        String typeString = getStringInput("Please Select a Type");
        if (!Type.getTypes().contains(typeString)) {
            typeString = getStringInput("Please Select a Type from the list");
            showListOfTypes();
        }
        poiController.setPOIType(typeString, poi);
    }

    /**
     * This method is used to set the name of a pending POI.
     *
     * @param poi The pending POI whose name is to be set.
     */
    private void setPendingPOIName(PendingPOI poi) {
        poiController.setPOIName(getStringInput("Please Insert a Name for your POI"), poi);
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
    public void createPendingItinerary() {
        PendingItinerary itinerary = new PendingItinerary(this.contributor);
        this.selectPOI(itinerary);
        this.setItineraryName(itinerary);
        this.setItineraryDescription(itinerary);
        this.appendItinerary(itinerary);
    }

    /**
     * This method is used to select a POI for an itinerary.
     *
     * @param itinerary The itinerary for which a POI is to be selected.
     */
    private void selectPOI(PendingItinerary itinerary) {
        showListOfPOIs();
        do {
            this.itineraryController.selectPOIToAdd(itinerary, this.itineraryController.getPOIList().get(inputScanner.nextInt()));
        } while (itinerary.getListOfPOIs().isEmpty() || !confirmItinerary());
    }

    /**
     * This method is used to set the name of an itinerary.
     *
     * @param itinerary The itinerary whose name is to be set.
     */
    private void setItineraryName(IItinerary itinerary) {
        this.itineraryController.setItineraryName(itinerary, this.getStringInput("Please Enter a Name for your Itinerary!!! "));
    }

    /**
     * This method is used to set the description of an itinerary.
     *
     * @param itinerary The itinerary whose description is to be set.
     */
    private void setItineraryDescription(PendingItinerary itinerary) {
        this.itineraryController.setItineraryDescription(itinerary, this.getStringInput("Please Enter a Description for your Itinerary !!! "));
    }

    /**
     * This method is used to append an itinerary.
     *
     * @param itinerary The itinerary to be appended.
     */
    private void appendItinerary(PendingItinerary itinerary) {
        this.itineraryController.append(itinerary);
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