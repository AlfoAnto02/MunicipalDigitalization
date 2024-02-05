package it.cs.unicam.MunicipalDigitalization.api.util.controllers;

import it.cs.unicam.MunicipalDigitalization.api.io.IContributorView;
import it.cs.unicam.MunicipalDigitalization.api.model.elements.IPOI;
import it.cs.unicam.MunicipalDigitalization.api.model.Municipality;
import it.cs.unicam.MunicipalDigitalization.api.model.elements.PendingPOI;
import it.cs.unicam.MunicipalDigitalization.api.util.Coordinate;
import it.cs.unicam.MunicipalDigitalization.api.util.enumerations.POIType;

import java.util.List;
import java.util.Optional;

/**
 * This class represents the controller for points of interest (POIs).
 * It provides methods to set the coordinates, type, and name of a POI, get a list of types, get a list of pending POIs, select a POI, and validate or invalidate a POI.
 */
public class POIController {

    /**
     * The contributor's view.
     */
    private final IContributorView contributorView;

    /**
     * The municipality associated with the Controller.
     */
    private final Municipality municipality;

    /**
     * Constructor for the POIController class.
     * It initializes the POIController with the provided view and municipality.
     *
     * @param view         The contributor's view.
     * @param municipality The municipality.
     */
    public POIController(IContributorView view, Municipality municipality) {
        this.contributorView = view;
        this.municipality = municipality;
    }

    /**
     * This method is used to validate a Pending POI.
     *
     * @param poi The POI to be validated.
     */
    public void validatePOI(PendingPOI poi) {
        this.municipality.getPendingManager().removePOI(poi);
        this.municipality.getPOIList().add(poi);
    }

    /**
     * This method is used to invalidate a Pending POI.
     *
     * @param poi The POI to be invalidated.
     */
    public void invalidatePOI(PendingPOI poi) {
        this.municipality.getPendingManager().removePOI(poi);
    }

    /**
     * This method is used to append a pending POI to the pending Manager of the Municipality
     * using the Append Method present in the Municipality.
     *
     * @param poi The pending POI to append.
     */
    public void append(PendingPOI poi) {
        this.municipality.appendPOI(poi);
    }

    /**
     * This method is used to upload a POI to the Municipality.
     *
     * @param poi The POI to be uploaded.
     */
    public void upload(IPOI poi) {
        this.municipality.uploadPOI(poi);
    }

    /**
     * This method is used to set the coordinate of a POI.
     *
     * @param coordinate The coordinate to be set.
     * @param poi         The POI whose coordinate are to be set.
     */
    public void setPOICoordinates(Coordinate coordinate, IPOI poi) {
        if (municipality.checkCoordinates(coordinate)) {
            poi.setCoordinates(coordinate);
        } else throw new IllegalArgumentException("Coordinate error");
    }

    /**
     * This method is used to set the type of POI.
     *
     * @param typeString The type to be set.
     * @param poi        The POI whose type is to be set.
     */
    public void setPOIType(String typeString, IPOI poi) {
        Optional<POIType> oType = POIType.selectType(typeString);
        if (oType.isEmpty()) {
            throw new IllegalArgumentException("Wrong POIType");
        } else {
            POIType POIType = oType.get();
            poi.setType(POIType);
        }
    }

    /**
     * This method is used to set the name of a POI.
     *
     * @param name The name to be set.
     * @param poi  The POI whose name is to be set.
     */
    public void setPOIName(String name, IPOI poi) {
        if (name.isEmpty()) throw new IllegalArgumentException("Illegal Name");
        poi.setName(name);
    }

    /**
     * This method is used to get a list of pending POIs.
     *
     * @return A list of pending POIs.
     */
    public List<PendingPOI> getPendingPoiList() {
        return this.municipality.getPendingManager().getPendingPOIs();
    }


}