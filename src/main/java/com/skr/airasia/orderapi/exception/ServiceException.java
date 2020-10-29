package com.skr.airasia.orderapi.exception;

import lombok.Getter;

public class ServiceException  extends Exception {

    /** Status code */
    @Getter
    private String code;

    /** error message */
    @Getter
    private String message;

    @Getter
    private String status;

    /**
     * Constructor
     *
     * @param code
     *     status code
     * @param message
     *     error message
     */
    public ServiceException(String code, String status, String message) {
        super(message);
        this.code = code;
        this.status = status;
    }

    /**
     * Constructor
     *
     * @param message
     *     error message
     */
    public ServiceException(String message) {
        super(message);
        this.message = message;
    }
}

