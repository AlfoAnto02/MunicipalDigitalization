package it.cs.unicam.MunicipalDigitalization.tests;

import it.cs.unicam.MunicipalDigitalization.api.model.Municipality;
import it.cs.unicam.MunicipalDigitalization.api.model.users.AuthorizedContributor;
import it.cs.unicam.MunicipalDigitalization.api.util.Coordinate;
import it.cs.unicam.MunicipalDigitalization.api.util.ElementStatus;
import it.cs.unicam.MunicipalDigitalization.api.util.POIType;
import it.cs.unicam.MunicipalDigitalization.db.Repository.MunicipalRepository;
import it.cs.unicam.MunicipalDigitalization.db.Repository.POIRepository;
import it.cs.unicam.MunicipalDigitalization.db.Repository.UserRepository;
import it.cs.unicam.MunicipalDigitalization.db.Services.uploadingServices.POIUploadingService;
import it.cs.unicam.MunicipalDigitalization.db.controllers.dto.input.POIInputDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class is used to test the functionality of the POI Repository.
 * It uses the Spring Boot Test framework to run tests in a Spring context.
 * It also uses the Transactional annotation to ensure that database operations are rolled back after each test.
 */
@SpringBootTest
@Transactional
public class POIRepoTest {

    @Autowired
    private MunicipalRepository municipalService;

    @Autowired
    private POIRepository poiService;

    @Autowired
    private UserRepository userService;

    @Autowired
    private POIUploadingService uploadingService;

    /**
     * This test method creates an authorized POI and verifies its creation.
     */
    @Test
    public void createAuthorizedPOI() {
        Municipality municipality = createMunicipality(createTerritory());
        AuthorizedContributor user = createUser(municipality);
        POIInputDTO poiDTO = new POIInputDTO("Monteleone", POIType.Cinema, user.getId(), new Coordinate(1, 1));
        uploadingService.uploadPOI(poiDTO);

        assertPOIProperties(user, municipality);
    }

    /**
     * This test method attempts to create an unauthorized POI and expects an IllegalArgumentException.
     */
    @Test
    public void createUnauthorizedPOI() {
        Municipality municipality = createMunicipality(createTerritory());
        AuthorizedContributor user = createUser(municipality);

        assertThrows(IllegalArgumentException.class, () -> {
            POIInputDTO poiDTO = new POIInputDTO("Monteleone", POIType.Cinema, user.getId(), new Coordinate(400, 400));
            uploadingService.uploadPOI(poiDTO);
        });
    }

    /**
     * This method creates a Municipality object and saves it to the database.
     *
     * @param territory A list of Coordinates that define the territory of the municipality.
     * @return The created Municipality object.
     */
    private Municipality createMunicipality(List<Coordinate> territory) {
        Municipality municipality = new Municipality();
        municipality.setName("Municipal");
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
    private AuthorizedContributor createUser(Municipality municipality) {
        AuthorizedContributor user = new AuthorizedContributor();
        user.setMunicipality(municipality);
        userService.save(user);
        return user;
    }

    /**
     * This method asserts the properties of the created POI.
     *
     * @param user         The AuthorizedContributor who created the POI.
     * @param municipality The Municipality object to which the POI belongs.
     */
    private void assertPOIProperties(AuthorizedContributor user, Municipality municipality) {
        assertTrue(poiService.findByName("Monteleone").isPresent());
        assertEquals(ElementStatus.PUBLISHED, poiService.findByName("Monteleone").get().getElementStatus());
        assertEquals(1, municipalService.getReferenceById(municipality.getId()).getListOfPOIs().size());
        assertEquals(1, userService.getReferenceById(user.getId()).getAuthoredPOIs().size());
        assertEquals("Monteleone", poiService.findByName("Monteleone").get().getName());
        assertEquals(POIType.Cinema, poiService.findByName("Monteleone").get().getPOIType());
        assertEquals(user, poiService.findByName("Monteleone").get().getAuthor());
        assertEquals(municipality, poiService.findByName("Monteleone").get().getMunicipality());
    }
}