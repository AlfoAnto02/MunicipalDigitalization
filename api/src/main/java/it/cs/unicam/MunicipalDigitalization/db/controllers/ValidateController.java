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

@RestController
public class ValidateController {
    private final ValidateService validateService;

    @Autowired
    public ValidateController(ValidateService validateService) {
        this.validateService = validateService;
    }

    @RequestMapping(value = "/validate/poi", method = RequestMethod.POST)
    public ResponseEntity<Object> validatePOI(@RequestBody ValidateRequest request){
        validateService.validatePOI(request);
        return new ResponseEntity<>("POI validated", HttpStatus.OK  );
    }
}
