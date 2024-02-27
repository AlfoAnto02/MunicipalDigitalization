package it.cs.unicam.MunicipalDigitalization.db.Services;

import it.cs.unicam.MunicipalDigitalization.api.util.UserRole;
import it.cs.unicam.MunicipalDigitalization.db.Services.Mediators.*;
import it.cs.unicam.MunicipalDigitalization.db.controllers.Requests.ValidateRequest;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This class is a service for the validation of pending Contents, POIs and Itineraries.
 */
@Service
public class ValidateService {
    private final POIMediator poiMediator;
    private final ItineraryMediator itineraryMediator;
    private final ContentMediator contentMediator;
    private final ContestMediator contestMediator;

    @Autowired
    public ValidateService(POIMediator poiService, ItineraryMediator itineraryService, ContentMediator contentMediator,
                           ContestMediator contestMediator) {
        this.poiMediator = poiService;
        this.itineraryMediator = itineraryService;
        this.contentMediator = contentMediator;
        this.contestMediator = contestMediator;
    }

    /**
     * method to validate a POI
     *
     * @param request The request to validate.
     */
    public void validatePOI(ValidateRequest request){
        poiMediator.validatePOI(request);
    }

    /**
     * method to validate an Itinerary
     *
     * @param request The request to validate.
     */
    public void validateItinerary(ValidateRequest request){
        itineraryMediator.validateItinerary(request);
    }


    /**
     * method to validate a Content
     *
     * @param request The request to validate.
     */
    public void validateContent(ValidateRequest request) {
        contentMediator.validateContent(request);
    }

    /**
     * method to validate a Contest
     *
     * @param validateRequest The request to validate.
     */
    public void validateContest(ValidateRequest validateRequest) {
        contestMediator.validateContest(validateRequest);
    }
}
