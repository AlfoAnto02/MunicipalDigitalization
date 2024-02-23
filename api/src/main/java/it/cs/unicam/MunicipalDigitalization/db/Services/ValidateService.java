package it.cs.unicam.MunicipalDigitalization.db.Services;

import it.cs.unicam.MunicipalDigitalization.api.util.UserRole;
import it.cs.unicam.MunicipalDigitalization.db.Services.Mediators.POIMediator;
import it.cs.unicam.MunicipalDigitalization.db.Services.Mediators.UserMediator;
import it.cs.unicam.MunicipalDigitalization.db.controllers.Requests.ValidateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This class is a service for the validation of pending Contents, POIs and Itineraries.
 */
@Service
public class ValidateService {
    private final POIMediator poiMediator;

    @Autowired
    public ValidateService(POIMediator poiService) {
        this.poiMediator = poiService;
    }

    /**
     * method to validate a POI
     *
     * @param request The request to validate.
     */
    public void validatePOI(ValidateRequest request){
        poiMediator.validatePOI(request);
    }


}
