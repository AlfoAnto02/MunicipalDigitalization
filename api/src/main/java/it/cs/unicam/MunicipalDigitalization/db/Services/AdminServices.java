package it.cs.unicam.MunicipalDigitalization.db.Services;

import it.cs.unicam.MunicipalDigitalization.api.model.Municipality;
import it.cs.unicam.MunicipalDigitalization.api.model.users.AbstractAuthenticatedUser;
import it.cs.unicam.MunicipalDigitalization.api.util.DesignPattern.Command.GiveRoleCommand;
import it.cs.unicam.MunicipalDigitalization.api.util.DesignPattern.Command.RemoveRoleCommand;
import it.cs.unicam.MunicipalDigitalization.api.util.UserRole;
import it.cs.unicam.MunicipalDigitalization.db.controllers.dto.input.MunicipalityInputDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class for admin operations.
 * It contains methods to remove and add roles to users and to create municipalities.
 */
@Service
@AllArgsConstructor(onConstructor_ = @Autowired)
public class AdminServices {
    private final UserService userService;

    private final MunicipalService municipalService;

    /**
     * Method to remove a role from a user by an admin
     *
     * @param adminID ID of the admin
     * @param userID  ID of the user
     * @param role    Role to remove
     */

    public void removeRole(Long adminID, Long userID, UserRole role) {
        if (userService.getUserById(adminID).getRole().contains(UserRole.PLATFORM_GESTOR)) {
            AbstractAuthenticatedUser user = userService.getUserById(userID);
            RemoveRoleCommand removeRoleCommand = new RemoveRoleCommand(role, userService.getUserById(userID));
            removeRoleCommand.execute();
            userService.saveUser(user);
        } else throw new IllegalArgumentException("User not authorized to remove role");
    }

    /**
     * Method to add a role to a user by an admin
     *
     * @param adminID ID of the admin
     * @param userID  ID of the user
     * @param role    Role to add
     */

    public void addRole(Long adminID, Long userID, UserRole role) {
        if (userService.getUserById(adminID).getRole().contains(UserRole.PLATFORM_GESTOR)) {
            AbstractAuthenticatedUser user = userService.getUserById(userID);
            GiveRoleCommand giveRoleCommand = new GiveRoleCommand(role, userService.getUserById(userID));
            giveRoleCommand.execute();
            userService.saveUser(user);
        } else throw new IllegalArgumentException("User not authorized to add role");
    }

    /**
     * Method to create a municipality by an admin
     *
     * @param adminID         ID of the admin
     * @param municipalityDTO the municipality to be created
     */

    public void createMunicipality(Long adminID, MunicipalityInputDTO municipalityDTO) {
        if (userService.getUserById(adminID).getRole().contains(UserRole.PLATFORM_GESTOR)) {
            Municipality municipality = new Municipality(municipalityDTO.territory(), municipalityDTO.municipality_name());
            municipalService.saveMunicipal(municipality);
        } else throw new IllegalArgumentException("User not authorized to create municipality");
    }

}
