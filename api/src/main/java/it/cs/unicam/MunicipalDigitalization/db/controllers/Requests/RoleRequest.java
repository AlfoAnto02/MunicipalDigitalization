package it.cs.unicam.MunicipalDigitalization.db.controllers.Requests;

import it.cs.unicam.MunicipalDigitalization.api.util.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * This class is a class used for a request to change the role of a user.
 */
@Data
@AllArgsConstructor
public class RoleRequest {
    
    private Long adminID;
    private Long userID;
    private UserRole role;
    
}
