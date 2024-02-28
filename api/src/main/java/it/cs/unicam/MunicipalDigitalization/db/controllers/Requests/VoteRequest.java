package it.cs.unicam.MunicipalDigitalization.db.controllers.Requests;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VoteRequest {
    
    long voterID;
    long requestID;

}
