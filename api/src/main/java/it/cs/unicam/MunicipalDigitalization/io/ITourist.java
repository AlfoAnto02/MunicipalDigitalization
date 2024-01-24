package it.cs.unicam.MunicipalDigitalization.io;

import it.cs.unicam.MunicipalDigitalization.model.Municipality;
import it.cs.unicam.MunicipalDigitalization.model.User;
import it.cs.unicam.MunicipalDigitalization.util.ViewController;

import java.util.Scanner;

public class ITourist {
    private ViewController viewController;
    private User tourist;
    private Municipality municipality;

    private Scanner inputScanner;
    public ITourist(Municipality municipality, User tourist){
        this.municipality=municipality;
        this.tourist=tourist;
        this.viewController=new ViewController(this,this.municipality);
        Scanner inputScanner = new Scanner(System.in);
    }

    public void getPOIs() {
        System.out.println("This are the POIs Present in the Municipality");
        System.out.println(this.viewController.getPOIsInformation());
    }

    public void selectPOI(String id){
        System.out.println("Select a POI using the ID for see the Specific Details of that POI");
        
    }

    public void getItineraries(){
        this.viewController.getItineraryDetails(this.getStringInput("Please Select an Itinerary using an ID"));
    }

    public void selectItinerary(String id){
        this.viewController.getPOIDetails(this.getStringInput("Please Select a POI using an ID"));
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
