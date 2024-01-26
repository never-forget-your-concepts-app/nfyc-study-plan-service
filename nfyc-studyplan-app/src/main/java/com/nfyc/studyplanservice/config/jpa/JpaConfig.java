package com.nfyc.studyplanservice.config.jpa;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EntityScan("com.nfyc.domain")
public class JpaConfig {
}
