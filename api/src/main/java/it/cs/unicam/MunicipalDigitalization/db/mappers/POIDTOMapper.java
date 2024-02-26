package it.cs.unicam.MunicipalDigitalization.db.mappers;

import it.cs.unicam.MunicipalDigitalization.api.model.elements.AbstractPOI;
import it.cs.unicam.MunicipalDigitalization.db.controllers.dto.output.POIOutputDTO;
import org.springframework.stereotype.Service;

import java.util.function.Function;

/**
 * This class is used to map a POI object to a POIDTO object.
 * It implements the Function interface.
 */
@Service
public class POIDTOMapper implements Function<AbstractPOI, POIOutputDTO> {
    @Override
    public POIOutputDTO apply(AbstractPOI poi) {
        return new POIOutputDTO(
                poi.getId(),
                poi.getName(),
                poi.getMunicipality().getName(),
                poi.getType(),
                poi.getCoordinate(),
                poi.getCreationDate()
        );
    }
}
