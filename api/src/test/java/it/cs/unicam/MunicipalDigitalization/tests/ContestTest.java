package it.cs.unicam.MunicipalDigitalization.tests;

import it.cs.unicam.MunicipalDigitalization.api.model.Municipality;
import it.cs.unicam.MunicipalDigitalization.api.model.users.Animator;
import it.cs.unicam.MunicipalDigitalization.api.model.users.AuthenticatedTourist;
import it.cs.unicam.MunicipalDigitalization.api.model.users.AuthorizedContributor;
import it.cs.unicam.MunicipalDigitalization.api.model.users.Contributor;
import it.cs.unicam.MunicipalDigitalization.api.util.ContestType;
import it.cs.unicam.MunicipalDigitalization.api.util.Coordinate;
import it.cs.unicam.MunicipalDigitalization.api.util.InvitationType;
import it.cs.unicam.MunicipalDigitalization.api.util.POIType;
import it.cs.unicam.MunicipalDigitalization.db.Repository.MunicipalRepository;
import it.cs.unicam.MunicipalDigitalization.db.Repository.UserRepository;
import it.cs.unicam.MunicipalDigitalization.db.Services.*;
import it.cs.unicam.MunicipalDigitalization.db.Services.Mediators.ContributionMediator;
import it.cs.unicam.MunicipalDigitalization.db.Services.uploadingServices.ContestUploadingService;
import it.cs.unicam.MunicipalDigitalization.db.Services.uploadingServices.ContributionUploadingService;
import it.cs.unicam.MunicipalDigitalization.db.Services.uploadingServices.POIUploadingService;
import it.cs.unicam.MunicipalDigitalization.db.controllers.Requests.ValidateRequest;
import it.cs.unicam.MunicipalDigitalization.db.controllers.Requests.VoteRequest;
import it.cs.unicam.MunicipalDigitalization.db.controllers.dto.input.ContestInputDTO;
import it.cs.unicam.MunicipalDigitalization.db.controllers.dto.input.ContributionInputDTO;
import it.cs.unicam.MunicipalDigitalization.db.controllers.dto.input.POIInputDTO;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
public class ContestTest {
    @Autowired
    private MunicipalService municipalService2;
    @Autowired
    private MunicipalRepository municipalService;
    @Autowired
    private POIService poiService;
    @Autowired
    private UserRepository userService;
    @Autowired
    private POIUploadingService poiUploadingService;
    @Autowired
    private ContestUploadingService contestUploadingService;
    @Autowired
    private ContestService contestService;
    @Autowired
    private ContributionUploadingService contributionUploadingService;
    @Autowired
    private ValidateService validateService;
    @Autowired
    private ParticipationService participationService;
    @Autowired
    private ContributionMediator contributionMediator;

    @Test
    public void createContest() {
        Municipality municipality = createMunicipality();
        municipalService2.saveMunicipal(municipality);

        Municipality municipality1 = new Municipality();
        municipality1.setName("Ancona");

        List<Coordinate> coordinates = new ArrayList<>();
        coordinates.add(new Coordinate(12, 14));
        coordinates.add(new Coordinate(25, 21));
        coordinates.add(new Coordinate(3, 30));

        municipality1.setTerritory(coordinates);

        assertThrows(IllegalArgumentException.class, () -> municipalService2.saveMunicipal(municipality1));
        
        /*Municipality municipality2 = createSecondMunicipality();
        municipalService.save(municipality2);*/

        AuthorizedContributor authorizedContributor = new AuthorizedContributor("Mario", "Rossi", municipality);
        userService.save(authorizedContributor);
        
        /*AuthorizedContributor authorizedContributor2 = new AuthorizedContributor("Luigi", "Romano", municipality2);
        userService.save(authorizedContributor2);*/

        Contributor contributor = new Contributor("Giovanni", "Bianchi", municipality);

        createPOIs(authorizedContributor);
        /*createSecondPOIs(authorizedContributor2);*/
        /*createPOIs(contributor);*/

        Animator animator = new Animator("Luca", "Verdi", municipality);
        userService.save(animator);

        List<Long> pois = getPoiIds();

        ContestInputDTO contestInputDTO = new ContestInputDTO("Concorsooooo", "Concorso per la città di Ancona", animator.getId(), InvitationType.PUBLIC, ContestType.PHOTO_CONTEST, pois, null, 1);

        contestUploadingService.uploadContest(contestInputDTO);

        validateContest(animator, authorizedContributor);
    }

