package it.cs.unicam.MunicipalDigitalization.db.Services.Mediators;

import it.cs.unicam.MunicipalDigitalization.api.model.elements.AbstractItinerary;
import it.cs.unicam.MunicipalDigitalization.api.util.ElementStatus;
import it.cs.unicam.MunicipalDigitalization.api.util.UserRole;
import it.cs.unicam.MunicipalDigitalization.db.Services.ItineraryService;
import it.cs.unicam.MunicipalDigitalization.db.Services.MunicipalService;
import it.cs.unicam.MunicipalDigitalization.db.Services.POIService;
import it.cs.unicam.MunicipalDigitalization.db.Services.UserService;
import it.cs.unicam.MunicipalDigitalization.db.controllers.Requests.ValidateRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * This class is a mediator for the itinerary service, the municipality service and the user service.
 */
@Component
@AllArgsConstructor(onConstructor_ = @Autowired)
public class ItineraryMediator {
    private final ItineraryService itineraryService;

    private final MunicipalService municipalityService;

    private final UserService userService;
    private final POIService poiService;

    /**
     * This method saves an itinerary and adds it to the municipality and the author.
     *
     * @param itinerary the itinerary to save
     */
    public void saveItinerary(AbstractItinerary itinerary) {

        this.itineraryService.saveItinerary(itinerary);


        municipalityService.addItinerary(itinerary.getMunicipality().getId(), itinerary);
        userService.addItinerary(itinerary.getAuthor().getId(), itinerary);
        poiService.addItinerary(itinerary.getPOIs(), itinerary);
    }

    /**
     * This method validates an itinerary using the ValidateRequest.
     *
     * @param request the request to validate
     */

    public void validateItinerary(ValidateRequest request) {
        if (userService.getUserById(request.getValidatorID()).getRole().contains(UserRole.CURATOR) && (itineraryService
                .getItineraryById(request.getRequestID()).getElementStatus().equals(ElementStatus.PENDING)) &&
                userService.getUserById(request.getValidatorID()).getMunicipality().getId().equals(itineraryService.getItineraryById(request.getRequestID()).getMunicipality().getId())) {
            itineraryService.validateItinerary(request.getRequestID(), request.isValidated());
            userService.updateUserItineraryList(request.getRequestID(), request.isValidated());
            municipalityService.updateMunicipalityItineraryList(request.getRequestID(), request.isValidated());
        } else if (itineraryService.getItineraryById(request.getRequestID()).getElementStatus().equals(ElementStatus.PUBLISHED)) {
            throw new IllegalArgumentException("This itinerary is already Published");
        } else throw new IllegalArgumentException("You are not a Curator");

    }
}
