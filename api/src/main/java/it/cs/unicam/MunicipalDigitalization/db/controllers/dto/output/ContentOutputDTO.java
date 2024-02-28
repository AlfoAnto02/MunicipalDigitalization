package it.cs.unicam.MunicipalDigitalization.db.controllers.dto.output;

import it.cs.unicam.MunicipalDigitalization.api.util.ContentType;

public record ContentOutputDTO(
        Long content_id,
        String content_name,
        ContentType content_type,
        String content_field,
        String author
) {
}
