package com.skr.airasia.orderapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@AllArgsConstructor
public enum OrderErrorCode {

    UNEXPECTED_ERROR(           "OR1001", "Unexpected Error Occurred"),
    REPOSITORY_ERROR(           "OR1002", "Repository Exception Occurred"),
    UNABLE_TO_PROCESS_ORDER(    "OR2001", "Unable to Process Order"),
    INTERNAL_SERVICE_ERROR(     "OR1003", "Internal Service Error");

    @Getter
    private String code;
    @Getter
    private String message;
}
