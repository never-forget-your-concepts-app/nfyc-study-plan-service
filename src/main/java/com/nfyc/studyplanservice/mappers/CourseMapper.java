package com.nfyc.studyplanservice.mappers;

import com.nfyc.studyplanservice.model.domain.Course;
import com.nfyc.studyplanservice.model.dto.CourseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CourseMapper {

    CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);
    CourseDTO courseToCourseDTO (Course course);
    @Mapping(target = "creationDate", ignore = true)
    @Mapping(target = "topics", ignore = true)
    Course courseDTOToCourse(CourseDTO courseDTO);
}
