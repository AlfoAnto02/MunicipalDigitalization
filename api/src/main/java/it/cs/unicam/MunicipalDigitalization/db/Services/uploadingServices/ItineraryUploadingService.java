package it.cs.unicam.MunicipalDigitalization.db.Services.uploadingServices;

import it.cs.unicam.MunicipalDigitalization.api.util.DesignPattern.Builder.ItineraryBuilder;
import it.cs.unicam.MunicipalDigitalization.api.util.DesignPattern.FactoryMethod.ItineraryBuilderFactory;
import it.cs.unicam.MunicipalDigitalization.db.Services.Mediators.ItineraryMediator;
import it.cs.unicam.MunicipalDigitalization.db.Services.MunicipalService;
import it.cs.unicam.MunicipalDigitalization.db.Services.POIService;
import it.cs.unicam.MunicipalDigitalization.db.Services.UserService;
import it.cs.unicam.MunicipalDigitalization.db.controllers.dto.ItineraryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItineraryUploadingService {

    private final MunicipalService municipalService;
    private final UserService userService;
    private final ItineraryMediator itineraryMediator;
    private final POIService poiService;
    private final ItineraryBuilderFactory itineraryBuilderFactory;

    @Autowired
    public ItineraryUploadingService(MunicipalService municipalService, UserService userService,
                                     ItineraryMediator itineraryMediator, POIService poiService,
                                     ItineraryBuilderFactory itineraryBuilderFactory) {
        this.municipalService = municipalService;
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
    public void uploadItinerary(ItineraryDTO itineraryDTO) {
        checkItinerary(itineraryDTO);
        ItineraryBuilder builder = itineraryBuilderFactory.createBuilderForUser(userService.getUserById(itineraryDTO.author()));
        buildItinerary(builder, itineraryDTO);
        this.itineraryMediator.saveItinerary(builder.build());
    }

    /**
     * Builds an itinerary
     *
     * @param itineraryBuilder the builder to be used
     * @param itineraryDTO the itinerary to be built
     */
    private void buildItinerary(ItineraryBuilder itineraryBuilder, ItineraryDTO itineraryDTO) {
        itineraryBuilder.setItineraryName(itineraryDTO.name());
        itineraryBuilder.setItineraryAuthor(userService.getUserById(itineraryDTO.author()));
        itineraryBuilder.setItineraryDescription(itineraryDTO.description());
        municipalService.findMunicipalByID(itineraryDTO.municipality()).ifPresent(itineraryBuilder::setItineraryMunicipality);
        itineraryBuilder.setItineraryCoordinates(itineraryDTO.coordinate());
        itineraryBuilder.addPOIs(poiService.getPOIsByIds(itineraryDTO.pois()));
        itineraryBuilder.setItineraryType();
        itineraryBuilder.setItineraryStatus();
    }

    private void checkItinerary(ItineraryDTO itineraryDTO) {
        //TODO
    }
}
