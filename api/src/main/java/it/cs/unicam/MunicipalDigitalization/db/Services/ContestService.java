package it.cs.unicam.MunicipalDigitalization.db.Services;

import it.cs.unicam.MunicipalDigitalization.api.model.elements.ContributionContest;
import it.cs.unicam.MunicipalDigitalization.api.util.ContestStatus;
import it.cs.unicam.MunicipalDigitalization.api.util.MatchingAlgorithms;
import it.cs.unicam.MunicipalDigitalization.db.Repository.ContestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContestService {
    private final ContestRepository contestRepository;

    @Autowired
    public ContestService(ContestRepository contestRepository, UserService userService) {
        this.contestRepository = contestRepository;
    }

    /**
     * Method to get a contest by its id.
     *
     * @param id The id of the contest.
     * @return The contest with the given id.
     */
    public ContributionContest getContestById(Long id) {
        return contestRepository.getReferenceById(id);
    }

    /**
     * Method to save a contest.
     *
     * @param contest The contest to save.
     */
    public void saveContest(ContributionContest contest) {
        if (!MatchingAlgorithms.isContestSimilarToContestList(contest, contestRepository.findAll())) contestRepository.save(contest);
        else throw new IllegalArgumentException("Contest already exists");
    }

    /**
     * Method to validate a contest.
     *
     * @param requestID The id of the request.
     * @param validated A boolean indicating if the contest is validated.
     */
    public void validateContest(long requestID, boolean validated) {
        if (validated) {
            ContributionContest contest = contestRepository.getReferenceById(requestID);
            contest.setContestStatus(ContestStatus.ON_GOING);
            contestRepository.save(contest);
        } else {
            contestRepository.deleteById(requestID);
        }
    }

    /**
     * Method to get all contests.
     *
     * @return A list of all contests.
     */
    public List<ContributionContest> getAllContests() {
        return contestRepository.findAll();
    }

}
