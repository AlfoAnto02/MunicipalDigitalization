package it.cs.unicam.MunicipalDigitalization.db.Services.uploadingServices;

import it.cs.unicam.MunicipalDigitalization.api.util.Coordinate;
import it.cs.unicam.MunicipalDigitalization.api.util.DesignPattern.Builder.POIBuilder;
import it.cs.unicam.MunicipalDigitalization.api.util.DesignPattern.FactoryMethod.POIBuilderFactory;
import it.cs.unicam.MunicipalDigitalization.api.util.MatchingAlgorithms;
import it.cs.unicam.MunicipalDigitalization.api.util.UserRole;
import it.cs.unicam.MunicipalDigitalization.db.Services.Mediators.POIMediator;
import it.cs.unicam.MunicipalDigitalization.db.Services.UserService;
import it.cs.unicam.MunicipalDigitalization.db.controllers.dto.input.POIInputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static it.cs.unicam.MunicipalDigitalization.api.util.MatchingAlgorithms.containsSpecialCharacters;

/**
 * Service class for uploading POIs to the database
 */
@Service
public class POIUploadingService {
    /**
     * UserService instance
     */
    private final UserService userService;

    /**
     * POIMediator instance
     */
    private final POIMediator poiMediator;

    /**
     * POIBuilderFactory instance
     */
    private final POIBuilderFactory poiBuilderFactory;

    /**
     * Constructor for POIUploadingService
     *
     * @param userService       UserService instance
     * @param poiMediator       POIMediator instance
     * @param poiBuilderFactory POIBuilderFactory instance
     */
    @Autowired
    public POIUploadingService(UserService userService, POIMediator poiMediator, POIBuilderFactory poiBuilderFactory) {
        this.userService = userService;
        this.poiMediator = poiMediator;
        this.poiBuilderFactory = poiBuilderFactory;
    }

    /**
     * Uploads a POI to the database
     *
     * @param poiDTO the POI to be uploaded
     */
    public void uploadPOI(POIInputDTO poiDTO) {
        checkPOI(poiDTO);
        POIBuilder builder = poiBuilderFactory.createBuilderForUser(userService.getUserById(poiDTO.poi_author()));
        buildPOI(builder, poiDTO);
        poiMediator.savePOI(builder.build());
    }

    /**
     * Builds a POI from a DTO using the right builder
     *
     * @param poiBuilder the builder to be used
     * @param poidto     the DTO to be used
     */
    private void buildPOI(POIBuilder poiBuilder, POIInputDTO poidto) {
        poiBuilder.setPOIAuthor(userService.getUserById(poidto.poi_author()));
        poiBuilder.setPOIMunicipality(userService.getUserById(poidto.poi_author()).getMunicipality());
        poiBuilder.setPOICoordinates(poidto.poi_coordinate());
        poiBuilder.setPOIName(poidto.poi_name());
        poiBuilder.setPOIType(poidto.poiType());
        poiBuilder.setPOIStatus();
    }

    /**
     * Checks if the POI is valid
     *
     * @param poiDTO the POI to be checked
     */
    private void checkPOI(POIInputDTO poiDTO) {
        checkPOIAuthor(poiDTO);
        checkPOICoordinates(poiDTO);
        checkPOIName(poiDTO);
        checkPOIType(poiDTO);
    }

    /**
     * Checks if the POI author is valid
     *
     * @param poiDTO the POI to be checked
     */
    private void checkPOIAuthor(POIInputDTO poiDTO) {
        if (!userService.getUserById((poiDTO.poi_author())).getRole().contains(UserRole.CONTRIBUTOR) && !userService.getUserById((poiDTO.poi_author())).getRole().contains(UserRole.AUTHORIZED_CONTRIBUTOR) && !userService.getUserById((poiDTO.poi_author())).getRole().contains(UserRole.CURATOR)) {
            throw new IllegalArgumentException("The author is not authorized to upload a POI");
        }
    }

    /**
     * Checks if the POI coordinates are valid
     *
     * @param poiDTO the POI to be checked
     */
    private void checkPOICoordinates(POIInputDTO poiDTO) {

        List<Coordinate> territory = userService.getUserById(poiDTO.poi_author()).getMunicipality().getTerritory();

        if (poiDTO.poi_coordinate() == null) {
            throw new IllegalArgumentException("The coordinates must not be null");
        }

        if (!MatchingAlgorithms.isInsidePolygon(territory, poiDTO.poi_coordinate())) {
            throw new IllegalArgumentException("The coordinates must be within the municipality territory");
        }
    }

    /**
     * Checks if the POI name is valid
     *
     * @param poiDTO the POI to be checked
     */
    private void checkPOIName(POIInputDTO poiDTO) {
        if (poiDTO.poi_name().length() > 25 || poiDTO.poi_name().length() < 3) {
            throw new IllegalArgumentException("The name must be between 3 and 25 characters");
        }

        if (containsSpecialCharacters(poiDTO.poi_name())) {
            throw new IllegalArgumentException("The name must not contain special characters");
        }

        if (poiDTO.poi_name().isBlank()) {
            throw new IllegalArgumentException("The name must not be null or blank");
        }
    }

    /**
     * Checks if the POI type is valid
     *
     * @param poiDTO the POI to be checked
     */
    private void checkPOIType(POIInputDTO poiDTO) {
        if (poiDTO.poiType() == null) {
            throw new IllegalArgumentException("The type must not be null");
        }
    }
}