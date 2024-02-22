package com.nfyc.studyplanservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
public class NfycStudyPlanServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(NfycStudyPlanServiceApplication.class, args);
    }

}
