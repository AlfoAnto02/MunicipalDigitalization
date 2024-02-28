package it.cs.unicam.MunicipalDigitalization.db.Services.uploadingServices;

import it.cs.unicam.MunicipalDigitalization.api.util.Coordinate;
import it.cs.unicam.MunicipalDigitalization.api.util.DesignPattern.Builder.POIBuilder;
import it.cs.unicam.MunicipalDigitalization.api.util.DesignPattern.FactoryMethod.POIBuilderFactory;
import it.cs.unicam.MunicipalDigitalization.api.util.UserRole;
import it.cs.unicam.MunicipalDigitalization.db.Services.Mediators.POIMediator;
import it.cs.unicam.MunicipalDigitalization.db.Services.UserService;
import it.cs.unicam.MunicipalDigitalization.db.controllers.dto.input.POIInputDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static it.cs.unicam.MunicipalDigitalization.api.util.MatchingAlgorithms.containsSpecialCharacters;

/**
 * Service class for uploading POIs to the database
 */
@Service
@AllArgsConstructor(onConstructor_ = @Autowired)
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

        if (poiDTO.poi_coordinate() == null) {
            throw new IllegalArgumentException("The coordinates must not be null");
        }

        if (!checkCoordinate(poiDTO.poi_coordinate(), poiDTO)) {
            throw new IllegalArgumentException("The coordinates must be inside the perimeter area of the Municipality");
        }
    }

    /**
     * Check if the coordinate is inside the perimeter area of the Municipality
     *
     * @param coordinate the coordinate to check
     * @return true if the coordinate is inside the perimeter area of the Municipality, false otherwise
     */
    public boolean checkCoordinate(Coordinate coordinate, POIInputDTO poiDTO) {
        List<Coordinate> territory = userService.getUserById(poiDTO.poi_author()).getMunicipality().getTerritory();

        int intersections = 0;

        for (int i = 0, j = territory.size() - 1; i < territory.size(); j = i++) {
            Coordinate c1 = territory.get(i);
            Coordinate c2 = territory.get(j);
            if ((c1.getX() > coordinate.getX()) != (c2.getX() > coordinate.getX())) {
                if (coordinate.getY() < (c2.getY() - c1.getY()) * (coordinate.getX() - c1.getX()) / (c2.getX() - c1.getX()) + c1.getY()) {
                    intersections++;
                }
            }
        }
        return intersections % 2 != 0;
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