package it.cs.unicam.MunicipalDigitalization.db.controllers.Requests;

import it.cs.unicam.MunicipalDigitalization.api.util.UserRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleRequest {
    private Long adminID;
    private Long userID;
    private UserRole role;

    public RoleRequest(Long userID, UserRole role, Long adminID) {
        this.adminID = adminID;
        this.userID = userID;
        this.role = role;
    }
}