    private Municipality createMunicipality() {
        List<Coordinate> coordinates = new ArrayList<>();
        coordinates.add(new Coordinate(0, 0));
        coordinates.add(new Coordinate(100, 0));
        coordinates.add(new Coordinate(0, 100));
        coordinates.add(new Coordinate(100, 100));
        return new Municipality(coordinates, "Anconaaa");
    }

    // Secondo Comune per il secondo AuthorizedContributor
    /*private Municipality createSecondMunicipality() {
        List<Coordinate> coordinates = new ArrayList<>();
        coordinates.add(new Coordinate(1, 1));
        coordinates.add(new Coordinate(1, 200));
        coordinates.add(new Coordinate(200, 200));
        coordinates.add(new Coordinate(200, 1));
        return new Municipality(coordinates, "Roma");
    }*/

    private void createPOIs(AuthorizedContributor authorizedContributor) {
        poiUploadingService.uploadPOI(new POIInputDTO("Monteleone", POIType.Cinema, authorizedContributor.getId(), new Coordinate(12, 12)));
        poiUploadingService.uploadPOI(new POIInputDTO("Luce verde", POIType.Cinema, authorizedContributor.getId(), new Coordinate(13, 13)));
        poiUploadingService.uploadPOI(new POIInputDTO("Falconara", POIType.Cinema, authorizedContributor.getId(), new Coordinate(14, 15)));
    }

    // Secondo AuthorizedContributor
    /*private void createSecondPOIs(AuthorizedContributor authorizedContributor) {
        poiUploadingService.uploadPOI(new POIInputDTO("daje", POIType.Cinema, authorizedContributor.getId(), new Coordinate(12, 12)));
        poiUploadingService.uploadPOI(new POIInputDTO("Luce roma", POIType.Cinema, authorizedContributor.getId(), new Coordinate(13, 13)));
        poiUploadingService.uploadPOI(new POIInputDTO("forza", POIType.Cinema, authorizedContributor.getId(), new Coordinate(14, 15)));
    }*/

    private void createPOIs(Contributor contributor) {
        poiUploadingService.uploadPOI(new POIInputDTO("primopoi", POIType.Cinema, contributor.getId(), new Coordinate(12, 12)));
        poiUploadingService.uploadPOI(new POIInputDTO("secondopoi", POIType.Cinema, contributor.getId(), new Coordinate(13, 13)));
        poiUploadingService.uploadPOI(new POIInputDTO("indipercui", POIType.Cinema, contributor.getId(), new Coordinate(14, 15)));
    }

    private List<Long> getPoiIds() {
        List<Long> pois = new ArrayList<>();
        pois.add(poiService.getPOIbyName("Monteleone").get().getId());
        pois.add(poiService.getPOIbyName("Luce verde").get().getId());
        pois.add(poiService.getPOIbyName("Falconara").get().getId());

        // Secondo AuthorizedContributor
        /*pois.add(poiService.getPOIbyName("daje").get().getId());
        pois.add(poiService.getPOIbyName("Luce roma").get().getId());
        pois.add(poiService.getPOIbyName("forza").get().getId());*/

        // Contributor
        /*pois.add(poiService.getPOIbyName("primopoi").get().getId());
        pois.add(poiService.getPOIbyName("secondopoi").get().getId());
        pois.add(poiService.getPOIbyName("indipercui").get().getId());*/
        return pois;
    }

    private void validateContest(Animator animator, AuthorizedContributor authorizedContributor) {
        AuthenticatedTourist authenticatedTourist = new AuthenticatedTourist("Giovanni", "Bianchi", animator.getMunicipality());
        userService.save(authenticatedTourist);

        participationService.participateToAContest(1L, authorizedContributor.getId());
        participationService.participateToAContest(1L, authenticatedTourist.getId());

        validateService.validateContest(new ValidateRequest(animator.getId(), 1L, true));

        contributionUploadingService.uploadContribution(new ContributionInputDTO("La vita", "Foto di Ancona", "fotodiancona.png", 1L, authenticatedTourist.getId()));

        contributionMediator.voteContribution(new VoteRequest(authenticatedTourist.getId(), 1L));
    }
}