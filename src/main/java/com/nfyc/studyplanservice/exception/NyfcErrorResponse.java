package com.nfyc.studyplanservice.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NyfcErrorResponse {

    @NotNull
    String errorCode;
    @NotNull
    String message;
    @NotNull
    String errorType;

}
