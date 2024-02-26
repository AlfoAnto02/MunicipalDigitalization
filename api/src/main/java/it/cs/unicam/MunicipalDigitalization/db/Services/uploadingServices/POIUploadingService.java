package it.cs.unicam.MunicipalDigitalization.db.Services.uploadingServices;

import it.cs.unicam.MunicipalDigitalization.api.util.DesignPattern.Builder.POIBuilder;
import it.cs.unicam.MunicipalDigitalization.api.util.DesignPattern.FactoryMethod.POIBuilderFactory;
import it.cs.unicam.MunicipalDigitalization.api.util.UserRole;
import it.cs.unicam.MunicipalDigitalization.db.Services.Mediators.POIMediator;
import it.cs.unicam.MunicipalDigitalization.db.Services.UserService;
import it.cs.unicam.MunicipalDigitalization.db.controllers.dto.input.POIInputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static it.cs.unicam.MunicipalDigitalization.api.util.MatchingAlgorithms.containsSpecialCharacters;

/**
 * Service class for uploading POIs to the database
 */
@Service
public class POIUploadingService {
    private final UserService userService;
    private final POIMediator poiMediator;
    private final POIBuilderFactory poiBuilderFactory;

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
     * Checks if a POI is valid
     *
     * @param poiDTO the POI to be checked
     */
    private void checkPOI(POIInputDTO poiDTO) {

        if (!userService.getUserById((poiDTO.poi_author())).getRole().contains(UserRole.CONTRIBUTOR) && !userService.getUserById((poiDTO.poi_author())).getRole().contains(UserRole.AUTHORIZED_CONTRIBUTOR) && !userService.getUserById((poiDTO.poi_author())).getRole().contains(UserRole.CURATOR)) {
            throw new IllegalArgumentException("The author is not authorized to upload a POI");
        }
        
        // TODO check if the poi is within the municipality coordinates

        if (poiDTO.poi_coordinate() == null) {
            throw new IllegalArgumentException("The coordinates must not be null");
        }

        if (poiDTO.poi_name().length() > 25 || poiDTO.poi_name().length() < 3) {
            throw new IllegalArgumentException("The name must be between 3 and 25 characters");
        }

        if (containsSpecialCharacters(poiDTO.poi_name())) {
            throw new IllegalArgumentException("The name must not contain special characters");
        }

        if (poiDTO.poi_name().isBlank()) {
            throw new IllegalArgumentException("The name must not be null or blank");
        }

        if (poiDTO.poiType() == null) {
            throw new IllegalArgumentException("The type must not be null");
        }
    }
}
