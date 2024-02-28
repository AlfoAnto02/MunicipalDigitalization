package it.cs.unicam.MunicipalDigitalization.db.controllers.Requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@AllArgsConstructor
public class VoteRequest {
    
    long voterID;
    long requestID;

}
