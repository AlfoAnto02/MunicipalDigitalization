package it.cs.unicam.MunicipalDigitalization.io;

import it.cs.unicam.MunicipalDigitalization.model.*;
import it.cs.unicam.MunicipalDigitalization.util.*;

import java.util.List;
import java.util.Scanner;

public class AbstractIContributorsView implements IContributorsView {

    /**
     *
     * The scanner to get input from the user.
     *
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

    public AbstractIContributorsView(Municipality municipality) {
        this.municipality = municipality;
        this.inputScanner = new Scanner(System.in);
        this.poiController = new POIController(this, municipality);
        this.itineraryController = new ItineraryController(this, municipality);
        this.contentController = new ContentController(this, municipality);
    }

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
        return null;
    }

    private void showListOfPOIs() {
        List<IPOI> poiList = this.itineraryController.getPOIList();
        System.out.println("Please, Select the number of which Poi do you wanna add to your itinerary !!! ");
        for (int i = 0; i < poiList.size(); i++) {
            System.out.printf("%d %s%n", i, poiList.get(i).getName());
        }
    }

    public void selectPOI(IItinerary itinerary) {
        this.showListOfPOIs();
        do {
            this.itineraryController.selectPOIToAdd(itinerary, this.itineraryController.getPOIList().get(inputScanner.nextInt()));
        } while (itinerary.getListOfPOIs().isEmpty() || !confirmItinerary());
    }

    private void selectPoisAction() {

    }

    private boolean confirmItinerary() {
        return getStringInput("Do you want to continue Adding POIs?  Y/N").equalsIgnoreCase("Y");
    }

    public void setItineraryName(IItinerary itinerary) {
        this.itineraryController.setItineraryName(itinerary, this.getStringInput("Please Enter a Name for your Itinerary!!! "));
    }

    public void setType(IPOI poi) {
        this.showListOfTypes();
        String typeString = getStringInput("Please Select a Type");
        if (!Type.getTypes().contains(typeString)) {
            typeString = getStringInput("Please Select a Type from the list");
            showListOfTypes();
        }
        poiController.setPOIType(typeString, poi);
    }

    public void setPOIName(IPOI poi) {
        poiController.setPOIName(getStringInput("Please Insert a Name for your POI"), poi);
    }

    private Contributor getUser() {
        return null;
    }

    private void getContentTypeList() {

    }

    private void selectType(IContent content) {

    }

    private void setDescription(IContent content) {

    }

    private void setLink(IContent content) {

    }

    private void setPhoto(IContent content) {

    }

    /**
     * This method is used to create a point of interest (POI).
     * The implementation should handle the creation of a new POI.
     */
    @Override
    public void createPOI() {

    }

    /**
     * This method is used to create an itinerary.
     * The implementation should handle the creation of a new itinerary.
     */
    @Override
    public void createItinerary() {

    }

    /**
     * This method is used to create a content.
     * The implementation should handle the creation of a new content.
     */
    @Override
    public void createContent() {

    }

    /**
     * This method is used to get the POI controller.
     * The implementation should return the POI controller.
     *
     * @return The POI controller.
     */
    @Override
    public POIController getPOIController() {
        return null;
    }

    /**
     * This method is used to get the itinerary controller.
     * The implementation should return the itinerary controller.
     *
     * @return The itinerary controller.
     */
    @Override
    public ItineraryController getItineraryController() {
        return null;
    }

    /**
     * This method is used to get the content controller.
     * The implementation should return the content controller.
     *
     * @return The content controller.
     */
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
     * This method is used to get input from the user
     *
     * @param message The message to be displayed to the user.
     * @return the input from the user, a string.
     */
    @Override
    public String getStringInput(String message) {
        System.out.println(message);
        return inputScanner.nextLine();
    }
}
