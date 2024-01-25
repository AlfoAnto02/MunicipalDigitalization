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
     * The Authenticated User.
     */
    private final AuthenticatedUser user;

    /**
     * Constructor for the IContributor class.
     *
     * @param municipality The municipality of the contributor.
     */
    public AbstractIContributorsView(Municipality municipality, AuthenticatedUser user) {
        this.municipality = municipality;
        this.user = user;
        this.inputScanner = new Scanner(System.in);
        this.poiController = new POIController(this, municipality);
        this.itineraryController = new ItineraryController(this, municipality);
        this.contentController = new ContentController(this);
    }

    /**
     * This method is used to set the POI Coordinates.
     *
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
    public void showListOfPOITypes() {
        System.out.println("Select one of the following types");
        System.out.println(Type.getTypes());
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
        this.showListOfPOITypes();
        String typeString = getStringInput("Please Select a Type");
        if (!Type.getTypes().contains(typeString)) {
            typeString = getStringInput("Please Select a Type from the list");
            showListOfPOITypes();
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

    /**
     * this method returns the user
     *
     * @return the user
     */
    private AuthenticatedUser getUser() {
        return this.user;
    }

    /**
     * This method is used to get the Content Type List.
     */
    public void getContentTypeList() {
        System.out.println("Select one of the following types");
        System.out.println(ContentType.getListOfTypes());
    }

    /**
     * This method is used to select a type.
     *
     * @param content The content to which the type is to be added.
     */
    public void selectType(IContent content) {
        this.contentController.selectType(ContentType.valueOf(getStringInput("Please Select a Type")), content);
    }

    /**
     * This method is used to set the description of a content.
     *
     * @param content The content whose description is to be set.
     */
    public void setDescription(IContent content) {
        this.contentController.setDescription(ContentType.valueOf(getStringInput("Please insert a Description for your Content")), content);
    }

    /**
     * This method is used to set the link of a content.
     *
     * @param content The content whose link is to be set.
     */
    public void setLink(IContent content) {
        this.contentController.setLink(ContentType.valueOf(getStringInput("Please insert a Link for your Content")), content);
    }

    /**
     * This method is used to set the photo of a content.
     *
     * @param content The content whose photo is to be set.
     */
    public void setPhoto(IContent content) {
        this.contentController.setPhoto(ContentType.valueOf(getStringInput("Please insert a Photo for your Content")), content);
    }

    @Override
    public void createPOI(IPOI poi) {
        this.setPOICoordinates(poi);
        this.showListOfPOITypes();
        this.setType(poi);
        this.setPOIName(poi);
    }


    @Override
    public void createItinerary(IItinerary itinerary) {
        this.showListOfPOIs();
        this.selectPOI(itinerary);
        this.setItineraryName(itinerary);
    }

    @Override
    public void createContent(IContent content) {
        this.getContentTypeList();
        this.selectType(content);
        switch (content.getType()) {
            case IMAGE -> {
                System.out.println("Inserisci il link dell'immagine");
                String link = this.inputScanner.nextLine();
                content.setLink(link);
            }
            case LINK -> {
                System.out.println("Inserisci il link");
                String link = this.inputScanner.nextLine();
                content.setLink(link);
            }
            case DESCRIPTION -> {
                System.out.println("Inserisci la descrizione");
                String description = this.inputScanner.nextLine();
                content.setDescription(description);
            }
            default -> throw new IllegalArgumentException("Tipo non valido");
        }
    }

    /**
     * This method is used to select a municipal element.
     * if the element is not found, an exception is thrown.
     *
     * @return The municipal element selected.
     */
    public IMunicipalElements selectMunicipalElement() {
        System.out.println("Scegli un elemento a cui associare il contenuto");
        System.out.println(municipality.getItineraryList());
        System.out.println(municipality.getPOIList());
        System.out.println("Inserisci il nome dell'elemento");
        String name = this.inputScanner.nextLine();
        for (IItinerary itinerary : municipality.getItineraryList()) {
            if (itinerary.getName().equals(name)) {
                return itinerary;
            }
        }

        for (IPOI poi : municipality.getPOIList()) {
            if (poi.getName().equals(name)) {
                return poi;
            }
        }

        throw new IllegalArgumentException("Elemento non trovato");
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
    @Override
    public String getStringInput(String message) {
        System.out.println(message);
        return inputScanner.nextLine();
    }
}
