package com.nfyc.studyplanservice.repositories;

import com.nfyc.studyplanservice.model.domain.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TopicRepository extends JpaRepository<Topic, UUID> {
    Optional<Topic> findById(UUID topicID);
    List<Topic> findTopicsByCourse_CourseID(UUID courseId);
}
