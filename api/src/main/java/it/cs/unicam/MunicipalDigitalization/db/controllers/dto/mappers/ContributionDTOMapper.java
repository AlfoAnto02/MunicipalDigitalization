package it.cs.unicam.MunicipalDigitalization.db.controllers.dto.mappers;

import it.cs.unicam.MunicipalDigitalization.api.model.elements.Contribution;
import it.cs.unicam.MunicipalDigitalization.db.controllers.dto.output.ContributionOutputDTO;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ContributionDTOMapper implements Function<Contribution, ContributionOutputDTO> {

    @Override
    public ContributionOutputDTO apply(Contribution contribution) {
        return new ContributionOutputDTO(
                contribution.getId(),
                contribution.getTitle(),
                contribution.getDescription(),
                contribution.getContribution(),
                contribution.getContest().getContestName(),
                contribution.getAuthor().getName(),
                contribution.getTotalVotes()
        );
    }
}
