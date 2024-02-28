package it.cs.unicam.MunicipalDigitalization.db.Services.uploadingServices;

import it.cs.unicam.MunicipalDigitalization.api.util.ContestStatus;
import it.cs.unicam.MunicipalDigitalization.api.util.DesignPattern.Builder.ContributionBuilder;
import it.cs.unicam.MunicipalDigitalization.api.util.DesignPattern.Builder.ContributionContestBuilder;
import it.cs.unicam.MunicipalDigitalization.db.Services.ContestService;
import it.cs.unicam.MunicipalDigitalization.db.Services.ContributionService;
import it.cs.unicam.MunicipalDigitalization.db.Services.Mediators.ContributionMediator;
import it.cs.unicam.MunicipalDigitalization.db.Services.UserService;
import it.cs.unicam.MunicipalDigitalization.db.controllers.dto.input.ContestInputDTO;
import it.cs.unicam.MunicipalDigitalization.db.controllers.dto.input.ContributionInputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContributionUploadingService {
    private final UserService userService;
    private final ContestService contestService;
    private final ContributionMediator contributionMediator;

    @Autowired
    public ContributionUploadingService(UserService userService, ContestService contestService,
                                        ContributionMediator contributionMediator) {
        this.userService = userService;
        this.contestService = contestService;
        this.contributionMediator = contributionMediator;
    }

    /**
     * Upload a Contribution to a Contest
     *
     * @param contributionInputDTO DTO of the Contribution
     */
    public void uploadContribution(ContributionInputDTO contributionInputDTO) {
        checkContribution(contributionInputDTO);
        ContributionBuilder contributionBuilder = new ContributionBuilder();
        buildContribution(contributionBuilder, contributionInputDTO);
        this.contributionMediator.saveContribution(contributionBuilder.build());
    }

    /**
     * build a Contribution from a ContributionInputDTO
     *
     * @param contributionBuilder Builder of the Contribution
     * @param contributionInputDTO DTO of the Contribution
     */
    private void buildContribution(ContributionBuilder contributionBuilder, ContributionInputDTO contributionInputDTO) {
        contributionBuilder.setTitle(contributionInputDTO.title());
        contributionBuilder.setDescription(contributionInputDTO.description());
        contributionBuilder.setContribution(contributionInputDTO.content());
        contributionBuilder.setContributionContest(contestService.getContestById(contributionInputDTO.contributionContestId()));
        contributionBuilder.setAuthor(userService.getUserById(contributionInputDTO.authorId()));
    }

    /**
     * Check if the Contribution is valid
     *
     * @param contributionInputDTO DTO of the Contribution
     */
    private void checkContribution(ContributionInputDTO contributionInputDTO) {
        if(!contestService.getContestById(contributionInputDTO.contributionContestId()).getMunicipality()
                .equals(userService.getUserById(contributionInputDTO.authorId()).getMunicipality())) {
            throw new IllegalArgumentException("You can't vote a Contest of another Municipality");
        }
        if(!contestService.getContestById(contributionInputDTO.contributionContestId()).getContestStatus().equals(ContestStatus.ON_GOING)) {
            throw new IllegalArgumentException("Contest it is not on going");
        }
        if(userService.getUserById(contributionInputDTO.authorId()) == null) {
            throw new IllegalArgumentException("Author not found");
        }
        if (contestService.getContestById(contributionInputDTO.contributionContestId()) == null) {
            throw new IllegalArgumentException("Contest not found");
        }
        if(contributionInputDTO.title() == null || contributionInputDTO.title().isEmpty() ||
                contributionInputDTO.title().length()<5 || contributionInputDTO.title().length()>50) {
            throw new IllegalArgumentException("Title Error");
        }
        if(contributionInputDTO.description() == null || contributionInputDTO.description().isEmpty() ||
                contributionInputDTO.description().length()<5 || contributionInputDTO.description().length()>200) {
            throw new IllegalArgumentException("Description Error");
        }
        if(!userService.getUserById(contributionInputDTO.authorId()).getContestsParticipated()
                .contains(contestService.getContestById(contributionInputDTO.contributionContestId())) &&
                !contestService.getContestById(contributionInputDTO.contributionContestId()).getParticipants()
                        .contains(userService.getUserById(contributionInputDTO.authorId()))) {
            throw new IllegalArgumentException("User Doesn't participate to the contest");
        }
    }

}
