package com.mjc.school.controller.aspects;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.mjc.school.controller.impl.NewsContoller;

@Component
@Aspect
public class NewsAspect {

    @Autowired
    NewsContoller newsContoller;

    @Pointcut("@annotation(com.mjc.school.controller.annotations.OnDelete)")
    public void onDelete(){}


    @After("onDelete() && args(arg)")
    public void deleteNews(Object arg){
        var newsList = newsContoller.readAll();
        for (var news: newsList){
            if (news.getAuthorId() == (long) arg){
                newsContoller.deleteById(news.getId());
                System.out.printf("News with %s authorId is deleted%n", news.getAuthorId());
            }
        }
    }


}
