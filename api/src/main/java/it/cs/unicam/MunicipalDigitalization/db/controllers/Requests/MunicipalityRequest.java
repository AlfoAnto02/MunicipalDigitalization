package it.cs.unicam.MunicipalDigitalization.db.controllers.Requests;

import it.cs.unicam.MunicipalDigitalization.db.controllers.dto.MunicipalityDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MunicipalityRequest {
    private Long adminID;
    private MunicipalityDTO municipalityDTO;

    public MunicipalityRequest(Long adminID, MunicipalityDTO municipalityDTO) {
        this.adminID = adminID;
        this.municipalityDTO = municipalityDTO;
    }


}
