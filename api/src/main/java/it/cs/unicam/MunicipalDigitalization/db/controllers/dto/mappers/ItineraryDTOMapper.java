package it.cs.unicam.MunicipalDigitalization.db.controllers.dto.mappers;

import it.cs.unicam.MunicipalDigitalization.api.model.elements.AbstractContent;
import it.cs.unicam.MunicipalDigitalization.api.model.elements.AbstractItinerary;
import it.cs.unicam.MunicipalDigitalization.api.model.elements.AbstractMunicipalElement;
import it.cs.unicam.MunicipalDigitalization.api.util.ElementStatus;
import it.cs.unicam.MunicipalDigitalization.db.controllers.dto.output.ItineraryOutputDTO;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ItineraryDTOMapper implements Function<AbstractItinerary, ItineraryOutputDTO> {
    @Override
    public ItineraryOutputDTO apply(AbstractItinerary abstractItinerary) {
        return new ItineraryOutputDTO(
                abstractItinerary.getId(),
                abstractItinerary.getName(),
                abstractItinerary.getMunicipality().getName(),
                abstractItinerary.getTypes(),
                abstractItinerary.getDescription(),
                abstractItinerary.getCoordinate(),
                abstractItinerary.getPOIs()
                        .stream()
                        .map(AbstractMunicipalElement::getName)
                        .toList()
                        .toString(),
                abstractItinerary.getListOfContents()
                        .stream()
                        .filter(c -> c.getElementStatus().equals(ElementStatus.PUBLISHED))
                        .map(AbstractContent::getName)
                        .toList()
                        .toString(),
                abstractItinerary.getCreationDate()
        );
    }
}
