package com.nfyc.studyplanservice.model.dto;

import com.nfyc.studyplanservice.model.domain.FILTER_OPERATORS;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
public class FilterRequestDTO {
    @NotNull
    @Pattern(regexp = "^(course.courseID)|(course.courseName)|(topic.topicID)|(topic.topicName)|(topic.priority)$",
            message = "The provided label is not supported", flags = Pattern.Flag.CASE_INSENSITIVE)
    String label;

    @NotNull
    FILTER_OPERATORS operator;

    @NotNull
    String value;

    @AssertTrue(message = "The provided operation is not supported on the label")
    public boolean isOperationValidOnLabel() {
        return this.operator.getAllowedOperations().contains(this.label);
    }
}
