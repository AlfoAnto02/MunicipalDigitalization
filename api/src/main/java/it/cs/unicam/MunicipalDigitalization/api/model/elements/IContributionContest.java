package it.cs.unicam.MunicipalDigitalization.api.model.elements;

import it.cs.unicam.MunicipalDigitalization.api.model.Municipality;
import it.cs.unicam.MunicipalDigitalization.api.model.users.AbstractAuthenticatedUser;
import it.cs.unicam.MunicipalDigitalization.api.model.users.Animator;
import it.cs.unicam.MunicipalDigitalization.api.util.ContestStatus;
import it.cs.unicam.MunicipalDigitalization.api.util.ContestType;
import it.cs.unicam.MunicipalDigitalization.api.util.InvitationType;

public interface IContributionContest {

    /**
     * This method returns the name of the contribution contest.
     *
     * @return The name of the contribution contest.
     */
    String getContestName();

    /**
     * This method returns the description of the contribution contest.
     *
     * @return The description of the contribution contest.
     */
    String getContestDescription();

    /**
     * This method returns the list of POIs of the contribution contest.
     *
     * @return The list of POIs of the contribution contest.
     */
    void addPOI(AbstractPOI poi);

    /**
     * This method returns the list of itineraries of the contribution contest.
     *
     * @return The list of itineraries of the contribution contest.
     */
    void addItinerary(AbstractItinerary itinerary);

    /**
     * This method returns the InvitationType of the contribution contest. It can be public or invite Only
     *
     * @return The invitationType
     */
    InvitationType getInvitationType();

    /**
     * This method returns the minimum number of participants of the contribution contest.
     *
     * @return The minimum number of participants of the contribution contest.
     */
    int getMinParticipants();

    /**
     * This method returns the ContestType of the contribution contest.
     *
     * @return The ContestType of the contribution contest.
     */
    ContestType getContestType();

    /**
     * This method returns the author of the contribution contest.
     *
     * @return The animator of the contribution contest.
     */
    AbstractAuthenticatedUser getAuthor();

    /**
     * This method returns the municipality of the contribution contest.
     *
     * @return The municipality of the contribution contest.
     */
    Municipality getMunicipality();

    /**
     * This method returns the status of the contribution contest.
     *
     * @return The status of the contribution contest.
     */
    ContestStatus getContestStatus();

}
