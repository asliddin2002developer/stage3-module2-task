package com.mjc.school.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
@ComponentScan(basePackages={"com.mjc.school", "com.mjc.school.repository", "com.mjc.school.service.dto"})
public class ProjectConfig {
}
