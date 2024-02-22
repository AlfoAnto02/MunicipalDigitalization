package it.cs.unicam.MunicipalDigitalization.api.util;

/**
 * This enum represents the possible status of the POI that are Pending and Published
 */
public enum ElementStatus {
    /**
     * The status of the POI is pending, and it will not be visible to the users.
     * Only the curator can see it.
     */
    PENDING,

    /**
     * The status of the POI is published, and it will be visible to the users.
     */
    PUBLISHED,
}
