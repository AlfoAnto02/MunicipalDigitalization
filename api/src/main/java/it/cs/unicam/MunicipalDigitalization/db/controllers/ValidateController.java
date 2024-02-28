package it.cs.unicam.MunicipalDigitalization.db.controllers;

import it.cs.unicam.MunicipalDigitalization.db.Services.ValidateService;
import it.cs.unicam.MunicipalDigitalization.db.controllers.Requests.ValidateRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class is a RestController that handles the requests related to the validation of the Entities.
 */
@RestController
@AllArgsConstructor(onConstructor_ = @Autowired)
public class ValidateController {
    private final ValidateService validateService;

    /**
     * Validates a POI
     *
     * @param request the request to validate the POI
     * @return a message indicating that the POI has been validated
     */

    @RequestMapping(value = "/v1/validate/poi", method = RequestMethod.POST)
    public ResponseEntity<Object> validatePOI(@RequestBody ValidateRequest request) {
        validateService.validatePOI(request);
        return new ResponseEntity<>("POI validated", HttpStatus.OK);
    }

    /**
     * Validates an Itinerary
     *
     * @param request the request to validate the Itinerary
     * @return a message indicating that the Itinerary has been validated
     */
    @RequestMapping(value = "/v1/validate/itinerary", method = RequestMethod.POST)
    public ResponseEntity<Object> validateItinerary(@RequestBody ValidateRequest request) {
        validateService.validateItinerary(request);
        return new ResponseEntity<>("Itinerary validated", HttpStatus.OK);
    }

    /**
     * Validates a Content
     *
     * @param request the request to validate the Content
     * @return a message indicating that the Content has been validated
     */
    @RequestMapping(value = "/v1/validate/content", method = RequestMethod.POST)
    public ResponseEntity<Object> validateContent(@RequestBody ValidateRequest request) {
        validateService.validateContent(request);
        return new ResponseEntity<>("Content validated", HttpStatus.OK);
    }


}
