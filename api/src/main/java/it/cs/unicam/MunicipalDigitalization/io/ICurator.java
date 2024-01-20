package it.cs.unicam.MunicipalDigitalization.io;

import it.cs.unicam.MunicipalDigitalization.model.Curator;
import it.cs.unicam.MunicipalDigitalization.model.PendingItinerary;
import it.cs.unicam.MunicipalDigitalization.model.PendingPOI;
import it.cs.unicam.MunicipalDigitalization.model.Platform;

import java.util.List;

/**
 * This class represents a curator's view and extends the authorized contributor's view.
 * A curator can validate or invalidate points of interest (POIs) and itineraries.
 */
public class ICurator extends IAuthorizedContributor {

    /**
     * The curator.
     */
    private Curator curator;

    /**
     * Constructor for the ICurator class.
     *
     * @param platform The platform of the curator.
     * @param curator  The curator.
     */
    public ICurator(Platform platform, Curator curator) {
        super(platform, curator);
    }

    /**
     * This method is used to start the process of validating a POI.
     * It prompts the curator to select a POI and decide whether to validate it.
     */
    public void startValidatePOI() {
        List<PendingPOI> list = showPendingPOIs();
        PendingPOI selectedPOI = list.get(inputScanner.nextInt());
        showPOIInfo(selectedPOI);
        String answer = getStringInput("Do you want to validate this POI?? Y/N");
        if (answer.equalsIgnoreCase("Y")) validatePOI(selectedPOI);
        else invalidatePOI(selectedPOI);
    }

    /**
     * This method is used to show a list of pending POIs.
     *
     * @return The list of pending POIs.
     */
    private List<PendingPOI> showPendingPOIs() {
        List<PendingPOI> list = this.getPOIController().getPendingPoiList();
        printList(list);
        return list;
    }

    /**
     * This method is used to show the information of a POI.
     *
     * @param poi The POI whose information is to be shown.
     */
    private void showPOIInfo(PendingPOI poi) {
        System.out.println(poi.toString());
    }

    /**
     * This method is used to validate a POI.
     *
     * @param poi The POI to be validated.
     */
    private void validatePOI(PendingPOI poi) {
        this.getPOIController().validatePOI(poi);
    }

    /**
     * This method is used to invalidate a POI.
     *
     * @param poi The POI to be invalidated.
     */
    private void invalidatePOI(PendingPOI poi) {
        this.getPOIController().invalidatePOI(poi);
    }

    /**
     * This method is used to start the process of validating an itinerary.
     * It prompts the curator to select an itinerary and decide whether to validate it.
     */
    private void startValidateItinerary() {
        List<PendingItinerary> list = showPendingItineraries();
        PendingItinerary selectedItinerary = list.get(inputScanner.nextInt());
        showItineraryInfo(selectedItinerary);
        String answer = getStringInput("Do you want to validate this Itinerary?? Y/N");
        if (answer.equalsIgnoreCase("Y")) validateItinerary(selectedItinerary);
        else invalidateItinerary(selectedItinerary);
    }

    /**
     * This method is used to show a list of pending itineraries.
     *
     * @return The list of pending itineraries.
     */
    private List<PendingItinerary> showPendingItineraries() {
        List<PendingItinerary> list = this.getItineraryController().getPendingItineraryList();
        printList(list);
        return list;
    }

    /**
     * This method is used to show the information of an itinerary.
     *
     * @param itinerary The itinerary whose information is to be shown.
     */
    private void showItineraryInfo(PendingItinerary itinerary) {
        System.out.println(itinerary.toString());
    }

    /**
     * This method is used to validate an itinerary.
     *
     * @param pendingItinerary The itinerary to be validated.
     */
    private void validateItinerary(PendingItinerary pendingItinerary) {
        this.getItineraryController().validateItinerary(pendingItinerary);
    }

    /**
     * This method is used to invalidate an itinerary.
     *
     * @param pendingItinerary The itinerary to be invalidated.
     */
    private void invalidateItinerary(PendingItinerary pendingItinerary) {
        this.getItineraryController().invalidateItinerary(pendingItinerary);
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
            result.append(i++).append(") ").append(item.toString()).append(" \n");
        }
        System.out.println(result);
    }
}