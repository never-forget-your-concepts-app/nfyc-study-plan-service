package com.nfyc.studyplanservice.model.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FilterRequestListDTO {

    @JsonIgnore
    private String filterCondition;

    @JsonProperty("and")
    @JsonAlias("or")
    @Valid
    private List<FilterRequestDTO> filters;
}
