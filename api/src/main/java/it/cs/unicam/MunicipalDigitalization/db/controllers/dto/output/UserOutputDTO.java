package it.cs.unicam.MunicipalDigitalization.db.controllers.dto.output;


import it.cs.unicam.MunicipalDigitalization.api.model.elements.AbstractContent;
import it.cs.unicam.MunicipalDigitalization.api.model.elements.AbstractItinerary;
import it.cs.unicam.MunicipalDigitalization.api.model.elements.AbstractPOI;
import it.cs.unicam.MunicipalDigitalization.api.util.UserRole;

import java.util.List;

public record UserOutputDTO (
    Long userID,
    String username,
    String password,
    String municipalityName,
    List<UserRole> userRoles
    //List<AbstractPOI> poisCreated
    //List<AbstractItinerary> itinerariesCreated,
    //List<AbstractContent> contentsCreated
)


{
}
