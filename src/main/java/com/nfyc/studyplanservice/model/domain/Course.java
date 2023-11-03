package com.nfyc.studyplanservice.model.domain;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

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
    @Type(type="org.hibernate.type.UUIDCharType")
    @Column(length = 36, columnDefinition = "varchar(36)", updatable = false, nullable = false)
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

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Topic> topics = new HashSet<>();

    /**
     * Adds topic to a course and syncs the bidirectional mapping.
     * @param topic
     *
     */
    public void addTopic(Topic topic) {
        //For Builder Pattern
        if (topics == null) {
            topics = new HashSet<>();
        }
        topic.setCourse(this);
        this.topics.add(topic);
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
