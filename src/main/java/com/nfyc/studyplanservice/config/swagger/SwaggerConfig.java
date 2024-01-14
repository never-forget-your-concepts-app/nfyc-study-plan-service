package com.nfyc.studyplanservice.config.swagger;

import com.fasterxml.jackson.databind.JsonNode;
import com.nfyc.studyplanservice.model.dto.CourseDTO;
import com.nfyc.studyplanservice.model.dto.StudyPlanDTO;
import com.nfyc.studyplanservice.model.dto.StudyPlanListDTO;
import com.nfyc.studyplanservice.model.dto.TopicDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.awt.print.Pageable;
import java.security.Timestamp;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig implements WebMvcConfigurer {

  @Bean
  public Docket api() {
    List<Class<?>> ignoredClasses = Arrays.asList(
      CourseDTO.class,
      JsonNode.class,
      Pageable.class,
      Page.class, // Assuming Page is a generic class, adjust accordingly
      Sort.class,
      StudyPlanDTO.class,
      StudyPlanListDTO.class,
      Timestamp.class,
      TopicDTO.class

    );


    return new Docket(DocumentationType.SWAGGER_2).select()
      .paths(PathSelectors.ant("/api/**"))
      .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
        .build().apiInfo(apiInfo());
  }

  @Bean
  public ApiInfo apiInfo(){
    return new ApiInfoBuilder()
      .title("Study Plan Service")
      .description("APIs for study plan service")
      .version("1.0.0")
      .build();
  }
}
