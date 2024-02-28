package it.cs.unicam.MunicipalDigitalization.db.Services;

import it.cs.unicam.MunicipalDigitalization.api.model.elements.*;
import it.cs.unicam.MunicipalDigitalization.api.model.users.AbstractAuthenticatedUser;
import it.cs.unicam.MunicipalDigitalization.api.util.ContestStatus;
import it.cs.unicam.MunicipalDigitalization.api.util.ElementStatus;
import it.cs.unicam.MunicipalDigitalization.api.util.UserRole;
import it.cs.unicam.MunicipalDigitalization.db.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for the User entity
 * It provides methods for adding POIs and Itineraries to the user's list of authored elements
 */
@Service
@AllArgsConstructor(onConstructor_ = @Autowired)
public class UserService {


    private final UserRepository userRepository;

    /**
     * Add a POI to the user's list of authored POIs
     *
     * @param userID ID of the user
     * @param poi    POI to be added
     */
    public void addPOI(Long userID, AbstractPOI poi) {
        AbstractAuthenticatedUser user = userRepository.getReferenceById(userID);
        user.addPOI(poi);
        userRepository.save(user);
    }

    /**
     * Add an itinerary to the user's list of authored itineraries
     *
     * @param userID    ID of the user
     * @param itinerary Itinerary to be added
     */

    public void addItinerary(Long userID, AbstractItinerary itinerary) {
        AbstractAuthenticatedUser user = userRepository.getReferenceById(userID);
        user.addItinerary(itinerary);
        userRepository.save(user);
    }

    /**
     * Add a content to the user's list of authored contents
     *
     * @param id      ID of the user
     * @param content Content to be added
     */
    public void addContent(Long id, AbstractContent content) {
        AbstractAuthenticatedUser user = userRepository.getReferenceById(id);
        user.addContent(content);
        userRepository.save(user);
    }

    /**
     * Update the user's list of authored POIs after the Validation of a POI
     *
     * @param poiID    ID of the poi
     * @param validate true if the poi is validated, false if it is not
     */

    public void updateUserPOIList(long poiID, boolean validate) {
        AbstractAuthenticatedUser user = userRepository.findByAuthoredPOIsId(poiID);
        if (validate) {
            user.getAuthoredPOIs()
                    .stream()
                    .filter(p -> p.getId().equals(poiID))
                    .forEach(p -> p.setElementStatus(ElementStatus.PUBLISHED));
            userRepository.save(user);
        } else {
            user.getAuthoredPOIs().removeIf(p -> p.getId().equals(poiID));
            userRepository.save(user);
        }
    }

    /**
     * Update the user's list of authored itineraries after the Validation of an itinerary
     *
     * @param itineraryID ID of the itinerary
     * @param validate    true if the itinerary is validated, false if it is not
     */
    public void updateUserItineraryList(long itineraryID, boolean validate) {
        AbstractAuthenticatedUser user = userRepository.findByAuthoredItinerariesId(itineraryID);
        if (validate) {
            user.getAuthoredItineraries()
                    .stream()
                    .filter(p -> p.getId().equals(itineraryID))
                    .forEach(p -> p.setElementStatus(ElementStatus.PUBLISHED));
            userRepository.save(user);
        } else {
            Optional<AbstractItinerary> itineraryToRemove = user.getAuthoredItineraries()
                    .stream()
                    .filter(p -> p.getId().equals(itineraryID))
                    .findFirst();
            itineraryToRemove.ifPresent(user.getAuthoredItineraries()::remove);
            userRepository.save(user);
        }
    }

    /**
     * Update the user's list of authored contests after the Validation of a Contest
     *
     * @param requestID ID of the request
     * @param validated true if the contest is validated, false if it is not
     */

    public void updateUserContestList(long requestID, boolean validated) {
        AbstractAuthenticatedUser user = userRepository.findByAuthoredContestsId(requestID);
        if (validated) {
            user.getAuthoredContests()
                    .stream()
                    .filter(c -> c.getId().equals(requestID))
                    .forEach(c -> c.setContestStatus(ContestStatus.ON_GOING));
            userRepository.save(user);
        } else {
            Optional<ContributionContest> contestToRemove = user.getAuthoredContests()
                    .stream()
                    .filter(c -> c.getId().equals(requestID))
                    .findFirst();
            contestToRemove.ifPresent(user.getAuthoredContests()::remove);
            userRepository.save(user);
        }
    }

    /**
     * Update the user's list of authored contents after the Validation of a Content
     *
     * @param requestID ID of the request
     * @param validated true if the content is validated, false if it is not
     */

    public void updateUserContentList(long requestID, boolean validated) {
        AbstractAuthenticatedUser user = userRepository.findByAuthoredContentsId(requestID);
        if (validated) {
            user.getAuthoredContents()
                    .stream()
                    .filter(c -> c.getId().equals(requestID))
                    .forEach(c -> c.setElementStatus(ElementStatus.PUBLISHED));
            userRepository.save(user);
        } else {
            Optional<AbstractContent> contentToRemove = user.getAuthoredContents()
                    .stream()
                    .filter(c -> c.getId().equals(requestID))
                    .findFirst();
            contentToRemove.ifPresent(user.getAuthoredContents()::remove);
            userRepository.save(user);
        }
    }

    public void saveUser(AbstractAuthenticatedUser user) {
        userRepository.save(user);
    }

    public AbstractAuthenticatedUser getUserById(Long id) {
        return userRepository.getReferenceById(id);
    }

    public List<AbstractAuthenticatedUser> getUsersByRole(UserRole role) {
        return userRepository.findAllByRole(role);
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    public AbstractAuthenticatedUser getUserByName(String name) {
        return userRepository.findByName(name);
    }


    public List<AbstractAuthenticatedUser> getAllUsers() {
        return userRepository.findAll();
    }

    public void addContest(Long authorId, ContributionContest contributionContest) {
        AbstractAuthenticatedUser user = userRepository.getReferenceById(authorId);
        user.addAuthoredContest(contributionContest);
        userRepository.save(user);
    }

    public void addContribution(Long id, Contribution contribution) {
        AbstractAuthenticatedUser user = userRepository.getReferenceById(id);
        user.addContribution(contribution);
        userRepository.save(user);
    }

    public void voteContribution(Contribution contribution, Long userId) {
        AbstractAuthenticatedUser user = userRepository.getReferenceById(userId);
        user.voteContribution(contribution);
        userRepository.save(user);
    }
}
