package it.cs.unicam.MunicipalDigitalization.db.controllers;
import it.cs.unicam.MunicipalDigitalization.db.Services.uploadingServices.POIUploadingService;
import it.cs.unicam.MunicipalDigitalization.db.controllers.dto.POIDTO;
import it.cs.unicam.MunicipalDigitalization.db.Services.POIService;
import it.cs.unicam.MunicipalDigitalization.db.mappers.POIDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * This class represents the controller for points of interest (POIs).
 */

@RestController
public class POIController {

    private final POIUploadingService uploadingService;
    private final POIService poiService;
    private final POIDTOMapper poiDTOMapper;

    @Autowired
    public POIController(POIUploadingService uploadingService, POIService poiService, POIDTOMapper poiDTOMapper) {
        this.uploadingService = uploadingService;
        this.poiService = poiService;
        this.poiDTOMapper = poiDTOMapper;
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




}