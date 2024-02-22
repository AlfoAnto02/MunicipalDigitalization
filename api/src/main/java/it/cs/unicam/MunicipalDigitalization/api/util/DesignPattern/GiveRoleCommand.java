package it.cs.unicam.MunicipalDigitalization.api.util.DesignPattern;

import it.cs.unicam.MunicipalDigitalization.api.model.actors.AbstractAuthenticatedUser;
import it.cs.unicam.MunicipalDigitalization.api.util.UserRole;

public class GiveRoleCommand implements ICommand{
    private final UserRole role;
    private final AbstractAuthenticatedUser user;

    public GiveRoleCommand(UserRole role, AbstractAuthenticatedUser user) {
        this.role = role;
        this.user = user;
    }

    @Override
    public void execute() {
        user.addRole(role);
    }
}
