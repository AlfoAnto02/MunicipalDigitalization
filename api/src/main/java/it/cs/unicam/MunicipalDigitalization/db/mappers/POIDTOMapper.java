package it.cs.unicam.MunicipalDigitalization.db.mappers;

import it.cs.unicam.MunicipalDigitalization.api.model.elements.AbstractPOI;
import it.cs.unicam.MunicipalDigitalization.db.controllers.dto.POIDTO;
import org.springframework.stereotype.Service;

import java.util.function.Function;

/**
 * This class is used to map a POI object to a POIDTO object.
 * It implements the Function interface.
 */
@Service
public class POIDTOMapper implements Function<AbstractPOI, POIDTO> {
    @Override
    public POIDTO apply(AbstractPOI poi) {
        return new POIDTO(
                poi.getName(),
                poi.getPOIType(),
                poi.getAuthor().getId(),
                poi.getMunicipality().getId(),
                poi.getCoordinate(),
                poi.getElementStatus()
        );
    }
}
