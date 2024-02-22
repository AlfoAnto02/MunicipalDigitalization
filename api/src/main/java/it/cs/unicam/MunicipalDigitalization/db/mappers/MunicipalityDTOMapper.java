package it.cs.unicam.MunicipalDigitalization.db.mappers;

import it.cs.unicam.MunicipalDigitalization.api.model.Municipality;
import it.cs.unicam.MunicipalDigitalization.db.controllers.dto.MunicipalityDTO;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class MunicipalityDTOMapper implements Function<Municipality, MunicipalityDTO> {

    @Override
    public MunicipalityDTO apply(Municipality municipality) {
        return new MunicipalityDTO(
                municipality.getName(),
                municipality.getTerritory()
        );
    }
}
