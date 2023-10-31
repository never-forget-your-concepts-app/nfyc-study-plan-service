package com.nfyc.studyplanservice.model.dto;

import com.nfyc.studyplanservice.mappers.TopicMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mapstruct.factory.Mappers;


import java.sql.Timestamp;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TopicDTO {

    TopicMapper INSTANCE = Mappers.getMapper(TopicMapper.class);

    private UUID topicID;
    private String topicName;
    private Integer priority;
    private Timestamp lastRevised;
    private UUID courseID;
}
