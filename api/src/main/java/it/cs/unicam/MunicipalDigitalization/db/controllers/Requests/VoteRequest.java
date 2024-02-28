package it.cs.unicam.MunicipalDigitalization.db.controllers.Requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VoteRequest {
    long voterID;
    long requestID;

    public VoteRequest(long voterID, long requestID) {
        this.voterID = voterID;
        this.requestID = requestID;
    }
}
