package it.cs.unicam.MunicipalDigitalization.db.controllers.Requests;

import lombok.AllArgsConstructor;
import lombok.Data;

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
