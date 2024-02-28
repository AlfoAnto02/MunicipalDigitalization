package it.cs.unicam.MunicipalDigitalization.db.Services;

import it.cs.unicam.MunicipalDigitalization.api.model.elements.Contribution;
import it.cs.unicam.MunicipalDigitalization.api.model.users.AbstractAuthenticatedUser;
import it.cs.unicam.MunicipalDigitalization.api.util.MatchingAlgorithms;
import it.cs.unicam.MunicipalDigitalization.db.Repository.ContributionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContributionService {

    private final ContributionRepository contributionRepository;
    private final UserService userService;

    @Autowired
    public ContributionService(ContributionRepository contributionRepository, UserService userService) {
        this.contributionRepository = contributionRepository;
        this.userService = userService;
    }

    public void saveContribution(Contribution contribution) {
        if (MatchingAlgorithms.isContributionSimilatrToAContributionList(contribution, contributionRepository.findAll())) {
            throw new IllegalArgumentException("Contribution already exists");
        }
        contributionRepository.save(contribution);
    }

    public Contribution getContributionById(Long id) {
        return contributionRepository.getReferenceById(id);
    }

    public void voteContribution(Long contributionId, AbstractAuthenticatedUser user) {
        {
            Contribution contribution = contributionRepository.getReferenceById(contributionId);
            contribution.addVote(user);
            contributionRepository.save(contribution);
        }
    }

    public List<Contribution> getContributionByContestId(Long contestId) {
        return contributionRepository.getContributionByContestId(contestId);
    }
}
