package it.cs.unicam.MunicipalDigitalization.db.controllers.dto.input;

import it.cs.unicam.MunicipalDigitalization.api.util.ContentType;
import it.cs.unicam.MunicipalDigitalization.api.util.ElementStatus;

/**
 * Data Transfer Object for Content
 *
 * @param content_name name of the content
 * @param referredPOI_id referred POI id of the content
 * @param referredItinerary_id referred Itinerary id of the content
 * @param author_id author id of the content
 * @param contentType type of the content
 * @param content_field the content
 */
public record ContentInputDTO (
        String content_name,
        Long referredPOI_id,
        Long referredItinerary_id,
        Long author_id,
        ContentType contentType,
        String content_field
)
{
}
