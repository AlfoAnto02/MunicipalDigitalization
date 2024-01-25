package it.cs.unicam.MunicipalDigitalization.io;

import it.cs.unicam.MunicipalDigitalization.model.Curator;
import it.cs.unicam.MunicipalDigitalization.model.PendingContent;
import it.cs.unicam.MunicipalDigitalization.model.PendingItinerary;
import it.cs.unicam.MunicipalDigitalization.model.PendingPOI;

import java.util.List;

/**
 * This class represents a curator's view and extends the authorized contributor's view.
 * A curator can validate or invalidate points of interest (POIs), an itinerary or a content.
 */
public class CuratorView extends AuthorizedContributorView {

//    /**
//     * The curator.
//     */
//    private Curator curator;

    /**
     * Constructor of the class
     *
     * @param curator actor
     */
    public CuratorView(Curator curator) {
        super(curator);
    }

    /**
     * This method is used to start the process of validating a POI.
     * It prompts the curator to select a POI and decide whether to validate it.
     */
    public void startValidatePOI() {
        List<PendingPOI> list = getPOIController().getPendingPoiList();
        printList(list);
        PendingPOI selectedPOI = list.get(inputScanner.nextInt());
        System.out.println(selectedPOI.toString());
        String answer = getStringInput("Do you want to validate this POI?? Y/N");
        if (answer.equalsIgnoreCase("Y")) getPOIController().validatePOI(selectedPOI);
        else getPOIController().invalidatePOI(selectedPOI);
    }

    /**
     * This method is used to start the process of validating an itinerary.
     * It prompts the curator to select an itinerary and decide whether to validate it.
     */
    public void startValidateItinerary() {
        List<PendingItinerary> list = getItineraryController().getPendingItineraryList();
        printList(list);
        PendingItinerary selectedItinerary = list.get(inputScanner.nextInt());
        System.out.println(selectedItinerary.toString());
        String answer = getStringInput("Do you want to validate this Itinerary?? Y/N");
        if (answer.equalsIgnoreCase("Y")) getItineraryController().validateItinerary(selectedItinerary);
        else getItineraryController().invalidateItinerary(selectedItinerary);
    }

    /**
     * This method is used to start the process of validating a Content.
     * It prompts the Curator to select a Content and decide to validate or not
     */
    public void startValidateContent() {
        List<PendingContent> list = getContentController().getPendingContents();
        printList(list);
        PendingContent selectedContent = list.get(inputScanner.nextInt());
        System.out.println(selectedContent.toString());
        String answer = getStringInput("Do you want to validate this Content?? Y/N");
        if (answer.equalsIgnoreCase("Y")) getContentController().validateContent(selectedContent);
        else getContentController().invalidateContent(selectedContent);
    }

    /**
     * This method is used to print a list of items.
     *
     * @param list The list of items to be printed.
     */
    private void printList(List<?> list) {
        StringBuilder result = new StringBuilder();
        int i = 0;
        for (Object item : list) {
            result.append(String.format("%d) %s%n", i++, item));
        }
        System.out.println(result);
    }
}