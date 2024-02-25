package it.cs.unicam.MunicipalDigitalization.db.controllers.dto.input;

import it.cs.unicam.MunicipalDigitalization.api.util.ContentType;
import it.cs.unicam.MunicipalDigitalization.api.util.ElementStatus;

/**
 * Data Transfer Object for Content
 *
 * @param name name of the content
 * @param referredPOI_id referred POI id of the content
 * @param referredItinerary_id referred Itinerary id of the content
 * @param author_id author id of the content
 * @param contentType type of the content
 * @param description description of the content
 * @param link link of the content
 * @param photo photo of the content
 */
public record ContentInputDTO (
        String name,
        Long referredPOI_id,
        Long referredItinerary_id,
        Long author_id,
        ContentType contentType,
        String description,
        String link,
        String photo
)
{
}
