package it.cs.unicam.MunicipalDigitalization.db.controllers;

import it.cs.unicam.MunicipalDigitalization.api.util.ElementStatus;
import it.cs.unicam.MunicipalDigitalization.api.util.UserRole;
import it.cs.unicam.MunicipalDigitalization.db.Services.MunicipalService;
import it.cs.unicam.MunicipalDigitalization.db.Services.POIService;
import it.cs.unicam.MunicipalDigitalization.db.Services.UserService;
import it.cs.unicam.MunicipalDigitalization.db.Services.uploadingServices.POIUploadingService;
import it.cs.unicam.MunicipalDigitalization.db.controllers.dto.input.POIInputDTO;
import it.cs.unicam.MunicipalDigitalization.db.controllers.dto.mappers.POIDTOMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * This class is a RestController that handles the requests related to the POI entity.
 */
@RestController
@AllArgsConstructor(onConstructor_ = @Autowired)
public class POIController {

    private final POIUploadingService uploadingService;
    private final POIService poiService;
    private final POIDTOMapper poiDTOMapper;
    private final UserService userService;
    private final MunicipalService municipalService;

    /**
     * Returns all the POIs in the database
     *
     * @param id the id of the municipality
     * @return all the POIs in the database
     */
    @RequestMapping(value = "/v1/pois/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getPOIsByMunicipalityID(@PathVariable Long id) {
        return new ResponseEntity<>(poiService.getAllPOIs()
                .stream()
                .filter(poi -> poi.getMunicipality().getId().equals(id))
                .filter(poi -> poi.getElementStatus().equals(ElementStatus.PUBLISHED))
                .map(poiDTOMapper), HttpStatus.OK);
    }

    /**
     * Uploads a POI to the database
     *
     * @param poidto the POI to be uploaded
     * @return a message indicating that the POI has been added
     */

    @RequestMapping(value = "/v1/poi/upload", method = RequestMethod.POST)
    public ResponseEntity<Object> uploadPOI(@RequestBody POIInputDTO poidto) {
        uploadingService.uploadPOI(poidto);
        return new ResponseEntity<>("Product added ", HttpStatus.OK);
    }


/*
    @RequestMapping(value = "/v1/pois", method = RequestMethod.GET)
    public ResponseEntity<Object> getPOIs(){
        return new ResponseEntity<>(poiService.getAllPOIs()
                .stream()
                .filter(poi -> poi.getElementStatus().equals(ElementStatus.PUBLISHED))
                .map(poiDTOMapper),
                HttpStatus.OK);
    }
*/

    /**
     * Returns a POI of a Municipality by its ID
     *
     * @param municipalID id of the Municipality
     * @param poiID       id of the POI
     * @return the POI with the given ID
     */
    @RequestMapping(value = "/v1/poi/{municipalID}/{poiID}", method = RequestMethod.GET)
    public ResponseEntity<Object> getPOIByIds(@PathVariable Long municipalID, @PathVariable Long poiID) {
        return new ResponseEntity<>(municipalService.getMunicipalByID(municipalID).getPOIList()
                .stream()
                .filter(poi -> poi.getElementStatus().equals(ElementStatus.PUBLISHED))
                .filter(poi -> poi.getId().equals(poiID))
                .map(poiDTOMapper), HttpStatus.OK);
    }

    /**
     * Returns all the POIs of the Municipality. Only the curator can do this Rest call
     *
     * @param curatorId the id of the curator
     * @return all the Pending POIs in the database
     */
    @RequestMapping(value = "/v1/pois/pending/{curatorId}", method = RequestMethod.GET)
    public ResponseEntity<Object> getPendingPOIs(@PathVariable Long curatorId) {
        if (userService.getUserById(curatorId).getRole().contains(UserRole.CURATOR)) {
            return new ResponseEntity<>(poiService.getAllPOIs()
                    .stream()
                    .filter(poi -> poi.getMunicipality().equals(userService.getUserById(curatorId).getMunicipality()))
                    .filter(poi -> poi.getElementStatus().equals(ElementStatus.PENDING))
                    .map(poiDTOMapper),
                    HttpStatus.OK);
        }
        return new ResponseEntity<>("You are not a curator", HttpStatus.FORBIDDEN);
    }

}