package it.cs.unicam.MunicipalDigitalization.db.controllers;
import it.cs.unicam.MunicipalDigitalization.db.Services.ValidateService;
import it.cs.unicam.MunicipalDigitalization.db.controllers.Requests.ValidateRequest;
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
public class ValidateController {
    private final ValidateService validateService;

    @Autowired
    public ValidateController(ValidateService validateService) {
        this.validateService = validateService;
    }

    /**
     * Validates a POI
     *
     * @param request the request to validate the POI
     * @return a message indicating that the POI has been validated
     */

    @RequestMapping(value = "/v1/validate/poi", method = RequestMethod.POST)
    public ResponseEntity<Object> validatePOI(@RequestBody ValidateRequest request){
        validateService.validatePOI(request);
        return new ResponseEntity<>("POI validated", HttpStatus.OK  );
    }

    public ResponseEntity<Object> validateItinerary(@RequestBody ValidateRequest request){
        validateService.validateItinerary(request);
        return new ResponseEntity<>("Itinerary validated", HttpStatus.OK  );
    }


}
