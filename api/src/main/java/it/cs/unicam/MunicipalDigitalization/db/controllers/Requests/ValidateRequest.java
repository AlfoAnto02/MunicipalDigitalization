package it.cs.unicam.MunicipalDigitalization.db.controllers.Requests;

import lombok.Getter;
import lombok.Setter;

/**
 * This class is a class used for a request to validate a request.
 */
@Getter
@Setter
public class ValidateRequest {
    long validatorID;
    long requestID;
    boolean isValidated;

    /**
     * Constructor for the ValidateRequest class.
     *
     * @param validatorID id of the curator that made the request
     * @param requestID   id of the request to validate
     * @param isValidated boolean that indicates if the request is validated
     */
    public ValidateRequest(long validatorID, long requestID, boolean isValidated) {
        this.validatorID = validatorID;
        this.requestID = requestID;
        this.isValidated = isValidated;
    }
}
