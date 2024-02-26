package it.cs.unicam.MunicipalDigitalization.api.util.DesignPattern.Command;

import it.cs.unicam.MunicipalDigitalization.api.model.users.AbstractAuthenticatedUser;
import it.cs.unicam.MunicipalDigitalization.api.util.UserRole;

/**
 * This class is used to remove a role from a user.
 */
public class RemoveRoleCommand implements ICommand{
    private final UserRole role;
    private final AbstractAuthenticatedUser user;

    /**
     * this is the constructor of the class.
     *
     * @param role the role to remove
     * @param user the user from which to remove the role
     */
    public RemoveRoleCommand(UserRole role, AbstractAuthenticatedUser user) {
        this.role = role;
        this.user = user;
    }

    @Override
    public void execute() {
        user.removeRole(role);
    }
}
