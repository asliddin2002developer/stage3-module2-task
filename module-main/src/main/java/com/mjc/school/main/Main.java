package com.mjc.school.main;

import com.mjc.school.config.ProjectConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;



public class Main {
    public static void main(String[] args){
        try (var c = new AnnotationConfigApplicationContext(ProjectConfig.class)) {
           var crud = c.getBean(CrudImpl.class);
           while (true) {
               crud.implementCrud();
           }
        }
    }
}
