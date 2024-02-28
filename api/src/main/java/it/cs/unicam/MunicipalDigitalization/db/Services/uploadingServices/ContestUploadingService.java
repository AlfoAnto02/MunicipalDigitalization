package it.cs.unicam.MunicipalDigitalization.db.Services.uploadingServices;

import it.cs.unicam.MunicipalDigitalization.api.util.ContestType;
import it.cs.unicam.MunicipalDigitalization.api.util.DesignPattern.Builder.ContributionContestBuilder;
import it.cs.unicam.MunicipalDigitalization.api.util.InvitationType;
import it.cs.unicam.MunicipalDigitalization.db.Services.ItineraryService;
import it.cs.unicam.MunicipalDigitalization.db.Services.Mediators.ContestMediator;
import it.cs.unicam.MunicipalDigitalization.db.Services.POIService;
import it.cs.unicam.MunicipalDigitalization.db.Services.UserService;
import it.cs.unicam.MunicipalDigitalization.db.controllers.dto.input.ContestInputDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class for uploading contests to the database.
 */
@Service
@AllArgsConstructor(onConstructor_ = @Autowired)
public class ContestUploadingService {

    private final UserService userService;

    private final POIService poiService;

    private final ItineraryService itineraryService;

    private final ContestMediator contestMediator;

    /**
     * Uploads a contest to the database.
     *
     * @param contestInputDTO the contest in DTO to be uploaded
     */
    public void uploadContest(ContestInputDTO contestInputDTO) {
        checkContest(contestInputDTO);
        ContributionContestBuilder contestBuilder = new ContributionContestBuilder();
        buildContest(contestBuilder, contestInputDTO);
        this.contestMediator.saveContest(contestBuilder.build());
    }

    /**
     * Builds a contest using the ContributionContestBuilder.
     *
     * @param contestBuilder  the builder to be used to build the contest
     * @param contestInputDTO the contest in DTO to be uploaded
     */
    private void buildContest(ContributionContestBuilder contestBuilder, ContestInputDTO contestInputDTO) {
        contestBuilder.setName(contestInputDTO.contest_name());
        contestBuilder.setDescription(contestInputDTO.contest_description());
        contestBuilder.setMunicipality(userService.getUserById(contestInputDTO.contest_author_id()).getMunicipality());
        contestBuilder.setAuthor(userService.getUserById(contestInputDTO.contest_author_id()));
        contestBuilder.setInvitationType(contestInputDTO.contest_invitationType());
        contestBuilder.setMinParticipants(contestInputDTO.minParticipants());
        contestBuilder.setContestType(contestInputDTO.contestType());
        if (contestInputDTO.contest_pois() != null && !contestInputDTO.contest_pois().isEmpty()) {
            contestBuilder.setPOIs(poiService.getPOIsByIds(contestInputDTO.contest_pois()));
        }
        if (contestInputDTO.contest_itineraries() != null && !contestInputDTO.contest_itineraries().isEmpty()) {
            contestBuilder.setItineraries(itineraryService.getItinerariesByIds(contestInputDTO.contest_itineraries()));
        }
    }

    /**
     * Checks if the contest is valid.
     *
     * @param contestInputDTO the contest in DTO to be uploaded
     */
    private void checkContest(ContestInputDTO contestInputDTO) {
        checkPOIMunicipalityOfTheAnimator(contestInputDTO);
        checkContestName(contestInputDTO);
        checkContestDescription(contestInputDTO);
        checkContestInvitationType(contestInputDTO);
        checkMinParticipants(contestInputDTO);
        checkContestType(contestInputDTO);
    }

    /**
     * Checks if the Animator's municipality matches the contest's municipality.
     *
     * @param contestInputDTO the contest in DTO to be uploaded
     */
    private void checkPOIMunicipalityOfTheAnimator(ContestInputDTO contestInputDTO) {
        for (Long poiId : contestInputDTO.contest_pois()) {
            if (!poiService.getPOIByID(poiId).getMunicipality().equals(userService.getUserById(contestInputDTO.contest_author_id()).getMunicipality())) {
                throw new IllegalArgumentException("POI's municipality doesn't match the Animator's municipality");
            }
        }
    }

    /**
     * Checks if the contest name is valid.
     *
     * @param contestInputDTO the contest in DTO to be uploaded
     */
    private void checkContestName(ContestInputDTO contestInputDTO) {
        if (contestInputDTO.contest_name() == null || contestInputDTO.contest_name().length() > 100 || contestInputDTO.contest_name().length() < 10) {
            throw new IllegalArgumentException("Contest Name is null");
        }
    }

    /**
     * Checks if the contest description is valid.
     *
     * @param contestInputDTO the contest in DTO to be uploaded
     */
    private void checkContestDescription(ContestInputDTO contestInputDTO) {
        if (contestInputDTO.contest_description() == null || contestInputDTO.contest_description().length() > 1000 || contestInputDTO.contest_description().length() < 20) {
            throw new IllegalArgumentException("Contest Description is invalid");
        }
    }

    /**
     * Checks if the contest invitation type is valid.
     *
     * @param contestInputDTO the contest in DTO to be uploaded
     */
    private void checkContestInvitationType(ContestInputDTO contestInputDTO) {
        if (contestInputDTO.contest_invitationType() == null || !(contestInputDTO.contest_invitationType().equals(InvitationType.INVITE_ONLY) || contestInputDTO.contest_invitationType().equals(InvitationType.PUBLIC))) {
            throw new IllegalArgumentException("Contest Invitation Type is invalid");
        }
    }

    /**
     * Checks if the minimum number of participants is valid.
     *
     * @param contestInputDTO the contest in DTO to be uploaded
     */
    private void checkMinParticipants(ContestInputDTO contestInputDTO) {
        if ((contestInputDTO.minParticipants() > ((userService.getUserById(contestInputDTO.contest_author_id()).getMunicipality().getListOfIUsers().size()) / 10) || contestInputDTO.minParticipants() < 0)) {
            throw new IllegalArgumentException("Min Participants is invalid");
        }
    }

    /**
     * Checks if the contest type is valid.
     *
     * @param contestInputDTO the contest in DTO to be uploaded
     */
    private void checkContestType(ContestInputDTO contestInputDTO) {
        if (contestInputDTO.contestType() == null || !(contestInputDTO.contestType().equals(ContestType.PHOTO_CONTEST) || contestInputDTO.contestType().equals(ContestType.WRITING_CONTEST))) {
            throw new IllegalArgumentException("Contest Type is invalid");
        }
    }
}