package com.nfyc.studyplanservice.model.domain;

import com.nfyc.studyplanservice.model.dto.FilterRequestDTO;
import com.nfyc.studyplanservice.model.dto.FilterRequestListDTO;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class CourseSpecifications {

    public static Specification<Course> applyCourseFilters(FilterRequestListDTO filterRequestListDTO){
        return (root, query, criteriaBuilder) -> {
            Join<Course, Topic> topicJoin = root.join("topics", JoinType.INNER);
            List<Predicate> predicateList = new ArrayList<>();
            for (FilterRequestDTO filter : filterRequestListDTO.getFilters()) {
                if (filter.getOperator().equals(FILTER_OPERATORS.LIKE)) {
                    predicateList.add(filter.getLabel().equals("course.courseName") ?
                            criteriaBuilder.like(criteriaBuilder.lower(topicJoin.get("courseName")), filter.getValue().toLowerCase() + "%") :
                            criteriaBuilder.like(criteriaBuilder.lower(topicJoin.get("topicName")), filter.getValue().toLowerCase() + "%"));
                } else if (filter.getOperator().equals(FILTER_OPERATORS.LESSTHAN)) {
                    predicateList.add(criteriaBuilder.lessThan(topicJoin.get("priority"), Integer.valueOf(filter.getValue())));
                } else if (filter.getOperator().equals(FILTER_OPERATORS.GREATERTHAN)) {
                    predicateList.add(criteriaBuilder.greaterThan(topicJoin.get("priority"), Integer.valueOf(filter.getValue())));
                } else if (filter.getOperator().equals(FILTER_OPERATORS.EQUALTO)) {
                    predicateList.add(criteriaBuilder.equal(topicJoin.get("priority"), Integer.valueOf(filter.getValue())));
                }
            }

            if (filterRequestListDTO.getFilterCondition().equals("and")){
                return criteriaBuilder.and(predicateList.toArray(new Predicate[0]));
            } else {
                return criteriaBuilder.or(predicateList.toArray(new Predicate[0]));
            }

        };

    }
}
