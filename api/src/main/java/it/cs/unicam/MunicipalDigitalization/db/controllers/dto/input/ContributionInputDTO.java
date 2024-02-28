package it.cs.unicam.MunicipalDigitalization.db.controllers.dto.input;

public record ContributionInputDTO(
        String title,
        String description,
        String content,
        Long contributionContestId,
        Long authorId
) {
}
