package it.cs.unicam.MunicipalDigitalization.api.util.DesignPattern.Builder;

import it.cs.unicam.MunicipalDigitalization.api.model.Municipality;
import it.cs.unicam.MunicipalDigitalization.api.model.elements.AbstractItinerary;
import it.cs.unicam.MunicipalDigitalization.api.model.elements.AbstractPOI;
import it.cs.unicam.MunicipalDigitalization.api.model.elements.ContributionContest;
import it.cs.unicam.MunicipalDigitalization.api.model.users.AbstractAuthenticatedUser;
import it.cs.unicam.MunicipalDigitalization.api.util.ContestType;
import it.cs.unicam.MunicipalDigitalization.api.util.InvitationType;

import java.util.List;

public interface IContributionContestBuilder {

    /**
     * Sets the title of the contribution contest.
     *
     * @param title the title of the contribution contest.
     * @return the contribution contest builder.
     */
    void setName(String title);

    /**
     * Sets the description of the contribution contest.
     *
     * @param description the description of the contribution contest.
     * @return the contribution contest builder.
     */
    void setDescription(String description);

    /**
     * Sets the municipality of the contribution contest.
     *
     * @param municipality the municipality of the contribution contest.
     */
    void setMunicipality(Municipality municipality);


    /**
     * Sets the author of the contribution contest.
     *
     * @param author the author of the contribution contest.
     */
    void setAuthor(AbstractAuthenticatedUser author);

    /**
     * Sets the invitation type of the contribution contest.
     *
     * @param invitationType the invitation type of the contribution contest.
     */
    void setInvitationType(InvitationType invitationType);

    /**
     * Sets the minimum number of participants for the contribution contest.
     *
     * @param minParticipants the minimum number of participants for the contribution contest.
     */
    void setMinParticipants(int minParticipants);

    /**
     * Sets the type of the contribution contest.
     *
     * @param contestType the type of the contribution contest.
     */
    void setContestType(ContestType contestType);

    /**
     * Sets the Pois of the contribution contest.
     *
     * @param pois the Pois of the contribution contest.
     */
    void setPOIs(List<AbstractPOI> pois);

    /**
     * Sets the itineraries of the contribution contest.
     *
     * @param itineraries the itineraries of the contribution contest.
     */
    void setItineraries(List<AbstractItinerary> itineraries);

    /**
     * Builds the contribution contest.
     *
     * @return the contribution contest.
     */
    ContributionContest build();
}
