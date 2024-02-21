package it.cs.unicam.MunicipalDigitalization.api.util.controllers;

import it.cs.unicam.MunicipalDigitalization.api.model.Municipality;
import it.cs.unicam.MunicipalDigitalization.api.model.actors.AuthorizedContributor;
import it.cs.unicam.MunicipalDigitalization.api.util.Coordinate;
import it.cs.unicam.MunicipalDigitalization.api.util.UserRole;
import it.cs.unicam.MunicipalDigitalization.api.util.controllers.dto.POIDTO;
import it.cs.unicam.MunicipalDigitalization.db.Services.MunicipalService;
import it.cs.unicam.MunicipalDigitalization.db.Services.POIService;
import it.cs.unicam.MunicipalDigitalization.db.Services.UploadingService;
import it.cs.unicam.MunicipalDigitalization.db.Services.UserService;
import it.cs.unicam.MunicipalDigitalization.db.mappers.POIDTOMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This class represents the controller for points of interest (POIs).
 */

@RestController
public class POIController {

    private final UploadingService uploadingService;

    private final POIService poiService;

    private final MunicipalService municipalService;

    private final UserService userService;
    private final POIDTOMapper poiDTOMapper;

    @Autowired
    public POIController(UploadingService uploadingService, POIService poiService, MunicipalService municipalService
            , UserService userService, POIDTOMapper poiDTOMapper) {
        this.uploadingService = uploadingService;
        this.poiService = poiService;
        this.municipalService = municipalService;
        this.userService = userService;
        this.poiDTOMapper = poiDTOMapper;
    }

    @PostConstruct
    public void init(){
        Municipality municipality = new Municipality();
        municipality.setTerritory(List.of(new Coordinate(10, 10), new Coordinate(50, 50), new Coordinate(100, 100)));
        municipality.setName("Ancona");
        municipalService.saveMunicipal(municipality);
        AuthorizedContributor contributor = new AuthorizedContributor("Alfonso", "password", municipality);
        contributor.setRole(UserRole.AUTHORIZED_CONTRIBUTOR);
        userService.saveUser(contributor);

    }

    @RequestMapping(value = "/pois/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getPOIsByMunicipalityID(@PathVariable Long id){
        return new ResponseEntity<>(poiService.getAllPOIs()
                .stream()
                .map(poiDTOMapper), HttpStatus.OK);
    }

    @RequestMapping(value = "/poi/upload", method = RequestMethod.POST)
    public ResponseEntity<Object> uploadPOI(@RequestBody POIDTO poidto){
        uploadingService.uploadPOI(poidto);
        return new ResponseEntity<>("Product added :)", HttpStatus.OK);
    }




}