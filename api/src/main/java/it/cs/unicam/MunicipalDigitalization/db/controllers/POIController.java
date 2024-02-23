package it.cs.unicam.MunicipalDigitalization.db.controllers;
import it.cs.unicam.MunicipalDigitalization.api.util.ElementStatus;
import it.cs.unicam.MunicipalDigitalization.api.util.UserRole;
import it.cs.unicam.MunicipalDigitalization.db.Services.UserService;
import it.cs.unicam.MunicipalDigitalization.db.Services.uploadingServices.POIUploadingService;
import it.cs.unicam.MunicipalDigitalization.db.controllers.dto.POIDTO;
import it.cs.unicam.MunicipalDigitalization.db.Services.POIService;
import it.cs.unicam.MunicipalDigitalization.db.mappers.POIDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * This class is a RestController that handles the requests related to the POI entity.
 */

@RestController
public class POIController {

    private final POIUploadingService uploadingService;
    private final POIService poiService;
    private final POIDTOMapper poiDTOMapper;
    private final UserService userService;

    @Autowired
    public POIController(POIUploadingService uploadingService, POIService poiService, POIDTOMapper poiDTOMapper,
                         UserService userService) {
        this.uploadingService = uploadingService;
        this.poiService = poiService;
        this.poiDTOMapper = poiDTOMapper;
        this.userService = userService;
    }

    /**
     * Returns all the POIs in the database
     *
     * @param id the id of the municipality
     * @return all the POIs in the database
     */
    @RequestMapping(value = "/pois/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getPOIsByMunicipalityID(@PathVariable Long id){
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

    @RequestMapping(value = "/poi/upload", method = RequestMethod.POST)
    public ResponseEntity<Object> uploadPOI(@RequestBody POIDTO poidto){
        uploadingService.uploadPOI(poidto);
        return new ResponseEntity<>("Product added :)", HttpStatus.OK);
    }

    /**
     * Returns all the POIs in the database
     *
     * @return all the POIs in the database
     */
    @RequestMapping(value = "/pois", method = RequestMethod.GET)
    public ResponseEntity<Object> getAllPublishedPOIs(){
        return new ResponseEntity<>(poiService.getAllPOIs()
                .stream()
                .filter(poi -> poi.getElementStatus().equals(ElementStatus.PUBLISHED))
                .map(poiDTOMapper),
                HttpStatus.OK);
    }

    /**
     * Returns all the POIs in the database visible only by the curator
     *
     * @param curatorId the id of the curator
     * @return all the Pending POIs in the database
     */
    @RequestMapping(value = "/pois/pending/{curatorId}", method = RequestMethod.GET)
    public ResponseEntity<Object> getPendingPOIs(@PathVariable Long curatorId){
        if(userService.getUserById(curatorId).getRole().contains(UserRole.CURATOR)){
        return new ResponseEntity<>(poiService.getAllPOIs()
                .stream()
                .filter(poi -> poi.getElementStatus().equals(ElementStatus.PENDING))
                .map(poiDTOMapper),
                HttpStatus.OK);
    }
    return new ResponseEntity<>("You are not a curator", HttpStatus.FORBIDDEN);
    }

}