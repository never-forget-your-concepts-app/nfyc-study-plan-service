package com.nfyc.studyplanservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Null;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseDTO implements Serializable {
    @Null(message = "CourseID should not be set by the user")
    private UUID courseID;
    @Length(min = 1, max = 100, message = "Course Name can only be between 1 and 100")
    private String courseName;
    private Boolean isRevised;
    private Timestamp lastRevised;
}
