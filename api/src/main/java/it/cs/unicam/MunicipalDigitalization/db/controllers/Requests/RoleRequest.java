package it.cs.unicam.MunicipalDigitalization.db.controllers.Requests;

import it.cs.unicam.MunicipalDigitalization.api.util.UserRole;
import lombok.Getter;
import lombok.Setter;

/**
 * This class is a class used for a request to change the role of a user.
 */
@Getter
@Setter
public class RoleRequest {
    private Long adminID;
    private Long userID;
    private UserRole role;

    /**
     * Constructor for the RoleRequest class.
     *
     * @param userID id of the user to change the role
     * @param role new role for the user
     * @param adminID id of the admin that made the request
     */
    public RoleRequest(Long userID, UserRole role, Long adminID) {
        this.adminID = adminID;
        this.userID = userID;
        this.role = role;
    }
}
