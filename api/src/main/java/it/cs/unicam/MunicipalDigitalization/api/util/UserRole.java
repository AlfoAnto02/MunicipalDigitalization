package it.cs.unicam.MunicipalDigitalization.api.util;

/**
 * This class represents the possible Role that a User can Have in this application
 */
public enum UserRole {
    /**
     * A tourist is a user that is not registered in the system
     */
    TOURIST,
    /**
     * An authenticated tourist is a user that is registered in the system. He can participate to Contribution Contests
     */
    AUTHENTICATED_TOURIST,
    /**
     * A contributor is a user that can contribute to the system by adding new content such as POIs, Contents and itineraries
     * All the contents are Pending.
     */
    CONTRIBUTOR,

    /**
     * An authorized contributor is a user that can contribute to the system by adding new content such as POIs, Contents and itineraries
     * All the contents are immediately visible to the users.
     */
    AUTHORIZED_CONTRIBUTOR,

    /**
     * An animator is a user that can create and manage Contribution Contests
     */
    ANIMATOR,

    /**
     * A curator is an AuthorizedContributor that can also approve or reject the contents added by the Contributors.
     */
    CURATOR,

    /**
     * A Platform_Gestor is a user that can manage the system. He can create and manage the users and the roles.
     * He also has the responsibility to create Municipalities
     */
    PLATFORM_GESTOR,
}
