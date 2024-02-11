package com.nfyc.studyplanservice.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    NYFC_ERR_NOT_FOUND("The %s with the given %s : %s does not exist.", ErrorType.VALIDATION),
    NYFC_ERR_EXCEPTION("An unexpected error occurred during program execution. Please review the logs for more details or contact support for assistance.",ErrorType.APPLICATION),
    NYFC_ERR_REQUEST_INVALID("The request is invalid.", ErrorType.VALIDATION),
    NYFC_ERR_FILTER_INVALID("Given Condition %s is not an array", ErrorType.VALIDATION),
    NYFC_ERR_DATABASE_EXCEPTION("Error retrieving data: %s",ErrorType.APPLICATION),
    NYFC_ERR_NULL_EXCEPTION("%s is NULL.",ErrorType.VALIDATION);

    private final String message;
    private final ErrorType errorType;

    ErrorCode(String message, ErrorType errorType) {
        this.message = message;
        this.errorType = errorType;

    }

}


