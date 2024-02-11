package com.nfyc.studyplanservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication
@EnableEurekaClient
public class NfycStudyPlanServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(NfycStudyPlanServiceApplication.class, args);
    }

}
