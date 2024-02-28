package it.cs.unicam.MunicipalDigitalization.db.Services.Mediators;

import it.cs.unicam.MunicipalDigitalization.api.model.elements.Contribution;
import it.cs.unicam.MunicipalDigitalization.db.Services.ContestService;
import it.cs.unicam.MunicipalDigitalization.db.Services.ContributionService;
import it.cs.unicam.MunicipalDigitalization.db.Services.UserService;
import it.cs.unicam.MunicipalDigitalization.db.controllers.Requests.VoteRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ContributionMediator {

    private final UserService userService;
    private final ContestService contestService;
    private final ContributionService contributionService;

    @Autowired
    public ContributionMediator(UserService userService, ContestService contestService, ContributionService contributionService) {
        this.userService = userService;
        this.contestService = contestService;
        this.contributionService = contributionService;
    }

    /**
     * This method saves a contribution and associates it with the author and the contest.
     *
     * @param contribution The contribution to save.
     */
    public void saveContribution(Contribution contribution) {
        this.contributionService.saveContribution(contribution);

        if(contribution.getAuthor().getAuthoredContributions().stream().noneMatch(c -> c.getId().equals(contribution.getId()))){
            userService.addContribution(contribution.getAuthor().getId(), contribution);
        }
        if(contribution.getContest().getContributions().stream().noneMatch(c -> c.getId().equals(contribution.getId()))){
            contestService.addContribution(contribution.getContest().getId(), contribution);
        }
    }

    /**
     * This method lets a user vote a contribution.
     *
     * @param voteRequest The vote request.
     */
    public void voteContribution(VoteRequest voteRequest) {
        this.contributionService.voteContribution(voteRequest.getRequestID(), userService.getUserById(voteRequest.getVoterID()));
        this.userService.voteContribution(this.contributionService.getContributionById(voteRequest.getRequestID()), voteRequest.getVoterID());
    }
}
