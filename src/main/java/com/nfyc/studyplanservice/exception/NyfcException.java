package com.nfyc.studyplanservice.exception;

public class NyfcException extends Exception{

    NyfcErrorResponse errorResponse;
    public NyfcException(ErrorCode errorCode, String... params){
        errorResponse=new NyfcErrorResponse(errorCode.toString(),String.format(errorCode.getMessage(),params),errorCode.getErrorType().toString());
    }
    @Override
    public String getMessage() {
        return errorResponse.getMessage();
    }

    public NyfcErrorResponse getError(){
        return errorResponse;
    }
}
