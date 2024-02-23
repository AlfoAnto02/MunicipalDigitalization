package it.cs.unicam.MunicipalDigitalization.db.Services;

import it.cs.unicam.MunicipalDigitalization.api.model.Municipality;
import it.cs.unicam.MunicipalDigitalization.api.model.actors.AbstractAuthenticatedUser;
import it.cs.unicam.MunicipalDigitalization.api.model.elements.AbstractItinerary;
import it.cs.unicam.MunicipalDigitalization.api.model.elements.AbstractPOI;
import it.cs.unicam.MunicipalDigitalization.api.util.ElementStatus;
import it.cs.unicam.MunicipalDigitalization.api.util.UserRole;
import it.cs.unicam.MunicipalDigitalization.db.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for the User entity
 * It provides methods for adding POIs and Itineraries to the user's list of authored elements
 */
@Service
public class UserService {


    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Add a POI to the user's list of authored POIs
     *
     * @param userID ID of the user
     * @param poi POI to be added
     */
    public void addPOI(Long userID, AbstractPOI poi){
        AbstractAuthenticatedUser user = userRepository.getReferenceById(userID);
        user.addPOI(poi);
        userRepository.save(user);
    }

    /**
     * Add an itinerary to the user's list of authored itineraries
     *
     * @param userID ID of the user
     * @param itinerary Itinerary to be added
     */

    public void addItinerary(Long userID, AbstractItinerary itinerary){
        AbstractAuthenticatedUser user = userRepository.getReferenceById(userID);
        user.addItinerary(itinerary);
        userRepository.save(user);
    }

    public void updateUserPOIList(long poiID, boolean validate) {
        AbstractAuthenticatedUser user = userRepository.findByAuthoredPOIsId(poiID);
        if(validate) {
            user.getAuthoredPOIs()
                    .stream()
                    .filter(p -> p.getId().equals(poiID))
                    .forEach(p -> p.setElementStatus(ElementStatus.PUBLISHED));
            userRepository.save(user);
        } else{
            user.getAuthoredPOIs().remove(user.getAuthoredPOIs()
                    .stream()
                    .filter(p -> p.getId().equals(poiID))
                    .findFirst()
                    .get());
            userRepository.save(user);
        }
    }















    public void saveUser(AbstractAuthenticatedUser user){
        userRepository.save(user);
    }
    public AbstractAuthenticatedUser getUserById(Long id){
        return userRepository.getReferenceById(id);
    }

    public List<AbstractAuthenticatedUser> getUsersByRole(UserRole role){
        return userRepository.findAllByRole(role);
    }

    public void deleteUserById(Long id){
        userRepository.deleteById(id);
    }

    public AbstractAuthenticatedUser getUserByName(String name){
        return userRepository.findByName(name);
    }


    public List<AbstractAuthenticatedUser> getAllUsers() {
        return userRepository.findAll();
    }

}
