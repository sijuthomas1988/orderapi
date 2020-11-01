package com.skr.airasia.orderapi.optimisticlocking;

/**
 * Optimistic Exception class
 */
public class OptimisticLockException extends Exception {

    /**
     * Constructor for optimistic exception
     *
     * @param description
     *     description of the exception occured
     */
    public OptimisticLockException(String description) {
        super(description);
    }
}
