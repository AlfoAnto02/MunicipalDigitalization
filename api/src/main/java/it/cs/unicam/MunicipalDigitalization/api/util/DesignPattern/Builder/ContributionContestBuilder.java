package it.cs.unicam.MunicipalDigitalization.api.util.DesignPattern.Builder;

import it.cs.unicam.MunicipalDigitalization.api.model.Municipality;
import it.cs.unicam.MunicipalDigitalization.api.model.elements.AbstractItinerary;
import it.cs.unicam.MunicipalDigitalization.api.model.elements.AbstractPOI;
import it.cs.unicam.MunicipalDigitalization.api.model.elements.ContributionContest;
import it.cs.unicam.MunicipalDigitalization.api.model.users.AbstractAuthenticatedUser;
import it.cs.unicam.MunicipalDigitalization.api.util.ContestType;
import it.cs.unicam.MunicipalDigitalization.api.util.InvitationType;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ContributionContestBuilder implements IContributionContestBuilder{
    private String title;
    private String description;
    private Municipality municipality;
    private AbstractAuthenticatedUser author;
    private InvitationType invitationType;
    private int minParticipants;
    private ContestType contestType;
    private List<AbstractPOI> pois;
    private List<AbstractItinerary> itineraries;

    public ContributionContestBuilder() {
        this.pois = new ArrayList<>();
        this.itineraries = new ArrayList<>();
    }

    @Override
    public void setName(String title) {
        this.title = title;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public void setMunicipality(Municipality municipality) {
        this.municipality = municipality;
    }

    @Override
    public void setAuthor(AbstractAuthenticatedUser author) {
        this.author = author;
    }

    @Override
    public void setInvitationType(InvitationType invitationType) {
        this.invitationType = invitationType;
    }

    @Override
    public void setMinParticipants(int minParticipants) {
        this.minParticipants = minParticipants;
    }

    @Override
    public void setContestType(ContestType contestType) {
        this.contestType = contestType;
    }

    @Override
    public void setPOIs(List<AbstractPOI> pois) {
        this.pois.addAll(pois);
    }

    @Override
    public void setItineraries(List<AbstractItinerary> itineraries) {
        this.itineraries.addAll(itineraries);
    }

    @Override
    public ContributionContest build() {
        return new ContributionContest(title, description, invitationType, minParticipants, contestType, author,
                itineraries, pois, municipality);
    }
}
