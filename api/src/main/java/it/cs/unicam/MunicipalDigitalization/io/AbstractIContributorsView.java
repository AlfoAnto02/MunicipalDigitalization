package it.cs.unicam.MunicipalDigitalization.io;

import it.cs.unicam.MunicipalDigitalization.model.*;
import it.cs.unicam.MunicipalDigitalization.util.*;

import java.util.List;

public class AbstractIContributorsView implements IContributorsView {

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

    private void selectPOI(AuthorizedItinerary itinerary) {
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

    private void setItineraryName(AuthorizedItinerary itinerary) {
        this.itineraryController.setItineraryName(itinerary, this.getStringInput("Please Enter a Name for your Itinerary!!! "));
    }

    private void setType(AuthorizedPOI poi) {
        this.showListOfTypes();
        String typeString = getStringInput("Please Select a Type");
        if (!Type.getTypes().contains(typeString)) {
            typeString = getStringInput("Please Select a Type from the list");
            showListOfTypes();
        }
        poiController.setPOIType(typeString, poi);
    }

    private void setPOIName(AuthorizedPOI poi) {
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
     * This method is used to get string input from the user.
     *
     * @param message The message to be displayed to the user.
     * @return The string input from the user.
     */
    @Override
    public String getStringInput(String message) {
        return null;
    }
}
