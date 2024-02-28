package it.cs.unicam.MunicipalDigitalization.db.Services;

import it.cs.unicam.MunicipalDigitalization.db.Services.Mediators.ContentMediator;
import it.cs.unicam.MunicipalDigitalization.db.Services.Mediators.ContestMediator;
import it.cs.unicam.MunicipalDigitalization.db.Services.Mediators.ItineraryMediator;
import it.cs.unicam.MunicipalDigitalization.db.Services.Mediators.POIMediator;
import it.cs.unicam.MunicipalDigitalization.db.controllers.Requests.ValidateRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This class is a service for the validation of pending Contents, POIs and Itineraries.
 */
@Service
@AllArgsConstructor(onConstructor_ = @Autowired)
public class ValidateService {
    private final POIMediator poiMediator;
    private final ItineraryMediator itineraryMediator;
    private final ContentMediator contentMediator;
    private final ContestMediator contestMediator;

    /**
     * method to validate a POI
     *
     * @param request The request to validate.
     */
    public void validatePOI(ValidateRequest request) {
        poiMediator.validatePOI(request);
    }

    /**
     * method to validate an Itinerary
     *
     * @param request The request to validate.
     */
    public void validateItinerary(ValidateRequest request) {
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
