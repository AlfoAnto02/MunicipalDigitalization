package it.cs.unicam.MunicipalDigitalization.tests;

import it.cs.unicam.MunicipalDigitalization.api.model.Municipality;
import it.cs.unicam.MunicipalDigitalization.api.model.users.Animator;
import it.cs.unicam.MunicipalDigitalization.api.model.users.AuthorizedContributor;
import it.cs.unicam.MunicipalDigitalization.api.util.ContestType;
import it.cs.unicam.MunicipalDigitalization.api.util.Coordinate;
import it.cs.unicam.MunicipalDigitalization.api.util.InvitationType;
import it.cs.unicam.MunicipalDigitalization.api.util.POIType;
import it.cs.unicam.MunicipalDigitalization.db.Repository.ItineraryRepository;
import it.cs.unicam.MunicipalDigitalization.db.Repository.MunicipalRepository;
import it.cs.unicam.MunicipalDigitalization.db.Repository.UserRepository;
import it.cs.unicam.MunicipalDigitalization.db.Services.ContestService;
import it.cs.unicam.MunicipalDigitalization.db.Services.POIService;
import it.cs.unicam.MunicipalDigitalization.db.Services.uploadingServices.ContestUploadingService;
import it.cs.unicam.MunicipalDigitalization.db.Services.uploadingServices.ItineraryUploadingService;
import it.cs.unicam.MunicipalDigitalization.db.Services.uploadingServices.POIUploadingService;
import it.cs.unicam.MunicipalDigitalization.db.controllers.dto.input.ContestInputDTO;
import it.cs.unicam.MunicipalDigitalization.db.controllers.dto.input.POIInputDTO;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
public class ContestTest {
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
    private ContestUploadingService contestUploadingService;
    @Autowired
    private ContestService contestService;

    @Test
    public void createContest() {
        List<Coordinate> coordinates = new ArrayList<Coordinate>();
        coordinates.add(new Coordinate(10.0, 10.0));
        coordinates.add(new Coordinate(20.0, 10.0));
        coordinates.add(new Coordinate(10.0, 20.0));
        coordinates.add(new Coordinate(20.0, 20.0));
        Municipality municipality = new Municipality(coordinates,"Ancona");

        municipalService.save(municipality);

        AuthorizedContributor authorizedContributor = new AuthorizedContributor("Mario", "Rossi", municipality);
        userService.save(authorizedContributor);

        POIInputDTO poiDTO = new POIInputDTO("Monteleone", POIType.Cinema, authorizedContributor.getId(), new Coordinate(12, 12));
        POIInputDTO poiDTO2 = new POIInputDTO("Luce verde", POIType.Cinema, authorizedContributor.getId(), new Coordinate(13, 13));
        POIInputDTO poiDTO3 = new POIInputDTO("Falconara", POIType.Cinema, authorizedContributor.getId(), new Coordinate(14, 15));

        poiUploadingService.uploadPOI(poiDTO);
        poiUploadingService.uploadPOI(poiDTO2);
        poiUploadingService.uploadPOI(poiDTO3);

        Animator animator = new Animator("Luca", "Verdi", municipality);
        userService.save(animator);

        List<Long> pois = new ArrayList<Long>();
        pois.add(poiService.getPOIbyName("Monteleone").get().getId());
        pois.add(poiService.getPOIbyName("Luce verde").get().getId());
        pois.add(poiService.getPOIbyName("Falconara").get().getId());

        ContestInputDTO contestInputDTO = new ContestInputDTO("Concorso", "Concorso per la città di Ancona",
                animator.getId(), InvitationType.PUBLIC, ContestType.PHOTO_CONTEST,pois, null, 2);

        assertEquals(3,poiService.getAllPOIs().size());
        assertEquals(3,poiService.getPOIsByIds(contestInputDTO.contest_pois()).size());
        contestUploadingService.uploadContest(contestInputDTO);

        assertEquals(1,contestService.getAllContests().size());
        assertEquals(3,contestService.getContestById(1L).getPois().size());

        assertEquals(poiService.getPOIByID(1L).getContest().size(),1);
        assertEquals(poiService.getPOIByID(2L).getContest().size(),1);

        assertEquals(userService.findById(animator.getId()).get().getAuthoredContests().size(),1);

        assertEquals(contestService.getContestById(1L).getPois().get(2),poiService.getPOIByID(3L));




    }
}
