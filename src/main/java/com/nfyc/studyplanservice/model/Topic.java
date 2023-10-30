package com.nfyc.studyplanservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "topics")
public class Topic {

    @Id
    @GeneratedValue(generator = "uuid-hibernate-generator")
    @GenericGenerator(name = "uuid-hibernate-generator", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID topicID;

    @Column(name = "topic_name")
    private String topicName;

    @Column(name = "priority")
    private Integer priority;

    @Column(name = "last_revised")
    private Timestamp lastRevised;

    @Column(name = "creation_date")
    @CreationTimestamp
    private Timestamp creationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;
}
