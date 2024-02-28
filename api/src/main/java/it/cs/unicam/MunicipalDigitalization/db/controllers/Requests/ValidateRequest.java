package it.cs.unicam.MunicipalDigitalization.db.controllers.Requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * This class is a class used for a request to validate a request.
 */
@Data
@AllArgsConstructor
public class ValidateRequest {
    
    long validatorID;
    long requestID;
    boolean isValidated;

}
