package it.cs.unicam.MunicipalDigitalization.db.Services.uploadingServices;

import it.cs.unicam.MunicipalDigitalization.api.util.ContestType;
import it.cs.unicam.MunicipalDigitalization.api.util.DesignPattern.Builder.ContributionContestBuilder;
import it.cs.unicam.MunicipalDigitalization.api.util.InvitationType;
import it.cs.unicam.MunicipalDigitalization.db.Services.ItineraryService;
import it.cs.unicam.MunicipalDigitalization.db.Services.Mediators.ContestMediator;
import it.cs.unicam.MunicipalDigitalization.db.Services.POIService;
import it.cs.unicam.MunicipalDigitalization.db.Services.UserService;
import it.cs.unicam.MunicipalDigitalization.db.controllers.dto.input.ContestInputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContestUploadingService {

    private final UserService userService;
    private final POIService poiService;
    private final ItineraryService itineraryService;
    private final ContestMediator contestMediator;

    @Autowired
    public ContestUploadingService(UserService userService, POIService poiService, ItineraryService itineraryService,
                                   ContestMediator contestMediator) {
        this.userService = userService;
        this.poiService = poiService;
        this.itineraryService = itineraryService;
        this.contestMediator = contestMediator;
    }

    /**
     * Uploads a contest to the database
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
     * Builds a contest using the ContributionContestBuilder
     *
     * @param contestBuilder the builder to be used to build the contest
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
        if(contestInputDTO.contest_pois()!=null && !contestInputDTO.contest_pois().isEmpty()) {
            contestBuilder.setPOIs(poiService.getPOIsByIds(contestInputDTO.contest_pois()));
        }
        if (contestInputDTO.contest_itineraries()!=null && !contestInputDTO.contest_itineraries().isEmpty()) {
            contestBuilder.setItineraries(itineraryService.getItinerariesByIds(contestInputDTO.contest_itineraries()));
        }
    }

    /**
     * Checks if the contest is valid
     *
     * @param contestInputDTO the contest in DTO to be uploaded
     */

    private void checkContest(ContestInputDTO contestInputDTO) {
        if(contestInputDTO.contest_name() == null || contestInputDTO.contest_name().length()>100 || contestInputDTO.contest_name().length()<10) {
            throw new IllegalArgumentException("Contest Name is null");
        }
        if(contestInputDTO.contest_description() == null || contestInputDTO.contest_description().length()>1000
                || contestInputDTO.contest_description().length()<20) {
            throw new IllegalArgumentException("Contest Description is invalid");
        }
        if(contestInputDTO.contest_invitationType() == null || !(contestInputDTO.contest_invitationType().
                equals(InvitationType.INVITE_ONLY) || contestInputDTO.contest_invitationType().equals(InvitationType.PUBLIC))){
            throw new IllegalArgumentException("Contest Invitation Type is invalid");
        }
        if((contestInputDTO.minParticipants()>(userService.getUserById(contestInputDTO.contest_author_id())
                .getMunicipality().getListOfIUsers().size()) / 10) || contestInputDTO.minParticipants()<1)
            throw new IllegalArgumentException("Min Participants is invalid");
        if(contestInputDTO.contestType() == null || !(contestInputDTO.contestType().equals(ContestType.PHOTO_CONTEST)
                || contestInputDTO.contestType().equals(ContestType.WRITING_CONTEST))) throw new IllegalArgumentException("Contest Type is invalid");
    }
}
