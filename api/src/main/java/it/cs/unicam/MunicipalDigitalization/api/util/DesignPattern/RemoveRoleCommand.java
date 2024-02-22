package it.cs.unicam.MunicipalDigitalization.api.util.DesignPattern;

import it.cs.unicam.MunicipalDigitalization.api.model.actors.AbstractAuthenticatedUser;
import it.cs.unicam.MunicipalDigitalization.api.util.UserRole;

public class RemoveRoleCommand implements ICommand{
    private final UserRole role;
    private final AbstractAuthenticatedUser user;

    public RemoveRoleCommand(UserRole role, AbstractAuthenticatedUser user) {
        this.role = role;
        this.user = user;
    }

    @Override
    public void execute() {
        user.removeRole(role);
    }
}
