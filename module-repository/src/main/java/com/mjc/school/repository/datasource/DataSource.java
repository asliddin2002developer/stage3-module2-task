package com.mjc.school.repository.datasource;

import com.mjc.school.repository.model.impl.AuthorModel;
import com.mjc.school.repository.model.impl.NewsModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@Component("dataSource")
public class DataSource {
    private final List<NewsModel> newsDataSource;
    private final List<AuthorModel> authorsDataSource;

    public DataSource(){
        authorsDataSource = new ArrayList<>();
        newsDataSource = new ArrayList<>();
        fillAuthorsDataSource();
        fillNewsDataSource();
    }


    public void fillNewsDataSource(){
        Random r = new Random();
        for (int i = 1; i <= 20; i++) {
            this.newsDataSource.add(new NewsModel(
                    Utils.getRandomContentByFilePath("news"),
                    Utils.getRandomContentByFilePath("content"),
                    (long) r.nextInt(authorsDataSource.size()),
                    Utils.getRandomDate(),
                    Utils.getRandomDate()

            ));
        }
    }

    public void fillAuthorsDataSource(){
        for(int i=1; i<=20; i++){
            this.authorsDataSource.add(new AuthorModel(
                    Utils.getRandomContentByFilePath("authors")
            ));
        }
    }

    public List<NewsModel> getNewsDataSource(){
        return newsDataSource;
    }
    public List<AuthorModel> getAuthorsDataSource(){
        return this.authorsDataSource;
    }

    public void addNewsToDataSource(NewsModel news){
        newsDataSource.add(news);
    }
    public void addAuthorToDataSource(AuthorModel author){
        authorsDataSource.add(author);
    }



}
