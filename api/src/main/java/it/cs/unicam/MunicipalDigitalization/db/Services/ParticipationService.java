package it.cs.unicam.MunicipalDigitalization.db.Services;

import it.cs.unicam.MunicipalDigitalization.api.model.elements.ContributionContest;
import it.cs.unicam.MunicipalDigitalization.api.model.users.AbstractAuthenticatedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParticipationService {

    private final ContestService contestService;
    private final UserService userService;

    @Autowired
    public ParticipationService(ContestService contestService, UserService userService) {
        this.contestService = contestService;
        this.userService = userService;
    }

    public void participateToAContest(Long contestId, Long userId) {
        ContributionContest contest = contestService.getContestById(contestId);
        if (!contest.getParticipants().contains(userService.getUserById(userId))) {
            contest.addParticipant(userService.getUserById(userId));
            contestService.saveContest(contest);
            AbstractAuthenticatedUser user = userService.getUserById(userId);
            user.addParticipatedContest(contest);
            userService.saveUser(user);
        } else throw new IllegalArgumentException("User already participated to the contest");
    }
}
