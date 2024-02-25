package it.cs.unicam.MunicipalDigitalization.db.Services.Mediators;

import it.cs.unicam.MunicipalDigitalization.api.model.elements.AbstractItinerary;
import it.cs.unicam.MunicipalDigitalization.api.model.elements.AbstractMunicipalElement;
import it.cs.unicam.MunicipalDigitalization.api.util.ElementStatus;
import it.cs.unicam.MunicipalDigitalization.api.util.UserRole;
import it.cs.unicam.MunicipalDigitalization.db.Repository.ItineraryRepository;
import it.cs.unicam.MunicipalDigitalization.db.Services.ItineraryService;
import it.cs.unicam.MunicipalDigitalization.db.Services.MunicipalService;
import it.cs.unicam.MunicipalDigitalization.db.Services.UserService;
import it.cs.unicam.MunicipalDigitalization.db.Services.ValidateService;
import it.cs.unicam.MunicipalDigitalization.db.controllers.Requests.ValidateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * This class is a mediator for the itinerary service, the municipality service and the user service.
 */
@Component
public class ItineraryMediator {
    private final ItineraryService itineraryService;

    private final MunicipalService municipalityService;

    private final UserService userService;

    @Autowired
    public ItineraryMediator(ItineraryService itineraryService, MunicipalService municipalityService, UserService userService) {
        this.itineraryService = itineraryService;
        this.municipalityService = municipalityService;
        this.userService = userService;
    }

    /**
     * This method saves an itinerary and adds it to the municipality and the author.
     *
     * @param itinerary the itinerary to save
     */
    public void saveItinerary(AbstractItinerary itinerary){
        this.itineraryService.saveItinerary(itinerary);

        //Get the municipality id and the author Id
        Long municipalityId = itinerary.getMunicipality().getId();
        Long authorId = itinerary.getAuthor().getId();

        municipalityService.addItinerary(itinerary.getMunicipality().getId(), itinerary);
        userService.addItinerary(itinerary.getAuthor().getId(), itinerary);
        System.out.println(itineraryService.getItineraryById(itinerary.getId()).getPOIs().size());
        System.out.println(itineraryService.getItineraryById(itinerary.getId()).getPOIs().stream().map(AbstractMunicipalElement::getName).toList().toString());
        System.out.println(municipalityService.getMunicipalByID(municipalityId).getListOfItineraries().get(0).getPOIs().size());
    }

    /**
     * This method validates an itinerary using the ValidateRequest.
     *
     * @param request the request to validate
     */

    public void validateItinerary(ValidateRequest request){
        if(userService.getUserById(request.getCuratorID()).getRole().contains(UserRole.CURATOR) && (itineraryService
                .getItineraryById(request.getRequestID()).getElementStatus().equals(ElementStatus.PENDING))){
            itineraryService.validateItinerary(request.getRequestID(),request.isValidated());
            userService.updateUserItineraryList(request.getRequestID(), request.isValidated());
            municipalityService.updateMunicipalityItineraryList(request.getRequestID(), request.isValidated());
        }
        else if(itineraryService.getItineraryById(request.getRequestID()).getElementStatus().equals(ElementStatus.PUBLISHED)){
            throw new IllegalArgumentException("This itinerary is already Published");
        }
        else throw new IllegalArgumentException("You are not a Curator");

    }
}
