package com.example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
  @Value("${routes.user}") private String userUrl;
  @Value("${routes.course}") private String courseUrl;
  @Value("${routes.classroom}") private String classroomUrl;
  @Value("${routes.assessment}") private String assessmentUrl;
  @Value("${routes.notification}") private String notificationUrl;

  @Bean
  public RouteLocator routes(RouteLocatorBuilder rlb) {
    return rlb.routes()
      .route("user", r -> r.path("/auth/**","/users/**").uri(userUrl))
      .route("course", r -> r.path("/courses/**","/enrollments/**").uri(courseUrl))
      .route("classroom", r -> r.path("/classrooms/**","/attendance/**","/ws/**").uri(classroomUrl))
      .route("assessment", r -> r.path("/assessments/**").uri(assessmentUrl))
      .route("notification", r -> r.path("/notifications/**").uri(notificationUrl))
      .build();
  }
}
