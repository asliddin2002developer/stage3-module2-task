package com.mjc.school.main;

import com.mjc.school.config.ProjectConfig;
import com.mjc.school.controller.manager.MenuManager;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;



public class Main {
    public static void main(String[] args){
        try (var c = new AnnotationConfigApplicationContext(ProjectConfig.class)) {
           var crud = c.getBean(MenuManager.class);
           while (true) {
               try {
                   crud.manageCrud();
               }catch(Exception e){
                   e.printStackTrace();
               }
           }
        }
    }
}
