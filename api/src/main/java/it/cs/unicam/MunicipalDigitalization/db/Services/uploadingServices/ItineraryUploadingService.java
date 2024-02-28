package it.cs.unicam.MunicipalDigitalization.db.Services.uploadingServices;

import it.cs.unicam.MunicipalDigitalization.api.util.DesignPattern.Builder.ItineraryBuilder;
import it.cs.unicam.MunicipalDigitalization.api.util.DesignPattern.FactoryMethod.ItineraryBuilderFactory;
import it.cs.unicam.MunicipalDigitalization.api.util.UserRole;
import it.cs.unicam.MunicipalDigitalization.db.Services.Mediators.ItineraryMediator;
import it.cs.unicam.MunicipalDigitalization.db.Services.POIService;
import it.cs.unicam.MunicipalDigitalization.db.Services.UserService;
import it.cs.unicam.MunicipalDigitalization.db.controllers.dto.input.ItineraryInputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static it.cs.unicam.MunicipalDigitalization.api.util.MatchingAlgorithms.containsSpecialCharacters;

/**
 * This class is responsible for uploading an itinerary to the database
 * It uses the ItineraryMediator to save the itinerary
 * It uses the ItineraryBuilderFactory to create the itinerary
 */
@Service
public class ItineraryUploadingService {

    /**
     * UserService instance
     */
    private final UserService userService;

    /**
     * ItineraryMediator instance
     */
    private final ItineraryMediator itineraryMediator;

    /**
     * POIService instance
     */
    private final POIService poiService;

    /**
     * ItineraryBuilderFactory instance
     */
    private final ItineraryBuilderFactory itineraryBuilderFactory;

    /**
     * Constructor for ItineraryUploadingService
     *
     * @param userService             UserService instance
     * @param itineraryMediator       ItineraryMediator instance
     * @param poiService              POIService instance
     * @param itineraryBuilderFactory ItineraryBuilderFactory instance
     */
    @Autowired
    public ItineraryUploadingService(UserService userService, ItineraryMediator itineraryMediator, POIService poiService, ItineraryBuilderFactory itineraryBuilderFactory) {
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
     * @param itineraryDTO     the itinerary to be built
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
        checkItineraryName(itineraryDTO);
        checkItineraryAuthor(itineraryDTO);
        checkItineraryDescription(itineraryDTO);
        checkItineraryPOIs(itineraryDTO);
    }

    /**
     * Checks if the itinerary name is valid
     *
     * @param itineraryDTO the itinerary to be checked
     */
    private void checkItineraryName(ItineraryInputDTO itineraryDTO) {
        if (itineraryDTO.itinerary_name().length() > 40 || itineraryDTO.itinerary_name().length() < 10) {
            throw new IllegalArgumentException("The name must be between 10 and 40 characters");
        }

        if (itineraryDTO.itinerary_name().isBlank()) {
            throw new IllegalArgumentException("The name must not be null or blank");
        }

        if (containsSpecialCharacters(itineraryDTO.itinerary_name())) {
            throw new IllegalArgumentException("The name must not contain special characters");
        }
    }

    /**
     * Checks if the itinerary author is valid
     *
     * @param itineraryDTO the itinerary to be checked
     */
    private void checkItineraryAuthor(ItineraryInputDTO itineraryDTO) {
        if (userService.getUserById(itineraryDTO.authorID()).getRole().contains(UserRole.CONTRIBUTOR) && userService.getUserById(itineraryDTO.authorID()).getRole().contains(UserRole.AUTHORIZED_CONTRIBUTOR) && userService.getUserById(itineraryDTO.authorID()).getRole().contains(UserRole.CURATOR)) {
            throw new IllegalArgumentException("The author is not authorized to upload an itinerary");
        }
    }

    /**
     * Checks if the itinerary description is valid
     *
     * @param itineraryDTO the itinerary to be checked
     */
    private void checkItineraryDescription(ItineraryInputDTO itineraryDTO) {
        if (itineraryDTO.itinerary_description() == null) {
            throw new IllegalArgumentException("The description is null");
        }

        if (itineraryDTO.itinerary_description().length() > 1000) {
            throw new IllegalArgumentException("The description is too long");
        }

        if (itineraryDTO.itinerary_description().length() < 10) {
            throw new IllegalArgumentException("The description is too short");
        }
    }

    /**
     * Checks if the itinerary POIs are valid
     *
     * @param itineraryDTO the itinerary to be checked
     */
    private void checkItineraryPOIs(ItineraryInputDTO itineraryDTO) {


        if (userService.getUserById(itineraryDTO.authorID()).getMunicipality() == null) {
            throw new IllegalArgumentException("The author is not associated with a municipality");
        }

        if (itineraryDTO.POIsIDs().isEmpty()) {
            throw new IllegalArgumentException("The itinerary must contain at least one POI");
        }
    }
}