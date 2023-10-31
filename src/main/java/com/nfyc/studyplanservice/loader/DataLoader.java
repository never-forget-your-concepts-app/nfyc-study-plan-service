package com.nfyc.studyplanservice.loader;

import com.nfyc.studyplanservice.model.domain.Course;
import com.nfyc.studyplanservice.model.domain.Topic;
import com.nfyc.studyplanservice.repositories.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.HashSet;


@Component
@RequiredArgsConstructor
@ComponentScan("com.nfyc.studyplanservice.repositories")
public class DataLoader implements CommandLineRunner {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public void run(String... args) throws Exception {
        loadData();
    }

    private void loadData() {
        if (courseRepository.count() == 0L) {
            Course mathsCourse = Course.builder().courseName("Mathematics").isRevised(false)
                    .lastRevised(new Timestamp(System.currentTimeMillis())).build();

            Topic algebraTopic = Topic.builder().topicName("Algebra").
                    lastRevised(new Timestamp(System.currentTimeMillis())).priority(2).build();
            Topic geometryTopic = Topic.builder().topicName("Geometry").
                    lastRevised(new Timestamp(System.currentTimeMillis())).priority(5).build();

            mathsCourse.addTopic(algebraTopic);
            mathsCourse.addTopic(geometryTopic);

            courseRepository.save(mathsCourse);
        }
    }
}
