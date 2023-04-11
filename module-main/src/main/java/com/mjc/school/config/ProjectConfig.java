package com.mjc.school.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages={"com.mjc.school", "com.mjc.school.controller.aspects"})
public class ProjectConfig {
}
