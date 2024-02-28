package it.cs.unicam.MunicipalDigitalization.db.Services.Mediators;

import it.cs.unicam.MunicipalDigitalization.api.model.elements.ContributionContest;
import it.cs.unicam.MunicipalDigitalization.api.util.ContestStatus;
import it.cs.unicam.MunicipalDigitalization.api.util.UserRole;
import it.cs.unicam.MunicipalDigitalization.db.Services.*;
import it.cs.unicam.MunicipalDigitalization.db.controllers.Requests.ValidateRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor(onConstructor_ = @Autowired)
public class ContestMediator {
    private final UserService userService;
    private final POIService poiService;
    private final MunicipalService municipalService;
    private final ContestService contestService;

    private final ItineraryService itineraryService;

    /**
     * Saves a contest to the database changing the associations with the municipality, the author and the pois/itineraries
     * of the contest
     *
     * @param contributionContest the contest to be saved
     */
    public void saveContest(ContributionContest contributionContest) {
        contestService.saveContest(contributionContest);

        //Get the municipality id and the author id
        Long municipalityId = contributionContest.getMunicipality().getId();
        Long authorId = contributionContest.getAuthor().getId();

        //Check if the contest is already associated with the municipality
        if (municipalService.getMunicipalByID(municipalityId).getContests().stream().noneMatch(c -> c.getId().equals(contributionContest.getId()))) {
            municipalService.addContest(municipalityId, contributionContest);
        }
        //Check if the contest is already associated with the author
        if (userService.getUserById(authorId).getAuthoredContests().stream().noneMatch(c -> c.getId().equals(contributionContest.getId()))) {
            userService.addContest(authorId, contributionContest);
        }
        this.poiService.addContestToPOIs(contributionContest.getPois(), contributionContest);
        this.itineraryService.addContestToItineraries(contributionContest.getItineraries(), contributionContest);
    }

    /**
     * method to validate a Contest
     *
     * @param validateRequest The request to validate.
     */
    public void validateContest(ValidateRequest validateRequest) {
        if (userService.getUserById(validateRequest.getValidatorID()).getRole().contains(UserRole.ANIMATOR) && (contestService
                .getContestById(validateRequest.getRequestID()).getContestStatus().equals(ContestStatus.OPEN))) {
            if (contestService.getContestById(validateRequest.getRequestID()).getActualNumberOfParticipants()
                    > contestService.getContestById(validateRequest.getRequestID()).getMinParticipants()) {
                contestService.validateContest(validateRequest.getRequestID(), validateRequest.isValidated());
                userService.updateUserContestList(validateRequest.getRequestID(), validateRequest.isValidated());
            } else throw new IllegalArgumentException("The contest has not enough participants");
        } else throw new IllegalArgumentException("The user is not an Animator or the contest is not open");
    }
}

