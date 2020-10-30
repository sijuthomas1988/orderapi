package com.skr.airasia.orderapi.optimisticlocking;

public class OptimisticLockException extends Exception {

    public OptimisticLockException(String description) {
        super(description);
    }
}
