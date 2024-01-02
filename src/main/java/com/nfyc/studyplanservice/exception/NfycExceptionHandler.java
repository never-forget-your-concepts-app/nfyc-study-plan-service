package com.nfyc.studyplanservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class NfycExceptionHandler{

    @ExceptionHandler(NyfcException.class)
    public @ResponseBody
    NyfcErrorResponse nyfcException(ErrorCode errorCode) {
        NyfcException nyfcException=new NyfcException(errorCode);
        return nyfcException.getError();

    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public NyfcErrorResponse handleInternalServerError() {
        ErrorCode errorCode=ErrorCode.NYFC_ERR_EXCEPTION;
        NyfcErrorResponse errorResponse=new NyfcErrorResponse(errorCode.toString(),errorCode.getMessage(),ErrorType.APPLICATION.toString());
        return errorResponse;
    }
}
