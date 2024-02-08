package it.cs.unicam.MunicipalDigitalization.db.Services;

import it.cs.unicam.MunicipalDigitalization.api.model.Municipality;
import it.cs.unicam.MunicipalDigitalization.api.model.actors.AbstractAuthenticatedUser;
import it.cs.unicam.MunicipalDigitalization.api.util.UserRole;
import it.cs.unicam.MunicipalDigitalization.db.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public AbstractAuthenticatedUser getUserById(Long id){
        return userRepository.getReferenceById(id);
    }

    public List<AbstractAuthenticatedUser> getUsersByMunicipality(Municipality municipality){
        return userRepository.findAllByMunicipality(municipality);
    }

    public List<AbstractAuthenticatedUser> getUsersByRole(UserRole role){
        return userRepository.findAllByRole(role);
    }

    public void saveUser(AbstractAuthenticatedUser user){
        userRepository.save(user);
    }

    public void deleteUser(AbstractAuthenticatedUser user){
        userRepository.delete(user);
    }

    public void deleteUserById(Long id){
        userRepository.deleteById(id);
    }

    public void saveAllUsers(List<AbstractAuthenticatedUser> users){
        userRepository.saveAll(users);
    }



}
