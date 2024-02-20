package it.cs.unicam.MunicipalDigitalization.api.util;

/**
 * This enum represents the possible status of the Contribution Contest
 */
public enum ContestStatus {
    /**
     * The contribution Contest is join able but not yet started
     */
    OPEN,
    /**
     * The contribution contest is started and is join able. The participants can
     * submit their contribution
     */
    ON_GOING,
    /**
     * The contribution contest is closed and the participants can no longer submit.
     * In this case the contribution with more likes will win the Contest.
     */
    CLOSED,
}
