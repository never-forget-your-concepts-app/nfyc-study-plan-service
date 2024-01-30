
CREATE TABLE Courses
(
    courseId      CHAR(36) PRIMARY KEY,
    course_name    VARCHAR(50) NOT NULL,
    is_revised    BOOLEAN,
    creation_date  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_revised TIMESTAMP
);

CREATE TABLE Topics
(
    topicId      CHAR(36) PRIMARY KEY,
    topic_name      VARCHAR(50) ,
    priority       INTEGER NOT NULL,
    creation_date  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_revised   TIMESTAMP,
    course_id      CHAR(36),
    FOREIGN KEY (course_id) REFERENCES Courses(courseId)
);
