package it.cs.unicam.MunicipalDigitalization;

import it.cs.unicam.MunicipalDigitalization.api.model.Municipality;
import it.cs.unicam.MunicipalDigitalization.api.model.actors.AuthorizedContributor;
import it.cs.unicam.MunicipalDigitalization.api.model.elements.AbstractItinerary;
import it.cs.unicam.MunicipalDigitalization.api.util.Coordinate;
import it.cs.unicam.MunicipalDigitalization.api.util.ElementStatus;
import it.cs.unicam.MunicipalDigitalization.api.util.POIType;
import it.cs.unicam.MunicipalDigitalization.db.Services.ItineraryService;
import it.cs.unicam.MunicipalDigitalization.db.Services.uploadingServices.ItineraryUploadingService;
import it.cs.unicam.MunicipalDigitalization.db.Services.uploadingServices.POIUploadingService;
import it.cs.unicam.MunicipalDigitalization.db.controllers.dto.input.ItineraryInputDTO;
import it.cs.unicam.MunicipalDigitalization.db.Repository.ItineraryRepository;
import it.cs.unicam.MunicipalDigitalization.db.Repository.MunicipalRepository;
import it.cs.unicam.MunicipalDigitalization.db.Repository.UserRepository;
import it.cs.unicam.MunicipalDigitalization.db.Services.POIService;
import it.cs.unicam.MunicipalDigitalization.db.controllers.dto.input.POIInputDTO;
import it.cs.unicam.MunicipalDigitalization.db.controllers.dto.output.ItineraryOutputDTO;
import it.cs.unicam.MunicipalDigitalization.db.mappers.ItineraryDTOMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This class is used to test the ItineraryRepository.
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

    @Autowired
    private ItineraryService itineraryService1;

    /**
     * This method is used to test the creation of an authorized itinerary.
     */
    @Test
    public void createAuthorizedItinerary(){
        ItineraryDTOMapper itineraryDTOMapper = new ItineraryDTOMapper();
        //Create a Municipality
        Municipality municipality = new Municipality();
        municipality.setName("Municipality");
        municipalService.save(municipality);

        //Create a Contributor
        AuthorizedContributor user = new AuthorizedContributor();
        user.setMunicipality(municipality);
        userService.save(user);

        //System.out.println(user.getId());
        //System.out.println(municipality.getId());

        //Create a POIs
        POIInputDTO poi1 = new POIInputDTO("Monteleone", POIType.Cinema, user.getId(), new Coordinate(1,1));
        POIInputDTO poi2 = new POIInputDTO("Ginevra", POIType.Cinema, user.getId(),new Coordinate(2,2));
        poiUploadingService.uploadPOI(poi1);
        poiUploadingService.uploadPOI(poi2);

        //Create an Itinerary
        ItineraryInputDTO itineraryDTO = new ItineraryInputDTO("Super itinerario", "Cinemaaaaaaa",
                user.getId(), new Coordinate(1,1), new ArrayList<>
                (List.of(poiService.getPOIbyName("Monteleone").get().getId(), poiService.getPOIbyName("Ginevra").get().getId())));
        uploadingService.uploadItinerary(itineraryDTO);

        assertEquals(itineraryService.findByName("Super itinerario").get().getName(), "Super itinerario");

        assertEquals(itineraryService.findByName("Super itinerario").get().getPOIs().size(), 2);

        assertEquals(itineraryService.findByName("Super itinerario").get().getPOIs().get(1).getName(), "Ginevra");

        List<ItineraryOutputDTO> abstractItineraries = itineraryService1.getAllItineraries()
                .stream()
                .filter(itinerary -> itinerary.getMunicipality().getId().equals(municipality.getId()))
                .filter(itinerary -> itinerary.getElementStatus().equals(ElementStatus.PUBLISHED))
                .map(itineraryDTOMapper)
                .toList();
        for (ItineraryOutputDTO abstractItinerary : abstractItineraries) {
            System.out.println(abstractItinerary.listOfPOIs());
        }




    }

}
