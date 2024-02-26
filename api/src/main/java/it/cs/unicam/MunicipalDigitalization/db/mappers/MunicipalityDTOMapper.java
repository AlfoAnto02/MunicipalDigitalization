package it.cs.unicam.MunicipalDigitalization.db.mappers;

import it.cs.unicam.MunicipalDigitalization.api.model.Municipality;
import it.cs.unicam.MunicipalDigitalization.api.model.users.AbstractAuthenticatedUser;
import it.cs.unicam.MunicipalDigitalization.db.controllers.dto.output.MunicipalityOutputDTO;
import org.springframework.stereotype.Service;

import java.util.function.Function;

/**
 * This class is used to map a Municipality object to a MunicipalityDTO object.
 * It implements the Function interface.
 */
@Service
public class MunicipalityDTOMapper implements Function<Municipality, MunicipalityOutputDTO> {

    @Override
    public MunicipalityOutputDTO apply(Municipality municipality) {
        return new MunicipalityOutputDTO(
                municipality.getId(),
                municipality.getName(),
                municipality.getTerritory(),
                municipality.getPOIList().stream().map(new POIDTOMapper()).toList(),
                municipality.getListOfItineraries().stream().map(new ItineraryDTOMapper()).toList(),
                municipality.getListOfIUsers().stream().map(AbstractAuthenticatedUser::getName).toList()
        );
    }
}
