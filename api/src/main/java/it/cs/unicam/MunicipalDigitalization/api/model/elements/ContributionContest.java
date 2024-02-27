package it.cs.unicam.MunicipalDigitalization.api.model.elements;

import it.cs.unicam.MunicipalDigitalization.api.model.Municipality;
import it.cs.unicam.MunicipalDigitalization.api.model.users.AbstractAuthenticatedUser;
import it.cs.unicam.MunicipalDigitalization.api.util.ContestStatus;
import it.cs.unicam.MunicipalDigitalization.api.util.ContestType;
import it.cs.unicam.MunicipalDigitalization.api.util.InvitationType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(
        name = "ContributionContest",
        uniqueConstraints = {
                @UniqueConstraint(name = "Identification",
                        columnNames = "id")
            }
        )
public class ContributionContest implements IContributionContest{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the contest.
     */
    @Column(name = "ContestName")
    private String contestName;

    /**
     * The description of the contest.
     */
    @Column(name = "ContestDescription")
    private String contestDescription;

    /**
     * The type of invitation for the contest.
     */
    @Column(name = "InvitationType", nullable = false)
    private InvitationType invitationType;

    /**
     * The minimum number of participants for the contest set by the Animator
     */
    @Column(name = "MinParticipants", nullable = false)
    private int minParticipants;

    /**
     * The type of contest.
     */
    @Column(name = "ContestType", nullable = false)
    private ContestType contestType;

    /**
     * The author of the contest. It has the Role of an Animator.
     */
    @JoinColumn(name = "Author", nullable = false)
    @ManyToOne
    private AbstractAuthenticatedUser author;

    @Column(name = "ContestStatus", nullable = false)
    private ContestStatus contestStatus;

    /**
     * The list of itineraries that are part of the contest.
     */

    @ManyToMany(mappedBy = "contest")
    private List<AbstractItinerary> itineraries;

    /**
     * The list of POIs that are part of the contest.
     */
    @ManyToMany(mappedBy = "contest")
    private List<AbstractPOI> pois;

    /**
     * The list of participants to the contest.
     */
    @ManyToMany(mappedBy = "contestsParticipated")
    private List<AbstractAuthenticatedUser> participants;

    /**
     * The municipality where the contest is taking place.
     */
    @JoinColumn(name = "Municipality", nullable = false)
    @ManyToOne
    private Municipality municipality;

    /**
     * The date of creation of the contest.
     */
    @Column(name = "CreationDate", nullable = false)
    private LocalDateTime creationDate;


    /**
     * Constructor for the ContributionContest class used by the Builder.
     *
     * @param contestName The name of the contest.
     * @param contestDescription The description of the contest.
     * @param invitationType The type of invitation for the contest.
     * @param minParticipants  The minimum number of participants for the contest set by the Animator
     * @param contestType The type of contest.
     * @param author The author of the contest. It has the Role of an Animator.
     * @param itineraries The list of itineraries that are part of the contest.
     * @param pois The list of POIs that are part of the contest.
     * @param municipality The municipality where the contest is taking place.
     */
    public ContributionContest(String contestName, String contestDescription, InvitationType invitationType,
                               int minParticipants, ContestType contestType, AbstractAuthenticatedUser author,
                               List<AbstractItinerary> itineraries, List<AbstractPOI> pois, Municipality municipality) {
        this.contestName = contestName;
        this.contestDescription = contestDescription;
        this.invitationType = invitationType;
        this.minParticipants = minParticipants;
        this.contestType = contestType;
        this.author = author;
        this.itineraries = itineraries;
        this.pois = pois;
        this.municipality = municipality;
        this.creationDate = LocalDateTime.now();
        this.contestStatus = ContestStatus.OPEN;
    }

    public ContributionContest() {
        this.itineraries = new ArrayList<>();
        this.pois = new ArrayList<>();
        this.creationDate = LocalDateTime.now();
    }

    /**
     * Method to add a POI to the contest.
     *
     * @param poi The POI to be added to the contest.
     */
    @Override
    public void addPOI(AbstractPOI poi) {
        this.pois.add(poi);
    }

    /**
     * Method to add an itinerary to the contest.
     *
     * @param itinerary The itinerary to be added to the contest.
     */
    @Override
    public void addItinerary(AbstractItinerary itinerary) {
        this.itineraries.add(itinerary);

    }

    /**
     * Method to add a participant to the contest.
     *
     * @param userById The user to be added to the contest.
     */
    public void addParticipant(AbstractAuthenticatedUser userById) {
        this.participants.add(userById);
    }

    public int getActualNumberOfParticipants() {
        return this.participants.size();
    }
}
