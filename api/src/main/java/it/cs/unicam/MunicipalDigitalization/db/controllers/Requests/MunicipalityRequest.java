package it.cs.unicam.MunicipalDigitalization.db.controllers.Requests;


import it.cs.unicam.MunicipalDigitalization.db.controllers.dto.input.MunicipalityInputDTO;
import lombok.Getter;
import lombok.Setter;

/**
 * Class used to request the creation of a new Municipality
 */
@Getter
@Setter
public class MunicipalityRequest {
    private Long adminID;
    private MunicipalityInputDTO municipalityDTO;

    /**
     * Constructor for the MunicipalityRequest
     *
     * @param adminID id of the admin that is creating the municipality
     * @param municipalityDTO DTO of the municipality to be created
     */
    public MunicipalityRequest(Long adminID, MunicipalityInputDTO municipalityDTO) {
        this.adminID = adminID;
        this.municipalityDTO = municipalityDTO;
    }


}
