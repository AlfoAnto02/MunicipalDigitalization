package it.cs.unicam.MunicipalDigitalization.db.Services;

import it.cs.unicam.MunicipalDigitalization.api.model.elements.ContributionContest;
import it.cs.unicam.MunicipalDigitalization.api.model.users.AbstractAuthenticatedUser;
import it.cs.unicam.MunicipalDigitalization.api.util.ContestStatus;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor_ = @Autowired)
public class ParticipationService {

    private final ContestService contestService;
    private final UserService userService;

    public void participateToAContest(Long contestId, Long userId) {
        ContributionContest contest = contestService.getContestById(contestId);
            if (contest.getParticipants().contains(userService.getUserById(userId))) throw new IllegalArgumentException("User already participated to the contest");
            if(contest.getContestStatus().equals(ContestStatus.CLOSED)) throw new IllegalArgumentException("Contest is closed");
            if (!contest.getMunicipality().equals(userService.getUserById(userId).getMunicipality())) throw new IllegalArgumentException("User is not from the same municipality");
            contest.addParticipant(userService.getUserById(userId));
            contestService.saveContest(contest);
            AbstractAuthenticatedUser user = userService.getUserById(userId);
            user.addParticipatedContest(contest);
            userService.saveUser(user);
    }
}
