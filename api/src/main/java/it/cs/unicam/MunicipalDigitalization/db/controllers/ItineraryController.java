package it.cs.unicam.MunicipalDigitalization.db.controllers;

import it.cs.unicam.MunicipalDigitalization.api.util.ElementStatus;
import it.cs.unicam.MunicipalDigitalization.api.util.UserRole;
import it.cs.unicam.MunicipalDigitalization.db.Services.ItineraryService;
import it.cs.unicam.MunicipalDigitalization.db.Services.MunicipalService;
import it.cs.unicam.MunicipalDigitalization.db.Services.UserService;
import it.cs.unicam.MunicipalDigitalization.db.Services.uploadingServices.ItineraryUploadingService;
import it.cs.unicam.MunicipalDigitalization.db.controllers.dto.input.ItineraryInputDTO;
import it.cs.unicam.MunicipalDigitalization.db.mappers.ItineraryDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ItineraryController {
    private final ItineraryUploadingService itineraryUploadingService;
    private final MunicipalService municipalService;
    private final ItineraryDTOMapper itineraryDTOMapper;
    private final UserService userService;
    private final ItineraryService itineraryService;

    @Autowired
    public ItineraryController(ItineraryUploadingService itineraryUploadingService, UserService userService,
                               MunicipalService municipalService, ItineraryDTOMapper itineraryDTOMapper,
                               ItineraryService itineraryService) {
        this.itineraryUploadingService = itineraryUploadingService;
        this.municipalService = municipalService;
        this.itineraryDTOMapper = itineraryDTOMapper;
        this.userService = userService;
        this.itineraryService = itineraryService;
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
                .filter(itinerary -> itinerary.getMunicipality().getId().equals(id))
                .filter(itinerary -> itinerary.getElementStatus().equals(ElementStatus.PUBLISHED))
                .map(itineraryDTOMapper), HttpStatus.OK);
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

    /**
     * Returns an itinerary of a Municipality by its id
     *
     * @param municipalID id of the Municipality
     * @param itineraryID id of the Itinerary
     * @return the itinerary with the given id
     */
    @RequestMapping(value = "/v1/itinerary/{municipalID}/{itineraryID}", method = RequestMethod.GET)
    public ResponseEntity<Object> getItineraryByIds(@PathVariable Long municipalID, @PathVariable Long itineraryID){
        return new ResponseEntity<>(municipalService.getMunicipalByID(municipalID).getListOfItineraries()
                .stream()
                .filter(itinerary -> itinerary.getElementStatus().equals(ElementStatus.PUBLISHED))
                .filter(itinerary -> itinerary.getId().equals(itineraryID))
                .map(itineraryDTOMapper), HttpStatus.OK);
    }

    /**
     * Returns all the pending itineraries of the Municipality. Only the curator can do this Rest call
     *
     * @param curatorID id of the curator
     * @return all the pending itineraries of the Municipality
     */
    @RequestMapping(value = "/v1/itinerary/pending/{curatorID}", method = RequestMethod.GET)
    public ResponseEntity<Object> getPendingItineraries(@PathVariable Long curatorID){
        if(userService.getUserById(curatorID).getRole().contains(UserRole.CURATOR)){
            return new ResponseEntity<>(itineraryService.getAllItineraries()
                    .stream()
                    .filter(itinerary -> itinerary.getMunicipality().equals(userService.getUserById(curatorID).getMunicipality()))
                    .filter(itinerary -> itinerary.getElementStatus().equals(ElementStatus.PENDING))
                    .map(itineraryDTOMapper), HttpStatus.OK);
        }
        return new ResponseEntity<>("You are not a curator", HttpStatus.FORBIDDEN);
    }


}
