package it.cs.unicam.MunicipalDigitalization;

import it.cs.unicam.MunicipalDigitalization.api.model.Municipality;
import it.cs.unicam.MunicipalDigitalization.api.model.actors.AuthorizedContributor;
import it.cs.unicam.MunicipalDigitalization.api.util.Coordinate;
import it.cs.unicam.MunicipalDigitalization.api.util.POIType;
import it.cs.unicam.MunicipalDigitalization.api.util.UserRole;
import it.cs.unicam.MunicipalDigitalization.api.util.controllers.dto.ItineraryDTO;
import it.cs.unicam.MunicipalDigitalization.api.util.controllers.dto.POIDTO;
import it.cs.unicam.MunicipalDigitalization.db.Repository.ItineraryRepository;
import it.cs.unicam.MunicipalDigitalization.db.Repository.MunicipalRepository;
import it.cs.unicam.MunicipalDigitalization.db.Repository.UserRepository;
import it.cs.unicam.MunicipalDigitalization.db.Services.POIService;
import it.cs.unicam.MunicipalDigitalization.db.Services.UploadingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    private UploadingService uploadingService;

    @Test
    public void createAuthorizedItinerary(){
        //Create a Municipality
        Municipality municipality = new Municipality();
        municipality.setName("Municipality");
        municipalService.save(municipality);

        //Create a Contributor
        AuthorizedContributor user = new AuthorizedContributor();
        user.setRole(UserRole.AUTHORIZED_CONTRIBUTOR);
        user.setMunicipality(municipality);
        userService.save(user);

        System.out.println(user.getId());
        System.out.println(municipality.getId());

        //Create a POIs
        POIDTO poi1 = new POIDTO("Monteleone", POIType.Cinema, user.getId(), municipality.getId(), new Coordinate(1,1));
        POIDTO poi2 = new POIDTO("Ginevra", POIType.Cinema, user.getId(), municipality.getId(), new Coordinate(2,2));
        uploadingService.uploadPOI(poi1);
        uploadingService.uploadPOI(poi2);

        //Create an Itinerary
        ItineraryDTO itineraryDTO = new ItineraryDTO("Super itinerario", "Cinema", "Questo è un fantastico itinerario",
                user.getId(), municipality.getId(), new Coordinate(1,1), new ArrayList<>
                (List.of(poiService.getPOIbyName("Monteleone").get().getId(), poiService.getPOIbyName("Ginevra").get().getId())));
        uploadingService.uploadItinerary(itineraryDTO);

        assertEquals(itineraryService.findByName("Super itinerario").get().getName(), "Super itinerario");

    }

}
