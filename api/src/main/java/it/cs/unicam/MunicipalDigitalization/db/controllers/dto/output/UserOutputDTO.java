package it.cs.unicam.MunicipalDigitalization.db.controllers.dto.output;


import it.cs.unicam.MunicipalDigitalization.api.util.UserRole;

import java.util.List;

public record UserOutputDTO(
        Long user_id,
        String username,
        String password,
        String municipality_name,
        List<UserRole> user_roles
        //List<AbstractPOI> poisCreated
        //List<AbstractItinerary> itinerariesCreated,
        //List<AbstractContent> contentsCreated
) {
}
