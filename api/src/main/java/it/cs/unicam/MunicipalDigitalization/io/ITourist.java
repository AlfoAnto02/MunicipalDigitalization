package it.cs.unicam.MunicipalDigitalization.io;

import it.cs.unicam.MunicipalDigitalization.model.Municipality;
import it.cs.unicam.MunicipalDigitalization.model.User;
import it.cs.unicam.MunicipalDigitalization.util.ViewController;

import java.util.Scanner;

public class ITourist {
    private final ViewController viewController;
    private final User tourist;
    private final Municipality municipality;

    private final Scanner inputScanner;
    public ITourist(Municipality municipality, User tourist){
        this.municipality=municipality;
        this.tourist=tourist;
        this.viewController=new ViewController(this,this.municipality);
        this.inputScanner = new Scanner(System.in);
    }

    public void viewPOI() {
        this.getPOIs();
        this.selectPOI();
    }

    public void viewItinerary() {
        this.getItineraries();
        this.viewItinerary();
    }

    private void getPOIs() {
        System.out.println("This are the POIs Present in the Municipality");
        System.out.println(this.viewController.getPOIsInformation());
    }

    private void selectPOI(){
        this.viewController.getPOIDetails(this.getStringInput("Please Select a POI using an ID"));
    }

    private void getItineraries(){
        System.out.println("This are the Itineraries Present in the Municipality");
        System.out.println(this.viewController.getItinerariesInformation());
    }

    private void selectItinerary(){
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
