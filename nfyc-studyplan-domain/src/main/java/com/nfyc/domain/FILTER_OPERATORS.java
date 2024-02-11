package com.nfyc.domain;

import java.util.HashSet;
import java.util.Set;

public enum FILTER_OPERATORS {
    // IN (Set.of("course.courseID", "topic.topicID")),
    LIKE (Set.of("course.courseName", "topic.topicName")),
    GREATERTHAN (Set.of("topic.priority")),
    LESSTHAN (Set.of("topic.priority")),
    EQUALTO (Set.of("topic.priority"));
    private Set<String> allowedOperationSet = new HashSet<>();
    FILTER_OPERATORS(Set<String> allowedOperations) {
        this.allowedOperationSet = allowedOperations;
    }
    public Set<String> getAllowedOperations() {
        return allowedOperationSet;
    }
}
