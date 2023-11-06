package com.nfyc.studyplanservice.repositories;

import com.nfyc.studyplanservice.model.domain.Course;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CourseRepository extends JpaRepository<Course, UUID> {
    Optional<Course> findById(UUID uuid);
    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, attributePaths = {"topics"})
    List<Course> findAll(Specification<Course> specification);
}
