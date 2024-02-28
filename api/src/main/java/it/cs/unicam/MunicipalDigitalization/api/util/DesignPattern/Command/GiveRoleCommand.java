package it.cs.unicam.MunicipalDigitalization.api.util.DesignPattern.Command;

import it.cs.unicam.MunicipalDigitalization.api.model.users.AbstractAuthenticatedUser;
import it.cs.unicam.MunicipalDigitalization.api.util.UserRole;

/**
 * This class is the concrete command for giving a role to a user.
 */
public class GiveRoleCommand implements ICommand {
    private final UserRole role;
    private final AbstractAuthenticatedUser user;

    /**
     * this is the constructor of the class.
     *
     * @param role the role to give to the user
     * @param user the user to give the role to
     */
    public GiveRoleCommand(UserRole role, AbstractAuthenticatedUser user) {
        this.role = role;
        this.user = user;
    }

    @Override
    public void execute() {
        user.addRole(role);
    }
}
