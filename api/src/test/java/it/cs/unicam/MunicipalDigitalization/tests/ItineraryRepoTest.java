package it.cs.unicam.MunicipalDigitalization.tests;

import it.cs.unicam.MunicipalDigitalization.api.model.Municipality;
import it.cs.unicam.MunicipalDigitalization.api.model.users.AuthorizedContributor;
import it.cs.unicam.MunicipalDigitalization.api.util.Coordinate;
import it.cs.unicam.MunicipalDigitalization.api.util.POIType;
import it.cs.unicam.MunicipalDigitalization.db.Repository.ItineraryRepository;
import it.cs.unicam.MunicipalDigitalization.db.Repository.MunicipalRepository;
import it.cs.unicam.MunicipalDigitalization.db.Repository.UserRepository;
import it.cs.unicam.MunicipalDigitalization.db.Services.POIService;
import it.cs.unicam.MunicipalDigitalization.db.Services.uploadingServices.ItineraryUploadingService;
import it.cs.unicam.MunicipalDigitalization.db.Services.uploadingServices.POIUploadingService;
import it.cs.unicam.MunicipalDigitalization.db.controllers.dto.input.ItineraryInputDTO;
import it.cs.unicam.MunicipalDigitalization.db.controllers.dto.input.POIInputDTO;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This class is used to test the functionality of the Itinerary Repository.
 * It uses the Spring Boot Test framework to run tests in a Spring context.
 * It also uses the Transactional annotation to ensure that database operations are rolled back after each test.
 */
@SpringBootTest
@Transactional
public class ItineraryRepoTest {

    @Autowired
    private MunicipalRepository municipalService;

    @Autowired
    private ItineraryRepository itineraryService;

    @Autowired
    private POIService poiService;

    @Autowired
    private UserRepository userService;

    @Autowired
    private ItineraryUploadingService uploadingService;

    @Autowired
    private POIUploadingService poiUploadingService;

    /**
     * This test method creates an authorized itinerary and verifies its creation.
     */
    @Test
    public void createAuthorizedItinerary() {
        Municipality municipality = createMunicipality(createTerritory());
        AuthorizedContributor user = createContributor(municipality);
        createPOIs(user, "Monteleone", "Ginevra");
        createItinerary(user, "Monteleone", "Ginevra");
        assertEquals("Super itinerario", itineraryService.findByName("Super itinerario").orElseThrow(() -> new IllegalArgumentException("Itinerary with name Super itinerario not found")).getName());
    }

    /**
     * This method creates a Municipality object and saves it to the database.
     *
     * @param territory A list of Coordinates that define the territory of the municipality.
     * @return The created Municipality object.
     */
    private Municipality createMunicipality(List<Coordinate> territory) {
        Municipality municipality = new Municipality();
        municipality.setName("Municipality");
        municipality.setTerritory(territory);
        municipalService.save(municipality);
        return municipality;
    }

    /**
     * This method creates a list of Coordinates that define a territory.
     *
     * @return A list of Coordinates.
     */
    private List<Coordinate> createTerritory() {
        List<Coordinate> coordinates = new ArrayList<>();
        coordinates.add(new Coordinate(1, 1));
        coordinates.add(new Coordinate(1, 200));
        coordinates.add(new Coordinate(200, 200));
        coordinates.add(new Coordinate(200, 1));
        return coordinates;
    }

    /**
     * This method creates an AuthorizedContributor object and saves it to the database.
     *
     * @param municipality The Municipality object to which the AuthorizedContributor belongs.
     * @return The created AuthorizedContributor object.
     */
    private AuthorizedContributor createContributor(Municipality municipality) {
        AuthorizedContributor user = new AuthorizedContributor();
        user.setMunicipality(municipality);
        userService.save(user);
        return user;
    }

    /**
     * This method creates multiple POI objects and saves them to the database.
     *
     * @param user  The AuthorizedContributor who creates the POIs.
     * @param names The names of the POIs to be created.
     */
    private void createPOIs(AuthorizedContributor user, String... names) {
        for (String name : names) {
            poiUploadingService.uploadPOI(new POIInputDTO(name, POIType.Cinema, user.getId(), new Coordinate(1, 1)));
        }
    }

    /**
     * This method creates an Itinerary object and saves it to the database.
     *
     * @param user     The AuthorizedContributor who creates the Itinerary.
     * @param poiNames The names of the POIs to be included in the Itinerary.
     */
    private void createItinerary(AuthorizedContributor user, String... poiNames) {
        List<Long> poiIds = new ArrayList<>();
        for (String poiName : poiNames) {
            poiIds.add(poiService.getPOIbyName(poiName).orElseThrow(() -> new IllegalArgumentException("POI with name " + poiName + " not found")).getId());
        }
        uploadingService.uploadItinerary(new ItineraryInputDTO("Super itinerario", "Questo è un fantastico itinerario", user.getId(), new Coordinate(1, 1), poiIds));
    }
}
