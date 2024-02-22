package it.cs.unicam.MunicipalDigitalization.db.controllers.dto;

import it.cs.unicam.MunicipalDigitalization.api.util.ContentType;
import it.cs.unicam.MunicipalDigitalization.api.util.ElementStatus;

/**
 * Data Transfer Object for Content
 *
 * @param name name of the content
 * @param referredPOI referred POI id of the content
 * @param referredItinerary referred Itinerary id of the content
 * @param author author id of the content
 * @param type type of the content
 * @param description description of the content
 * @param link link of the content
 * @param photo photo of the content
 */
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
