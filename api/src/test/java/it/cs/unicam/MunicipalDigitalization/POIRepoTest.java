package it.cs.unicam.MunicipalDigitalization;

import it.cs.unicam.MunicipalDigitalization.api.model.Municipality;
import it.cs.unicam.MunicipalDigitalization.api.model.users.AuthorizedContributor;
import it.cs.unicam.MunicipalDigitalization.api.util.Coordinate;
import it.cs.unicam.MunicipalDigitalization.api.util.ElementStatus;
import it.cs.unicam.MunicipalDigitalization.api.util.POIType;
import it.cs.unicam.MunicipalDigitalization.db.Services.uploadingServices.POIUploadingService;
import it.cs.unicam.MunicipalDigitalization.db.Repository.MunicipalRepository;
import it.cs.unicam.MunicipalDigitalization.db.Repository.POIRepository;
import it.cs.unicam.MunicipalDigitalization.db.Repository.UserRepository;
import it.cs.unicam.MunicipalDigitalization.db.controllers.dto.input.POIInputDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * This class tests the POIRepository
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
     * This method tests the creation of a POI
     */
    @Test
    public void createAuthorizedPOI(){
        //Create a Municipality
        Municipality municipality = new Municipality();
        municipality.setName("Municipality");
        municipalService.save(municipality);

        //Create a Contributor
        AuthorizedContributor user = new AuthorizedContributor();
        user.setMunicipality(municipality);
        userService.save(user);

        System.out.println(user.getId());
        System.out.println(municipality.getId());

        //Create a POI
        POIInputDTO poiDTO = new POIInputDTO("Monteleone", POIType.Cinema, user.getId(), new Coordinate(1,1));
        uploadingService.uploadPOI(poiDTO);

        //Check if the POI present
        assertTrue(poiService.findByName("Monteleone").isPresent());

        //Check if the POI is Authorized
        assertEquals(poiService.findByName("Monteleone").get().getElementStatus(), ElementStatus.PUBLISHED);

        //Check if the POI is in the Municipality
        assertEquals(municipalService.getReferenceById(municipality.getId()).getListOfPOIs().size(), 1);

        //Check if the POI is in the User
        assertEquals(userService.getReferenceById(user.getId()).getAuthoredPOIs().size(), 1);

        //Check if the POI is Correct

        assertEquals(poiService.findByName("Monteleone").get().getName(), "Monteleone");
        assertEquals(poiService.findByName("Monteleone").get().getPOIType(), POIType.Cinema);
        assertEquals(poiService.findByName("Monteleone").get().getAuthor(), user);
        assertEquals(poiService.findByName("Monteleone").get().getMunicipality(), municipality);

    }
}
