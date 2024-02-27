package it.cs.unicam.MunicipalDigitalization.db.controllers.dto.output;

import it.cs.unicam.MunicipalDigitalization.api.util.ContestStatus;
import it.cs.unicam.MunicipalDigitalization.api.util.ContestType;

import java.util.List;

/**
 * DTO used to output a contest
 */
public record ContestOutputDTO(
    Long contest_id,
    String contest_name,
    String contest_description,
    ContestType contestType,
    ContestStatus contestStatus,
    List<String> contest_pois,
    List<String> contest_itineraries,
    int minParticipants,
    int actualNumberOfParticipants,
    String contest_author
){

}
