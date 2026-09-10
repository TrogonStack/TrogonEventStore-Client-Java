package io.trogonstack.eventstore.client;

/**
 * Specifies the direction of a read operation.
 */
public enum Direction {
    /**
     * Read in the forward direction.
     */
    Forwards,
    /**
     * Read in the backward direction.
     */
    Backwards
}
