package com.nfyc.studyplanservice.model.dto;

import com.nfyc.studyplanservice.mappers.TopicMapper;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.mapstruct.factory.Mappers;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TopicDTO implements Serializable {
    @Null(message = "Topic ID should not be set by client")
    private UUID topicID;
    @Length(min = 1, max = 100, message = "Topic Name can only be between 1 and 100")
    private String topicName;
    @Min(value = 1, message = "Minimum Priority should be at least 1")
    @Max(value = 5, message = "Maximum Priority can't exceed 5")
    private Integer priority;
    private Timestamp lastRevised;
    @NotNull
    private UUID courseID;
}
