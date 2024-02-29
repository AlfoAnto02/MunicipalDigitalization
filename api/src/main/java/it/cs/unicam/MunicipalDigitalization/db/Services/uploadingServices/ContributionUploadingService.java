package it.cs.unicam.MunicipalDigitalization.db.Services.uploadingServices;

import it.cs.unicam.MunicipalDigitalization.api.util.ContestStatus;
import it.cs.unicam.MunicipalDigitalization.api.util.ContestType;
import it.cs.unicam.MunicipalDigitalization.api.util.DesignPattern.Builder.ContributionBuilder;
import it.cs.unicam.MunicipalDigitalization.db.Services.ContestService;
import it.cs.unicam.MunicipalDigitalization.db.Services.Mediators.ContributionMediator;
import it.cs.unicam.MunicipalDigitalization.db.Services.UserService;
import it.cs.unicam.MunicipalDigitalization.db.controllers.dto.input.ContributionInputDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service for uploading contributions.
 */
@Service
@AllArgsConstructor(onConstructor_ = @Autowired)
public class ContributionUploadingService {

    private final UserService userService;

    private final ContestService contestService;

    private final ContributionMediator contributionMediator;

    /**
     * Upload a Contribution to a Contest.
     *
     * @param contributionInputDTO DTO of the Contribution.
     */
    public void uploadContribution(ContributionInputDTO contributionInputDTO) {
        checkContribution(contributionInputDTO);
        ContributionBuilder contributionBuilder = new ContributionBuilder();
        buildContribution(contributionBuilder, contributionInputDTO);
        this.contributionMediator.saveContribution(contributionBuilder.build());
    }

    /**
     * Build a Contribution from a ContributionInputDTO.
     *
     * @param contributionBuilder  Builder of the Contribution.
     * @param contributionInputDTO DTO of the Contribution.
     */
    private void buildContribution(ContributionBuilder contributionBuilder, ContributionInputDTO contributionInputDTO) {
        contributionBuilder.setTitle(contributionInputDTO.title());
        contributionBuilder.setDescription(contributionInputDTO.description());
        contributionBuilder.setContribution(contributionInputDTO.content());
        contributionBuilder.setContributionContest(contestService.getContestById(contributionInputDTO.contributionContestId()));
        contributionBuilder.setAuthor(userService.getUserById(contributionInputDTO.authorId()));
    }

    /**
     * Check if the Contribution is valid.
     *
     * @param contributionInputDTO DTO of the Contribution.
     */
    private void checkContribution(ContributionInputDTO contributionInputDTO) {
        checkMunicipality(contributionInputDTO);
        checkContestStatus(contributionInputDTO);
        checkAuthorExists(contributionInputDTO);
        checkContestExists(contributionInputDTO);
        checkTitle(contributionInputDTO);
        checkDescription(contributionInputDTO);
        checkUserParticipation(contributionInputDTO);
        checkContributionType(contributionInputDTO);
    }

    /**
     * Check if the contribution is valid for the contest type.
     *
     * @param contributionInputDTO DTO of the Contribution.
     */
    private void checkContributionType(ContributionInputDTO contributionInputDTO) {
        if (contributionInputDTO.content() == null || contributionInputDTO.content().isEmpty()) {
            throw new IllegalArgumentException("Contribution field is empty");
        }
        if(contestService.getContestById(contributionInputDTO.contributionContestId()).getContestType().equals(ContestType.PHOTO_CONTEST)
                && !(contributionInputDTO.content().endsWith("png") || (contributionInputDTO.content().endsWith("jpg")) ||
                (contributionInputDTO.content().endsWith("jpeg")))){
            throw new IllegalArgumentException("Contribution is not a photo for photo contest");
        }
        if(contestService.getContestById(contributionInputDTO.contributionContestId()).getContestType().equals(ContestType.WRITING_CONTEST)
                && !(contributionInputDTO.content().length()<10 || (contributionInputDTO.content().length()>1000))){
            throw new IllegalArgumentException("Contribution description error for writing contest");
        }
    }

    /**
     * Check if the municipality of the contest and the user are the same.
     *
     * @param contributionInputDTO DTO of the Contribution.
     */
    private void checkMunicipality(ContributionInputDTO contributionInputDTO) {
        if (!contestService.getContestById(contributionInputDTO.contributionContestId()).getMunicipality()
                .equals(userService.getUserById(contributionInputDTO.authorId()).getMunicipality())) {
            throw new IllegalArgumentException("You can't vote a Contest of another Municipality");
        }
    }

    /**
     * Check if the contest is ongoing.
     *
     * @param contributionInputDTO DTO of the Contribution.
     */
    private void checkContestStatus(ContributionInputDTO contributionInputDTO) {
        if (!contestService.getContestById(contributionInputDTO.contributionContestId()).getContestStatus().equals(ContestStatus.ON_GOING)) {
            throw new IllegalArgumentException("Contest it is not on going");
        }
    }

    /**
     * Check if the author exists.
     *
     * @param contributionInputDTO DTO of the Contribution.
     */
    private void checkAuthorExists(ContributionInputDTO contributionInputDTO) {
        if (userService.getUserById(contributionInputDTO.authorId()) == null) {
            throw new IllegalArgumentException("Author not found");
        }
    }

    /**
     * Check if the contest exists.
     *
     * @param contributionInputDTO DTO of the Contribution.
     */
    private void checkContestExists(ContributionInputDTO contributionInputDTO) {
        if (contestService.getContestById(contributionInputDTO.contributionContestId()) == null) {
            throw new IllegalArgumentException("Contest not found");
        }
    }

    /**
     * Check if the title is valid.
     *
     * @param contributionInputDTO DTO of the Contribution.
     */
    private void checkTitle(ContributionInputDTO contributionInputDTO) {
        if (contributionInputDTO.title() == null || contributionInputDTO.title().isEmpty()
                || contributionInputDTO.title().length() < 5 || contributionInputDTO.title().length() > 50) {
            throw new IllegalArgumentException("Title Error");
        }
    }

    /**
     * Check if the description is valid.
     *
     * @param contributionInputDTO DTO of the Contribution.
     */
    private void checkDescription(ContributionInputDTO contributionInputDTO) {
        if (contributionInputDTO.description() == null || contributionInputDTO.description().isEmpty()
                || contributionInputDTO.description().length() < 5 || contributionInputDTO.description().length() > 200) {
            throw new IllegalArgumentException("Description Error");
        }
    }

    /**
     * Check if the user is participating in the contest.
     *
     * @param contributionInputDTO DTO of the Contribution.
     */
    private void checkUserParticipation(ContributionInputDTO contributionInputDTO) {
        if (!userService.getUserById(contributionInputDTO.authorId()).getContestsParticipated()
                .contains(contestService.getContestById(contributionInputDTO.contributionContestId())) &&
                !contestService.getContestById(contributionInputDTO.contributionContestId()).getParticipants()
                        .contains(userService.getUserById(contributionInputDTO.authorId()))) {
            throw new IllegalArgumentException("User Doesn't participate to the contest");
        }
    }
}
