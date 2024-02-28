package it.cs.unicam.MunicipalDigitalization.tests;


import it.cs.unicam.MunicipalDigitalization.api.model.Municipality;
import it.cs.unicam.MunicipalDigitalization.api.model.users.AuthorizedContributor;
import it.cs.unicam.MunicipalDigitalization.api.util.ContentType;
import it.cs.unicam.MunicipalDigitalization.api.util.Coordinate;
import it.cs.unicam.MunicipalDigitalization.api.util.POIType;
import it.cs.unicam.MunicipalDigitalization.db.Repository.MunicipalRepository;
import it.cs.unicam.MunicipalDigitalization.db.Repository.UserRepository;
import it.cs.unicam.MunicipalDigitalization.db.Services.ContentService;
import it.cs.unicam.MunicipalDigitalization.db.Services.POIService;
import it.cs.unicam.MunicipalDigitalization.db.Services.uploadingServices.ContentUploadingService;
import it.cs.unicam.MunicipalDigitalization.db.Services.uploadingServices.POIUploadingService;
import it.cs.unicam.MunicipalDigitalization.db.controllers.dto.input.ContentInputDTO;
import it.cs.unicam.MunicipalDigitalization.db.controllers.dto.input.POIInputDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This class is used to test the functionality of the Content Repository.
 * It uses the Spring Boot Test framework to run tests in a Spring context.
 * It also uses the Transactional annotation to ensure that database operations are rolled back after each test.
 */
@SpringBootTest
@Transactional
public class ContentRepoTest {

    @Autowired
    private MunicipalRepository municipalService;

    @Autowired
    private POIService poiService;

    @Autowired
    private UserRepository userService;

    @Autowired
    private ContentUploadingService uploadingService;

    @Autowired
    private ContentService contentService;

    @Autowired
    private POIUploadingService poiUploadingService;

    /**
     * This test method creates an authorized content and verifies its creation.
     */
    @Test
    public void createAuthorizedContent() {
        Municipality municipality = createMunicipality(createTerritory());
        AuthorizedContributor user = createContributor(municipality);
        createPOI(user);
        createContent(user);
        assertContentProperties();
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
     * This method creates a POI object and saves it to the database.
     *
     * @param user The AuthorizedContributor who creates the POI.
     */
    private void createPOI(AuthorizedContributor user) {
        POIInputDTO poiDTO = new POIInputDTO("Monteleone", POIType.Cinema, user.getId(), new Coordinate(1, 1));
        poiUploadingService.uploadPOI(poiDTO);
    }

    /**
     * This method creates a Content object and saves it to the database.
     *
     * @param user The AuthorizedContributor who creates the Content.
     */
    private void createContent(AuthorizedContributor user) {
        ContentInputDTO contentDTO = new ContentInputDTO("Barcellona", 1L, null, user.getId(), ContentType.PHOTO, "barcellonaspettacolo.png");
        uploadingService.uploadContent(contentDTO);
    }

    /**
     * This method asserts the properties of the created Content.
     */
    private void assertContentProperties() {
        assertEquals(1, contentService.getAllContents().size());
        assertEquals(1, poiService.getPOIByID(1L).getListOfContents().size());
        assertEquals(contentService.getContentById(1L), poiService.getPOIByID(1L).getListOfContents().getFirst());
    }
}