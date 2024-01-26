package com.nfyc.studyplanservice.loader;

import com.nfyc.domain.Course;
import com.nfyc.domain.Topic;
import com.nfyc.studyplanservice.repositories.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;


@Component
@RequiredArgsConstructor
@ComponentScan("com.nfyc.studyplanservice")
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

            Course historyCourse = Course.builder().courseName("History").isRevised(true)
                    .lastRevised(new Timestamp(System.currentTimeMillis())).build();

            Topic ancientHistoryTopic = Topic.builder().topicName("Ancient History")
                    .lastRevised(new Timestamp(System.currentTimeMillis())).priority(3).build();
            Topic modernHistoryTopic = Topic.builder().topicName("Modern History")
                    .lastRevised(new Timestamp(System.currentTimeMillis())).priority(4).build();

            historyCourse.addTopic(ancientHistoryTopic);
            historyCourse.addTopic(modernHistoryTopic);

            courseRepository.save(historyCourse);

            Course biologyCourse = Course.builder().courseName("Biology").isRevised(true)
                    .lastRevised(new Timestamp(System.currentTimeMillis())).build();

            Topic cellBiologyTopic = Topic.builder().topicName("Cell Biology")
                    .lastRevised(new Timestamp(System.currentTimeMillis())).priority(2).build();
            Topic ecologyTopic = Topic.builder().topicName("Ecology")
                    .lastRevised(new Timestamp(System.currentTimeMillis())).priority(5).build();
            Topic zoologyTopic = Topic.builder().topicName("Zoology")
                    .lastRevised(new Timestamp(System.currentTimeMillis())).priority(4).build();

            biologyCourse.addTopic(cellBiologyTopic);
            biologyCourse.addTopic(ecologyTopic);
            biologyCourse.addTopic(zoologyTopic);

            courseRepository.save(biologyCourse);
        }
    }
}
