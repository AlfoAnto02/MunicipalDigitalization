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
public class AbstractIContributorsView implements IContributorsView {

    /**
     * The scanner to get input from the user.
     */
    protected final Scanner inputScanner;

    /**
     * The POI controller of the contributor.
     */
    private POIController poiController;

    /**
     * The itinerary controller of the contributor.
     */
    private ItineraryController itineraryController;

    /**
     * The content controller of the contributor.
     */
    private ContentController contentController;

    /**
     * The municipality of the contributor.
     */
    private final Municipality municipality;

    /**
     * Constructor for the IContributor class.
     *
     * @param municipality The municipality of the contributor.
     */
    public AbstractIContributorsView(Municipality municipality) {
        this.municipality = municipality;
        this.inputScanner = new Scanner(System.in);
        this.poiController = new POIController(this, municipality);
        this.itineraryController = new ItineraryController(this, municipality);
        this.contentController = new ContentController(this, municipality);
    }

    /**
     * This method is used to set the POI Coordinates.
     * @param poi The POI whose coordinates are to be set.
     */
    public void setPOICoordinates(IPOI poi) {
        double x = getInput("Please Insert the x coordinate");
        double y = getInput("Please Insert the y coordinate");
        poiController.setPOICoordinates(new Coordinates(x, y), poi);
    }

    /**
     * This method is used to show a list of types.
     */
    public void showListOfTypes() {
        System.out.println("Select one of the following types");
        System.out.println(Type.getTypes());
    }

    private String showPOIList() {
        //TODO: implement this method
        return null;
    }

    /**
     * This method is used to show a list of POIs.
     */
    private void showListOfPOIs() {
        List<IPOI> poiList = this.itineraryController.getPOIList();
        System.out.println("Please, Select the number of which Poi do you wanna add to your itinerary !!! ");
        for (int i = 0; i < poiList.size(); i++) {
            System.out.printf("%d %s%n", i, poiList.get(i).getName());
        }
    }

    /**
     * This method is used to select a POI.
     *
     * @param itinerary The itinerary to which the POI is to be added.
     */
    public void selectPOI(IItinerary itinerary) {
        this.showListOfPOIs();
        do {
            this.itineraryController.selectPOIToAdd(itinerary, this.itineraryController.getPOIList().get(inputScanner.nextInt()));
        } while (itinerary.getListOfPOIs().isEmpty() || !confirmItinerary());
    }

    private void selectPoisAction() {
        //TODO: implement this method
    }

    /**
     * This method is used to confirm an itinerary.
     *
     * @return True if the itinerary is confirmed, false otherwise.
     */
    private boolean confirmItinerary() {
        return getStringInput("Do you want to continue Adding POIs?  Y/N").equalsIgnoreCase("Y");
    }

    /**
     * This method is used to set the name of an itinerary.
     *
     * @param itinerary The itinerary whose name is to be set.
     */
    public void setItineraryName(IItinerary itinerary) {
        this.itineraryController.setItineraryName(itinerary, this.getStringInput("Please Enter a Name for your Itinerary!!! "));
    }

    /**
     * This method is used to set the type of a POI.
     *
     * @param poi The POI whose type is to be set.
     */
    public void setType(IPOI poi) {
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
    public void setPOIName(IPOI poi) {
        poiController.setPOIName(getStringInput("Please Insert a Name for your POI"), poi);
    }

    private Contributor getUser() {
        //TODO: implement this method
        return null;
    }

    private void getContentTypeList() {
        //TODO: implement this method
    }

    private void selectType(IContent content) {
        //TODO: implement this method
    }

    private void setDescription(IContent content) {
        //TODO: implement this method
    }

    private void setLink(IContent content) {
        //TODO: implement this method
    }

    private void setPhoto(IContent content) {
        //TODO: implement this method
    }

    @Override
    public void createPOI() {
        //TODO: implement this method
    }

    @Override
    public void createItinerary() {
        //TODO: implement this method
    }

    @Override
    public void createContent() {
        //TODO: implement this method
    }

    @Override
    public POIController getPOIController() {
        return null;
    }

    @Override
    public ItineraryController getItineraryController() {
        return null;
    }

    @Override
    public ContentController getContentController() {
        return null;
    }

    /**
     * This method is used to get input from the user.
     *
     * @param message The message to be displayed to the user.
     * @return The input from the user, a double.
     */
    private double getInput(String message) {
        System.out.println(message);
        return inputScanner.nextDouble();
    }

    /**
     * This method is used to get string input from the user.
     *
     * @param message The message to be displayed to the user.
     * @return The string input from the user.
     */
    public String getStringInput(String message) {
        System.out.println(message);
        return inputScanner.nextLine();
    }
}
