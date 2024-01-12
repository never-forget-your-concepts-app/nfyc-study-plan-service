package com.nfyc.studyplanservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class NfycExceptionHandler{

    @ExceptionHandler(NyfcException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody NyfcErrorResponse nyfcException(NyfcException exception) {
        return exception.getError();

    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public NyfcErrorResponse handleInternalServerError(Exception exception) {
        ErrorCode errorCode=ErrorCode.NYFC_ERR_EXCEPTION;
        NyfcErrorResponse errorResponse=new NyfcErrorResponse(errorCode.toString(),errorCode.getMessage(),ErrorType.APPLICATION.toString());
        return errorResponse;
    }
}
