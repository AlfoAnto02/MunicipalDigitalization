package it.cs.unicam.MunicipalDigitalization.db.controllers.dto.mappers;

import it.cs.unicam.MunicipalDigitalization.api.model.elements.AbstractMunicipalElement;
import it.cs.unicam.MunicipalDigitalization.api.model.elements.ContributionContest;
import it.cs.unicam.MunicipalDigitalization.db.controllers.dto.output.ContestOutputDTO;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ContestDTOMapper implements Function<ContributionContest, ContestOutputDTO> {

    @Override
    public ContestOutputDTO apply(ContributionContest contributionContest) {
        return new ContestOutputDTO(
                contributionContest.getId(),
                contributionContest.getContestName(),
                contributionContest.getContestDescription(),
                contributionContest.getContestType(),
                contributionContest.getContestStatus(),
                contributionContest.getPois().stream().map(AbstractMunicipalElement::getName).toList(),
                contributionContest.getItineraries().stream().map(AbstractMunicipalElement::getName).toList(),
                contributionContest.getMinParticipants(),
                contributionContest.getActualNumberOfParticipants(),
                contributionContest.getAuthor().getName()
        );
    }
}
