package it.cs.unicam.MunicipalDigitalization.db.controllers;

import it.cs.unicam.MunicipalDigitalization.db.Services.ContestService;
import it.cs.unicam.MunicipalDigitalization.db.Services.ContributionService;
import it.cs.unicam.MunicipalDigitalization.db.Services.Mediators.ContributionMediator;
import it.cs.unicam.MunicipalDigitalization.db.Services.uploadingServices.ContributionUploadingService;
import it.cs.unicam.MunicipalDigitalization.db.controllers.Requests.VoteRequest;
import it.cs.unicam.MunicipalDigitalization.db.controllers.dto.input.ContributionInputDTO;
import it.cs.unicam.MunicipalDigitalization.db.controllers.dto.mappers.ContributionDTOMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor(onConstructor_ = @Autowired)
public class ContributionController {

    private final ContributionUploadingService contributionUploadingService;
    private final ContestService contestService;
    private final ContributionDTOMapper contributionDTOMapper;
    private final ContributionMediator contributionMediator;
    private final ContributionService contributionService;

    /**
     * This method permits a user to contribute to a contest
     *
     * @param contributionInputDTO the contribution to be added
     * @return a message indicating that the contribution has been added
     */
    @RequestMapping(value = "/v1/contest/contribute", method = RequestMethod.POST)
    public ResponseEntity<Object> contributeToAContest(@RequestBody ContributionInputDTO contributionInputDTO) {
        this.contributionUploadingService.uploadContribution(contributionInputDTO);
        return new ResponseEntity<>("Contribution added", HttpStatus.OK);
    }

    /**
     * This method returns all the contributions of a contest
     *
     * @param contest_id the contest
     * @return the contributions
     */
    @RequestMapping(value = "/v1/contest/{contest_id}/contributions", method = RequestMethod.GET)
    public ResponseEntity<Object> getContributions(@PathVariable Long contest_id) {
        return new ResponseEntity<>(contestService.getContestById(contest_id).getContributions()
                .stream()
                .map(contributionDTOMapper)
                , HttpStatus.OK);
    }

    /**
     * This method permits a user to vote a contribution
     *
     * @param voteRequest the vote
     * @return a message indicating that the contribution has been voted
     */
    @RequestMapping(value = "/v1/contest/vote", method = RequestMethod.POST)
    public ResponseEntity<Object> voteContribution(@RequestBody VoteRequest voteRequest) {
        this.contributionMediator.voteContribution(voteRequest);
        return new ResponseEntity<>("ContributionVoted", HttpStatus.OK);
    }
}
