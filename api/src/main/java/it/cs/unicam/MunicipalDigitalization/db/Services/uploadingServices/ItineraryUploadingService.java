package it.cs.unicam.MunicipalDigitalization.db.Services.uploadingServices;

import it.cs.unicam.MunicipalDigitalization.api.util.DesignPattern.Builder.ItineraryBuilder;
import it.cs.unicam.MunicipalDigitalization.api.util.DesignPattern.FactoryMethod.ItineraryBuilderFactory;
import it.cs.unicam.MunicipalDigitalization.db.Services.Mediators.ItineraryMediator;
import it.cs.unicam.MunicipalDigitalization.db.Services.MunicipalService;
import it.cs.unicam.MunicipalDigitalization.db.Services.POIService;
import it.cs.unicam.MunicipalDigitalization.db.Services.UserService;
import it.cs.unicam.MunicipalDigitalization.db.controllers.dto.input.ItineraryInputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This class is responsible for uploading an itinerary to the database
 * It uses the ItineraryMediator to save the itinerary
 * It uses the ItineraryBuilderFactory to create the itinerary
 */
@Service
public class ItineraryUploadingService {

    private final UserService userService;
    private final ItineraryMediator itineraryMediator;
    private final POIService poiService;
    private final ItineraryBuilderFactory itineraryBuilderFactory;

    @Autowired
    public ItineraryUploadingService(UserService userService, ItineraryMediator itineraryMediator,
                                     POIService poiService, ItineraryBuilderFactory itineraryBuilderFactory) {
        this.userService = userService;
        this.itineraryMediator = itineraryMediator;
        this.poiService = poiService;
        this.itineraryBuilderFactory = itineraryBuilderFactory;
    }

    /**
     * Uploads an itinerary to the database
     *
     * @param itineraryDTO the itinerary to be uploaded
     */
    public void uploadItinerary(ItineraryInputDTO itineraryDTO) {
        checkItinerary(itineraryDTO);
        ItineraryBuilder builder = itineraryBuilderFactory.createBuilderForUser(userService.getUserById(itineraryDTO.authorID()));
        buildItinerary(builder, itineraryDTO);
        this.itineraryMediator.saveItinerary(builder.build());
    }

    /**
     * Builds an itinerary
     *
     * @param itineraryBuilder the builder to be used
     * @param itineraryDTO the itinerary to be built
     */
    private void buildItinerary(ItineraryBuilder itineraryBuilder, ItineraryInputDTO itineraryDTO) {
        itineraryBuilder.setItineraryName(itineraryDTO.itinerary_name());
        itineraryBuilder.setItineraryAuthor(userService.getUserById(itineraryDTO.authorID()));
        itineraryBuilder.setItineraryDescription(itineraryDTO.itinerary_description());
        itineraryBuilder.setItineraryMunicipality(userService.getUserById(itineraryDTO.authorID()).getMunicipality());
        itineraryBuilder.addPOIs(poiService.getPOIsByIds(itineraryDTO.POIsIDs()));
        itineraryBuilder.setItineraryCoordinates(itineraryDTO.coordinate());
        itineraryBuilder.setItineraryType();
        itineraryBuilder.setItineraryStatus();
    }

    /**
     * Checks if the itinerary is valid
     *
     * @param itineraryDTO the itinerary to be checked
     */
    private void checkItinerary(ItineraryInputDTO itineraryDTO) {
        //TODO
    }
}
