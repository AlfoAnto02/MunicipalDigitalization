package it.cs.unicam.MunicipalDigitalization.db.controllers.Requests;


import it.cs.unicam.MunicipalDigitalization.db.controllers.dto.input.MunicipalityInputDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * Class used to request the creation of a new Municipality
 */
@Data
@AllArgsConstructor
public class MunicipalityRequest {
    
    private Long adminID;
    private MunicipalityInputDTO municipalityDTO;

}
