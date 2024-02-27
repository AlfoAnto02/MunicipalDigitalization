package it.cs.unicam.MunicipalDigitalization.db.controllers.dto.input;

import it.cs.unicam.MunicipalDigitalization.api.util.ContestType;
import it.cs.unicam.MunicipalDigitalization.api.util.InvitationType;

import java.util.List;

/**
 * DTO used to upload a contest
 */
public record ContestInputDTO (
    String contest_name,
    String contest_description,
    Long contest_author_id,
    InvitationType  contest_invitationType,
    ContestType contestType,
    List<Long> contest_pois,
    List<Long> contest_itineraries,
    int minParticipants
){

}