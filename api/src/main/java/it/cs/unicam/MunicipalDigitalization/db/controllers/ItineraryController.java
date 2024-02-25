package it.cs.unicam.MunicipalDigitalization.db.controllers;

import it.cs.unicam.MunicipalDigitalization.db.Services.ItineraryService;
import it.cs.unicam.MunicipalDigitalization.db.Services.UserService;
import it.cs.unicam.MunicipalDigitalization.db.Services.uploadingServices.ItineraryUploadingService;
import it.cs.unicam.MunicipalDigitalization.db.controllers.dto.input.ItineraryInputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ItineraryController {
    private final ItineraryUploadingService itineraryUploadingService;
    private final ItineraryService itineraryService;
    private final UserService userService;

    @Autowired
    public ItineraryController(ItineraryUploadingService itineraryUploadingService, ItineraryService itineraryService, UserService userService) {
        this.itineraryUploadingService = itineraryUploadingService;
        this.itineraryService = itineraryService;
        this.userService = userService;
    }

    /**
     * Returns all the itineraries in a municipality
     *
     * @param id the id of the municipality
     * @return all the itineraries in the database
     */

    @RequestMapping(value = "/v1/itineraries/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getItinerariesByMunicipalityID(@PathVariable Long id){
        return new ResponseEntity<>(itineraryService.getAllItineraries()
                .stream()
                .filter(itinerary -> itinerary.getMunicipality().getId().equals(id)), HttpStatus.OK);
    }

    /**
     * Uploads an itinerary to the database
     *
     * @param itineraryDTO the itinerary to be uploaded
     * @return a message indicating that the itinerary has been added
     */
    @RequestMapping(value = "/v1/itinerary/upload", method = RequestMethod.POST)
    public ResponseEntity<Object> uploadItinerary(@RequestBody ItineraryInputDTO itineraryDTO){
        itineraryUploadingService.uploadItinerary(itineraryDTO);
        return new ResponseEntity<>("Itinerary added :)", HttpStatus.OK);
    }
}
