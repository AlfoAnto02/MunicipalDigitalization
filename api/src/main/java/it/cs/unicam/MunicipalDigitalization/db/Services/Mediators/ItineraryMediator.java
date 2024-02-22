package it.cs.unicam.MunicipalDigitalization.db.Services.Mediators;

import it.cs.unicam.MunicipalDigitalization.api.model.elements.AbstractItinerary;
import it.cs.unicam.MunicipalDigitalization.db.Repository.ItineraryRepository;
import it.cs.unicam.MunicipalDigitalization.db.Services.ItineraryService;
import it.cs.unicam.MunicipalDigitalization.db.Services.MunicipalService;
import it.cs.unicam.MunicipalDigitalization.db.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    public void saveItinerary(AbstractItinerary itinerary){
        this.itineraryService.saveItinerary(itinerary);

        //Get the municipality id and the author Id
        Long municipalityId = itinerary.getMunicipality().getId();
        Long authorId = itinerary.getAuthor().getId();

        if(itinerary.getMunicipality().getPOIList().stream().anyMatch(p -> p.getId().equals(itinerary.getId()))){
            municipalityService.addItinerary(itinerary.getMunicipality().getId(), itinerary);
        }

        if(itinerary.getAuthor().getAuthoredPOIs().stream().anyMatch(p -> p.getId().equals(itinerary.getId()))){
            userService.addItinerary(itinerary.getAuthor().getId(), itinerary);
        }
    }
}
