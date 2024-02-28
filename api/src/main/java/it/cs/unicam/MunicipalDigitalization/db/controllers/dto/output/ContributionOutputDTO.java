package it.cs.unicam.MunicipalDigitalization.db.controllers.dto.output;

public record ContributionOutputDTO(
        Long contribution_id,
        String title,
        String description,
        String content,
        String contest_name,
        String author_name,
        int likeCounter

) {
}
