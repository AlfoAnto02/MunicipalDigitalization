package it.cs.unicam.MunicipalDigitalization.api.util.controllers.dto;

import it.cs.unicam.MunicipalDigitalization.api.util.ContentType;
import it.cs.unicam.MunicipalDigitalization.api.util.ElementStatus;

public record ContentDTO (
        String name,
        Long referredPOI,
        Long referredItinerary,
        Long author,
        ContentType type,
        String description,
        String link,
        String photo
)
{
}
