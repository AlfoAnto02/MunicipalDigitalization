package it.cs.unicam.MunicipalDigitalization.db.controllers;

import it.cs.unicam.MunicipalDigitalization.api.util.ContestStatus;
import it.cs.unicam.MunicipalDigitalization.api.util.UserRole;
import it.cs.unicam.MunicipalDigitalization.db.Services.MunicipalService;
import it.cs.unicam.MunicipalDigitalization.db.Services.ParticipationService;
import it.cs.unicam.MunicipalDigitalization.db.Services.UserService;
import it.cs.unicam.MunicipalDigitalization.db.Services.ValidateService;
import it.cs.unicam.MunicipalDigitalization.db.Services.uploadingServices.ContestUploadingService;
import it.cs.unicam.MunicipalDigitalization.db.controllers.Requests.ValidateRequest;
import it.cs.unicam.MunicipalDigitalization.db.controllers.dto.input.ContestInputDTO;
import it.cs.unicam.MunicipalDigitalization.db.mappers.ContestDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ContestController {
    private final ContestUploadingService contestUploadingService;
    private final MunicipalService municipalService;
    private final UserService userService;
    private final ContestDTOMapper contestDTOMapper;
    private final ParticipationService participationService;
    private final ValidateService validateService;

    @Autowired
    public ContestController(ContestUploadingService contestUploadingService, MunicipalService municipalService,
                             UserService userService, ContestDTOMapper contestDTOMapper, ParticipationService participationService,
                             ValidateService validateService) {
        this.contestUploadingService = contestUploadingService;
        this.municipalService = municipalService;
        this.userService = userService;
        this.contestDTOMapper = contestDTOMapper;
        this.participationService = participationService;
        this.validateService = validateService;
    }

    /**
     * Uploads a contest to the database only if the Animator perform this action
     *
     * @param contestInputDTO the contest to be uploaded
     * @return a message indicating that the contest has been added
     */

    @RequestMapping(value ="/v1/uploadContest", method = RequestMethod.POST)
    public ResponseEntity<Object> uploadContest(@RequestBody ContestInputDTO contestInputDTO) {
        if(userService.getUserById(contestInputDTO.contest_author_id()).getRole().contains(UserRole.CURATOR)) {
            contestUploadingService.uploadContest(contestInputDTO);
            return new ResponseEntity<>("Contest Uploaded", HttpStatus.OK);
        } else return new ResponseEntity<>("User is not Authorized", HttpStatus.FORBIDDEN);
    }

    /**
     * Returns all the Open or OnGoing contests of a municipality
     *
     * @param municipality_id the municipality
     * @return the contests
     */
    @RequestMapping(value="/v1/municipality/{municipality_id}/contests", method = RequestMethod.GET)
    public ResponseEntity<Object> getContributionContests(@PathVariable Long municipality_id) {
        return new ResponseEntity<>(municipalService.getMunicipalByID(municipality_id).getContests()
                .stream()
                .filter(contest -> contest.getContestStatus().equals(ContestStatus.OPEN) || contest.getContestStatus().equals(ContestStatus.ON_GOING))
                .map(contestDTOMapper)
                ,HttpStatus.OK);
    }

    /**
     * This method permits a user to participate a contest
     *
     * @param contest_id id of the contest
     * @param user_id id of the user
     * @return a message indicating that the participation has been added
     */

    @RequestMapping(value="/v1/contest/{contest_id}/participate/{user_id}", method = RequestMethod.POST)
    public ResponseEntity<Object> participateToAContest(@PathVariable Long contest_id, @PathVariable Long user_id) {
        participationService.participateToAContest(contest_id, user_id);
        return new ResponseEntity<>("Participation added", HttpStatus.OK);
    }

    @RequestMapping(value="/v1/contest/validate", method = RequestMethod.POST)
    public ResponseEntity<Object> validateContributionContest(@RequestBody ValidateRequest validateRequest){
        this.validateService.validateContest(validateRequest);
        return new ResponseEntity<>("Contest validated", HttpStatus.OK);
    }

}
