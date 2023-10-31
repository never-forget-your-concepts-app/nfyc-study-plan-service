package com.nfyc.studyplanservice.model.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(generator = "uuid-hibernate-generator")
    @GenericGenerator(name = "uuid-hibernate-generator", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID courseID;

    @Column(name = "course_name")
    private String courseName;

    @Column(name = "is_revised")
    private Boolean isRevised;

    @Column(name = "creation_date")
    @CreationTimestamp
    private Timestamp creationDate;

    @Column(name = "last_revised")
    private Timestamp lastRevised;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Topic> topics = new HashSet<>();

    /**
     * Adds topic to a course and syncs the bidirectional mapping.
     * @param topic
     *
     */
    public void addTopic(Topic topic) {
        this.topics.add(topic);
        topic.setCourse(this);
    }

    /**
     * Removes the topic from a course and updates the bidirectional mapping.
     * @param topic
     */
    public void removeTopic(Topic topic) {
        this.topics.remove(topic);
        topic.setCourse(null);
    }
}
